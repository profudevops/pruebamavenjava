package com.mx.profuturo.bolsa.model.reports.response;

/*
 * 
 * por nivel de puesto
 * por analista
 * por mes
 * 
 */
public class HiringsResponse  extends AbstractFiltersResponse{
	
	private String tituloVacante;	
	private String fecha; 
	private String contratacion;	
	private String nivelPuesto;	
	private String analista;
	private String mes;
	
	
	public String getTituloVacante() {
		return tituloVacante;
	}
	public void setTituloVacante(String tituloVacante) {
		this.tituloVacante = tituloVacante;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getContratacion() {
		return contratacion;
	}
	public void setContratacion(String contratacion) {
		this.contratacion = contratacion;
	}
	public String getNivelPuesto() {
		return nivelPuesto;
	}
	public void setNivelPuesto(String nivelPuesto) {
		this.nivelPuesto = nivelPuesto;
	}
	public String getAnalista() {
		return analista;
	}
	public void setAnalista(String analista) {
		this.analista = analista;
	}
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
	
	
	
	
}
