package com.mx.profuturo.bolsa.model.recruitment.service.dto;

import com.mx.profuturo.bolsa.model.recruitment.response.RegistroProceso;

public class InterviewAppointmentDTO  {

	private Integer idEntrevista;
	
	//private String entrevistador;
	private Integer tipoEntrevistador; //500 ana 501 consu 502 503 interesado
	private String fecha;
	private Integer hora;
	private Integer sede;
	private Integer tipoEntrevista;
	private Boolean necesitaRespuesta;
	private String idEntrevistador;
	/*private Integer idDivision;
	private Integer idRegional;
	private Integer idGerencia;*/
	private String codigoEtapa;
	private String modo;
	private Integer idEtapa;
	private Integer idReclutamiento;
	private String idUsuario;
	private Integer estatus = 1;
	

	public InterviewAppointmentDTO() {
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
 
	public Integer getHora() {
		return hora;
	}

	public void setHora(Integer hora) {
		this.hora = hora;
	}

	public Integer getSede() {
		return sede;
	}

	public void setSede(Integer sede) {
		this.sede = sede;
	}

	public Integer getTipoEntrevista() {
		return tipoEntrevista;
	}

	public void setTipoEntrevista(Integer tipoEntrevista) {
		this.tipoEntrevista = tipoEntrevista;
	}


	public Integer getIdEntrevista() {
		return idEntrevista;
	}

	public void setIdEntrevista(Integer idEntrevista) {
		this.idEntrevista = idEntrevista;
	}

	public Boolean getNecesitaRespuesta() {
		return necesitaRespuesta;
	}

	public void setNecesitaRespuesta(Boolean necesitaRespuesta) {
		this.necesitaRespuesta = necesitaRespuesta;
	}
/*
	public Integer getIdDivision() {
		return idDivision;
	}

	public void setIdDivision(Integer idDivision) {
		this.idDivision = idDivision;
	}

	public Integer getIdRegional() {
		return idRegional;
	}

	public void setIdRegional(Integer idRegional) {
		this.idRegional = idRegional;
	}

	public Integer getIdGerencia() {
		return idGerencia;
	}

	public void setIdGerencia(Integer idGerencia) {
		this.idGerencia = idGerencia;
	}
*/
	public String getCodigoEtapa() {
		return codigoEtapa;
	}

	public void setCodigoEtapa(String codigoEtapa) {
		this.codigoEtapa = codigoEtapa;
	}

	public Integer getTipoEntrevistador() {
		return tipoEntrevistador;
	}

	public void setTipoEntrevistador(Integer tipoEntrevistador) {
		this.tipoEntrevistador = tipoEntrevistador;
	}

	public String getIdEntrevistador() {
		return idEntrevistador;
	}

	public void setIdEntrevistador(String idEntrevistador) {
		this.idEntrevistador = idEntrevistador;
	}

	public String getModo() {
		return modo;
	}

	public void setModo(String modo) {
		this.modo = modo;
	}

	public Integer getIdEtapa() {
		return idEtapa;
	}

	public void setIdEtapa(Integer idEtapa) {
		this.idEtapa = idEtapa;
	}

	public Integer getIdReclutamiento() {
		return idReclutamiento;
	}

	public void setIdReclutamiento(Integer idReclutamiento) {
		this.idReclutamiento = idReclutamiento;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Integer getEstatus() {
		return estatus;
	}

	public void setEstatus(Integer estatus) {
		this.estatus = estatus;
	}
	
}
