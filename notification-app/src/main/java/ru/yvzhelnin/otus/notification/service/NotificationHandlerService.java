package ru.yvzhelnin.otus.notification.service;

import ru.yvzhelnin.otus.notification.dto.NotificationDto;

import java.util.List;

public interface NotificationHandlerService {

    void consumeNotification(String message);

    List<NotificationDto> getNotifications();
}
