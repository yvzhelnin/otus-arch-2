package ru.yvzhelnin.otus.billing.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.yvzhelnin.otus.billing.exception.AccountNotFoundException;
import ru.yvzhelnin.otus.billing.exception.ClientNotFoundException;
import ru.yvzhelnin.otus.billing.exception.ErrorMessage;
import ru.yvzhelnin.otus.billing.exception.NotEnoughMoneyException;
import ru.yvzhelnin.otus.billing.exception.PermissionDeniedException;

import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class WebRestControllerAdvice {

    @ExceptionHandler({ClientNotFoundException.class})
    public ErrorMessage handleClientNotFoundException(ClientNotFoundException e, HttpServletResponse response) {
        return new ErrorMessage(HttpStatus.NOT_FOUND.value(), e.getMessage());
    }

    @ExceptionHandler({AccountNotFoundException.class})
    public ErrorMessage handleAccountNotFoundException(AccountNotFoundException e, HttpServletResponse response) {
        return new ErrorMessage(HttpStatus.NOT_FOUND.value(), e.getMessage());
    }

    @ExceptionHandler({PermissionDeniedException.class})
    public ErrorMessage handlePermissionDeniedException(PermissionDeniedException e, HttpServletResponse response) {
        return new ErrorMessage(HttpStatus.FORBIDDEN.value(), e.getMessage());
    }

    @ExceptionHandler({NotEnoughMoneyException.class})
    public ErrorMessage handleNotEnoughMoneyException(NotEnoughMoneyException e, HttpServletResponse response) {
        response.setStatus(HttpStatus.PAYMENT_REQUIRED.value());
        return new ErrorMessage(HttpStatus.PAYMENT_REQUIRED.value(), e.getMessage());
    }
}
