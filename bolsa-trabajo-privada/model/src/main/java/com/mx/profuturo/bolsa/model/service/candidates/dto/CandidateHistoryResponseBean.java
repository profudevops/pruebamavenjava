package com.mx.profuturo.bolsa.model.service.candidates.dto;

import java.io.Serializable;
import java.util.List;

public class CandidateHistoryResponseBean implements Serializable {

    private List<HistoryBean> actuales;
    private List<HistoryBean> anteriores;

    public List<HistoryBean> getActuales() {
        return actuales;
    }

    public void setActuales(List<HistoryBean> actuales) {
        this.actuales = actuales;
    }

    public List<HistoryBean> getAnteriores() {
        return anteriores;
    }

    public void setAnteriores(List<HistoryBean> anteriores) {
        this.anteriores = anteriores;
    }
}
