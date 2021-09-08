package com.mx.profuturo.bolsa.model.service.cartera;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class LoadFiltersRequest implements Serializable {

    private String modo;

    public String getModo() {
        return modo;
    }

    public void setModo(String modo) {
        this.modo = modo;
    }

    public Map getAsMap() {
        Map parameters = new HashMap<>();
        parameters.put("modo", this.getModo());
        return parameters;
    }
}
