package com.mx.profuturo.bolsa.model.reports.response;

public class InterviewTypeResponse  extends AbstractFiltersResponse{

	private String tipoEntrevista;	
	private String tipoPuesto;	
	private String candidato;	
	private String estatusReclutamiento;	
	private String analista;
	public String getTipoEntrevista() {
		return tipoEntrevista;
	}
	public void setTipoEntrevista(String tipoEntrevista) {
		this.tipoEntrevista = tipoEntrevista;
	}
	public String getTipoPuesto() {
		return tipoPuesto;
	}
	public void setTipoPuesto(String tipoPuesto) {
		this.tipoPuesto = tipoPuesto;
	}
	public String getCandidato() {
		return candidato;
	}
	public void setCandidato(String candidato) {
		this.candidato = candidato;
	}

	public String getAnalista() {
		return analista;
	}
	public void setAnalista(String analista) {
		this.analista = analista;
	}
	public String getEstatusReclutamiento() {
		return estatusReclutamiento;
	}
	public void setEstatusReclutamiento(String estatusReclutamiento) {
		this.estatusReclutamiento = estatusReclutamiento;
	}
	
	
	
	
}
