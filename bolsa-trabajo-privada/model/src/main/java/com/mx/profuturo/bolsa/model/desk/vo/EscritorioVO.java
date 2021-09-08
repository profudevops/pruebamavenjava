package com.mx.profuturo.bolsa.model.desk.vo;

import java.util.ArrayList;
import java.util.LinkedList;

public class EscritorioVO {
	
	private String codigo = "";
	private LinkedList<Object> widgets = new LinkedList();
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public LinkedList<Object> getWidgets() {
		return widgets;
	}
	public void setWidgets(LinkedList<Object> widgets) {
		this.widgets = widgets;
	}
	
	

}
