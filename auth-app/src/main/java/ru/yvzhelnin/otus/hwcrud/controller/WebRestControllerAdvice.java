package ru.yvzhelnin.otus.hwcrud.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.yvzhelnin.otus.hwcrud.exception.ClientNotFoundException;
import ru.yvzhelnin.otus.hwcrud.exception.ErrorMessage;

import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class WebRestControllerAdvice {

    @ExceptionHandler({ClientNotFoundException.class})
    public ErrorMessage handleMethodArgumentNotValidException(ClientNotFoundException e, HttpServletResponse response) {
        return new ErrorMessage(404, e.getMessage());
    }
}
