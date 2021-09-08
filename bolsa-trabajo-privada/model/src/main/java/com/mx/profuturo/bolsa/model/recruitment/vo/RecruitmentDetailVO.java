package com.mx.profuturo.bolsa.model.recruitment.vo;

import java.util.LinkedHashMap;
import java.util.LinkedList;

import com.mx.profuturo.bolsa.model.recruitment.bean.PhaseBean;

public class RecruitmentDetailVO {
	
	private Integer idPublicacion;
	private Integer idCandidato;
	private String tituloVacante;
	private String nombreCandidato;
	private String estado;
	
	private String numeroProvisional;
	private Long folio;
	private Boolean esRepostulacion;
	private Boolean esVacanteMaestra;
	private String idObservador;
	private String correoObservador;
	
	private String idUsuario;
	private String idConsultor;
	
	private Integer idPreRechazo;
	private String comentario;
	
	
	private Integer idCurrentInterview;
	
	private LinkedList<RecruitmentStageVO> operaciones;
	private LinkedHashMap<String,LinkedList<RecruitmentStageVO>> etapas;
	private LinkedHashMap<String,PhaseBean> processMap;
	private LinkedList<EventVO> history;
	
	public Integer getIdPublicacion() {
		return idPublicacion;
	}
	public void setIdPublicacion(Integer idPublicacion) {
		this.idPublicacion = idPublicacion;
	}
	public Integer getIdCandidato() {
		return idCandidato;
	}
	public void setIdCandidato(Integer idCandidato) {
		this.idCandidato = idCandidato;
	}
	public String getTituloVacante() {
		return tituloVacante;
	}
	public void setTituloVacante(String tituloVacante) {
		this.tituloVacante = tituloVacante;
	}
	public String getNombreCandidato() {
		return nombreCandidato;
	}
	public void setNombreCandidato(String nombreCandidato) {
		this.nombreCandidato = nombreCandidato;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public LinkedHashMap<String, LinkedList<RecruitmentStageVO>> getEtapas() {
		return etapas;
	}
	public void setEtapas(LinkedHashMap<String, LinkedList<RecruitmentStageVO>> etapas) {
		this.etapas = etapas;
	}
	public LinkedList<RecruitmentStageVO> getOperaciones() {
		return operaciones;
	}
	public void setOperaciones(LinkedList<RecruitmentStageVO> operaciones) {
		this.operaciones = operaciones;
	}
	public LinkedHashMap<String, PhaseBean> getProcessMap() {
		return processMap;
	}
	public void setProcessMap(LinkedHashMap<String, PhaseBean> processMap) {
		this.processMap = processMap;
	}
	public void setHistory(LinkedList<EventVO> history) {
		this.history = history;
		
	}
	public LinkedList<EventVO> getHistory() {
		return history;
	}
	
	public Boolean getEsRepostulacion() {
		return esRepostulacion;
	}
	public void setEsRepostulacion(Boolean esRepostulacion) {
		this.esRepostulacion = esRepostulacion;
	}
	public String getIdObservador() {
		return idObservador;
	}
	public void setIdObservador(String idObservador) {
		this.idObservador = idObservador;
	}
	public String getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getIdConsultor() {
		return idConsultor;
	}
	public void setIdConsultor(String idConsultor) {
		this.idConsultor = idConsultor;
	}
	public String getCorreoObservador() {
		return correoObservador;
	}
	public void setCorreoObservador(String correoObservador) {
		this.correoObservador = correoObservador;
	}
	public Boolean getEsVacanteMaestra() {
		return esVacanteMaestra;
	}
	public void setEsVacanteMaestra(Boolean esVacanteMaestra) {
		this.esVacanteMaestra = esVacanteMaestra;
	}
	public Integer getIdCurrentInterview() {
		return idCurrentInterview;
	}
	public void setIdCurrentInterview(Integer idCurrentInterview) {
		this.idCurrentInterview = idCurrentInterview;
	}
	public Integer getIdPreRechazo() {
		return idPreRechazo;
	}
	public void setIdPreRechazo(Integer idPreRechazo) {
		this.idPreRechazo = idPreRechazo;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public String getNumeroProvisional() {
		return numeroProvisional;
	}
	public void setNumeroProvisional(String numeroProvisional) {
		this.numeroProvisional = numeroProvisional;
	}
	public Long getFolio() {
		return folio;
	}
	public void setFolio(Long folio) {
		this.folio = folio;
	}

	
}
