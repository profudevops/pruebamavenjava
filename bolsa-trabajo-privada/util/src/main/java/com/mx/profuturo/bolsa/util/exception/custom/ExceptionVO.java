package com.mx.profuturo.bolsa.util.exception.custom;

public class ExceptionVO {

    private String code;

    private String message;

    private Boolean stayHere;

    private Boolean renderAsJson;

    private Boolean system;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getStayHere() {
        return stayHere;
    }

    public void setStayHere(Boolean stayHere) {
        this.stayHere = stayHere;
    }

    public Boolean getRenderAsJson() {
        return renderAsJson;
    }

    public void setRenderAsJson(Boolean renderAsJson) {
        this.renderAsJson = renderAsJson;
    }

    public Boolean getSystem() {
        return system;
    }

    public void setSystem(Boolean system) {
        this.system = system;
    }
}
