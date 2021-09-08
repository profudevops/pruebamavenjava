package com.mx.profuturo.bolsa.model.service.vacancies.dto;

import com.mx.profuturo.bolsa.model.service.vacancies.base.Vacante;

import java.util.HashMap;
import java.util.Map;

public class VacanteDTO extends Vacante {

    private Integer idVacante;

    public Integer getIdVacante() {
        return idVacante;
    }

    public void setIdVacante(Integer idVacante) {
        this.idVacante = idVacante;
    }

    public Map getAsMap() {
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("idVacante",this.idVacante);
        return m;
    }
}
