package com.mx.profuturo.bolsa.model.graphics.vo.elements;

import java.util.LinkedList;

public class StackedChartValue {

	LinkedList<String> labels = new LinkedList<String>();
	LinkedList<StackedDataset> datasets = new LinkedList<StackedDataset>() ;
	public LinkedList<String> getLabels() {
		return labels;
	}
	public void setLabels(LinkedList<String> labels) {
		this.labels = labels;
	}
	public LinkedList<StackedDataset> getDatasets() {
		return datasets;
	}
	public void setDatasets(LinkedList<StackedDataset> datasets) {
		this.datasets = datasets;
	}
		
		
}
