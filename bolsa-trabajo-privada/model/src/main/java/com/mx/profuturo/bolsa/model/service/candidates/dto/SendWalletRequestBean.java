package com.mx.profuturo.bolsa.model.service.candidates.dto;

import java.io.Serializable;

public class SendWalletRequestBean implements Serializable {

    private int idCandidato;

    public int getIdCandidato() {
        return idCandidato;
    }

    public void setIdCandidato(int idCandidato) {
        this.idCandidato = idCandidato;
    }
}
