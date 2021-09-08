package com.mx.profuturo.bolsa.model.recruitment.service.dto;

import com.mx.profuturo.bolsa.model.recruitment.response.RegistroProceso;

public class FolioDTO extends RegistroProceso {
	
	protected Long folio;

	public FolioDTO() {
		// TODO Auto-generated constructor stub
	}

	
	public FolioDTO(RegistroProceso record) {
		super(record);
	}

	public Long getFolio() {
		return folio;
	}

	public void setFolio(Long folio) {
		this.folio = folio;
	}
	
	

}
