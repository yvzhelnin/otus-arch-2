package ru.yvzhelnin.otus.order.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.yvzhelnin.otus.order.dto.notification.NotificationType;
import ru.yvzhelnin.otus.order.enums.WithdrawResultType;
import ru.yvzhelnin.otus.order.exception.ClientNotFoundException;
import ru.yvzhelnin.otus.order.model.Client;
import ru.yvzhelnin.otus.order.model.Order;
import ru.yvzhelnin.otus.order.repository.ClientRepository;
import ru.yvzhelnin.otus.order.repository.OrderRepository;
import ru.yvzhelnin.otus.order.service.NotificationService;
import ru.yvzhelnin.otus.order.service.OrderService;

import java.math.BigDecimal;
import java.util.HashMap;
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

    private final ClientRepository clientRepository;

    private final NotificationService notificationService;

    public OrderServiceImpl(OrderRepository orderRepository,
                            ClientRepository clientRepository,
                            NotificationService notificationService) {
        this.orderRepository = orderRepository;
        this.clientRepository = clientRepository;
        this.notificationService = notificationService;
    }

    @Override
    public String placeOrder(String clientId, BigDecimal cost, int version) throws ClientNotFoundException {
        final String url = billingAppUrl + "/" + clientId + "?sum=" + cost;
        LOGGER.info("Assembled URL for money withdrawing: '{}'", url);
        RestTemplate restTemplate = new RestTemplate();
        final Order existingOrder = orderRepository.findByClientIdAndCost(clientId, cost);
        if (existingOrder != null && version <= existingOrder.getVersion()) {
            return existingOrder.getId();
        }
        final Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ClientNotFoundException("Клиент с идентификатором " + clientId + " не найден"));
        final Order newOrder = new Order(UUID.randomUUID().toString(), client, cost, version);
        HttpHeaders headers = new HttpHeaders();
        headers.set(CLIENT_ID_HEADER, clientId);
        Map<String, String> parameters = new HashMap<>();
        parameters.put("sum", cost.toString());
        HttpEntity requestEntity = new HttpEntity(headers);
        ResponseEntity response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class, parameters);

        String result = response.getHeaders().getFirst(WITHDRAW_RESULT_HEADER);
        LOGGER.info("Result: {}, sending the notification", result);

        if (Objects.equals(result, WithdrawResultType.OK.name())) {
            final String orderId = orderRepository.save(newOrder).getId();
            notificationService.sendNotification(NotificationType.SUCCESS, clientId, cost);

            return orderId;
        } else {
            notificationService.sendNotification(NotificationType.FAIL, clientId, cost);

            return "Недостаточно средств на счёте";
        }
    }
}
