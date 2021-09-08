package com.mx.profuturo.bolsa.model.recruitment.dto.form;

public class InterviewResume {
	private Integer idEntrevista;
	private String detalle;
	
	public Integer getIdEntrevista() {
		return idEntrevista;
	}
	public void setIdEntrevista(Integer idEntrevista) {
		this.idEntrevista = idEntrevista;
	}
	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	
	public InterviewResume() {
		
	}
	
	public InterviewResume(Integer idEntrevista, String detalle) {
		
		this.idEntrevista = idEntrevista;
		this.detalle = detalle;
	}
		
	
	
}
