package com.mx.profuturo.bolsa.model.reports.vo.data;

import com.mx.profuturo.bolsa.model.reports.response.AbstractFiltersResponse;

public class DatosFiltrosVO {
	
	protected String gerencia;	
	protected String regional;	
	protected String division;
	
	protected String areaInteres;
	protected String subAreaInteres;
	protected String centroCostos;
	public String getGerencia() {
		return gerencia;
	}
	public void setGerencia(String gerencia) {
		this.gerencia = gerencia;
	}
	public String getRegional() {
		return regional;
	}
	public void setRegional(String regional) {
		this.regional = regional;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public String getAreaInteres() {
		return areaInteres;
	}
	public void setAreaInteres(String areaInteres) {
		this.areaInteres = areaInteres;
	}
	public String getSubAreaInteres() {
		return subAreaInteres;
	}
	public void setSubAreaInteres(String subAreaInteres) {
		this.subAreaInteres = subAreaInteres;
	}
	public String getCentroCostos() {
		return centroCostos;
	}
	public void setCentroCostos(String centroCostos) {
		this.centroCostos = centroCostos;
	}
	
	
	public DatosFiltrosVO() {}
	
	public DatosFiltrosVO(DatosFiltrosVO vo) {
		this.areaInteres = vo.getAreaInteres();
		this.centroCostos= vo.getCentroCostos();
		this.division= vo.getDivision();
		this.gerencia= vo.getGerencia();
		this.regional= vo.getRegional();
		this.subAreaInteres= vo.getSubAreaInteres();
	}
	
	
	public DatosFiltrosVO(AbstractFiltersResponse e) {
		this.areaInteres = e.getAreaInteres();
		this.centroCostos= e.getCentroCostos();
		this.division= e.getDivision();
		this.gerencia= e.getGerencia();
		this.regional= e.getRegion();
		this.subAreaInteres= e.getSubAreaInteres();
	}
	
	

}
