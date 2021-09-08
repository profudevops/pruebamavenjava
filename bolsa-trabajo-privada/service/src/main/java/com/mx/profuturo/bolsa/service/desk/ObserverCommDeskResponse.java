package com.mx.profuturo.bolsa.service.desk;

import com.mx.profuturo.bolsa.model.desk.data.DatosGrafica;

public class ObserverCommDeskResponse {
	
	
	private KPIEscritorio entrevistasHoy;
	private KPIEscritorio candidatosProceso;
	private DatosGrafica candidatosEtapa;
	
	
	public KPIEscritorio getEntrevistasHoy() {
		return entrevistasHoy;
	}
	public void setEntrevistasHoy(KPIEscritorio entrevistasHoy) {
		this.entrevistasHoy = entrevistasHoy;
	}

	public DatosGrafica getCandidatosEtapa() {
		return candidatosEtapa;
	}
	public void setCandidatosEtapa(DatosGrafica candidatosEtapa) {
		this.candidatosEtapa = candidatosEtapa;
	}
	public KPIEscritorio getCandidatosProceso() {
		return candidatosProceso;
	}
	public void setCandidatosProceso(KPIEscritorio candidatosProceso) {
		this.candidatosProceso = candidatosProceso;
	}
	
	

}
