package com.mx.profuturo.bolsa.model.service.vacancies.dto;

import java.util.Map;

public class BusquedaVacanteDTO {

    private int idVacante;
    private Map<String,String> parametros;

    public int getIdVacante() {
        return idVacante;
    }

    public void setIdVacante(int idVacante) {
        this.idVacante = idVacante;
    }

    public Map<String, String> getParametros() {
        return parametros;
    }

    public void setParametros(Map<String, String> parametros) {
        this.parametros = parametros;
    }
}
