package ru.yvzhelnin.otus.order.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.yvzhelnin.otus.order.exception.ErrorMessage;
import ru.yvzhelnin.otus.order.exception.PermissionDeniedException;

import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class WebRestControllerAdvice {

    @ExceptionHandler({PermissionDeniedException.class})
    public ErrorMessage handlePermissionDeniedException(PermissionDeniedException e, HttpServletResponse response) {
        return new ErrorMessage(HttpStatus.FORBIDDEN.value(), e.getMessage());
    }
}
