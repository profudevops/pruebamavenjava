package com.mx.profuturo.bolsa.model.reports.vo.data;

import com.mx.profuturo.bolsa.model.reports.response.HiringsResponse;

public class ContratacionesMesVO extends ContratacionesNivelPuestoVO {
	
	private String mes;

	public ContratacionesMesVO(HiringsResponse e) {
		super(e);
		this.mes = e.getMes();
	}
	
	public ContratacionesMesVO() {
		
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}
	
	
}
