package com.mx.profuturo.bolsa.model.graphics.adapters;

import java.util.LinkedList;

public class AreaChartAdapter extends DonutChartAdapter {

	private LinkedList<String> labelsOrder = new LinkedList<>();
	private String axisX;
	private String axisY;

	public LinkedList<String> getLabelsOrder() {
		return labelsOrder;
	}

	public void setLabelsOrder(LinkedList<String> labelsOrder) {
		this.labelsOrder = labelsOrder;
	}

	public String getAxisX() {
		return axisX;
	}

	public void setAxisX(String axisX) {
		this.axisX = axisX;
	}

	public String getAxisY() {
		return axisY;
	}

	public void setAxisY(String axisY) {
		this.axisY = axisY;
	}
	
	
	
	
}
