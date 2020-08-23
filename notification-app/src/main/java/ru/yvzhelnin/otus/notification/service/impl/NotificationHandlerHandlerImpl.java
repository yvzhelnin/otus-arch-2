package ru.yvzhelnin.otus.notification.service.impl;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.yvzhelnin.otus.notification.service.NotificationHandlerService;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class NotificationHandlerHandlerImpl implements NotificationHandlerService {

    private static final Map<LocalDateTime, String> NOTIFICATIONS = new LinkedHashMap<>();

    @KafkaListener(topics = {"${kafka.notification.topic.name}"})
    @Override
    public void consumeNotification(String message) {
        NOTIFICATIONS.put(LocalDateTime.now(), message);
    }

    @Override
    public Map<LocalDateTime, String> getNotifications() {
        return NOTIFICATIONS;
    }
}
