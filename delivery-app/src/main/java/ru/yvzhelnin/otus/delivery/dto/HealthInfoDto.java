package ru.yvzhelnin.otus.delivery.dto;

import lombok.Getter;

@Getter
public class HealthInfoDto {

    private final String status;

    public HealthInfoDto(String status) {
        this.status = status;
    }
}
