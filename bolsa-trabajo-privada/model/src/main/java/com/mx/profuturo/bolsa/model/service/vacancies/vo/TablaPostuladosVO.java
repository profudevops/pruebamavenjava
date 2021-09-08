package com.mx.profuturo.bolsa.model.service.vacancies.vo;

import java.util.ArrayList;

public class TablaPostuladosVO {
    private ArrayList<ResumenAplicacionVO> postulados = new ArrayList<>();
    private int paginaActual;
    private int totalPaginas;

    public void setPostulados(ArrayList<ResumenAplicacionVO> postulados) {
        this.postulados = postulados;
    }

    public ArrayList<ResumenAplicacionVO> getPostulados() {
        return postulados;
    }

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
}
