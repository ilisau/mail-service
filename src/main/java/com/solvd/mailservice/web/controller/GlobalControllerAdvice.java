package com.solvd.mailservice.web.controller;

import com.solvd.mailservice.domain.exception.ExceptionMessage;
import org.springframework.http.HttpStatus;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerAdvice {

    /**
     * Handles mail exception.
     *
     * @param e exception
     * @return exception message
     */
    @ExceptionHandler(MailException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionMessage handleMailException(final MailException e) {
        return new ExceptionMessage(e.getMessage());
    }

    /**
     * Handles exception.
     *
     * @param e exception
     * @return exception message
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionMessage handleException(final Exception e) {
        return new ExceptionMessage("Internal error");
    }

}
