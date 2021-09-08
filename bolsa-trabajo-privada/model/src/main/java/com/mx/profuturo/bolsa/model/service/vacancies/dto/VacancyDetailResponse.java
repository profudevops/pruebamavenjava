package com.mx.profuturo.bolsa.model.service.vacancies.dto;

import java.io.Serializable;
import java.util.List;

public class VacancyDetailResponse implements Serializable {

    private String tituloVacante;
    private int idAreaInteres;
    private int subArea;
    private String fechaInicio;
    private String fechaFin;
    private int sueldoMin;
    private int sueldoMax;
    private String descripcion;
    private List<String> expectativas;
    private List<String> funciones;
    private int idVacante;
    private String video;
    private String asignada;
    private int numeroPostulados;
    private String idEstrategia;
    private String estatus;
    private boolean vacanteMaestra;

    public String getTituloVacante() {
        return tituloVacante;
    }

    public void setTituloVacante(String tituloVacante) {
        this.tituloVacante = tituloVacante;
    }

    public int getIdAreaInteres() {
        return idAreaInteres;
    }

    public void setIdAreaInteres(int idAreaInteres) {
        this.idAreaInteres = idAreaInteres;
    }

    public int getSubArea() {
        return subArea;
    }

    public void setSubArea(int subArea) {
        this.subArea = subArea;
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

    public int getSueldoMin() {
        return sueldoMin;
    }

    public void setSueldoMin(int sueldoMin) {
        this.sueldoMin = sueldoMin;
    }

    public int getSueldoMax() {
        return sueldoMax;
    }

    public void setSueldoMax(int sueldoMax) {
        this.sueldoMax = sueldoMax;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<String> getExpectativas() {
        return expectativas;
    }

    public void setExpectativas(List<String> expectativas) {
        this.expectativas = expectativas;
    }

    public List<String> getFunciones() {
        return funciones;
    }

    public void setFunciones(List<String> funciones) {
        this.funciones = funciones;
    }

    public int getIdVacante() {
        return idVacante;
    }

    public void setIdVacante(int idVacante) {
        this.idVacante = idVacante;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getAsignada() {
        return asignada;
    }

    public void setAsignada(String asignada) {
        this.asignada = asignada;
    }

    public int getNumeroPostulados() {
        return numeroPostulados;
    }

    public void setNumeroPostulados(int numeroPostulados) {
        this.numeroPostulados = numeroPostulados;
    }

    public String getIdEstrategia() {
        return idEstrategia;
    }

    public void setIdEstrategia(String idEstrategia) {
        this.idEstrategia = idEstrategia;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public boolean getVacanteMaestra() {
        return vacanteMaestra;
    }

    public void setVacanteMaestra(boolean vacanteMaestra) {
        this.vacanteMaestra = vacanteMaestra;
    }
}

