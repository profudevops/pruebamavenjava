package com.mx.profuturo.bolsa.model.recruitment.dto;

public class StepForm {

	
	//general
	protected Integer motivoRechazo;
	
	//FT
	
	protected String continuar;

	public String getContinuar() {
		return continuar;
	}

	public void setContinuar(String continuar) {
		this.continuar = continuar;
	}

	public Integer getMotivoRechazo() {
		return motivoRechazo;
	}

	public void setMotivoRechazo(Integer motivoRechazo) {
		this.motivoRechazo = motivoRechazo;
	}
	
	
	//PE_AG
	
	private String entrevistador;
	private String fecha;
	private String hora;
	private Integer sede;
	private Integer tipoEntrevista;

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public Integer getSede() {
		return sede;
	}

	public void setSede(Integer sede) {
		this.sede = sede;
	}

	public Integer getTipoEntrevista() {
		return tipoEntrevista;
	}

	public void setTipoEntrevista(Integer tipoEntrevista) {
		this.tipoEntrevista = tipoEntrevista;
	}

	public String getEntrevistador() {
		return entrevistador;
	}

	public void setEntrevistador(String entrevistador) {
		this.entrevistador = entrevistador;
	}
	
}
