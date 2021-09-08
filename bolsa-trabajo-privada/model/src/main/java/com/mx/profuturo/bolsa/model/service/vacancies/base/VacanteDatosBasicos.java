package com.mx.profuturo.bolsa.model.service.vacancies.base;

import java.util.ArrayList;

import com.mx.profuturo.bolsa.model.vo.common.CatalogoVO;

public abstract class VacanteDatosBasicos {

    private String tituloVacante;
    private Integer idAreaInteres;
    private Integer idNivelPuesto;
    private String nivelPuesto;
    private String nombreSubArea;
    private Integer subArea;
    private String fechaInicio;
    private String fechaFin;
    private Integer sueldoMin;
    private Integer sueldoMax;
    private String descripcion;
    private ArrayList<String> expectativas;
    private ArrayList<String> indispensable;
    private ArrayList<String> funciones;
    private Integer idVacante;
    private String tipo;
    private int idSubAreaInteres;
    private int idRama;

    public ArrayList<String> getIndispensable() {
        return indispensable;
    }

    public void setIndispensable(ArrayList<String> indispensable) {
        this.indispensable = indispensable;
    }

    public int getIdSubAreaInteres() {
        return idSubAreaInteres;
    }

    public void setIdSubAreaInteres(int idSubAreaInteres) {
        this.idSubAreaInteres = idSubAreaInteres;
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

    public Integer getIdNivelPuesto() {
        return idNivelPuesto;
    }

    public void setIdNivelPuesto(Integer idNivelPuesto) {
        this.idNivelPuesto = idNivelPuesto;
    }

    public String getNivelPuesto() {
        return nivelPuesto;
    }

    public void setNivelPuesto(String nivelPuesto) {
        this.nivelPuesto = nivelPuesto;
    }

    public String getNombreSubArea() {
        return nombreSubArea;
    }

    public void setNombreSubArea(String nombreSubArea) {
        this.nombreSubArea = nombreSubArea;
    }

    public Integer getSubArea() {
        return subArea;
    }

    public void setSubArea(Integer subArea) {
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

    public ArrayList<String> getExpectativas() {
        return expectativas;
    }

    public void setExpectativas(ArrayList<String> expectativas) {
        this.expectativas = expectativas;
    }

    public ArrayList<String> getFunciones() {
        return funciones;
    }

    public void setFunciones(ArrayList<String> funciones) {
        this.funciones = funciones;
    }

    public Integer getIdVacante() {
        return idVacante;
    }

    public void setIdVacante(Integer idVacante) {
        this.idVacante = idVacante;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getIdRama() {
        return idRama;
    }

    public void setIdRama(int idRama) {
        this.idRama = idRama;
    }
}
