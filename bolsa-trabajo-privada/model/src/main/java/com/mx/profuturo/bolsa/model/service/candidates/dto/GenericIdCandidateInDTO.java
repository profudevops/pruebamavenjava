package com.mx.profuturo.bolsa.model.service.candidates.dto;

public class GenericIdCandidateInDTO {
	
	private Integer idCandidato;
	private Integer idPublicacion;
	private Integer idVacante;
	private String idUsuario;


	public Integer getIdVacante() {
		return idVacante;
	}

	public void setIdVacante(Integer idVacante) {
		this.idVacante = idVacante;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

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
}
