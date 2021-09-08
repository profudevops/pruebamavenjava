package com.mx.profuturo.bolsa.model.service.vacancies.dto;

public class AbrirPlazasDTO {

	private Integer idPublicacion;
	private Integer numeroPlazasPorAbrir;
	private Integer numeroPlazasAbiertas;
	
	public Integer getIdPublicacion() {
		return idPublicacion;
	}
	public void setIdPublicacion(Integer idPublicacion) {
		this.idPublicacion = idPublicacion;
	}
	public Integer getNumeroPlazasPorAbrir() {
		return numeroPlazasPorAbrir;
	}
	public void setNumeroPlazasPorAbrir(Integer numeroPlazasPorAbrir) {
		this.numeroPlazasPorAbrir = numeroPlazasPorAbrir;
	}
	public Integer getNumeroPlazasAbiertas() {
		return numeroPlazasAbiertas;
	}
	public void setNumeroPlazasAbiertas(Integer numeroPlazasAbiertas) {
		this.numeroPlazasAbiertas = numeroPlazasAbiertas;
	}	
}