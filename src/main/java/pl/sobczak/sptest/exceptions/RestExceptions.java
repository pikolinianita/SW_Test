/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.sobczak.sptest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *@author Lukasz Sobczak 
 * Class With all Exceptions used by project - to have all
 * exceptions in one file (no sophisticated logic here)
 */
public class RestExceptions extends Exception {

    private RestExceptions() {
        //Use static inner classes only
    }

    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public static class HttpClientNoConnectionException extends RuntimeException {

        public HttpClientNoConnectionException(String text) {
            super(text);

        }

        public HttpClientNoConnectionException(String text, Throwable cause) {
            super(text, cause);
        }
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

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    static class HttpNoCorrectCombination extends RuntimeException {

        public HttpNoCorrectCombination(String text) {
            super(text);
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    static class NoSuchRecord extends RuntimeException {

        public NoSuchRecord(String text) {
            super(text);
        }
    }

    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    public static class AkeitaException extends RuntimeException {

        public AkeitaException(String message) {
            super(message);
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public static class BadSwapiRequest extends RuntimeException {

        public BadSwapiRequest(String text) {
            super(text);
        }
    }

}
