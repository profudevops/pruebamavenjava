package com.mx.profuturo.bolsa.model.recruitment.dto;

public class InterviewHoursDTO {
	
	
	private String codigoFase;
	private String fecha;
	private Integer tipoEntrevistador;
	private String idUsuario;
	private String idConsultor;
	
	
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getIdConsultor() {
		return idConsultor;
	}
	public void setIdConsultor(String idConsultor) {
		this.idConsultor = idConsultor;
	}
	public String getCodigoFase() {
		return codigoFase;
	}
	public void setCodigoFase(String codigoFase) {
		this.codigoFase = codigoFase;
	}
	public Integer getTipoEntrevistador() {
		return tipoEntrevistador;
	}
	public void setTipoEntrevistador(Integer tipoEntrevistador) {
		this.tipoEntrevistador = tipoEntrevistador;
	}
	
	
	
}
