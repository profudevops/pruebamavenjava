package com.mx.profuturo.bolsa.model.reports.vo.data;

import com.mx.profuturo.bolsa.model.reports.response.FinalistHiredResponse;


public class FinalistasContratadosVO extends MotivosRechazoVO {
	
	private String status;
	
	public FinalistasContratadosVO(FinalistHiredResponse e) {
		
		super(e);
		this.status = e.getEstatus();
		
	}
	
	public FinalistasContratadosVO() {
		// TODO Auto-generated constructor stub
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
