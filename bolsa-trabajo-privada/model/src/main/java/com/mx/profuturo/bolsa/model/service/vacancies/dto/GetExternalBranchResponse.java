package com.mx.profuturo.bolsa.model.service.vacancies.dto;

import java.io.Serializable;

public class GetExternalBranchResponse implements Serializable {

    private int id;
    private String descripcion;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
