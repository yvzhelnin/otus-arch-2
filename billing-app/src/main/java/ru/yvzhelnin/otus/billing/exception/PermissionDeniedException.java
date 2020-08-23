package ru.yvzhelnin.otus.billing.exception;

public class PermissionDeniedException extends Exception {

    public PermissionDeniedException(String message) {
        super(message);
    }
}
