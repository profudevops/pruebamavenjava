package com.mx.profuturo.bolsa.model.vo.Candidato;

import java.util.ArrayList;

public class ResumenCandidatosListVO {


   private ArrayList<CandidatoResumenVO> resumenCandidatos;
   private int paginaActual;
   private int totalPaginas;

    public ArrayList<CandidatoResumenVO> getResumenCandidatos() {
        return resumenCandidatos;
    }

    public void setResumenCandidatos(ArrayList<CandidatoResumenVO> resumenCandidatos) {
        this.resumenCandidatos = resumenCandidatos;
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
