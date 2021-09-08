package com.mx.profuturo.bolsa.model.service.candidates.dto;

public class IniciarReclutamientoDTO {
	
	private Integer idCandidato;
	private Integer idPublicacion;
	private Integer idUsuario;
	private String fecha;
	
	
	public Integer getIdCandidato() {
		return idCandidato;
	}
	public void setIdCandidato(Integer idCandidato) {
		this.idCandidato = idCandidato;
	}
	public Integer getIdPublicacion() {
		return idPublicacion;
	}
	public void setIdPublicacion(Integer idPublicacion) {
		this.idPublicacion = idPublicacion;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
}
