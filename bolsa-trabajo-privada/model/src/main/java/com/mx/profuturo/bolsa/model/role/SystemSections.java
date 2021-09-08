package com.mx.profuturo.bolsa.model.role;

public enum SystemSections {

    VACANCIES("VACANCIES");

    private final String value;
    SystemSections(String s) {
        this.value = s;
    }

    public String getValue(){
        return value;
    }
}
