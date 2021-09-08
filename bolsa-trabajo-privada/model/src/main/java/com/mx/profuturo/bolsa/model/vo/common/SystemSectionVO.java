package com.mx.profuturo.bolsa.model.vo.common;

import java.util.ArrayList;

public class SystemSectionVO {

    private String code;
    private String description;
    private ArrayList<String> operations = new ArrayList<>();

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<String> getOperations() {
        return operations;
    }

    public void setOperations(ArrayList<String> operations) {
        this.operations = operations;
    }
}
