package ru.yvzhelnin.otus.notification.dto;

import lombok.Getter;

@Getter
public class HealthInfoDto {

    private final String status;

    public HealthInfoDto(String status) {
        this.status = status;
    }
}
