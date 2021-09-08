package com.mx.profuturo.bolsa.model.recruitment.vo;


public class FolioVO  {

	protected Long id;
	protected String puesto; 
	protected String lineaNegocio;  
	protected String gerencia;  
	protected String divisional; 
	protected String recinto; 
	protected String estado;
	protected String descripcion;
    protected String codigo;
	
	public String getPuesto() {
		return puesto;
	}
	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}
	public String getLineaNegocio() {
		return lineaNegocio;
	}
	public void setLineaNegocio(String lineaNegocio) {
		this.lineaNegocio = lineaNegocio;
	}
	public String getGerencia() {
		return gerencia;
	}
	public void setGerencia(String gerencia) {
		this.gerencia = gerencia;
	}
	public String getDivisional() {
		return divisional;
	}
	public void setDivisional(String divisional) {
		this.divisional = divisional;
	}
	public String getRecinto() {
		return recinto;
	}
	public void setRecinto(String recinto) {
		this.recinto = recinto;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	
	
}
