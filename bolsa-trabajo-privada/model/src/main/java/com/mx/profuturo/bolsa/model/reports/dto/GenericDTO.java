package com.mx.profuturo.bolsa.model.reports.dto;

public class GenericDTO {
	
	protected Integer idUsuario;
	
	protected String codigoRama;
	
	protected String fechaInicio;
	protected String fechaFin;
	
	// corporativo
	
	protected Integer idAreaInteres;
	
	//comercial
	
	protected Integer idGerencia;
	protected Integer idRegional;
	protected Integer idDivisional;
	public Integer getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getCodigoRama() {
		return codigoRama;
	}
	public void setCodigoRama(String codigoRama) {
		this.codigoRama = codigoRama;
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
	public Integer getIdAreaInteres() {
		return idAreaInteres;
	}
	public void setIdAreaInteres(Integer idAreaInteres) {
		this.idAreaInteres = idAreaInteres;
	}
	public Integer getIdGerencia() {
		return idGerencia;
	}
	public void setIdGerencia(Integer idGerencia) {
		this.idGerencia = idGerencia;
	}
	public Integer getIdRegional() {
		return idRegional;
	}
	public void setIdRegional(Integer idRegional) {
		this.idRegional = idRegional;
	}
	public Integer getIdDivisional() {
		return idDivisional;
	}
	public void setIdDivisional(Integer idDivisional) {
		this.idDivisional = idDivisional;
	}
	
}
