/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.sobczak.sptest.controller;

import lombok.extern.apachecommons.CommonsLog;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import pl.sobczak.sptest.exceptions.RestExceptions;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 *
 * @author Lukasz Sobczak
 */
@CommonsLog
//@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler({RestExceptions.AkeitaException.class})
    public ResponseEntity<String> handleNotFoundException(RestExceptions.AkeitaException e) {
        return createErrorResponse(HttpStatus.I_AM_A_TEAPOT, e);
    }

    private ResponseEntity<String> createErrorResponse(HttpStatus status, Exception e) {
        log.error("Exception : ", e);
        return ResponseEntity.status(status).body(e.getMessage());
    }
}
