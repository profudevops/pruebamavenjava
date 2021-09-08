package com.mx.profuturo.bolsa.model.service.vacancies.vo;

import com.mx.profuturo.bolsa.model.vo.vacancy.ResumenPublicacionVO;

import java.util.LinkedList;

public class ResumenPublicacionesVO {

    private int paginaActual;
    private int totalPaginas;
    private LinkedList<ResumenPublicacionVO> resumenPublicaciones;

    public int getPaginaActual() {
        return paginaActual;
    }

    public void setPaginaActual(int paginaActual) {
        this.paginaActual = paginaActual;
    }

    public int getTotalPaginas() {
        return totalPaginas;
    }

    public void setTotalPaginas(int totalPaginas) {
        this.totalPaginas = totalPaginas;
    }

    public LinkedList<ResumenPublicacionVO> getResumenPublicaciones() {
        return resumenPublicaciones;
    }

    public void setResumenPublicaciones(LinkedList<ResumenPublicacionVO> resumenPublicaciones) {
        this.resumenPublicaciones = resumenPublicaciones;
    }
}
