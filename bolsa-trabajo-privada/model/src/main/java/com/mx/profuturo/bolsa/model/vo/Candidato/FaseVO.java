package com.mx.profuturo.bolsa.model.vo.Candidato;

public class FaseVO {
	private Integer id;
	private String nombre;
	private String fecha;
	private String descripcion;
	private boolean recomendado;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public boolean isRecomendado() {
		return recomendado;
	}
	public void setRecomendado(boolean recomendado) {
		this.recomendado = recomendado;
	}
	
}
