package ru.yvzhelnin.otus.order.dto;

import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Getter
public class PlaceOrderRequestDto {

    private final String clientId;

    private final String firstName;

    private final String lastName;

    private final String phoneNumber;

    private final String address;

    private final LocalDateTime deliverFrom;

    private final LocalDateTime deliverTill;

    private final Map<String, Long> positions;

    private final BigDecimal cost;

    private final int version;

    public PlaceOrderRequestDto(String clientId,
                                String firstName,
                                String lastName,
                                String phoneNumber,
                                String address,
                                LocalDateTime deliverFrom,
                                LocalDateTime deliverTill,
                                Map<String, Long> positions,
                                BigDecimal cost,
                                int version) {
        this.clientId = clientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.deliverFrom = deliverFrom;
        this.deliverTill = deliverTill;
        this.positions = positions;
        this.cost = cost;
        this.version = version;
    }
}
