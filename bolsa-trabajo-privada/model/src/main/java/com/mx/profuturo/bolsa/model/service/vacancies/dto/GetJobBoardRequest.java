package com.mx.profuturo.bolsa.model.service.vacancies.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class GetJobBoardRequest implements Serializable {

    private int idVacante;

    public int getIdVacante() {
        return idVacante;
    }

    public void setIdVacante(int idVacante) {
        this.idVacante = idVacante;
    }

    public Map getAsMap() {
        Map parameters = new HashMap<>();
        parameters.put("idVacante", this.getIdVacante());
        return parameters;
    }
}
