/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.sobczak.sptest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Lukasz Sobczak Class With all Exceptions that propagate to user - to
 * have all exceptions in one file (no sophisticated logic here)
 */
public class SwapiRestExceptions extends Exception {

    private SwapiRestExceptions() {
        //Use static inner classes only
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public static class HttpResourceNotFound extends RuntimeException {

        public HttpResourceNotFound(String text) {
            super(text);
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public static class HttpQueryNoHits extends RuntimeException {

        public HttpQueryNoHits(String text) {
            super(text);
        }
    }

    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    public static class AkeitaException extends RuntimeException {

        public AkeitaException(String message) {
            super(message);
        }
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static class RecordNotFound extends RuntimeException {

        public RecordNotFound(String text) {
            super(text);
        }
    }

}
