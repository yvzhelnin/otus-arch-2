package ru.yvzhelnin.otus.catalog.exception;

import lombok.Getter;
import org.springframework.data.annotation.Immutable;

@Immutable
@Getter
public class ErrorMessage {

    private final int code;

    private final String message;

    public ErrorMessage(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
