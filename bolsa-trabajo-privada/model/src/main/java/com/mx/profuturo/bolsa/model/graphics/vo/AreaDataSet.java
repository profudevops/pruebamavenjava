package com.mx.profuturo.bolsa.model.graphics.vo;

import java.util.LinkedList;

public class AreaDataSet {
	
	private LinkedList<Integer> data = new LinkedList<Integer>();
    private String label;
    private Boolean fill;
    
	public LinkedList<Integer> getData() {
		return data;
	}
	public void setData(LinkedList<Integer> data) {
		this.data = data;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public Boolean getFill() {
		return fill;
	}
	public void setFill(Boolean fill) {
		this.fill = fill;
	}
    
    

}
