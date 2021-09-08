package com.mx.profuturo.bolsa.model.service.candidates.dto;

import java.io.Serializable;
import java.util.List;

public class QualifyCandidateRequestBean implements Serializable {

    private String codigoCalificacion;
    private List<String> codigoEtiquetas;
    private int idCandidato;

    public String getCodigoCalificacion() {
        return codigoCalificacion;
    }

    public void setCodigoCalificacion(String codigoCalificacion) {
        this.codigoCalificacion = codigoCalificacion;
    }

    public List<String> getCodigoEtiquetas() {
        return codigoEtiquetas;
    }

    public void setCodigoEtiquetas(List<String> codigoEtiquetas) {
        this.codigoEtiquetas = codigoEtiquetas;
    }

    public int getIdCandidato() {
        return idCandidato;
    }

    public void setIdCandidato(int idCandidato) {
        this.idCandidato = idCandidato;
    }
}
