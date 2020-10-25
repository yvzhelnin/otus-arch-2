package ru.yvzhelnin.otus.warehouse.dto.model;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class GetModelDto {

    private final String article;

    private final Long brandCode;

    private final String brandName;

    private final String name;

    public GetModelDto(String article, Long brandCode, String brandName, String name) {
        this.article = article;
        this.brandCode = brandCode;
        this.brandName = brandName;
        this.name = name;
    }
}
