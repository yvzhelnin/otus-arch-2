package ru.yvzhelnin.otus.notification.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class NotificationDto {

    private final String timestamp;

    private final String message;

    public NotificationDto(String timestamp, String message) {
        this.timestamp = timestamp;
        this.message = message;
    }
}
