package ru.yvzhelnin.otus.delivery.service.rabbit;

import ru.yvzhelnin.otus.delivery.model.DeliveryInfo;

public interface NotificationService {

    void sendNotification(DeliveryInfo deliveryInfo);
}
