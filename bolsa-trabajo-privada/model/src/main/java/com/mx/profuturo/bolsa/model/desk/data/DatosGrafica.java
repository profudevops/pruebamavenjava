package com.mx.profuturo.bolsa.model.desk.data;

import java.util.LinkedList;

public class DatosGrafica extends DatosBasicosGrafica {

	protected LinkedList<String> datos;
	public LinkedList<String> getDatos() {
		return datos;
	}
	public void setDatos(LinkedList<String> datos) {
		this.datos = datos;
	}
	
}
