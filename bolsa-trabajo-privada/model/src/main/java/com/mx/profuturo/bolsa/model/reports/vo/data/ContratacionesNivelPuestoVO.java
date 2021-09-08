package com.mx.profuturo.bolsa.model.reports.vo.data;

import com.mx.profuturo.bolsa.model.reports.response.HiringsResponse;

public class ContratacionesNivelPuestoVO extends DatosFiltrosVO {

	protected String tituloVacante;	
	protected String fecha; 
	protected String contratacion;	
	private String nivelPuesto;	
	protected String analista;


	public ContratacionesNivelPuestoVO(HiringsResponse e) {
		super(e);
		this.tituloVacante = e.getTituloVacante();
		this.fecha = e.getFecha();
		this.contratacion = e.getContratacion();
		this.nivelPuesto = e.getNivelPuesto();
		this.analista = e.getAnalista();
	}
	
	
	public ContratacionesNivelPuestoVO() {
		// TODO Auto-generated constructor stub
	
	}
	

	public String getNivelPuesto() {
		return nivelPuesto;
	}

	public void setNivelPuesto(String nivelPuesto) {
		this.nivelPuesto = nivelPuesto;
	}


	public String getTituloVacante() {
		return tituloVacante;
	}


	public void setTituloVacante(String tituloVacante) {
		this.tituloVacante = tituloVacante;
	}


	public String getFecha() {
		return fecha;
	}


	public void setFecha(String fecha) {
		this.fecha = fecha;
	}


	public String getAnalista() {
		return analista;
	}


	public void setAnalista(String analista) {
		this.analista = analista;
	}


	public String getContratacion() {
		return contratacion;
	}


	public void setContratacion(String contratacion) {
		this.contratacion = contratacion;
	}
	
	
	
}
