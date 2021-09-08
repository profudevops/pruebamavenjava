package com.mx.profuturo.bolsa.model.reports.response;

public abstract class AbstractFiltersResponse {
	
	protected String rama;

	protected String fecha;
	
	protected String idAreaInteres;
	protected String areaInteres;
	protected String centroCostos;
	protected String subAreaInteres;
	
	
	protected String idRegion;
	protected String idDivision;
	protected String idGerencia;
	
	protected String region;
	protected String division;
	protected String gerencia;
	
	
	public String getRama() {
		return rama;
	}
	public void setRama(String rama) {
		this.rama = rama;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getIdAreaInteres() {
		return idAreaInteres;
	}
	public void setIdAreaInteres(String idAreaInteres) {
		this.idAreaInteres = idAreaInteres;
	}
	public String getCentroCostos() {
		return centroCostos;
	}
	public void setCentroCostos(String centroCostos) {
		this.centroCostos = centroCostos;
	}
	public String getSubAreaInteres() {
		return subAreaInteres;
	}
	public void setSubAreaInteres(String subAreaInteres) {
		this.subAreaInteres = subAreaInteres;
	}
	public String getIdRegion() {
		return idRegion;
	}
	public void setIdRegion(String idRegion) {
		this.idRegion = idRegion;
	}
	public String getIdDivision() {
		return idDivision;
	}
	public void setIdDivision(String idDivision) {
		this.idDivision = idDivision;
	}
	public String getIdGerencia() {
		return idGerencia;
	}
	public void setIdGerencia(String idGerencia) {
		this.idGerencia = idGerencia;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public String getGerencia() {
		return gerencia;
	}
	public void setGerencia(String gerencia) {
		this.gerencia = gerencia;
	}
	public String getAreaInteres() {
		return areaInteres;
	}
	public void setAreaInteres(String areaInteres) {
		this.areaInteres = areaInteres;
	}
	
	
	
}
