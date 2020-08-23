package ru.yvzhelnin.otus.billing.exception;

public class AccountNotFoundException extends Exception {

    public AccountNotFoundException(String message) {
        super(message);
    }
}
