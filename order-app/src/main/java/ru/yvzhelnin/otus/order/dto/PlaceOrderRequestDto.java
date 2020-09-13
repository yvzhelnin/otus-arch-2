package ru.yvzhelnin.otus.order.dto;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class PlaceOrderRequestDto {

    private final String clientId;

    private final BigDecimal cost;

    private final int version;

    public PlaceOrderRequestDto(String clientId, BigDecimal cost, int version) {
        this.clientId = clientId;
        this.cost = cost;
        this.version = version;
    }
}
