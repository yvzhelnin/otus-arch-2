package ru.yvzhelnin.otus.catalog.exception;

public class PermissionDeniedException extends Exception {

    public PermissionDeniedException(String message) {
        super(message);
    }
}
