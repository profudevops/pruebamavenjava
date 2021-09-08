package com.mx.profuturo.bolsa.model.recruitment.dto.form;

import java.util.LinkedList;

public class SEESGStageForm {

	private LinkedList<InterviewResume> entrevistas = new LinkedList<>();

	public LinkedList<InterviewResume> getEntrevistas() {
		return entrevistas;
	}

	public void setEntrevistas(LinkedList<InterviewResume> entrevistas) {
		this.entrevistas = entrevistas;
	}
}
