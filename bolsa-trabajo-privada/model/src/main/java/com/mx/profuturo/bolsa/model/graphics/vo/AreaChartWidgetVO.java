package com.mx.profuturo.bolsa.model.graphics.vo;

public class AreaChartWidgetVO extends DonutChartWidgetVO{

	private AreaData data = new AreaData();
	
	
	public AreaData getData() {
		return data;
	}

	public void setData(AreaData data) {
		this.data = data;
	}
	
	
	public AreaChartWidgetVO() {

		super.variant = "line-chart";
		super.type = "chart";
	}
		
	
}
