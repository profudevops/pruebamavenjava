package com.mx.profuturo.bolsa.model.service.vacancies.dto;

import java.util.LinkedList;

public class CrearVacanteDTO {

    private String tituloVacante;
    private Integer idAreaInteres;
    private Integer subArea;
    private String fechaInicio;
    private String fechaFin;
    private Integer sueldoMin;
    private Integer sueldoMax;
    private String descripcion;
    private LinkedList<String> expectativas;
    private LinkedList<String> indispensable;
    private LinkedList<String> funciones;
    private Integer idVacante;
    private Integer idNivelPuesto;
    private String video;
    private boolean vacanteMaestra;
    private String idUsuario;

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getSubArea() {
        return subArea;
    }

    public void setSubArea(Integer subArea) {
        this.subArea = subArea;
    }

    public Integer getIdNivelPuesto() {
        return idNivelPuesto;
    }

    public void setIdNivelPuesto(Integer idNivelPuesto) {
        this.idNivelPuesto = idNivelPuesto;
    }

    public boolean isVacanteMaestra() {
        return vacanteMaestra;
    }

    public void setVacanteMaestra(boolean vacanteMaestra) {
        this.vacanteMaestra = vacanteMaestra;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public Integer getIdSubAreaInteres() {
        return subArea;
    }

    public void setIdSubAreaInteres(Integer idSubAreaInteres) {
        this.subArea = idSubAreaInteres;
    }

    public Integer getIdVacante() {
        return idVacante;
    }

    public void setIdVacante(Integer idVacante) {
        this.idVacante = idVacante;
    }

    public String getTituloVacante() {
        return tituloVacante;
    }

    public void setTituloVacante(String tituloVacante) {
        this.tituloVacante = tituloVacante;
    }

    public Integer getIdAreaInteres() {
        return idAreaInteres;
    }

    public void setIdAreaInteres(Integer idAreaInteres) {
        this.idAreaInteres = idAreaInteres;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Integer getSueldoMin() {
        return sueldoMin;
    }

    public void setSueldoMin(Integer sueldoMin) {
        this.sueldoMin = sueldoMin;
    }

    public Integer getSueldoMax() {
        return sueldoMax;
    }

    public void setSueldoMax(Integer sueldoMax) {
        this.sueldoMax = sueldoMax;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LinkedList<String> getExpectativas() {
        return expectativas;
    }

    public void setExpectativas(LinkedList<String> expectativas) {
        this.expectativas = expectativas;
    }

    public LinkedList<String> getFunciones() {
        return funciones;
    }

    public void setFunciones(LinkedList<String> funciones) {
        this.funciones = funciones;
    }

    public LinkedList<String> getIndispensable() { return indispensable; }

    public void setIndispensable(LinkedList<String> indispensable) { this.indispensable = indispensable; }
}
