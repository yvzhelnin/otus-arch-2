package ru.yvzhelnin.otus.notification.dto;

import lombok.Getter;

@Getter
public class VersionDto {

    private final String version;

    public VersionDto(String version) {
        this.version = version;
    }
}
