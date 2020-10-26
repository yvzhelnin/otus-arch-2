package ru.yvzhelnin.otus.catalog.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.yvzhelnin.otus.catalog.exception.CatalogException;
import ru.yvzhelnin.otus.catalog.exception.ErrorMessage;
import ru.yvzhelnin.otus.catalog.exception.PermissionDeniedException;

import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class WebRestControllerAdvice {

    @ExceptionHandler({CatalogException.class})
    public ErrorMessage handleClientNotFoundException(CatalogException e, HttpServletResponse response) {
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    @ExceptionHandler({PermissionDeniedException.class})
    public ErrorMessage handlePermissionDeniedException(PermissionDeniedException e, HttpServletResponse response) {
        return new ErrorMessage(HttpStatus.FORBIDDEN.value(), e.getMessage());
    }
}
