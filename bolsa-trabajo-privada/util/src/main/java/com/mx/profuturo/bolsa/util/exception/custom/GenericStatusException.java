package com.mx.profuturo.bolsa.util.exception.custom;

import com.mx.profuturo.bolsa.util.messages.UtilI18n;

/**
 * Create Custom Generic Status Exception
 */
public class GenericStatusException extends Exception {

    private  String code;
    private  String message;
    private  Boolean stayHere;
    private  Boolean renderAsJson;
    protected  UtilI18n messageUtil;

    public GenericStatusException(){

    }

    public GenericStatusException(String code, String message, Boolean stayHere, Boolean renderAsJson, UtilI18n messageUtil) {
        this.code = code;
        this.message = message;
        this.stayHere = stayHere;
        this.renderAsJson = renderAsJson;
        this.messageUtil = messageUtil;
    }

    public GenericStatusException(UtilI18n messageUtil) {
        this.stayHere = Boolean.TRUE;
    }

    public GenericStatusException(String code, String message) {
        this.code = code;
        this.message = message;
        this.stayHere = Boolean.TRUE;
    }

    public GenericStatusException(Exception e) {
        this.message = e.getMessage();
        this.code = "0";
    }


    /**public GenericStatusException(int code, String message) {
     new GenericStatusException(String.valueOf(code), message);
     }*/

    public GenericStatusException(String code, String messageTag, Boolean stayHere) {
        this.code = code;
        this.message = messageTag;
        this.stayHere = stayHere;
    }

    public Boolean getRenderAsJson() {
        return renderAsJson;
    }

    public void setRenderAsJson(Boolean renderAsJson) {
        this.renderAsJson = renderAsJson;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String messages) {
        this.message = messages;
    }

    public Boolean getStayHere() {
        return stayHere;
    }

    public void setStayHere(Boolean stayHere) {
        this.stayHere = stayHere;
    }

    public UtilI18n getMessageUtil() {
        return messageUtil;
    }

    public void setMessageUtil(UtilI18n messageUtil) {
        this.messageUtil = messageUtil;
    }
}