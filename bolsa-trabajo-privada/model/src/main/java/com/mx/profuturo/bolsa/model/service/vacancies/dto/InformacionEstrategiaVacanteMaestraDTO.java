package com.mx.profuturo.bolsa.model.service.vacancies.dto;

import java.util.ArrayList;

public class InformacionEstrategiaVacanteMaestraDTO {

    private ArrayList<Integer> bolsasExternas;
    private String observador;
    private String idObservador;
    private int idPublicacion;
    private int visibilidad;

    public int getVisibilidad() {
        return visibilidad;
    }

    public void setVisibilidad(int visibilidad) {
        this.visibilidad = visibilidad;
    }

    public String getIdObservador() {
        return idObservador;
    }

    public void setIdObservador(String idObservador) {
        this.idObservador = idObservador;
    }

    public String getObservador() {
        return observador;
    }

    public void setObservador(String observador) {
        this.observador = observador;
    }

    public ArrayList<Integer> getBolsasExternas() {
        return bolsasExternas;
    }

    public void setBolsasExternas(ArrayList<Integer> bolsasExternas) {
        this.bolsasExternas = bolsasExternas;
    }

    public int getIdPublicacion() {
        return idPublicacion;
    }

    public void setIdPublicacion(int idPublicacion) {
        this.idPublicacion = idPublicacion;
    }
}
