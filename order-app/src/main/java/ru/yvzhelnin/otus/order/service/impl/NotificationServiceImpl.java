package ru.yvzhelnin.otus.order.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.yvzhelnin.otus.order.dto.notification.NotificationType;
import ru.yvzhelnin.otus.order.service.NotificationService;

import java.math.BigDecimal;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Value("${kafka.notification.topic.name}")
    private String notificationTopicName;

    private final KafkaTemplate<String, String> kafkaTemplate;

    public NotificationServiceImpl(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void sendNotification(NotificationType notificationType, String clientId, BigDecimal sum) {
        switch (notificationType) {
            case SUCCESS:
                kafkaTemplate.send(notificationTopicName, "Order from client with id = '" + clientId + "' for " + sum + " rubles has been successfully created");
                break;
            case FAIL:
                kafkaTemplate.send(notificationTopicName, "Couldn't create an order from client with id = '" + clientId + "' for " + sum + " rubles");
                break;
        }
    }
}
