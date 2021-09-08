package com.mx.profuturo.bolsa.service.desk;

import com.mx.profuturo.bolsa.model.desk.data.DatosCompuestosGrafica;
import com.mx.profuturo.bolsa.model.desk.data.DatosGrafica;

public class AdministratorCommDeskResponse {

	private KPIEscritorio candidatosPostulados;
	private KPIEscritorio candidatosProceso;
	private KPIEscritorio vacantesAbiertas;
	private KPIEscritorio plazasAbiertas;
	private DatosGrafica candidatosProcesoGrafica;
	private DatosGrafica postulacionesDia;
	
	
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
	public DatosGrafica getPostulacionesDia() {
		return postulacionesDia;
	}
	public void setPostulacionesDia(DatosGrafica postulacionesDia) {
		this.postulacionesDia = postulacionesDia;
	}
	
}
