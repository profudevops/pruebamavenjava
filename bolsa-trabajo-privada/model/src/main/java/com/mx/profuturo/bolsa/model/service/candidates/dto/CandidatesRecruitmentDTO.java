package com.mx.profuturo.bolsa.model.service.candidates.dto;

import java.util.List;

public class CandidatesRecruitmentDTO {

	private String idUsuario;
	private List<String> idColaboradores;
	private Boolean isAdmin;
	private Integer totalPagina;
	private Integer paginaActual;
	private Integer idRama;
	private Boolean isObserver;
	private String role;
	private String candidato;


	public String getCandidato() {
		return candidato;
	}

	public void setCandidato(String candidato) {
		this.candidato = candidato;
	}

	public Integer getTotalPagina() {
		return totalPagina;
	}
	public void setTotalPagina(Integer totalPagina) {
		this.totalPagina = totalPagina;
	}
	public Integer getPaginaActual() {
		return paginaActual;
	}
	public void setPaginaActual(Integer paginaActual) {
		this.paginaActual = paginaActual;
	}
	public List<String> getIdColaboradores() {
		return idColaboradores;
	}
	public void setIdColaboradores(List<String> idColaboradores) {
		this.idColaboradores = idColaboradores;
	}
	public Integer getIdRama() {
		return idRama;
	}
	public void setIdRama(Integer idRama) {
		this.idRama = idRama;
	}
	public Boolean getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	public String getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
	public Boolean getIsObserver() {
		return isObserver;
	}
	public void setIsObserver(Boolean isObserver) {
		this.isObserver = isObserver;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	

}
