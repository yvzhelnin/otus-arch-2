package ru.yvzhelnin.otus.order.service.impl;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.yvzhelnin.otus.order.enums.NotificationType;
import ru.yvzhelnin.otus.order.service.NotificationService;

import java.math.BigDecimal;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final RabbitTemplate rabbitTemplate;

    private final Queue notificationQueue;

    public NotificationServiceImpl(RabbitTemplate rabbitTemplate,
                                   @Qualifier("notificationQueue") Queue notificationQueue) {
        this.rabbitTemplate = rabbitTemplate;
        this.notificationQueue = notificationQueue;
    }

    @Override
    public void sendNotification(NotificationType notificationType, String clientId, BigDecimal sum) {
        switch (notificationType) {
            case SUCCESS:
                rabbitTemplate.convertAndSend(notificationQueue.getActualName(), "Order from client with id = '" + clientId + "' for " + sum + " rubles has been successfully created");
                break;
            case FAIL:
                rabbitTemplate.convertAndSend(notificationQueue.getActualName(), "Couldn't create an order from client with id = '" + clientId + "' for " + sum + " rubles");
                break;
        }
    }
}
