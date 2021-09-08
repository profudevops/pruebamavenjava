package com.mx.profuturo.bolsa.model.service.candidates.dto;

import java.io.Serializable;

public class HistoryBean implements Serializable {

    private int idReclutamiento;
    private boolean enProgreso;
    private String tituloVacante;
    private String ultimaEtapaAlcanzada;
    private String fecha;

    public int getIdReclutamiento() {
        return idReclutamiento;
    }

    public void setIdReclutamiento(int idReclutamiento) {
        this.idReclutamiento = idReclutamiento;
    }

    public boolean getEnProgreso() {
        return enProgreso;
    }

    public void setEnProgreso(boolean enProgreso) {
        this.enProgreso = enProgreso;
    }

    public String getTituloVacante() {
        return tituloVacante;
    }

    public void setTituloVacante(String tituloVacante) {
        this.tituloVacante = tituloVacante;
    }

    public String getUltimaEtapaAlcanzada() {
        return ultimaEtapaAlcanzada;
    }

    public void setUltimaEtapaAlcanzada(String ultimaEtapaAlcanzada) {
        this.ultimaEtapaAlcanzada = ultimaEtapaAlcanzada;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
