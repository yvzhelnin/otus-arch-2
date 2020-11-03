package ru.yvzhelnin.otus.delivery.service;

import ru.yvzhelnin.otus.delivery.enums.DeliveryStatus;

import java.util.List;

public interface DeliveryService {

    DeliveryStatus changeStatus(long deliveryId, DeliveryStatus status);

    void returnDeliveries(List<Long> deliveryIds);
}
