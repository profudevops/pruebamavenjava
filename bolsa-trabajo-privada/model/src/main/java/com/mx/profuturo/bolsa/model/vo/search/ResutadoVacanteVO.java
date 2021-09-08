package com.mx.profuturo.bolsa.model.vo.search;


import com.mx.profuturo.bolsa.model.vo.vacancy.InformacionBasicaVacante;


public class ResutadoVacanteVO {

    private Integer idPublilcacion;
    private String lugarTrabajo;
    private InformacionBasicaVacante informacionBasicaVacante;

    public Integer getIdPublilcacion() {
        return idPublilcacion;
    }

    public void setIdPublilcacion(Integer idPublilcacion) {
        this.idPublilcacion = idPublilcacion;
    }

    public String getLugarTrabajo() {
        return lugarTrabajo;
    }

    public void setLugarTrabajo(String lugarTrabajo) {
        this.lugarTrabajo = lugarTrabajo;
    }

    public InformacionBasicaVacante getInformacionBasicaVacante() {
        return informacionBasicaVacante;
    }

    public void setInformacionBasicaVacante(InformacionBasicaVacante informacionBasicaVacante) {
        this.informacionBasicaVacante = informacionBasicaVacante;
    }

    public ResutadoVacanteVO(Integer idPublilcacion, String lugarTrabajo, InformacionBasicaVacante informacionBasicaVacante) {
        this.idPublilcacion = idPublilcacion;
        this.lugarTrabajo = lugarTrabajo;
        this.informacionBasicaVacante = informacionBasicaVacante;
    }
}