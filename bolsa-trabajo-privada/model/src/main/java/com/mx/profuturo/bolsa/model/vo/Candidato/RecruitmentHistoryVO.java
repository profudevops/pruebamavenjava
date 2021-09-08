package com.mx.profuturo.bolsa.model.vo.Candidato;

import java.util.LinkedList;

public class RecruitmentHistoryVO {
	
	private LinkedList<RecruitmentEventVO> actuales = new LinkedList<>();
	private LinkedList<RecruitmentEventVO> anteriores = new LinkedList<>();
	
	public LinkedList<RecruitmentEventVO> getActuales() {
		return actuales;
	}
	public void setActuales(LinkedList<RecruitmentEventVO> actuales) {
		this.actuales = actuales;
	}
	public LinkedList<RecruitmentEventVO> getAnteriores() {
		return anteriores;
	}
	public void setAnteriores(LinkedList<RecruitmentEventVO> anteriores) {
		this.anteriores = anteriores;
	}
	
	

}
