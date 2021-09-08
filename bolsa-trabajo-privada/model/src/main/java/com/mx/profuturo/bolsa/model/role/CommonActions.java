package com.mx.profuturo.bolsa.model.role;

public enum CommonActions {


    CREATE("CREATE"),
    UPDATE("UPDATE"),
    SEE_RESPONSIBLE("SEE_RESPONSIBLE"),
    ASSIGN("ASSIGN"),
    ACTIVATE("ACTIVATE");


    private final String value;
    CommonActions(String s) {
        this.value = s;
    }

    public String getValue(){
        return value;
    }
}
