package ru.yvzhelnin.otus.warehouse.dto.model;

import lombok.Getter;
import lombok.ToString;
import ru.yvzhelnin.otus.warehouse.enums.EquipmentCategory;

@Getter
@ToString
public class AddModelDto {

    private final long brandCode;

    private final String name;

    private final EquipmentCategory equipmentCategory;

    public AddModelDto(long brandCode, String name, EquipmentCategory equipmentCategory) {
        this.brandCode = brandCode;
        this.name = name;
        this.equipmentCategory = equipmentCategory;
    }
}
