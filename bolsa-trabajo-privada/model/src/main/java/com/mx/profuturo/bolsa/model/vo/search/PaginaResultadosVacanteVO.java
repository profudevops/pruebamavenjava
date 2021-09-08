package com.mx.profuturo.bolsa.model.vo.search;


import com.mx.profuturo.bolsa.model.vo.vacancy.PublicacionVO;

import java.util.ArrayList;

public class PaginaResultadosVacanteVO {

    private ArrayList <PublicacionVO> resultadosVacante;
    private ArrayList <PublicacionVO> sugerenciasVacante;
    private boolean subirCV;

    public ArrayList<PublicacionVO> getResultadosVacante() {
        return resultadosVacante;
    }

    public void setResultadosVacante(ArrayList<PublicacionVO> resultadosVacante) {
        this.resultadosVacante = resultadosVacante;
    }

    public ArrayList<PublicacionVO> getSugerenciasVacante() {
        return sugerenciasVacante;
    }

    public void setSugerenciasVacante(ArrayList<PublicacionVO> sugerenciasVacante) {
        this.sugerenciasVacante = sugerenciasVacante;
    }

    public boolean isSubirCV() {
        return subirCV;
    }

    public void setSubirCV(boolean subirCV) {
        this.subirCV = subirCV;
    }
}