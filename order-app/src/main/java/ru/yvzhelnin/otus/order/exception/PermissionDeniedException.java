package ru.yvzhelnin.otus.order.exception;

public class PermissionDeniedException extends Exception {

    public PermissionDeniedException(String message) {
        super(message);
    }
}
