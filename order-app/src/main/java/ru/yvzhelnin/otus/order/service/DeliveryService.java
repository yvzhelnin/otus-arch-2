package ru.yvzhelnin.otus.order.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import ru.yvzhelnin.otus.order.model.Order;

public interface DeliveryService {

    void sendDeliveryRequest(Order order) throws JsonProcessingException;
}
