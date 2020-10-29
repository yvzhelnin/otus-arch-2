package ru.yvzhelnin.otus.order.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import ru.yvzhelnin.otus.order.dto.PlaceOrderRequestDto;

public interface OrderService {

    String placeOrder(PlaceOrderRequestDto orderRequestDto, String clientId) throws JsonProcessingException;
}
