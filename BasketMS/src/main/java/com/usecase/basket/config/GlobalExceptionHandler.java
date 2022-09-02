package com.usecase.basket.config;

import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public String handleError(HttpServletRequest req, HttpMediaTypeNotSupportedException ex)
    {
        System.out.println("Request: " + req.getRequestURL() + " raised " + ex);

        return "Bad Request please check the request";
    }
}
