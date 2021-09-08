package com.mx.profuturo.bolsa.model.service.vacancies.vo;

import java.util.LinkedList;

public class ResumenVacanteMaestraAnalistaVO {

    private int idVacante;
    private int idPublicacion;
    private String tituloVacante;
    private LinkedList<ResumenPlazaMaestraAnalistaVO> resumen;
    private String tipoReclutamiento;


    public String getTipoReclutamiento() { return tipoReclutamiento; }

    public void setTipoReclutamiento(String tipoReclutamiento) { this.tipoReclutamiento = tipoReclutamiento; }

    public int getIdVacante() {
        return idVacante;
    }

    public void setIdVacante(int idVacante) {
        this.idVacante = idVacante;
    }

    public int getIdPublicacion() {
        return idPublicacion;
    }

    public void setIdPublicacion(int idPublicacion) {
        this.idPublicacion = idPublicacion;
    }

    public String getTituloVacante() {
        return tituloVacante;
    }

    public void setTituloVacante(String tituloVacante) {
        this.tituloVacante = tituloVacante;
    }

    public LinkedList<ResumenPlazaMaestraAnalistaVO> getResumen() {
        return resumen;
    }

    public void setResumen(LinkedList<ResumenPlazaMaestraAnalistaVO> resumen) {
        this.resumen = resumen;
    }
}
