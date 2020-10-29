package ru.yvzhelnin.otus.order.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.yvzhelnin.otus.order.model.CustomerData;
import ru.yvzhelnin.otus.order.model.Order;
import ru.yvzhelnin.otus.order.model.OrderEquipment;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public class DeliveryDto implements Serializable {

    private static final long serialVersionUID = -7340188652358166916L;

    private final CustomerDto customerDto;

    private final Set<Long> inventoryNumbers;

    private final BigDecimal sum;

    public DeliveryDto(Order order) {
        CustomerData customerData = order.getCustomerData();
        final String fullName = customerData.getFirstName() + " " + customerData.getLastName();
        this.customerDto = new CustomerDto(fullName,
                customerData.getId(),
                customerData.getPhone(),
                customerData.getAddress(),
                customerData.getDeliverFrom(),
                customerData.getDeliverTill());

       this.inventoryNumbers = order.getEquipment().stream().map(OrderEquipment::getInventoryNumber).collect(Collectors.toSet());
       this.sum = order.getCost();
    }
}
