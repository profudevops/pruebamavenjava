package com.mx.profuturo.bolsa.model.vo.Candidato;

public class RecruitmentEventVO {
	
	private Integer idReclutamiento;
	private Boolean enProgreso;
	private String tituloVacante;
	private String ultimaEtapaAlcanzada;
	private String fecha;
	private Integer idPublicacion;
	
	public Integer getIdReclutamiento() {
		return idReclutamiento;
	}
	public void setIdReclutamiento(Integer idReclutamiento) {
		this.idReclutamiento = idReclutamiento;
	}
	public Boolean getEnProgreso() {
		return enProgreso;
	}
	public void setEnProgreso(Boolean enProgreso) {
		this.enProgreso = enProgreso;
	}
	public String getTituloVacante() {
		return tituloVacante;
	}
	public void setTituloVacante(String tituloVacante) {
		this.tituloVacante = tituloVacante;
	}
	public String getUltimaEtapaAlcanzada() {
		return ultimaEtapaAlcanzada;
	}
	public void setUltimaEtapaAlcanzada(String ultimaEtapaAlcanzada) {
		this.ultimaEtapaAlcanzada = ultimaEtapaAlcanzada;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public Integer getIdPublicacion() {
		return idPublicacion;
	}
	public void setIdPublicacion(Integer idPublicacion) {
		this.idPublicacion = idPublicacion;
	}	
	
	
	

}