package com.mx.profuturo.bolsa.model.service.vacancies.vo;

public class ResumenPlazaMaestraAnalistaVO {

    private String sede;
    private Integer idPublicacion;
    private Integer plazasAbiertas;
    private Integer plazasCubiertas;
    private String estatus;
    private Integer idSede;

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    public Integer getIdPublicacion() {
        return idPublicacion;
    }

    public void setIdPublicacion(Integer idPublicacion) {
        this.idPublicacion = idPublicacion;
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

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public Integer getIdSede() {
        return idSede;
    }

    public void setIdSede(Integer idSede) {
        this.idSede = idSede;
    }
}
