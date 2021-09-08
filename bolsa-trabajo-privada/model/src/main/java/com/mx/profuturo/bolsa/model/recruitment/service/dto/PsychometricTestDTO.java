package com.mx.profuturo.bolsa.model.recruitment.service.dto;

import com.mx.profuturo.bolsa.model.recruitment.response.RegistroProceso;

public class PsychometricTestDTO extends RegistroProceso {

	private String d;
	private String i;
	private String s;
	private String c;
	
	private Integer resultado;
	
	public PsychometricTestDTO(RegistroProceso record) {
		super(record);
	}
	
	public PsychometricTestDTO() {
		// TODO Auto-generated constructor stub
	}

	public String getD() {
		return d;
	}

	public void setD(String d) {
		this.d = d;
	}

	public String getI() {
		return i;
	}

	public void setI(String i) {
		this.i = i;
	}

	public String getS() {
		return s;
	}

	public void setS(String s) {
		this.s = s;
	}

	public String getC() {
		return c;
	}

	public void setC(String c) {
		this.c = c;
	}

	public Integer getResultado() {
		return resultado;
	}

	public void setResultado(Integer resultado) {
		this.resultado = resultado;
	}
	
	
	

}
