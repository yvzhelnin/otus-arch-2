package ru.yvzhelnin.otus.order.exception;

import lombok.Getter;

@Getter
public class ErrorMessage {

    private final int code;

    private final String message;

    public ErrorMessage(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
