package com.mx.profuturo.bolsa.model.recruitment.bean;

import java.util.LinkedHashMap;

public class PhaseBean {
	
	private String code;
	private String description;
	private Boolean cover = false;
	private Boolean complete = false;
	private Boolean open = false;
	private Integer completedStages = 0;
	private LinkedHashMap<String, StageBean> stages;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Boolean getCover() {
		return cover;
	}
	public void setCover(Boolean cover) {
		this.cover = cover;
	}
	public Boolean getComplete() {
		return complete;
	}
	public void setComplete(Boolean complete) {
		this.complete = complete;
	}
	public Boolean getOpen() {
		return open;
	}
	public void setOpen(Boolean open) {
		this.open = open;
	}
	public LinkedHashMap<String, StageBean> getStages() {
		return stages;
	}
	public void setStages(LinkedHashMap<String, StageBean> stages) {
		this.stages = stages;
	}
	public Integer getCompletedStages() {
		return completedStages;
	}
	public void setCompletedStages(Integer completedStages) {
		this.completedStages = completedStages;
	}


}
