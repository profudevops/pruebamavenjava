package com.mx.profuturo.bolsa.model.vo.Candidato;

import java.util.ArrayList;

public class CandidatoDetallesVO {
	private Integer id;
	private String nombre;
	private String correo;
	private String telefono;
	private String fechaNacimiento;
	private String estado;
	private String municipio;
	private String curriculum;
	private String urlCurriculum;
	private ScoreVO calificacion = new ScoreVO();
	private Boolean tieneCalificacion;
	private Boolean enReclutamiento;
	private Boolean contratado;
	
	/*
	private String calificacion; // 1=Talento	2=Apto	3=No Apto	4=Lista Negra
	private ArrayList<FaseVO> fases = new ArrayList<FaseVO>();
	private ArrayList<ProcesoReclutamientoVO> procesos=new ArrayList<ProcesoReclutamientoVO>();
	
	
	public ArrayList<ProcesoReclutamientoVO> getProcesos() {
		return procesos;
	}
	public void setProcesos(ArrayList<ProcesoReclutamientoVO> procesos) {
		this.procesos = procesos;
	}
		public String getCalificacion() {
		return calificacion;
	}
	public void setCalificacion(String calificacion) {
		this.calificacion = calificacion;
	}
	
	public ArrayList<FaseVO> getFases() {
		return fases;
	}
	public void setFases(ArrayList<FaseVO> fases) {
		this.fases = fases;
	}
	
	*/
	
	
	
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
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getCurriculum() {
		return curriculum;
	}
	public void setCurriculum(String curriculum) {
		this.curriculum = curriculum;
	}
	public Boolean getTieneCalificacion() {
		return tieneCalificacion;
	}
	public void setTieneCalificacion(Boolean tieneCalificacion) {
		this.tieneCalificacion = tieneCalificacion;
	}
	public String getUrlCurriculum() {
		return urlCurriculum;
	}
	public void setUrlCurriculum(String urlCurriculum) {
		this.urlCurriculum = urlCurriculum;
	}
	public Boolean getEnReclutamiento() {
		return enReclutamiento;
	}
	public void setEnReclutamiento(Boolean enReclutamiento) {
		this.enReclutamiento = enReclutamiento;
	}

	public ScoreVO getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(ScoreVO calificacion) {
		this.calificacion = calificacion;
	}
	public Boolean getContratado() {
		return contratado;
	}
	public void setContratado(Boolean contratado) {
		this.contratado = contratado;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
}
