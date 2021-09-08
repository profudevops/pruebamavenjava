package com.mx.profuturo.bolsa.model.recruitment.response;

import java.util.LinkedList;

public class ProcesoReclutamientoResponseBean {
	
	private DatosBasicos datosBasicos = new DatosBasicos();
	private Estrategia estrategia = new Estrategia();
	private LinkedList<RegistroProceso> registros = new  LinkedList<>();
	
	public DatosBasicos getDatosBasicos() {
		return datosBasicos;
	}
	public void setDatosBasicos(DatosBasicos datosBasicos) {
		this.datosBasicos = datosBasicos;
	}
	public Estrategia getEstrategia() {
		return estrategia;
	}
	public void setEstrategia(Estrategia estrategia) {
		this.estrategia = estrategia;
	}
	public LinkedList<RegistroProceso> getRegistros() {
		return registros;
	}
	public void setRegistros(LinkedList<RegistroProceso> registros) {
		this.registros = registros;
	}
	
	
}
