package com.mx.profuturo.bolsa.model.service.vacancies.vo;

import java.util.ArrayList;

public class EstrategiaVacanteMaestraVO {

    private ArrayList<Integer> bolsasExternas;
    private String numeroEmpleadoObservador;
    private int visibilidad;
    private String emailObservador;
    private String correoObservador;

    public int getVisibilidad() { return visibilidad; }

    public void setVisibilidad(int visibilidad) { this.visibilidad = visibilidad;   }

    public String getCorreoObservador() {
        return correoObservador;
    }

    public void setCorreoObservador(String correoObservador) {
        this.correoObservador = correoObservador;
    }

    public String getEmailObservador() {
        return emailObservador;
    }

    public void setEmailObservador(String emailObservador) {
        this.emailObservador = emailObservador;
    }

    public String getNumeroEmpleadoObservador() {
        return numeroEmpleadoObservador;
    }

    public void setNumeroEmpleadoObservador(String numeroEmpleadoObservador) {
        this.numeroEmpleadoObservador = numeroEmpleadoObservador;
    }

    public ArrayList<Integer> getBolsasExternas() {
        return bolsasExternas;
    }

    public void setBolsasExternas(ArrayList<Integer> bolsasExternas) {
        this.bolsasExternas = bolsasExternas;
    }
}
