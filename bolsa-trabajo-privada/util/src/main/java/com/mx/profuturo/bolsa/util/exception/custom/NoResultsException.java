package com.mx.profuturo.bolsa.util.exception.custom;

public class NoResultsException extends GenericStatusException {

    public NoResultsException() {
        super();
    }

    public NoResultsException(Exception e) {
        super(e);
    }

    public NoResultsException(String code, String message) {
        super(code, message);
    }


}
