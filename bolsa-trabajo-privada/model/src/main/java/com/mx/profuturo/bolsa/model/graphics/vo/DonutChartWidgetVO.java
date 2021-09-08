package com.mx.profuturo.bolsa.model.graphics.vo;

import com.mx.profuturo.bolsa.model.graphics.vo.elements.PairedArrayValue;

public class DonutChartWidgetVO extends WidgetBase {
	protected String size;
	protected String variant = new String();
	protected PairedArrayValue value = new PairedArrayValue();
	
	public DonutChartWidgetVO(){
		
		this.variant = "donut-chart";
		super.type = "chart";
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

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}
	
	


}
