package com.mx.profuturo.bolsa.model.service.areasinteres.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AreaInteresDTO implements Serializable {

	private int idArea;
	private String nombre;
	private String descripcion;
	private List<SubAreaInteresDTO> subareas;

	public int getIdArea() {
		return idArea;
	}
	public void setIdArea(int idArea) {
		this.idArea = idArea;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public List<SubAreaInteresDTO> getSubareas() {
		return subareas;
	}
	public void setSubareas(List<SubAreaInteresDTO> subareas) {
		this.subareas = subareas;
	}
	
	
}
