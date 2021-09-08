package com.mx.profuturo.bolsa.model.reports.response;


/*
 * Motivos de rechazo
 * Motivos de rechazo por etapa
 * Etapas de abandono
 * candidatos finalistas y contratados
 */

public class RejectReasonsResponse  extends AbstractFiltersResponse{
	private String fecha;	
	private String candidato;	
	private String motivo;	
	private String etapa;	
	private String analista;	
	private String observador;
	
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getCandidato() {
		return candidato;
	}
	public void setCandidato(String candidato) {
		this.candidato = candidato;
	}
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	public String getEtapa() {
		return etapa;
	}
	public void setEtapa(String etapa) {
		this.etapa = etapa;
	}
	public String getAnalista() {
		return analista;
	}
	public void setAnalista(String analista) {
		this.analista = analista;
	}
	public String getObservador() {
		return observador;
	}
	public void setObservador(String observador) {
		this.observador = observador;
	}	
	
	
}
