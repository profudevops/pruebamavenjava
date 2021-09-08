package com.mx.profuturo.bolsa.model.service.vacancies.vo;

import java.util.ArrayList;

public class ListaPublicacionesVO {

    private String vacante;
    private String sede;
    private Integer idSede;


    private ArrayList<DatosPullicacionVO> publicaciones;

    public String getVacante() {
        return vacante;
    }

    public void setVacante(String vacante) {
        this.vacante = vacante;
    }

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    public Integer getIdSede() {
        return idSede;
    }

    public void setIdSede(Integer idSede) {
        this.idSede = idSede;
    }

    public ArrayList<DatosPullicacionVO> getPublicaciones() {
        return publicaciones;
    }

    public void setPublicaciones(ArrayList<DatosPullicacionVO> publicaciones) {
        this.publicaciones = publicaciones;
    }
}
