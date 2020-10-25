package ru.yvzhelnin.otus.warehouse.dto;

import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@ToString
public class EquipmentDto {

    @NotNull
    private final String modelArticle;

    @NotNull
    private final int amount;

    public EquipmentDto(String modelArticle, Integer amount) {
        this.modelArticle = modelArticle;
        this.amount = amount;
    }
}
