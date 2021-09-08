package com.mx.profuturo.bolsa.util.exception.custom;

/**
 * Created by Luis Miguel Romero on 01/05/2017.
 */
public class AuthenticationException extends GenericStatusException {

    public AuthenticationException() {
        super();
    }

    public AuthenticationException(Exception e) {
        super(e);
    }

    public AuthenticationException(String code, String message) {
        super(code, message);
    }


}
