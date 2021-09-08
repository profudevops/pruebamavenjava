package com.mx.profuturo.bolsa.model.reports.vo.data;

import com.mx.profuturo.bolsa.model.reports.response.HiringsResponse;

public class ContratacionesAnalistaVO extends ContratacionesMesVO {
	
	
	private String analista;

	public ContratacionesAnalistaVO(HiringsResponse e) {
		// TODO Auto-generated constructor stub
		super(e);
		this.analista = e.getAnalista();
	}
	
	public ContratacionesAnalistaVO() {
		// TODO Auto-generated constructor stub
	}

	public String getAnalista() {
		return analista;
	}

	public void setAnalista(String analista) {
		this.analista = analista;
	}
	
	

}
