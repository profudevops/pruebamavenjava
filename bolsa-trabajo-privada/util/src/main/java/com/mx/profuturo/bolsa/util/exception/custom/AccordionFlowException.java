package com.mx.profuturo.bolsa.util.exception.custom;

/**
 * Created by Julio Arredondo on 01/05/2017.
 */
public class AccordionFlowException extends GenericStatusException {
    public AccordionFlowException(Exception e) {
        super(e);
    }

    public AccordionFlowException(String code, String message){
        super(code,message);
    }
}
