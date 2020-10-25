package ru.yvzhelnin.otus.warehouse.dto.brand;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class AddBrandDto {

    private final String name;

    public AddBrandDto(String name) {
        this.name = name;
    }
}
