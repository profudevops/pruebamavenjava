package com.mx.profuturo.bolsa.model.vo.common;

import java.util.ArrayList;

public class OpcionesFormularioVO {
    private ArrayList<CatalogoVO> gradoEstudios;
    private ArrayList<CatalogoVO> bolsaTrabajoFuente;

    public ArrayList<CatalogoVO> getGradoEstudios() {
        return gradoEstudios;
    }

    public void setGradoEstudios(ArrayList<CatalogoVO> gradoEstudios) {
        this.gradoEstudios = gradoEstudios;
    }

    public ArrayList<CatalogoVO> getBolsaTrabajoFuente() {
        return bolsaTrabajoFuente;
    }

    public void setBolsaTrabajoFuente(ArrayList<CatalogoVO> bolsaTrabajoFuente) {
        this.bolsaTrabajoFuente = bolsaTrabajoFuente;
    }
}
