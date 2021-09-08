package com.mx.profuturo.bolsa.model.vo.vacancy;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.LinkedList;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class VacanteGerenteComercialVO {

    private String tituloVacante;
    private Integer idVacante;
    private String idUsuario;
    private LinkedList<ResumenReclutadorComercialVO> publicaciones;

    public String getTituloVacante() {
        return tituloVacante;
    }

    public void setTituloVacante(String tituloVacante) {
        this.tituloVacante = tituloVacante;
    }

    public Integer getIdVacante() {
        return idVacante;
    }

    public void setIdVacante(Integer idVacante) {
        this.idVacante = idVacante;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public LinkedList<ResumenReclutadorComercialVO> getPublicaciones() {
        return publicaciones;
    }

    public void setPublicaciones(LinkedList<ResumenReclutadorComercialVO> publicaciones) {
        this.publicaciones = publicaciones;
    }
}
