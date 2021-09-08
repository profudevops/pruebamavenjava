package com.mx.profuturo.bolsa.model.graphics.vo;

import com.mx.profuturo.bolsa.model.graphics.vo.elements.PairedArrayValue;

public class BarChartWidgetVO extends WidgetBase {
	
	private String variant = new String();
	private PairedArrayValue value = new PairedArrayValue();
	private String size;
	private String labelX;
	private String labelY;
	
	public BarChartWidgetVO(){
		
		this.variant = "bar-chart";
		super.type = "chart";
		this.size = "lg";
		this.value = new PairedArrayValue();
	}

	public String getVariant() {
		return variant;
	}

	public void setVariant(String variant) {
		this.variant = variant;
	}

	public PairedArrayValue getValue() {
		return value;
	}

	public void setValue(PairedArrayValue value) {
		this.value = value;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public void setLabelX(String labelX) {
		this.labelX = labelX;
	}

	public void setLabelY(String labelY) {
		this.labelY = labelY;
	}

	public String getSize() {
		return size;
	}

	public String getLabelX() {
		return labelX;
	}

	public String getLabelY() {
		return labelY;
	}
	
	
	


}
