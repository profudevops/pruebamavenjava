package com.mx.profuturo.bolsa.model.recruitment.bean;

public class StageBean {
	
	private String code;
	private String descripcion;
	private Integer status = 0; 
	private Boolean hasModes = false;
	private String currentMode;
	private String currentModeDate;
	private Boolean complete = false;
	private Integer idEtapa;
	private String signal;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getCurrentMode() {
		return currentMode;
	}
	public void setCurrentMode(String currentMode) {
		this.currentMode = currentMode;
	}
	public String getCurrentModeDate() {
		return currentModeDate;
	}
	public void setCurrentModeDate(String currentModeDate) {
		this.currentModeDate = currentModeDate;
	}
	public Boolean getHasModes() {
		return hasModes;
	}
	public void setHasModes(Boolean hasModes) {
		this.hasModes = hasModes;
	}
	public Boolean getComplete() {
		return complete;
	}
	public void setComplete(Boolean complete) {
		this.complete = complete;
	}
	public Integer getIdEtapa() {
		return idEtapa;
	}
	public void setIdEtapa(Integer idEtapa) {
		this.idEtapa = idEtapa;
	}
	public String getSignal() {
		return signal;
	}
	public void setSignal(String signal) {
		this.signal = signal;
	}	
}
