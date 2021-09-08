package com.mx.profuturo.bolsa.service.reports;

import java.util.LinkedList;

public class MonthAux {

private LinkedList<String> months = new LinkedList<>();
	
	
	
	{
		months.add("ENERO");
		months.add("FEBRERO");
		months.add("MARZO");
		months.add("ABRIL");
		months.add("MAYO");
		months.add("JUNIO");
		months.add("JULIO");
		months.add("AGOSTO");
		months.add("SEPTIEMBRE");
		months.add("OCTUBRE");
		months.add("NOVIEMBRE");
		months.add("DICIEMBRE");
	}



	public LinkedList<String> getMonths() {
		return months;
	}



	public void setMonths(LinkedList<String> months) {
		this.months = months;
	}
	
	
}
