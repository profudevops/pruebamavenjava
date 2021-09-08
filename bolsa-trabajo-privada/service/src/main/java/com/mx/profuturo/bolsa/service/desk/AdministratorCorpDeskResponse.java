package com.mx.profuturo.bolsa.service.desk;

import com.mx.profuturo.bolsa.model.desk.data.DatosCompuestosGrafica;
import com.mx.profuturo.bolsa.model.desk.data.DatosGrafica;

public class AdministratorCorpDeskResponse {

	private KPIEscritorio candidatosPostulados;
	private KPIEscritorio candidatosProceso;
	private KPIEscritorio vacantesAbiertas;
	private KPIEscritorio plazasAbiertas;
	private DatosGrafica candidatosProcesoGrafica;
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
	public KPIEscritorio getVacantesAbiertas() {
		return vacantesAbiertas;
	}
	public void setVacantesAbiertas(KPIEscritorio vacantesAbiertas) {
		this.vacantesAbiertas = vacantesAbiertas;
	}
	public KPIEscritorio getPlazasAbiertas() {
		return plazasAbiertas;
	}
	public void setPlazasAbiertas(KPIEscritorio plazasAbiertas) {
		this.plazasAbiertas = plazasAbiertas;
	}
	public DatosGrafica getCandidatosProcesoGrafica() {
		return candidatosProcesoGrafica;
	}
	public void setCandidatosProcesoGrafica(DatosGrafica candidatosProcesoGrafica) {
		this.candidatosProcesoGrafica = candidatosProcesoGrafica;
	}
	public DatosCompuestosGrafica getEtapasReclutamiento() {
		return etapasReclutamiento;
	}
	public void setEtapasReclutamiento(DatosCompuestosGrafica etapasReclutamiento) {
		this.etapasReclutamiento = etapasReclutamiento;
	}
}
