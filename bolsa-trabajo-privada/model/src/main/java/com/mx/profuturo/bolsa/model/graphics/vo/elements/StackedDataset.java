package com.mx.profuturo.bolsa.model.graphics.vo.elements;

import java.util.LinkedList;

public class StackedDataset {

	private String label;
	private LinkedList<Integer> data;
	public StackedDataset() {
		this.data = new LinkedList<>();
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public LinkedList<Integer> getData() {
		return data;
	}
	public void setData(LinkedList<Integer> datasets) {
		this.data = datasets;
	}
	
	
}
