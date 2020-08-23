package ru.yvzhelnin.otus.order.service;

import ru.yvzhelnin.otus.order.dto.notification.NotificationType;

import java.math.BigDecimal;

public interface NotificationService {

    void sendNotification(NotificationType notificationType, String clientId, BigDecimal sum);
}
