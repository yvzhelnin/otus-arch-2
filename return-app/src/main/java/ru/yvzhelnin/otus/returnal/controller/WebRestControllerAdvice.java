package ru.yvzhelnin.otus.returnal.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.yvzhelnin.otus.returnal.exception.ErrorMessage;
import ru.yvzhelnin.otus.returnal.exception.ReturnServiceException;
import ru.yvzhelnin.otus.returnal.exception.PermissionDeniedException;

import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class WebRestControllerAdvice {

    @ExceptionHandler({ReturnServiceException.class})
    public ErrorMessage handlePermissionDeniedException(ReturnServiceException e, HttpServletResponse response) {
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    @ExceptionHandler({PermissionDeniedException.class})
    public ErrorMessage handlePermissionDeniedException(PermissionDeniedException e, HttpServletResponse response) {
        return new ErrorMessage(HttpStatus.FORBIDDEN.value(), e.getMessage());
    }
}
