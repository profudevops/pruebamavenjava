package com.mx.profuturo.bolsa.model.service.areasinteres.dto;

import java.io.Serializable;

public class DropInterestSubareaRequestBean implements Serializable {

    private int idArea;
    private int idSubArea;

    public int getIdArea() {
        return idArea;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
    }

    public int getIdSubArea() {
        return idSubArea;
    }

    public void setIdSubArea(int idSubArea) {
        this.idSubArea = idSubArea;
    }
}
