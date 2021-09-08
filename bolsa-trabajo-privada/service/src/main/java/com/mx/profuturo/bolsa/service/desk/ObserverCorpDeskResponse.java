package com.mx.profuturo.bolsa.service.desk;

import com.mx.profuturo.bolsa.model.desk.data.DatosGrafica;

public class ObserverCorpDeskResponse {
	
	private KPIEscritorio vacantesObservadas;
	private KPIEscritorio candidatosPrimerasEntrevistas;
	private KPIEscritorio entrevistasHoy;
	private DatosGrafica candidatosProceso;
	public KPIEscritorio getVacantesObservadas() {
		return vacantesObservadas;
	}
	public void setVacantesObservadas(KPIEscritorio vacantesObservadas) {
		this.vacantesObservadas = vacantesObservadas;
	}
	public KPIEscritorio getCandidatosPrimerasEntrevistas() {
		return candidatosPrimerasEntrevistas;
	}
	public void setCandidatosPrimerasEntrevistas(KPIEscritorio candidatosPrimerasEntrevistas) {
		this.candidatosPrimerasEntrevistas = candidatosPrimerasEntrevistas;
	}
	public KPIEscritorio getEntrevistasHoy() {
		return entrevistasHoy;
	}
	public void setEntrevistasHoy(KPIEscritorio entrevistasHoy) {
		this.entrevistasHoy = entrevistasHoy;
	}
	public DatosGrafica getCandidatosProceso() {
		return candidatosProceso;
	}
	public void setCandidatosProceso(DatosGrafica candidatosProceso) {
		this.candidatosProceso = candidatosProceso;
	}

	
}
