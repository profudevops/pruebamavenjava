package com.mx.profuturo.bolsa.model.service.vacancies.vo;

public class DatosPullicacionVO {


    private Integer idGerencia;
    private String gerencia;
    private Integer idAnalista;
    private String analista;
    private Integer plazasAbiertas;
    private Integer plazasCubiertas;
    private Integer idPublicacion;


    public Integer getIdGerencia() {
        return idGerencia;
    }

    public void setIdGerencia(Integer idGerencia) {
        this.idGerencia = idGerencia;
    }

    public String getGerencia() {
        return gerencia;
    }

    public void setGerencia(String gerencia) {
        this.gerencia = gerencia;
    }

    public Integer getIdAnalista() {
        return idAnalista;
    }

    public void setIdAnalista(Integer idAnalista) {
        this.idAnalista = idAnalista;
    }

    public String getAnalista() {
        return analista;
    }

    public void setAnalista(String analista) {
        this.analista = analista;
    }

    public Integer getPlazasAbiertas() {
        return plazasAbiertas;
    }

    public void setPlazasAbiertas(Integer plazasAbiertas) {
        this.plazasAbiertas = plazasAbiertas;
    }

    public Integer getPlazasCubiertas() {
        return plazasCubiertas;
    }

    public void setPlazasCubiertas(Integer plazasCubiertas) {
        this.plazasCubiertas = plazasCubiertas;
    }

    public Integer getIdPublicacion() {
        return idPublicacion;
    }

    public void setIdPublicacion(Integer idPublicacion) {
        this.idPublicacion = idPublicacion;
    }
}
