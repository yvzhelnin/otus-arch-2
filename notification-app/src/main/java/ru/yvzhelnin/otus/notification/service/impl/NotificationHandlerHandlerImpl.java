package ru.yvzhelnin.otus.notification.service.impl;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.yvzhelnin.otus.notification.dto.NotificationDto;
import ru.yvzhelnin.otus.notification.service.NotificationHandlerService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationHandlerHandlerImpl implements NotificationHandlerService {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private static final List<NotificationDto> NOTIFICATIONS = new ArrayList<>();

    @KafkaListener(topics = {"${kafka.notification.topic.name}"})
    @Override
    public void consumeNotification(String message) {
        NOTIFICATIONS.add(new NotificationDto(LocalDateTime.now().format(FORMATTER), message));
    }

    @Override
    public List<NotificationDto> getNotifications() {
        return NOTIFICATIONS;
    }
}
