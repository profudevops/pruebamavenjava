package com.mx.profuturo.bolsa.model.graphics.vo;

import java.util.LinkedList;

public class AreaData {

	private LinkedList<String> labels = new LinkedList<String>();
	private LinkedList<AreaDataSet> datasets = new LinkedList<AreaDataSet>();
	
	
	public LinkedList<String> getLabels() {
		return labels;
	}
	public void setLabels(LinkedList<String> labels) {
		this.labels = labels;
	}
	public LinkedList<AreaDataSet> getDatasets() {
		return datasets;
	}
	public void setDatasets(LinkedList<AreaDataSet> datasets) {
		this.datasets = datasets;
	}
	
	
}
