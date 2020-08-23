package ru.yvzhelnin.otus.order.service;

import java.math.BigDecimal;

public interface OrderService {

    void placeOrder(String clientId, BigDecimal cost);
}
