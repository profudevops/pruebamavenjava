package com.mx.profuturo.bolsa.model.recruitment.response;

public class FolioResponseBean {
	
	private Long folio;
	private  String puesto; 
	private  String nomPuesto;
	private  String numPlaza;

	private String regional;
	private String descPlaza;
	private String nomCentroCosto;
	
	public Long getFolio() {
		return folio;
	}
	public void setFolio(Long folio) {
		this.folio = folio;
	}
	public String getPuesto() {
		return puesto;
	}
	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}
	public String getNomPuesto() {
		return nomPuesto;
	}
	public void setNomPuesto(String nomPuesto) {
		this.nomPuesto = nomPuesto;
	}
	public String getNumPlaza() {
		return numPlaza;
	}
	public void setNumPlaza(String numPlaza) {
		this.numPlaza = numPlaza;
	}
	public String getDescPlaza() {
		return descPlaza;
	}
	public void setDescPlaza(String descPlaza) {
		this.descPlaza = descPlaza;
	}
	public String getNomCentroCosto() {
		return nomCentroCosto;
	}
	public void setNomCentroCosto(String nomCentroCosto) {
		this.nomCentroCosto = nomCentroCosto;
	}
	public String getRegional() {
		return regional;
	}
	public void setRegional(String regional) {
		this.regional = regional;
	}
	
	
	

}
