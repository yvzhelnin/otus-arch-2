package ru.yvzhelnin.otus.hwcrud.exception;

public class PermissionDeniedException extends Exception {

    public PermissionDeniedException(String message) {
        super(message);
    }
}
