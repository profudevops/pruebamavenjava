package com.mx.profuturo.bolsa.model.recruitment.response;

import com.mx.profuturo.bolsa.model.recruitment.dto.form.InterviewResume;

public class RegistroProceso {

	protected Integer idReclutamiento;
	protected Integer idEtapa;
	protected String codigo;
	protected String idUsuario;
	protected Integer estatus;
	protected String comentario;
	protected Boolean aprobado;
	protected Integer idRechazo;
	protected Integer idPreRechazo;
	protected String rechazo;
	protected String modo;
	protected Integer idEntrevista;
	protected ResumenEntrevista resumenEntrevista = new ResumenEntrevista(); 
	protected Boolean repostulacion = false;
	
	
	
	public Boolean getRepostulacion() {
		return repostulacion;
	}

	public void setRepostulacion(Boolean repostulacion) {
		this.repostulacion = repostulacion;
	}

	protected String fecha;
	
	protected String nombreUsuario;
	
	public RegistroProceso(RegistroProceso r) {
		this.aprobado = r.getAprobado();
		this.codigo = r.getCodigo();
		this.comentario = r.getComentario();
		this.estatus = r.getEstatus();
		this.fecha = r.getFecha();
		this.idEtapa = r.getIdEtapa();
		this.idRechazo = r.getIdRechazo();
		this.idUsuario = r.getIdUsuario();
		this.modo = r.getModo();
		this.idEntrevista = r.getIdEntrevista();
		this.idReclutamiento = r.getIdReclutamiento();
		this.idPreRechazo = r.getIdPreRechazo();
		this.repostulacion = r.getRepostulacion();
	}
	
	public RegistroProceso() {

	}
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public Boolean getAprobado() {
		return aprobado;
	}
	public void setAprobado(Boolean aprobado) {
		this.aprobado = aprobado;
	}
	public Integer getIdRechazo() {
		return idRechazo;
	}
	public void setIdRechazo(Integer idRechazo) {
		this.idRechazo = idRechazo;
	}
	public Integer getEstatus() {
		return estatus;
	}
	public void setEstatus(Integer estatus) {
		this.estatus = estatus;
	}
	public String getModo() {
		return modo;
	}
	public void setModo(String modo) {
		this.modo = modo;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public Integer getIdEtapa() {
		return idEtapa;
	}
	public void setIdEtapa(Integer idEtapa) {
		this.idEtapa = idEtapa;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public Integer getIdEntrevista() {
		return idEntrevista;
	}

	public void setIdEntrevista(Integer idEntrevista) {
		this.idEntrevista = idEntrevista;
	}

	public Integer getIdReclutamiento() {
		return idReclutamiento;
	}

	public void setIdReclutamiento(Integer idReclutamiento) {
		this.idReclutamiento = idReclutamiento;
	}

	public ResumenEntrevista getResumenEntrevista() {
		return resumenEntrevista;
	}

	public void setResumenEntrevista(ResumenEntrevista resumenEntrevista) {
		this.resumenEntrevista = resumenEntrevista;
	}

	public String getRechazo() {
		return rechazo;
	}

	public void setRechazo(String rechazo) {
		this.rechazo = rechazo;
	}

	public Integer getIdPreRechazo() {
		return idPreRechazo;
	}

	public void setIdPreRechazo(Integer idPreRechazo) {
		this.idPreRechazo = idPreRechazo;
	}
	
	
	
}
