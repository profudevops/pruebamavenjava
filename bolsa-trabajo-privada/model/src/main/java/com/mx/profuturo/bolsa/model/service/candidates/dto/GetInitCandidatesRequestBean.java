package com.mx.profuturo.bolsa.model.service.candidates.dto;

public class GetInitCandidatesRequestBean {
	
	private String userId;
	private int paginaActual;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getPaginaActual() {
		return paginaActual;
	}

	public void setPaginaActual(int paginaActual) {
		this.paginaActual = paginaActual;
	}
}
