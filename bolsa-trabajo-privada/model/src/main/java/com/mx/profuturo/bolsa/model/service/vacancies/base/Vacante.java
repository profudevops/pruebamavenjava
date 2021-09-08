package com.mx.profuturo.bolsa.model.service.vacancies.base;

public abstract class Vacante extends VacanteDatosBasicos {
    protected Integer sueldoInicial;
    protected Integer sueldoFinal;
    protected String descripcion;
    protected String esperado;
    protected String indispendable;

    public Integer getSueldoInicial() {
        return sueldoInicial;
    }

    public void setSueldoInicial(Integer sueldoInicial) {
        this.sueldoInicial = sueldoInicial;
    }

    public Integer getSueldoFinal() {
        return sueldoFinal;
    }

    public void setSueldoFinal(Integer sueldoFinal) {
        this.sueldoFinal = sueldoFinal;
    }

    @Override
    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEsperado() {
        return esperado;
    }

    public void setEsperado(String esperado) {
        this.esperado = esperado;
    }

    public String getIndispendable() {
        return indispendable;
    }

    public void setIndispendable(String indispendable) {
        this.indispendable = indispendable;
    }
}
