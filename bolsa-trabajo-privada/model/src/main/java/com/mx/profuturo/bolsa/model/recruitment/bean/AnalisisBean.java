package com.mx.profuturo.bolsa.model.recruitment.bean;

import java.util.LinkedHashMap;
import java.util.LinkedList;

import com.mx.profuturo.bolsa.model.recruitment.response.RegistroProceso;
import com.mx.profuturo.bolsa.model.recruitment.vo.RecruitmentStageVO;

public class AnalisisBean {

	private Boolean esReachazo;
	private Boolean esRepostulacion;
	private String faseMayorRango;
	private LinkedHashMap<String,LinkedList<RegistroProceso>> registrosPorFase = new LinkedHashMap<>();
	private LinkedHashMap<String,LinkedList<RecruitmentStageVO>> etapasProceso = new LinkedHashMap<>();
	private Object infoFases;
	public Boolean getEsReachazo() {
		return esReachazo;
	}
	public void setEsReachazo(Boolean esReachazo) {
		this.esReachazo = esReachazo;
	}
	public Boolean getEsRepostulacion() {
		return esRepostulacion;
	}
	public void setEsRepostulacion(Boolean esRepostulacion) {
		this.esRepostulacion = esRepostulacion;
	}
	public String getFaseMayorRango() {
		return faseMayorRango;
	}
	public void setFaseMayorRango(String faseMayorRango) {
		this.faseMayorRango = faseMayorRango;
	}

	public Object getInfoFases() {
		return infoFases;
	}
	public void setInfoFases(Object infoFases) {
		this.infoFases = infoFases;
	}

	public LinkedHashMap<String,LinkedList<RecruitmentStageVO>> getEtapasProceso() {
		return etapasProceso;
	}
	public void setEtapasProceso(LinkedHashMap<String,LinkedList<RecruitmentStageVO>> etapasProceso) {
		this.etapasProceso = etapasProceso;
	}
	public LinkedHashMap<String, LinkedList<RegistroProceso>> getRegistrosPorFase() {
		return registrosPorFase;
	}
	public void setRegistrosPorFase(LinkedHashMap<String, LinkedList<RegistroProceso>> registrosPorFase) {
		this.registrosPorFase = registrosPorFase;
	}
	

	
	
}
