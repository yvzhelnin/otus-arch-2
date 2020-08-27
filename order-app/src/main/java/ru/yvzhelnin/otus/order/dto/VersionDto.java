package ru.yvzhelnin.otus.order.dto;

import lombok.Getter;

@Getter
public class VersionDto {
    
    private final String version;

    public VersionDto(String version) {
        this.version = version;
    }
}
