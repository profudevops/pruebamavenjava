package com.mx.profuturo.bolsa.model.graphics.vo;

public class SingleKPIWidgetVO extends WidgetBase{
	
	private Integer value;
	private String status;
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public SingleKPIWidgetVO (){
		
		super.type = "Widget";
		
	}	
	
}
