package com.mx.profuturo.bolsa.model.service.candidates.dto;

import java.util.HashMap;
import java.util.Map;

public class CandidateEditEmailRequestBean {

    private int idCandidato;
    private String correo;

    public int getIdCandidato() {
        return idCandidato;
    }

    public void setIdCandidato(int idCandidato) {
        this.idCandidato = idCandidato;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Map getAsMap() {
        Map parameters = new HashMap<>();
        parameters.put("idCandidato", this.getIdCandidato());
        parameters.put("correo", this.getCorreo());
        return parameters;
    }

}
