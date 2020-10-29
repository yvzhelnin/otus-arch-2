package ru.yvzhelnin.otus.order.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import ru.yvzhelnin.otus.order.dto.PlaceOrderRequestDto;
import ru.yvzhelnin.otus.order.enums.OrderStatus;
import ru.yvzhelnin.otus.order.exception.OrderServiceException;

public interface OrderService {

    String placeOrder(PlaceOrderRequestDto orderRequestDto, String clientId) throws JsonProcessingException;

    OrderStatus changeStatus(OrderStatus newStatus, String clientId) throws OrderServiceException;
}
