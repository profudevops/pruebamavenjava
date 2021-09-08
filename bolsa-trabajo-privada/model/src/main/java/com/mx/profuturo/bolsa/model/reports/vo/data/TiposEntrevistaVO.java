package com.mx.profuturo.bolsa.model.reports.vo.data;

import com.mx.profuturo.bolsa.model.reports.response.InterviewTypeResponse;

public class TiposEntrevistaVO extends DatosFiltrosVO{
	
	private String status;
	private String tipo;
	private String fecha;
	

	public TiposEntrevistaVO(InterviewTypeResponse e) {
		// TODO Auto-generated constructor stub
		super(e);
		this.status = e.getEstatusReclutamiento();
		this.tipo = e.getTipoEntrevista();
		this.fecha = e.getFecha();
		
	}
	
	public TiposEntrevistaVO() {
		// TODO Auto-generated constructor stub
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	
	
}
