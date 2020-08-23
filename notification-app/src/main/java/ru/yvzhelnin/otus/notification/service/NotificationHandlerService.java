package ru.yvzhelnin.otus.notification.service;

import java.time.LocalDateTime;
import java.util.Map;

public interface NotificationHandlerService {

    void consumeNotification(String message);

    Map<LocalDateTime, String> getNotifications();
}
