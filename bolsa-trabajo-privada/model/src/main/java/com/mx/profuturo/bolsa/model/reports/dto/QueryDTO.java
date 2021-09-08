package com.mx.profuturo.bolsa.model.reports.dto;

public class QueryDTO {
	
	private String reportCode;
	
	private String fechaInicio;
	private String fechaFin;
	
	private String startDate;
	private String endDate;
	
	private Integer idAreaInteres;
	private Integer centroCostos;
	private Integer idSubAreaInteres;
	
	
	private Integer idRegion;
	private Integer idDivision;
	private Integer idGerencia;
	
	private String idAnalista;
	
	private Integer idNivelPuesto;
	
	private Integer idRama = 0;
	private String rama;
	
	private String tipoVacante;
	
	
	public String getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public String getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}
	public Integer getIdAreaInteres() {
		return idAreaInteres;
	}
	public void setIdAreaInteres(Integer idAreaInteres) {
		this.idAreaInteres = idAreaInteres;
	}
	public Integer getIdRegion() {
		return idRegion;
	}
	public void setIdRegion(Integer idRegion) {
		this.idRegion = idRegion;
	}
	public Integer getIdDivision() {
		return idDivision;
	}
	public void setIdDivision(Integer idDivision) {
		this.idDivision = idDivision;
	}
	public Integer getIdGerencia() {
		return idGerencia;
	}
	public void setIdGerencia(Integer idGerencia) {
		this.idGerencia = idGerencia;
	}
	public String getReportCode() {
		return reportCode;
	}
	public void setReportCode(String reportCode) {
		this.reportCode = reportCode;
	}
	public String getRama() {
		return rama;
	}
	public void setRama(String rama) {
		this.rama = rama;
	}
	public Integer getCentroCostos() {
		return centroCostos;
	}
	public void setCentroCostos(Integer centroCostos) {
		this.centroCostos = centroCostos;
	}
	
	

	
	public String getIdAnalista() {
		return idAnalista;
	}
	public void setIdAnalista(String idAnalista) {
		this.idAnalista = idAnalista;
	}
	public Integer getIdNivelPuesto() {
		return idNivelPuesto;
	}
	public void setIdNivelPuesto(Integer idNivelPuesto) {
		this.idNivelPuesto = idNivelPuesto;
	}
	public Integer getIdRama() {
		return idRama;
	}
	public void setIdRama(Integer idRama) {
		this.idRama = idRama;
	}
	public String getTipoVacante() {
		return tipoVacante;
	}
	public void setTipoVacante(String tipoVacante) {
		this.tipoVacante = tipoVacante;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public Integer getIdSubAreaInteres() {
		return idSubAreaInteres;
	}
	public void setIdSubAreaInteres(Integer idSubAreaInteres) {
		this.idSubAreaInteres = idSubAreaInteres;
	}	
	
}
