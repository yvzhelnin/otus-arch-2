package ru.yvzhelnin.otus.warehouse.dto.model;

import lombok.Getter;
import lombok.ToString;
import ru.yvzhelnin.otus.warehouse.enums.SeasonType;

@Getter
@ToString
public class AddModelDto {

    private final long brandCode;

    private final String name;

    private final SeasonType seasonType;

    public AddModelDto(long brandCode, String name, SeasonType seasonType) {
        this.brandCode = brandCode;
        this.name = name;
        this.seasonType = seasonType;
    }
}
