package ru.yvzhelnin.otus.order.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class CustomerDto implements Serializable {

    private static final long serialVersionUID = -5335934867582293424L;

    private final String fullName;

    private final String customerDataId;

    private final String phoneNumber;

    private final String address;

    private final LocalDateTime deliverFrom;

    private final LocalDateTime deliverTill;

    CustomerDto(String fullName,
                String customerDataId,
                String phoneNumber,
                String address,
                LocalDateTime deliverFrom,
                LocalDateTime deliverTill) {
        this.fullName = fullName;
        this.customerDataId = customerDataId;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.deliverFrom = deliverFrom;
        this.deliverTill = deliverTill;
    }
}
