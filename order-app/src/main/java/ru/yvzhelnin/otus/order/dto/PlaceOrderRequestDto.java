package ru.yvzhelnin.otus.order.dto;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class PlaceOrderRequestDto {

    private final String clientId;

    private final BigDecimal cost;

    public PlaceOrderRequestDto(String clientId, BigDecimal cost) {
        this.clientId = clientId;
        this.cost = cost;
    }
}
