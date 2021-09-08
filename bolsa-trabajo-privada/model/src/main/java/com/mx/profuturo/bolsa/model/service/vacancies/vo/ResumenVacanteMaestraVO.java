package com.mx.profuturo.bolsa.model.service.vacancies.vo;

import java.util.LinkedList;

public class ResumenVacanteMaestraVO {

    private Integer idVacante;
    private String tituloVacante;
    private LinkedList<ResumenPlazaMaestraVO> publicaciones;


    public Integer getIdVacante() {
        return idVacante;
    }

    public void setIdVacante(Integer idVacante) {
        this.idVacante = idVacante;
    }

    public String getTituloVacante() {
        return tituloVacante;
    }

    public void setTituloVacante(String tituloVacante) {
        this.tituloVacante = tituloVacante;
    }

    public LinkedList<ResumenPlazaMaestraVO> getPublicaciones() {
        return publicaciones;
    }

    public void setPublicaciones(LinkedList<ResumenPlazaMaestraVO> publicaciones) {
        this.publicaciones = publicaciones;
    }
}

