package com.mx.profuturo.bolsa.model.service.cartera;

import com.mx.profuturo.bolsa.model.vo.common.CatalogoVO;

import java.io.Serializable;
import java.util.List;

public class LoadFiltersResponse implements Serializable {

    private String modo;
    private List<CatalogoVO> calificaciones;
    private List<CatalogoVO> areasInteres;
    private List<CatalogoVO> tiempoEnCartera;
    private List<CatalogoVO> edad;
    private Integer paginaActual;
    private Integer totalPaginas;

    public List<CatalogoVO> getTiempoEnCartera() {
        return tiempoEnCartera;
    }

    public void setTiempoEnCartera(List<CatalogoVO> tiempoEnCartera) {
        this.tiempoEnCartera = tiempoEnCartera;
    }

    public Integer getPaginaActual() {
        return paginaActual;
    }

    public void setPaginaActual(Integer paginaActual) {
        this.paginaActual = paginaActual;
    }

    public Integer getTotalPaginas() {
        return totalPaginas;
    }

    public void setTotalPaginas(Integer totalPaginas) {
        this.totalPaginas = totalPaginas;
    }

    public String getModo() {
        return modo;
    }

    public void setModo(String modo) {
        this.modo = modo;
    }

    public List<CatalogoVO> getCalificaciones() {
        return calificaciones;
    }

    public void setCalificaciones(List<CatalogoVO> calificaciones) {
        this.calificaciones = calificaciones;
    }

    public List<CatalogoVO> getAreasInteres() {
        return areasInteres;
    }

    public void setAreasInteres(List<CatalogoVO> areasInteres) {
        this.areasInteres = areasInteres;
    }

    public List<CatalogoVO> getEdad() {
        return edad;
    }

    public void setEdad(List<CatalogoVO> edad) {
        this.edad = edad;
    }
}
