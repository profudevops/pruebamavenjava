package com.mx.profuturo.bolsa.model.recruitment.vo;

import com.mx.profuturo.bolsa.model.recruitment.response.ResumenEntrevista;

public class EventVO {
	
	private Integer idReclutamiento;
	private Integer idFase;
	protected Boolean editable;
	private String codigoFase;
	private String autor;
	private String titulo;
	private String fecha;
	private String contenido;
	private String nota;
	private String icono;
	private Integer idEntrevista;
	private ResumenEntrevista resumenEntrevista;
	

	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public Integer getIdReclutamiento() {
		return idReclutamiento;
	}
	public void setIdReclutamiento(Integer idReclutamiento) {
		this.idReclutamiento = idReclutamiento;
	}
	public Integer getIdFase() {
		return idFase;
	}
	public void setIdFase(Integer idFase) {
		this.idFase = idFase;
	}
	public Boolean getEditable() {
		return editable;
	}
	public void setEditable(Boolean editable) {
		this.editable = editable;
	}
	public String getCodigoFase() {
		return codigoFase;
	}
	public void setCodigoFase(String codigoFase) {
		this.codigoFase = codigoFase;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public String getContenido() {
		return contenido;
	}
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	public String getNota() {
		return nota;
	}
	public void setNota(String nota) {
		this.nota = nota;
	}
	public String getIcono() {
		return icono;
	}
	public void setIcono(String icono) {
		this.icono = icono;
	}
	public ResumenEntrevista getResumenEntrevista() {
		return resumenEntrevista;
	}
	public void setResumenEntrevista(ResumenEntrevista resumenEntrevista) {
		this.resumenEntrevista = resumenEntrevista;
	}
	public Integer getIdEntrevista() {
		return idEntrevista;
	}
	public void setIdEntrevista(Integer idEntrevista) {
		this.idEntrevista = idEntrevista;
	}
	
}
