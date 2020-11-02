package ru.yvzhelnin.otus.delivery.service.rabbit.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import ru.yvzhelnin.otus.delivery.dto.DeliveryDto;
import ru.yvzhelnin.otus.delivery.enums.DeliveryStatus;
import ru.yvzhelnin.otus.delivery.enums.DeliveryType;
import ru.yvzhelnin.otus.delivery.model.Courier;
import ru.yvzhelnin.otus.delivery.model.CustomerData;
import ru.yvzhelnin.otus.delivery.model.DeliveryInfo;
import ru.yvzhelnin.otus.delivery.repository.CustomerDataRepository;
import ru.yvzhelnin.otus.delivery.repository.DeliveryInfoRepository;
import ru.yvzhelnin.otus.delivery.service.CourierService;
import ru.yvzhelnin.otus.delivery.service.rabbit.DeliveryListenerService;

import java.io.IOException;

@Service
public class DeliveryListenerServiceImpl implements DeliveryListenerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DeliveryListenerServiceImpl.class);

    private final CustomerDataRepository customerDataRepository;

    private final DeliveryInfoRepository deliveryInfoRepository;

    private final CourierService courierService;

    private final ObjectMapper objectMapper;

    public DeliveryListenerServiceImpl(CustomerDataRepository customerDataRepository,
                                       DeliveryInfoRepository deliveryInfoRepository,
                                       CourierService courierService,
                                       ObjectMapper objectMapper) {
        this.customerDataRepository = customerDataRepository;
        this.deliveryInfoRepository = deliveryInfoRepository;
        this.courierService = courierService;
        this.objectMapper = objectMapper;
    }

    @Override
    @RabbitListener(queues = "${order.delivery.queue}")
    public void receive(String message) throws IOException {
        LOGGER.info("Received new delivery message: {}", message);
        DeliveryDto deliveryDto = objectMapper.readValue(message, DeliveryDto.class);
        CustomerData customerData = new CustomerData(deliveryDto.getCustomerDto());
        customerData = customerDataRepository.save(customerData);
        DeliveryInfo deliveryInfo = new DeliveryInfo();
        deliveryInfo.setCustomerData(customerData);
        deliveryInfo.setDeliveryType(DeliveryType.ISSUE);
        deliveryInfo.setDeliveryStatus(DeliveryStatus.SCHEDULED);
        deliveryInfo.setCost(deliveryDto.getSum());
        deliveryInfo.setDeliverFrom(deliveryDto.getDeliverFrom());
        deliveryInfo.setDeliverTill(deliveryDto.getDeliverTill());
        deliveryInfo.setReturnDate(deliveryDto.getReturnDate());

        final Courier courier = courierService.findFreeCourier(deliveryInfo.getDeliverFrom(), deliveryInfo.getDeliverTill());
        deliveryInfo.setCourier(courier);
        deliveryInfoRepository.save(deliveryInfo);
    }
}
