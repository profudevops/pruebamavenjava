package com.mx.profuturo.bolsa.service.desk;

import com.mx.profuturo.bolsa.model.desk.data.DatosGrafica;

public class AnalystCommDeskResponse {
	
	private KPIEscritorio entrevistasHoy;
	private KPIEscritorio nuevosPostulados;
	private KPIEscritorio plazasAbiertas;
	private KPIEscritorio misEntrevistasHoy;
	private KPIEscritorio entrevistasClientesHoy;
	private DatosGrafica candidatosEtapa;
	public KPIEscritorio getEntrevistasHoy() {
		return entrevistasHoy;
	}
	public void setEntrevistasHoy(KPIEscritorio entrevistasHoy) {
		this.entrevistasHoy = entrevistasHoy;
	}
	public KPIEscritorio getNuevosPostulados() {
		return nuevosPostulados;
	}
	public void setNuevosPostulados(KPIEscritorio nuevosPostulados) {
		this.nuevosPostulados = nuevosPostulados;
	}
	public KPIEscritorio getPlazasAbiertas() {
		return plazasAbiertas;
	}
	public void setPlazasAbiertas(KPIEscritorio plazasAbiertas) {
		this.plazasAbiertas = plazasAbiertas;
	}
	public KPIEscritorio getMisEntrevistasHoy() {
		return misEntrevistasHoy;
	}
	public void setMisEntrevistasHoy(KPIEscritorio misEntrevistasHoy) {
		this.misEntrevistasHoy = misEntrevistasHoy;
	}
	public KPIEscritorio getEntrevistasClientesHoy() {
		return entrevistasClientesHoy;
	}
	public void setEntrevistasClientesHoy(KPIEscritorio entrevistasClientesHoy) {
		this.entrevistasClientesHoy = entrevistasClientesHoy;
	}
	public DatosGrafica getCandidatosEtapa() {
		return candidatosEtapa;
	}
	public void setCandidatosEtapa(DatosGrafica candidatosEtapa) {
		this.candidatosEtapa = candidatosEtapa;
	}

}
