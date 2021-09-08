package com.mx.profuturo.bolsa.model.recruitment.response;

public class DatosBasicos {

	private Integer idReclutamiento;
	private Integer idCandidato;
	private Integer idPublicacion;
	private String nombreCandidato;
	private String nombreVacante;
	private String fechaCreacion;
	private Boolean abierto;
	private String estatus;
	private String idUsuario;
	private String idConsultor;
	private Boolean repostulacion = false;
	private String fechaRepostulacion;
	private String numeroProvicional;
	private String tipoReclutamiento;
	private Boolean vacanteMaestra = false;
	private Integer idGerencia;
	private Boolean tieneExpediente;
	private String idObservador;
	private String correoObservador;
	private Long folio;
	
	public Integer getIdReclutamiento() {
		return idReclutamiento;
	}
	public void setIdReclutamiento(Integer idReclutamiento) {
		this.idReclutamiento = idReclutamiento;
	}
	public String getNombreCandidato() {
		return nombreCandidato;
	}
	public void setNombreCandidato(String nombreCandidato) {
		this.nombreCandidato = nombreCandidato;
	}
	public String getNombreVacante() {
		return nombreVacante;
	}
	public void setNombreVacante(String nombreVacante) {
		this.nombreVacante = nombreVacante;
	}
	
	public Integer getIdCandidato() {
		return idCandidato;
	}
	public void setIdCandidato(Integer idCandidato) {
		this.idCandidato = idCandidato;
	}
	public Integer getIdPublicacion() {
		return idPublicacion;
	}
	public void setIdPublicacion(Integer idPublicacion) {
		this.idPublicacion = idPublicacion;
	}
	public Boolean getAbierto() {
		return abierto;
	}
	public void setAbierto(Boolean abierto) {
		this.abierto = abierto;
	}
	public String getEstatus() {
		return estatus;
	}
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
	
	public String getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public Boolean getRepostulacion() {
		return repostulacion;
	}
	public void setRepostulacion(Boolean repostulacion) {
		this.repostulacion = repostulacion;
	}
	public String getFechaRepostulacion() {
		return fechaRepostulacion;
	}
	public void setFechaRepostulacion(String fechaRepostulacion) {
		this.fechaRepostulacion = fechaRepostulacion;
	}
	public String getTipoReclutamiento() {
		return tipoReclutamiento;
	}
	public void setTipoReclutamiento(String tipoReclutamiento) {
		this.tipoReclutamiento = tipoReclutamiento;
	}
	public Boolean getVacanteMaestra() {
		return vacanteMaestra;
	}
	public void setVacanteMaestra(Boolean vacanteMaestra) {
		this.vacanteMaestra = vacanteMaestra;
	}

	public Integer getIdGerencia() {
		return idGerencia;
	}
	public void setIdGerencia(Integer idGerencia) {
		this.idGerencia = idGerencia;
	}
	public Boolean getTieneExpediente() {
		return tieneExpediente;
	}
	public void setTieneExpediente(Boolean tieneExpediente) {
		this.tieneExpediente = tieneExpediente;
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
	public String getNumeroProvicional() {
		return numeroProvicional;
	}
	public void setNumeroProvicional(String numeroProvicional) {
		this.numeroProvicional = numeroProvicional;
	}
	public Long getFolio() {
		return folio;
	}
	public void setFolio(Long folio) {
		this.folio = folio;
	}

	
	
}
