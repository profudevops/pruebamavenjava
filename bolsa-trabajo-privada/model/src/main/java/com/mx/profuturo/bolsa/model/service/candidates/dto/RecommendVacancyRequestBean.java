package com.mx.profuturo.bolsa.model.service.candidates.dto;

import java.io.Serializable;

public class RecommendVacancyRequestBean implements Serializable {

    private int idCandidato;
    private int idPublicacion;

    public int getIdCandidato() {
        return idCandidato;
    }

    public void setIdCandidato(int idCandidato) {
        this.idCandidato = idCandidato;
    }

    public int getIdPublicacion() {
        return idPublicacion;
    }

    public void setIdPublicacion(int idPublicacion) {
        this.idPublicacion = idPublicacion;
    }
}
