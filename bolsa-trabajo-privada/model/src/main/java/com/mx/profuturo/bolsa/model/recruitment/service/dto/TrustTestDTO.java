package com.mx.profuturo.bolsa.model.recruitment.service.dto;

import com.mx.profuturo.bolsa.model.recruitment.response.RegistroProceso;

public class TrustTestDTO extends RegistroProceso {

	private String resultado;
	
	public TrustTestDTO(RegistroProceso record) {
		super(record);
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

}
