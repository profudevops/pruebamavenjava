package com.mx.profuturo.bolsa.model.vo.vacancy;

import com.mx.profuturo.bolsa.model.vo.common.CatalogoVO;

import java.util.ArrayList;

public class InformacionDetalladaVacante {


    //TODO: por qué???

    private ArrayList<CatalogoVO> capacidades;
    private ArrayList<CatalogoVO> indispensable;
    private RazonesVacante razonesVacante;


    private String videoId;
    private boolean isAsesorAfore;

    public ArrayList<CatalogoVO> getCapacidades() {
        return capacidades;
    }

    public void setCapacidades(ArrayList<CatalogoVO> capacidades) {
        this.capacidades = capacidades;
    }

    public ArrayList<CatalogoVO> getIndispensable() {
        return indispensable;
    }

    public void setIndispensable(ArrayList<CatalogoVO> indispensable) {
        this.indispensable = indispensable;
    }

    public RazonesVacante getRazonesVacante() {
        return razonesVacante;
    }

    public void setRazonesVacante(RazonesVacante razonesVacante) {
        this.razonesVacante = razonesVacante;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public boolean isAsesorAfore() {
        return isAsesorAfore;
    }

    public void setAsesorAfore(boolean asesorAfore) {
        isAsesorAfore = asesorAfore;
    }


}

