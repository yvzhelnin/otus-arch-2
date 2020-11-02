package ru.yvzhelnin.otus.delivery.service;

import ru.yvzhelnin.otus.delivery.enums.DeliveryStatus;

public interface DeliveryService {

    DeliveryStatus changeStatus(long deliveryId, DeliveryStatus status);
}
