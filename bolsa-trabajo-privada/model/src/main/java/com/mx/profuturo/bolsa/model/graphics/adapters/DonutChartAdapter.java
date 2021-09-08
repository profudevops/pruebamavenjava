package com.mx.profuturo.bolsa.model.graphics.adapters;

import java.util.LinkedHashMap;


public class DonutChartAdapter {
	
	protected String title;
	protected LinkedHashMap<String, Integer> parts = new LinkedHashMap<>();
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public LinkedHashMap<String, Integer> getParts() {
		return parts;
	}
	public void setParts(LinkedHashMap<String, Integer> parts) {
		this.parts = parts;
	}
	public void compute(String m) {
		
		int i = 0;
		
		if(null == this.parts.get(m)) {
			this.parts.put(m, 0);
		}
		
		i = this.parts.get(m);
		this.parts.put(m, ++i);
		
	}
		
}
