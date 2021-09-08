package com.mx.profuturo.bolsa.model.service.vacancies.vo;

import com.mx.profuturo.bolsa.model.service.vacancies.base.VacanteDatosBasicos;

public class DetalleVacanteVO extends VacanteDatosBasicos {

 private String asignada;
 private String analista;
 private String gerencia;
 private String sede;
 private Integer plazasAbiertas;
 private int numeroPostulados;
 private int numeroEnProceso;
 private int idEstrategia;
 private int idPublicacion;
 private String estatus;
 private String tipo;
 private boolean vacanteMaestra;
 private String tipoReclutamiento;
 private Integer idSede;

    public Integer getIdSede() {
        return idSede;
    }

    public void setIdSede(Integer idSede) {
        this.idSede = idSede;
    }

    public String getTipoReclutamiento() {
        return tipoReclutamiento;
    }

    public void setTipoReclutamiento(String tipoReclutamiento) {
        this.tipoReclutamiento = tipoReclutamiento;
    }

    public Integer getPlazasAbiertas() {
        return plazasAbiertas;
    }

    public void setPlazasAbiertas(Integer plazasAbiertas) {
        this.plazasAbiertas = plazasAbiertas;
    }

    public String getAnalista() {
        return analista;
    }

    public void setAnalista(String analista) {
        this.analista = analista;
    }

    public String getGerencia() {
        return gerencia;
    }

    public void setGerencia(String gerencia) {
        this.gerencia = gerencia;
    }

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    public int getNumeroEnProceso() {
        return numeroEnProceso;
    }

    public void setNumeroEnProceso(int numeroEnProceso) {
        this.numeroEnProceso = numeroEnProceso;
    }

    public boolean isVacanteMaestra() {
        return vacanteMaestra;
    }

    public void setVacanteMaestra(boolean vacanteMaestra) {
        this.vacanteMaestra = vacanteMaestra;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
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

    public int getNumeroEnProeso() {
        return numeroEnProceso;
    }

    public void setNumeroEnProeso(int numeroEnProeso) {
        this.numeroEnProceso = numeroEnProeso;
    }

    public int getIdEstrategia() {
        return idEstrategia;
    }

    public void setIdEstrategia(int idEstrategia) {
        this.idEstrategia = idEstrategia;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getIdPublicacion() {
        return idPublicacion;
    }

    public void setIdPublicacion(int idPublicacion) {
        this.idPublicacion = idPublicacion;
    }
}
