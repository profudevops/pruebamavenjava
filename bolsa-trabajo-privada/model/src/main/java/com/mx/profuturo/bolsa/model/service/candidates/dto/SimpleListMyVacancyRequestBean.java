package com.mx.profuturo.bolsa.model.service.candidates.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class SimpleListMyVacancyRequestBean implements Serializable {

    String idUsuario;
    int idInmueble;

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdInmueble() {
        return idInmueble;
    }

    public void setIdInmueble(int idInmueble) {
        this.idInmueble = idInmueble;
    }


    public Map getAsMap() {
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("idUsuario",this.idUsuario);
        return m;
    }
}
