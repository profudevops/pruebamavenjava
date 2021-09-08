package com.mx.profuturo.bolsa.model.recruitment.dto;

public class InterviewDTO {
	
	protected Integer idReclutamiento;
	protected String faseAsociada;
	protected Integer idEntrevista;
	protected Integer tipoEntrevista;
	protected Integer idSede;
	protected String fecha;
	protected String hora;
	protected Integer idEntrevistador;
	public Integer getIdEntrevista() {
		return idEntrevista;
	}
	public void setIdEntrevista(Integer idEntrevista) {
		this.idEntrevista = idEntrevista;
	}
	public Integer getTipoEntrevista() {
		return tipoEntrevista;
	}
	public void setTipoEntrevista(Integer tipoEntrevista) {
		this.tipoEntrevista = tipoEntrevista;
	}
	public Integer getIdSede() {
		return idSede;
	}
	public void setIdSede(Integer idSede) {
		this.idSede = idSede;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public Integer getIdEntrevistador() {
		return idEntrevistador;
	}
	public void setIdEntrevistador(Integer idEntrevistador) {
		this.idEntrevistador = idEntrevistador;
	}
	public Integer getIdReclutamiento() {
		return idReclutamiento;
	}
	public void setIdReclutamiento(Integer idReclutamiento) {
		this.idReclutamiento = idReclutamiento;
	}
	public String getFaseAsociada() {
		return faseAsociada;
	}
	public void setFaseAsociada(String faseAsociada) {
		this.faseAsociada = faseAsociada;
	}
	
	
	
	
}
