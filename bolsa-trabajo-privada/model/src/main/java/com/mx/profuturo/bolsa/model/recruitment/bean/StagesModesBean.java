package com.mx.profuturo.bolsa.model.recruitment.bean;

import java.util.LinkedHashMap;

public class StagesModesBean {
	
	private String code;
	
	private LinkedHashMap<String, String> stages;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public LinkedHashMap<String, String> getStages() {
		return stages;
	}

	public void setStages(LinkedHashMap<String, String> stages) {
		this.stages = stages;
	}
	

}
