package com.mx.profuturo.bolsa.model.exception;

/**
 * Created by antonio on 22/11/2016.
 */
public class BadResponseException extends Exception{

    private final int code;

    public BadResponseException(String message, int code) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
