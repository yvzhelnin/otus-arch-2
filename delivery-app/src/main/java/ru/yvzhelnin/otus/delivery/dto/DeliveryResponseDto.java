package ru.yvzhelnin.otus.delivery.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class DeliveryResponseDto implements Serializable {

    private static final long serialVersionUID = -7340188652358166916L;

    private final String fullName;

    private final String phoneNumber;

    private final String address;

    private final BigDecimal sum;

    private final LocalDateTime deliverFrom;

    private final LocalDateTime deliverTill;

    public DeliveryResponseDto(String fullName,
                               String phoneNumber,
                               String address,
                               BigDecimal sum,
                               LocalDateTime deliverFrom,
                               LocalDateTime deliverTill) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.sum = sum;
        this.deliverFrom = deliverFrom;
        this.deliverTill = deliverTill;
    }
}
