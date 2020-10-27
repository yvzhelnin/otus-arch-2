package ru.yvzhelnin.otus.cart.dto;

import lombok.Getter;
import org.springframework.data.annotation.Immutable;

@Immutable
@Getter
public class HealthInfoDto {

    private final String status;

    public HealthInfoDto(String status) {
        this.status = status;
    }
}
