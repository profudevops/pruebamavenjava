package com.mx.profuturo.bolsa.model.service.vacancies.vo;

import java.util.ArrayList;

public class ResumenPlazaMaestraVO {


    private Integer idVacante;
    private String sede;
    private Integer idSede;
    private Integer plazasAbiertas;
    private String gerencia;
    private ArrayList<String> analistAsignado;

    public Integer getIdSede() {
        return idSede;
    }

    public void setIdSede(Integer idSede) {
        this.idSede = idSede;
    }

    public String getGerencia() {
        return gerencia;
    }

    public void setGerencia(String gerencia) {
        this.gerencia = gerencia;
    }

    public ArrayList<String> getAnalistAsignado() {
        return analistAsignado;
    }

    public void setAnalistAsignado(ArrayList<String> analistAsignado) {
        this.analistAsignado = analistAsignado;
    }

    public Integer getIdVacante() {
        return idVacante;
    }

    public void setIdVacante(Integer idVacante) {
        this.idVacante = idVacante;
    }

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    public Integer getPlazasAbiertas() {
        return plazasAbiertas;
    }

    public void setPlazasAbiertas(Integer plazasAbiertas) {
        this.plazasAbiertas = plazasAbiertas;
    }

}
