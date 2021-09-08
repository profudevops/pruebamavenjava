package com.mx.profuturo.bolsa.model.graphics.vo;

import com.mx.profuturo.bolsa.model.graphics.vo.elements.StackedChartValue;


public class StackedChartWidgetVO extends WidgetBase {
	
	private String size;
	private String labelX;
	private String labelY;
	private StackedChartValue value;
	
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getLabelX() {
		return labelX;
	}
	public void setLabelX(String labelX) {
		this.labelX = labelX;
	}
	public String getLabelY() {
		return labelY;
	}
	public void setLabelY(String labelY) {
		this.labelY = labelY;
	}
	public StackedChartWidgetVO() {
		
		this.type = "chart";
		this.variant = "bar-chart-stacked";
		this.size = "lg";
		
	}
	public StackedChartValue getValue() {
		return value;
	}
	public void setValue(StackedChartValue value) {
		this.value = value;
	}
	
	
	
	
	
}
