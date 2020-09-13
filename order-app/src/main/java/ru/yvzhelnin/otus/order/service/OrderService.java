package ru.yvzhelnin.otus.order.service;

import ru.yvzhelnin.otus.order.exception.ClientNotFoundException;

import java.math.BigDecimal;

public interface OrderService {

    String placeOrder(String clientId, BigDecimal cost, int version) throws ClientNotFoundException;
}
