package ru.yvzhelnin.otus.order.dto;

import lombok.Getter;
import lombok.Setter;
import ru.yvzhelnin.otus.order.model.CustomerData;
import ru.yvzhelnin.otus.order.model.Order;

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

    public DeliveryDto(Order order) {
        CustomerData customerData = order.getCustomerData();
        final String fullName = customerData.getFirstName() + " " + customerData.getLastName();
        this.customerDto = new CustomerDto(fullName,
                customerData.getId(),
                customerData.getPhone(),
                customerData.getAddress());
        this.sum = order.getCost();
        this.deliverFrom = customerData.getDeliverFrom();
        this.deliverTill = customerData.getDeliverTill();
    }
}
