package com.mx.profuturo.bolsa.model.graphics.vo.elements;

import java.util.LinkedList;

public class PairedArrayValue {
	
	private LinkedList<String> labels;
	private LinkedList<Object> datasets;
	public PairedArrayValue() {
	
		this.labels =  new LinkedList();
		this.datasets = new LinkedList();
	}
	public LinkedList getLabels() {
		return labels;
	}
	public void setLabels(LinkedList labels) {
		this.labels = labels;
	}
	public LinkedList getDatasets() {
		return datasets;
	}
	public void setDatasets(LinkedList datasets) {
		this.datasets = datasets;
	}
	
	
	public void addPair(String label, Integer data) {
		this.labels.add(label);
		this.datasets.add(data);
	}
	

}
