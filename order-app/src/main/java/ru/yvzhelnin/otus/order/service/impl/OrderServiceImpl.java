package ru.yvzhelnin.otus.order.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.yvzhelnin.otus.order.dto.PlaceOrderRequestDto;
import ru.yvzhelnin.otus.order.enums.NotificationType;
import ru.yvzhelnin.otus.order.enums.OrderStatus;
import ru.yvzhelnin.otus.order.enums.WithdrawResultType;
import ru.yvzhelnin.otus.order.exception.OrderServiceException;
import ru.yvzhelnin.otus.order.model.CustomerData;
import ru.yvzhelnin.otus.order.model.Order;
import ru.yvzhelnin.otus.order.repository.CustomerDataRepository;
import ru.yvzhelnin.otus.order.repository.OrderRepository;
import ru.yvzhelnin.otus.order.service.DeliveryService;
import ru.yvzhelnin.otus.order.service.NotificationService;
import ru.yvzhelnin.otus.order.service.OrderService;
import ru.yvzhelnin.otus.order.service.WarehouseService;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

    public static final String CLIENT_ID_HEADER = "X-Client-Id";

    private static final String WITHDRAW_RESULT_HEADER = "X-Withdraw-Result";

    @Value("${order.billing-app.url.withdraw}")
    private String billingAppUrl;

    private final OrderRepository orderRepository;

    private final CustomerDataRepository customerDataRepository;

    private final NotificationService notificationService;

    private final DeliveryService deliveryService;

    private final WarehouseService warehouseService;

    private final RestTemplate restTemplate;

    public OrderServiceImpl(OrderRepository orderRepository,
                            CustomerDataRepository customerDataRepository,
                            NotificationService notificationService,
                            DeliveryService deliveryService, WarehouseService warehouseService) {
        this.orderRepository = orderRepository;
        this.customerDataRepository = customerDataRepository;
        this.notificationService = notificationService;
        this.deliveryService = deliveryService;
        this.warehouseService = warehouseService;
        this.restTemplate = new RestTemplate();
    }

    @Override
    public String placeOrder(PlaceOrderRequestDto orderRequestDto, String clientId) throws JsonProcessingException {
        warehouseService.bookEquipment(orderRequestDto.getPositions(), orderRequestDto.getPhoneNumber(), clientId);

        final Order existingOrder = orderRepository.findByClientIdAndCost(clientId, orderRequestDto.getCost());
        if (existingOrder != null) {
            if (orderRequestDto.getVersion() <= existingOrder.getVersion()) {
                return existingOrder.getId();
            }
            orderRepository.delete(existingOrder);
        }
        CustomerData customerData = new CustomerData();
        customerData.setId(UUID.randomUUID().toString());
        customerData.setFirstName(orderRequestDto.getFirstName());
        customerData.setLastName(orderRequestDto.getLastName());
        customerData.setEmail(orderRequestDto.getEmail());
        customerData.setAddress(orderRequestDto.getAddress());
        customerData.setPhone(orderRequestDto.getPhoneNumber());
        customerData.setDeliverFrom(orderRequestDto.getDeliverFrom());
        customerData.setDeliverTill(orderRequestDto.getDeliverTill());
        customerData = customerDataRepository.save(customerData);

        final Order newOrder = new Order(UUID.randomUUID().toString(),
                clientId,
                customerData,
                OrderStatus.NEW,
                orderRequestDto.getCost(),
                orderRequestDto.getVersion());

        String withdrawalResult = payForOrder(clientId, orderRequestDto);
        LOGGER.info("Withdrawal attempt result: {}, sending the notification", withdrawalResult);

        if (Objects.equals(withdrawalResult, WithdrawResultType.OK.name())) {
            final String orderId = orderRepository.save(newOrder).getId();
            notificationService.sendNotification(NotificationType.SUCCESS, clientId, orderRequestDto.getCost());
            deliveryService.sendDeliveryRequest(newOrder);

            return orderId;
        } else {
            warehouseService.unBookEquipment(orderRequestDto.getPhoneNumber());
            notificationService.sendNotification(NotificationType.FAIL, clientId, orderRequestDto.getCost());

            return "Недостаточно средств на счёте";
        }
    }

    @Override
    public OrderStatus changeStatus(OrderStatus newStatus, String clientId) throws OrderServiceException {
        List<Order> clientOrders = orderRepository.findAllByClientId(clientId);
        Order lastOrder = clientOrders.stream()
                .max(Comparator.comparingInt(Order::getVersion))
                .orElseThrow(() -> new OrderServiceException("Нет заказов для клиента " + clientId));
        lastOrder.setStatus(newStatus);
        orderRepository.save(lastOrder);

        return lastOrder.getStatus();
    }

    private String payForOrder(String clientId, PlaceOrderRequestDto orderRequestDto) {
        final String url = billingAppUrl + "/" + clientId + "?sum=" + orderRequestDto.getCost();
        LOGGER.info("Assembled URL for money withdrawing: '{}'", url);
        HttpHeaders headers = new HttpHeaders();
        headers.set(CLIENT_ID_HEADER, clientId);
        Map<String, String> parameters = new HashMap<>();
        parameters.put("sum", orderRequestDto.getCost().toString());
        HttpEntity requestEntity = new HttpEntity(headers);
        ResponseEntity response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class, parameters);

        return response.getHeaders().getFirst(WITHDRAW_RESULT_HEADER);
    }
}
