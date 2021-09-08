package com.mx.profuturo.bolsa.model.service.vacancies.dto;

public class ObtenerPlazasAbiertasPorSedeDTO {
	
	private Integer idVacante;
	private Integer idSede;
	private String nombre;
	
	public Integer getIdSede() {
		return idSede;
	}
	public void setIdSede(Integer idSede) {
		this.idSede = idSede;
	}
	public Integer getIdVacante() {
		return idVacante;
	}
	public void setIdVacante(Integer idVacante) {
		this.idVacante = idVacante;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}
