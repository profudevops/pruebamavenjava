package com.mx.profuturo.bolsa.model.service.hiringform.dto;

public class GuardarEtapaFormularioDTO {
	
	private int etapa;
	private Integer idProceso;
	private FormularioEtapa1DTO etapa1;
	private FormularioEtapa2DTO etapa2;
	private FormularioEtapa3DTO etapa3;
	private FormularioEtapa4DTO etapa4;


	public void setIdProceso(Integer idProceso) {
		this.idProceso = idProceso;
	}

	public int getIdProceso() {
		return idProceso;
	}

	public int getEtapa() {
		return etapa;
	}

	public void setEtapa(int etapa) {
		this.etapa = etapa;
	}

	public FormularioEtapa1DTO getEtapa1() {
		return etapa1;
	}

	public void setEtapa1(FormularioEtapa1DTO etapa1) {
		this.etapa1 = etapa1;
	}

	public FormularioEtapa2DTO getEtapa2() {
		return etapa2;
	}

	public void setEtapa2(FormularioEtapa2DTO etapa2) {
		this.etapa2 = etapa2;
	}

	public FormularioEtapa3DTO getEtapa3() {
		return etapa3;
	}

	public void setEtapa3(FormularioEtapa3DTO etapa3) {
		this.etapa3 = etapa3;
	}

	public FormularioEtapa4DTO getEtapa4() {
		return etapa4;
	}

	public void setEtapa4(FormularioEtapa4DTO etapa4) {
		this.etapa4 = etapa4;
	}
}
