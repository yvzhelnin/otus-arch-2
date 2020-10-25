package ru.yvzhelnin.otus.warehouse.dto.brand;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class GetBrandDto {

    private final Long code;

    private final String name;

    public GetBrandDto(Long code, String name) {
        this.code = code;
        this.name = name;
    }
}
