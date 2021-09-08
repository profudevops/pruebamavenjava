package com.mx.profuturo.bolsa.model.service.vacancies.vo;

public class DetalleVacanteCerradaVO extends DetalleVacanteVO {

    private String motivoCierre;
    private String candidatoSeleccionado;

    public String getMotivoCierre() {
        return motivoCierre;
    }

    public void setMotivoCierre(String motivoCierre) {
        this.motivoCierre = motivoCierre;
    }

    public String getCandidatoSeleccionado() {
        return candidatoSeleccionado;
    }

    public void setCandidatoSeleccionado(String candidatoSeleccionado) {
        this.candidatoSeleccionado = candidatoSeleccionado;
    }
}
