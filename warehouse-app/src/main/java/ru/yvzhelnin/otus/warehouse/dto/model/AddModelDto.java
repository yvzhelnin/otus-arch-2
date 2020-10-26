package ru.yvzhelnin.otus.warehouse.dto.model;

import lombok.Getter;
import lombok.ToString;
import ru.yvzhelnin.otus.warehouse.enums.EquipmentCategory;

import java.math.BigDecimal;

@Getter
@ToString
public class AddModelDto {

    private final long brandCode;

    private final String name;

    private final BigDecimal bookValue;

    private final EquipmentCategory equipmentCategory;

    public AddModelDto(long brandCode,
                       String name,
                       BigDecimal bookValue,
                       EquipmentCategory equipmentCategory) {
        this.brandCode = brandCode;
        this.name = name;
        this.bookValue = bookValue;
        this.equipmentCategory = equipmentCategory;
    }
}
