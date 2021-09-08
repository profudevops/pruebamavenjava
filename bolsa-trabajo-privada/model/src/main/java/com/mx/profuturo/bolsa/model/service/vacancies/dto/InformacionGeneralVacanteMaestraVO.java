package com.mx.profuturo.bolsa.model.service.vacancies.dto;

public class InformacionGeneralVacanteMaestraVO {

    private int idVacante;
    private String tituloVacante;
    private int totalPlazasAbiertas;
    private int totalProcesos;

    public int getIdVacante() { return idVacante;
    }

    public void setIdVacante(int idVacante) {
        this.idVacante = idVacante;
    }

    public String getTituloVacante() {
        return tituloVacante;
    }

    public void setTituloVacante(String tituloVacante) {
        this.tituloVacante = tituloVacante;
    }

    public int getTotalPlazasAbiertas() {
        return totalPlazasAbiertas;
    }

    public void setTotalPlazasAbiertas(int totalPlazasAbiertas) {
        this.totalPlazasAbiertas = totalPlazasAbiertas;
    }

    public int getTotalProcesos() {
        return totalProcesos;
    }

    public void setTotalProcesos(int totalProcesos) {
        this.totalProcesos = totalProcesos;
    }
}