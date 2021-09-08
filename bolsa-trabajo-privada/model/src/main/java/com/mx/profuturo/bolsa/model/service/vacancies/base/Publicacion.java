package com.mx.profuturo.bolsa.model.service.vacancies.base;

public abstract class Publicacion {
    protected Integer id;
    protected Integer idResponsable;
    protected Integer idExamenTecnico;
    protected boolean publica;
    protected String psicometrico;
    protected boolean aplicarExamenConfianza;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdResponsable() {
        return idResponsable;
    }

    public void setIdResponsable(Integer idResponsable) {
        this.idResponsable = idResponsable;
    }

    public String getPsicometrico() {
        return psicometrico;
    }

    public void setPsicometrico(String psicometrico) {
        this.psicometrico = psicometrico;
    }

    public boolean isPublica() {
        return publica;
    }

    public void setPublica(boolean publica) {
        this.publica = publica;
    }

    public boolean isAplicarExamenConfianza() {
        return aplicarExamenConfianza;
    }

    public void setAplicarExamenConfianza(boolean aplicarExamenConfianza) {
        this.aplicarExamenConfianza = aplicarExamenConfianza;
    }

    public Integer getIdExamenTecnico() {
        return idExamenTecnico;
    }

    public void setIdExamenTecnico(Integer idExamenTecnico) {
        this.idExamenTecnico = idExamenTecnico;
    }
    
}
