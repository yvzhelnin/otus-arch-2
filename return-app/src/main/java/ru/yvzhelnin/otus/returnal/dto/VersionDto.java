package ru.yvzhelnin.otus.returnal.dto;

import lombok.Getter;

@Getter
public class VersionDto {
    
    private final String version;

    public VersionDto(String version) {
        this.version = version;
    }
}
