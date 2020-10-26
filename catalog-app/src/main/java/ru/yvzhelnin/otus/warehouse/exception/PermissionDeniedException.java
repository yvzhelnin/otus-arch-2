package ru.yvzhelnin.otus.warehouse.exception;

public class PermissionDeniedException extends Exception {

    public PermissionDeniedException(String message) {
        super(message);
    }
}
