package com.mx.profuturo.bolsa.model.recruitment.vo;

import com.mx.profuturo.bolsa.model.recruitment.dto.InterviewDTO;

public class InterviewVO extends InterviewDTO {
	
	private String nombreSede;
	private String nombreEntrevistador;
	public String getNombreSede() {
		return nombreSede;
	}
	public void setNombreSede(String nombreSede) {
		this.nombreSede = nombreSede;
	}
	public String getNombreEntrevistador() {
		return nombreEntrevistador;
	}
	public void setNombreEntrevistador(String nombreEntrevistador) {
		this.nombreEntrevistador = nombreEntrevistador;
	}
	
	
}