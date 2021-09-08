package com.mx.profuturo.bolsa.model.service.candidates.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class CandidateHistoryRequestBean implements Serializable {

    private int idCandidato;

    public int getIdCandidato() {
        return idCandidato;
    }

    public void setIdCandidato(int idCandidato) {
        this.idCandidato = idCandidato;
    }

    public Map getAsMap() {
        Map parameters = new HashMap<>();
        parameters.put("idCandidato", this.getIdCandidato());
        return parameters;
    }
}
