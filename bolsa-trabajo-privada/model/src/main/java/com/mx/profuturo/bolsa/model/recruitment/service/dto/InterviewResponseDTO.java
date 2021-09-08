package com.mx.profuturo.bolsa.model.recruitment.service.dto;

import com.mx.profuturo.bolsa.model.recruitment.response.RegistroProceso;

public class InterviewResponseDTO extends RegistroProceso {
	
	

	public InterviewResponseDTO(RegistroProceso record) {
		super(record);
	}

	public InterviewResponseDTO() {
		// TODO Auto-generated constructor stub
	}



	public Boolean getAprobado() {
		return aprobado;
	}

	public void setAprobado(Boolean aprobado) {
		this.aprobado = aprobado;
	}
	
}
