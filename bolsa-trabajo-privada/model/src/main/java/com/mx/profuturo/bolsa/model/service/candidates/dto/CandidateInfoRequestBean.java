package com.mx.profuturo.bolsa.model.service.candidates.dto;

import java.util.HashMap;
import java.util.Map;

public class CandidateInfoRequestBean {

    private String modo;

    public String getModo() {
        return modo;
    }

    public void setModo(String modo) {
        this.modo = modo;
    }

    public Map getAsMap() {
        Map parameters = new HashMap<>();
        parameters.put("modo", this.modo);
        return parameters;
    }

}
