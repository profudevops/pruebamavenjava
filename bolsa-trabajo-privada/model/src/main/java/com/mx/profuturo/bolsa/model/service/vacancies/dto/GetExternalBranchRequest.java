package com.mx.profuturo.bolsa.model.service.vacancies.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class GetExternalBranchRequest implements Serializable {

    private int idRama;

    public int getIdRama() {
        return idRama;
    }

    public void setIdRama(int idRama) {
        this.idRama = idRama;
    }

    public Map getAsMap() {
        Map parameters = new HashMap<>();
        parameters.put("idRama", this.getIdRama());
        return parameters;
    }
}
