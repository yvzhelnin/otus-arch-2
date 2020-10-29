package ru.yvzhelnin.otus.order.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.yvzhelnin.otus.order.dto.DeliveryDto;
import ru.yvzhelnin.otus.order.model.Order;
import ru.yvzhelnin.otus.order.service.DeliveryService;

@Service
public class DeliveryServiceImpl implements DeliveryService {

    private final RabbitTemplate rabbitTemplate;

    private final Queue deliveryQueue;

    private final ObjectMapper objectMapper;

    public DeliveryServiceImpl(RabbitTemplate rabbitTemplate,
                               @Qualifier("deliveryQueue") Queue deliveryQueue,
                               ObjectMapper objectMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.deliveryQueue = deliveryQueue;
        this.objectMapper = objectMapper;
    }

    @Override
    public void sendDeliveryRequest(Order order) throws JsonProcessingException {
        final DeliveryDto deliveryDto = new DeliveryDto(order);
        final String message = objectMapper.writeValueAsString(deliveryDto);
        rabbitTemplate.convertAndSend(deliveryQueue.getActualName(), message);
    }
}
