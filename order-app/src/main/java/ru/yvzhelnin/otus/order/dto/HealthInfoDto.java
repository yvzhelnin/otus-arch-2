package ru.yvzhelnin.otus.order.dto;

import lombok.Getter;

@Getter
public class HealthInfoDto {

    private final String status;

    public HealthInfoDto(String status) {
        this.status = status;
    }
}
