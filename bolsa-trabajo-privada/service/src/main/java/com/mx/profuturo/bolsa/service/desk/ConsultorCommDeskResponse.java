package com.mx.profuturo.bolsa.service.desk;

import com.mx.profuturo.bolsa.model.desk.data.DatosCompuestosGrafica;

public class ConsultorCommDeskResponse {

	private KPIEscritorio candidatosPostulados;
	private KPIEscritorio candidatosProceso;
	private KPIEscritorio plazasAbiertas;
	private DatosCompuestosGrafica etapasReclutamiento;
	public KPIEscritorio getCandidatosPostulados() {
		return candidatosPostulados;
	}
	public void setCandidatosPostulados(KPIEscritorio candidatosPostulados) {
		this.candidatosPostulados = candidatosPostulados;
	}
	public KPIEscritorio getCandidatosProceso() {
		return candidatosProceso;
	}
	public void setCandidatosProceso(KPIEscritorio candidatosProceso) {
		this.candidatosProceso = candidatosProceso;
	}
	public KPIEscritorio getPlazasAbiertas() {
		return plazasAbiertas;
	}
	public void setPlazasAbiertas(KPIEscritorio plazasAbiertas) {
		this.plazasAbiertas = plazasAbiertas;
	}
	public DatosCompuestosGrafica getEtapasReclutamiento() {
		return etapasReclutamiento;
	}
	public void setEtapasReclutamiento(DatosCompuestosGrafica etapasReclutamiento) {
		this.etapasReclutamiento = etapasReclutamiento;
	}
	
	
}
