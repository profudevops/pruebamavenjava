package com.mx.profuturo.bolsa.model.service.areasinteres.dto;

import java.io.Serializable;
import java.util.List;

public class UpdateInterestAreaRequestBean implements Serializable {

    private String descripcion;
    private int idArea;
    private String nombre;
    private List<SubAreaInteresDTO> subareas;

    public String getDescripcion() {
        return descripcion;
    }

    public int getIdArea() {
        return idArea;
    }

    public String getNombre() {
        return nombre;
    }

    public List<SubAreaInteresDTO> getSubareas() {
        return subareas;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setSubareas(List<SubAreaInteresDTO> subareas) {
        this.subareas = subareas;
    }
}
