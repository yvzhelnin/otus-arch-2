package ru.yvzhelnin.otus.returnal.dto;

import lombok.Getter;

@Getter
public class HealthInfoDto {

    private final String status;

    public HealthInfoDto(String status) {
        this.status = status;
    }
}
