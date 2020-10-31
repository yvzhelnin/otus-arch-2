package ru.yvzhelnin.otus.delivery.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CustomerDto implements Serializable {

    private static final long serialVersionUID = -5335934867582293424L;

    private final String fullName;

    private final String customerDataId;

    private final String phoneNumber;

    private final String address;

    CustomerDto(String fullName,
                String customerDataId,
                String phoneNumber,
                String address) {
        this.fullName = fullName;
        this.customerDataId = customerDataId;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
}
