package com.mx.profuturo.bolsa.model.graphics.vo.elements;

import java.util.LinkedList;

public class StackedBarChartDataSet {
	
	private String label;
	private LinkedList<Integer> datasets = new LinkedList<>();
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public LinkedList<Integer> getDatasets() {
		return datasets;
	}
	public void setDatasets(LinkedList<Integer> datasets) {
		this.datasets = datasets;
	}
	
	
}
