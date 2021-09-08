package com.mx.profuturo.bolsa.model.reports.vo.data;

import com.mx.profuturo.bolsa.model.reports.response.HiringsResponse;
import com.mx.profuturo.bolsa.model.reports.response.RecruitmentSourcesResponse;

public class FuentesReclutamientoVO extends DatosFiltrosVO {

	private String fecha;
	private String status;
	private String fuente;
	private String candidato;	

	
	public FuentesReclutamientoVO() {
		// TODO Auto-generated constructor stub
	}
	public FuentesReclutamientoVO(RecruitmentSourcesResponse e) {
		super(e);
		this.status = e.getEstatus();
		this.fuente = e.getFuente();
		this.candidato = e.getCandidato();
		this.fecha = e.getFecha();
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getFuente() {
		return fuente;
	}
	public void setFuente(String fuente) {
		this.fuente = fuente;
	}
	public String getCandidato() {
		return candidato;
	}
	public void setCandidato(String candidato) {
		this.candidato = candidato;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	
	
	
}
