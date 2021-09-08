package com.mx.profuturo.bolsa.model.graphics.vo.elements;

import java.util.LinkedList;

public class StackedPairedArrayValue {

	private LinkedList<String> labels;
	private LinkedList<Object> data;
	public StackedPairedArrayValue() {
	
		this.labels =  new LinkedList();
		this.data = new LinkedList();
	}
	public LinkedList<String> getLabels() {
		return labels;
	}
	public void setLabels(LinkedList<String> labels) {
		this.labels = labels;
	}
	public LinkedList<Object> getData() {
		return data;
	}
	public void setData(LinkedList<Object> data) {
		this.data = data;
	}
	
	
}
