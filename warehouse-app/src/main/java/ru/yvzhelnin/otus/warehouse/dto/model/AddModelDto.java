package ru.yvzhelnin.otus.warehouse.dto.model;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class AddModelDto {

    private final Long brandCode;

    private final String name;

    public AddModelDto(Long brandCode, String name) {
        this.brandCode = brandCode;
        this.name = name;
    }
}
