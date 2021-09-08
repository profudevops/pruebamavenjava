package com.mx.profuturo.bolsa.model.reports.vo.data;

import com.mx.profuturo.bolsa.model.reports.response.RejectReasonsResponse;

public class MotivosRechazoVO extends DatosFiltrosVO {

	protected String fecha;	
	protected String candidato;
	protected String motivo;	
	protected String etapa;
	protected String analista;	
	protected String  observador;
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

	public MotivosRechazoVO() {}
	public MotivosRechazoVO(DatosFiltrosVO vo) {
		super(vo);
	}
	public MotivosRechazoVO(RejectReasonsResponse e) {
		super(e);
		this.fecha = e.getFecha();
		this.candidato = e.getCandidato();
		this.motivo = e.getMotivo();
		this.etapa = e.getEtapa();
		this.analista = e.getAnalista();
		this.observador = e.getObservador();// this.getObservador();
		
	}
	
}
