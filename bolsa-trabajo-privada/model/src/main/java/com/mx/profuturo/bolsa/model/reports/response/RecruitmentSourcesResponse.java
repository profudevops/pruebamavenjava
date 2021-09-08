package com.mx.profuturo.bolsa.model.reports.response;

public class RecruitmentSourcesResponse   extends AbstractFiltersResponse{

	private String fuente;	
	private String candidato;	
	private String estatus;
	
	public String getFuente() {
		return fuente;
	}
	public void setFuente(String fuente) {
		this.fuente = fuente;
	}
	public String getCandidato() {
		return candidato;
	}
	public void setCandidato(String candidato) {
		this.candidato = candidato;
	}
	public String getEstatus() {
		return estatus;
	}
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}	
}
