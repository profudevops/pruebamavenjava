package com.mx.profuturo.bolsa.model.exception;

import java.io.FileNotFoundException;

/**
 * Created by antonio on 15/11/2016.
 */
public class FileEmptyException extends FileNotFoundException {

    public FileEmptyException(String s) {
        super(s);
    }

}
