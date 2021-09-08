package com.mx.profuturo.bolsa.service.desk;

import java.util.LinkedList;


import com.mx.profuturo.bolsa.model.desk.data.DatosGrafica;


public class RecruiterCorpDeskResponse {
	
	private KPIEscritorio entrevistasHoy;
	private KPIEscritorio nuevosPostulados;
	private KPIEscritorio vacantesAbiertas;
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
	public KPIEscritorio getVacantesAbiertas() {
		return vacantesAbiertas;
	}
	public void setVacantesAbiertas(KPIEscritorio vacantesAbiertas) {
		this.vacantesAbiertas = vacantesAbiertas;
	}
	public DatosGrafica getCandidatosEtapa() {
		return candidatosEtapa;
	}
	public void setCandidatosEtapa(DatosGrafica candidatosEtapa) {
		this.candidatosEtapa = candidatosEtapa;
	}
}
