package com.mx.profuturo.bolsa.model.service.hiringform.dto;

public class FormularioEtapa2DTO {
	
	private int nivelEstudios;
	private String nivelEstudiosTexto;
	private Integer estatus;
	private String estatusTexto;
	private String carrera;
	private String nombreEmpresa;
	private String actividad;
	private String nombreJefeInmediato;
	private String apellidoPaternoJefeInmediato;
	private String apellidoMaternoJefeInmediato;
	private String puestoJefe;
	private Integer periodoLaboradoInicio;
	private Integer periodoLaboradoFin;
	private String telefono;
	private String funciones;

	public String getApellidoPaternoJefeInmediato() {
		return apellidoPaternoJefeInmediato;
	}

	public void setApellidoPaternoJefeInmediato(String apellidoPaternoJefeInmediato) {
		this.apellidoPaternoJefeInmediato = apellidoPaternoJefeInmediato;
	}

	public String getApellidoMaternoJefeInmediato() {
		return apellidoMaternoJefeInmediato;
	}

	public void setApellidoMaternoJefeInmediato(String apellidoMaternoJefeInmediato) {
		this.apellidoMaternoJefeInmediato = apellidoMaternoJefeInmediato;
	}

	public String getNivelEstudiosTexto() {
		return nivelEstudiosTexto;
	}

	public void setNivelEstudiosTexto(String nivelEstudiosTexto) {
		this.nivelEstudiosTexto = nivelEstudiosTexto;
	}

	public String getEstatusTexto() {
		return estatusTexto;
	}

	public void setEstatusTexto(String estatusTexto) {
		this.estatusTexto = estatusTexto;
	}

	public int getNivelEstudios() {
		return nivelEstudios;
	}
	public void setNivelEstudios(int nivelEstudios) {
		this.nivelEstudios = nivelEstudios;
	}

	public String getNombreEmpresa() {
		return nombreEmpresa;
	}
	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}

	public String getNombreJefeInmediato() {
		return nombreJefeInmediato;
	}
	public void setNombreJefeInmediato(String nombreJefeInmediato) {
		this.nombreJefeInmediato = nombreJefeInmediato;
	}
	public String getPuestoJefe() {
		return puestoJefe;
	}
	public void setPuestoJefe(String puestoJefe) {
		this.puestoJefe = puestoJefe;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getFunciones() {
		return funciones;
	}
	public void setFunciones(String funciones) {
		this.funciones = funciones;
	}
	
	public Integer getEstatus() {
		return estatus;
	}
	public void setEstatus(Integer estatus) {
		this.estatus = estatus;
	}
	public String getCarrera() {
		return carrera;
	}
	public void setCarrera(String carrera) {
		this.carrera = carrera;
	}
	public String getActividad() {
		return actividad;
	}
	public void setActividad(String actividad) {
		this.actividad = actividad;
	}

	public Integer getPeriodoLaboradoInicio() {
		return periodoLaboradoInicio;
	}

	public void setPeriodoLaboradoInicio(Integer periodoLaboradoInicio) {
		this.periodoLaboradoInicio = periodoLaboradoInicio;
	}

	public Integer getPeriodoLaboradoFin() {
		return periodoLaboradoFin;
	}

	public void setPeriodoLaboradoFin(Integer periodoLaboradoFin) {
		this.periodoLaboradoFin = periodoLaboradoFin;
	}
}
