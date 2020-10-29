package ru.yvzhelnin.otus.order.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import ru.yvzhelnin.otus.order.dto.PlaceOrderRequestDto;
import ru.yvzhelnin.otus.order.exception.ClientNotFoundException;

import java.math.BigDecimal;

public interface OrderService {

    String placeOrder(PlaceOrderRequestDto orderRequestDto, String clientId) throws ClientNotFoundException, JsonProcessingException;
}
