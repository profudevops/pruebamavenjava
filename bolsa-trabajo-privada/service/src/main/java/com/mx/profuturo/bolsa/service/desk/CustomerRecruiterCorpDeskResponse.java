package com.mx.profuturo.bolsa.service.desk;

import com.mx.profuturo.bolsa.model.desk.data.DatosGrafica;

public class CustomerRecruiterCorpDeskResponse {

	private KPIEscritorio entrevistasHoy;
	private KPIEscritorio nuevosPostulados;
	private KPIEscritorio plazasAbiertas;
	private DatosGrafica candidatosEtapa;
	public KPIEscritorio getEntrevistasHoy() {
		return entrevistasHoy;
	}
	public void setEntrevistasHoy(KPIEscritorio entrevistasHoy) {
		this.entrevistasHoy = entrevistasHoy;
	}
	
	public KPIEscritorio getPlazasAbiertas() {
		return plazasAbiertas;
	}
	public void setPlazasAbiertas(KPIEscritorio plazasAbiertas) {
		this.plazasAbiertas = plazasAbiertas;
	}
	public DatosGrafica getCandidatosEtapa() {
		return candidatosEtapa;
	}
	public void setCandidatosEtapa(DatosGrafica candidatosEtapa) {
		this.candidatosEtapa = candidatosEtapa;
	}
	public KPIEscritorio getNuevosPostulados() {
		return nuevosPostulados;
	}
	public void setNuevosPostulados(KPIEscritorio nuevosPostulados) {
		this.nuevosPostulados = nuevosPostulados;
	}
	
	
	
	
	
}
