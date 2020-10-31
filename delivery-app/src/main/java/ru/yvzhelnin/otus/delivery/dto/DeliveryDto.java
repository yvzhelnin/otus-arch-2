package ru.yvzhelnin.otus.delivery.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class DeliveryDto implements Serializable {

    private static final long serialVersionUID = -7340188652358166916L;

    private final CustomerDto customerDto;

    private final BigDecimal sum;

    private final LocalDateTime deliverFrom;

    private final LocalDateTime deliverTill;

    public DeliveryDto(CustomerDto customerDto,
                       BigDecimal sum,
                       LocalDateTime deliverFrom,
                       LocalDateTime deliverTill) {
        this.customerDto = customerDto;
        this.sum = sum;
        this.deliverFrom = deliverFrom;
        this.deliverTill = deliverTill;
    }
}
