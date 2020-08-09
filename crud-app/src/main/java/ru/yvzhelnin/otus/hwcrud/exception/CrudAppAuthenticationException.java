package ru.yvzhelnin.otus.hwcrud.exception;

import org.springframework.security.core.AuthenticationException;

public class CrudAppAuthenticationException extends AuthenticationException {

    public CrudAppAuthenticationException(String message) {
        super(message);
    }
}
