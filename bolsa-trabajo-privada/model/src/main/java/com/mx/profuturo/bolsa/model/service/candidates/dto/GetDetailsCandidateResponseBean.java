package com.mx.profuturo.bolsa.model.service.candidates.dto;

import com.mx.profuturo.bolsa.model.vo.Candidato.CandidatoDetallesVO;

public class GetDetailsCandidateResponseBean {
	
	private boolean success;
	private CandidatoDetallesVO data;
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public CandidatoDetallesVO getData() {
		return data;
	}
	public void setData(CandidatoDetallesVO data) {
		this.data = data;
	}
	
	
	
}
