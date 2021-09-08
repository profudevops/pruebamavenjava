package com.mx.profuturo.bolsa.model.recruitment.bean;

public class StageModePairBean {

	private String code;
	private String mode;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	
	public StageModePairBean(){
		
	}
	
	public StageModePairBean(String code){
		this.code = code;
	}
	
	public StageModePairBean(String code, String mode){
		this.code = code;
		this.mode = mode;
	}
	public boolean compare(String codigo, String modo) {
		
		return (this.code.equals(codigo)&&this.mode.equals(modo));
	}
	
}
