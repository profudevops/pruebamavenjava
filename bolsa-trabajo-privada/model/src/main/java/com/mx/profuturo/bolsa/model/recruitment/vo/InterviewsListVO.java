package com.mx.profuturo.bolsa.model.recruitment.vo;

import java.util.ArrayList;

public class InterviewsListVO {

    private ArrayList<InterviewInfoVO> entrevistas;
    private int paginaActual;
    private int totalPaginas;

    public ArrayList<InterviewInfoVO> getEntrevistas() {
        return entrevistas;
    }

    public void setEntrevistas(ArrayList<InterviewInfoVO> entrevistas) {
        this.entrevistas = entrevistas;
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
