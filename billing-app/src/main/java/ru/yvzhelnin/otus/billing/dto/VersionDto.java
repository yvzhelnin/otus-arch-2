package ru.yvzhelnin.otus.billing.dto;

import lombok.Getter;
import org.springframework.data.annotation.Immutable;

@Immutable
@Getter
public class VersionDto {
    
    private final String version;

    public VersionDto(String version) {
        this.version = version;
    }
}
