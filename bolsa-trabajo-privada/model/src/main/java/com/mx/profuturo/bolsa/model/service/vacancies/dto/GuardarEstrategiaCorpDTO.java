package com.mx.profuturo.bolsa.model.service.vacancies.dto;

import java.util.ArrayList;

public class GuardarEstrategiaCorpDTO {

    private ArrayList<Integer> bolsasExternas;
    private int examenConfianza; //desaparece
    private Integer idVacante;
    private Integer idPublicacion;
    private String observador;
    private String reclutador;
    private int visibilidad;

    public Integer getIdPublicacion() {
        return idPublicacion;
    }

    public void setIdPublicacion(Integer idPublicacion) {
        this.idPublicacion = idPublicacion;
    }

    public ArrayList<Integer> getBolsasExternas() {
        return bolsasExternas;
    }

    public void setBolsasExternas(ArrayList<Integer> bolsasExternas) {
        this.bolsasExternas = bolsasExternas;
    }

    public int getExamenConfianza() {
        return examenConfianza;
    }

    public void setExamenConfianza(int examenConfianza) {
        this.examenConfianza = examenConfianza;
    }

    public Integer getIdVacante() {
        return idVacante;
    }

    public void setIdVacante(Integer idVacante) {
        this.idVacante = idVacante;
    }

    public String getObservador() {
        return observador;
    }

    public void setObservador(String observador) {
        this.observador = observador;
    }

    public String getReclutador() {
        return reclutador;
    }

    public void setReclutador(String reclutador) {
        this.reclutador = reclutador;
    }

    public int getVisibilidad() {
        return visibilidad;
    }

    public void setVisibilidad(int visibilidad) {
        this.visibilidad = visibilidad;
    }
}
