package com.mx.profuturo.bolsa.util.exception.custom;

/**
 * Created by lUiSm on 5/20/16.
 */
public final class ServiceException extends Exception {

    public ServiceException() {
        /*Default constructor*/
    }

    public ServiceException(String msg) {
        super(msg);
    }

    public ServiceException(String msg, Throwable t) {
        super(msg, t);
    }

    public ServiceException(Throwable t) {
        super(t);
    }

}

