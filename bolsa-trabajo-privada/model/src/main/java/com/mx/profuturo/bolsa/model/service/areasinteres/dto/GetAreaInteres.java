package com.mx.profuturo.bolsa.model.service.areasinteres.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class GetAreaInteres implements Serializable {

    private int idArea;

    public int getIdArea() {
        return idArea;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
    }

    public Map getAsMap() {
        Map parameters = new HashMap<>();
        parameters.put("idArea", this.getIdArea());
        return parameters;
    }


}
