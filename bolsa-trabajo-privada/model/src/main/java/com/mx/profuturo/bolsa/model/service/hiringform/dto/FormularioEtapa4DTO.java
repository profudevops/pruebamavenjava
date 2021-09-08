package com.mx.profuturo.bolsa.model.service.hiringform.dto;

public class FormularioEtapa4DTO {

	private boolean trabajadoEnMismoGiro;
	private String trabajadoEnMismoGiroDescripcion;
	private boolean trabajadoEnEmpresaBAL;
	private int idTrabajadoEnEmpresaBAL;
	private boolean familiaresConocidosBAL;
	private String familiaresConocidosBALNombre;
	private String familiaresConocidosBALApellidoPaterno;
	private String familiaresConocidosBALApellidoMaterno;
	private boolean trabajadoSegurosFinanzas;
	private String trabajadoSegurosFinanzasDescripcion;
	private boolean certificacionConsar;
	private String certificacionConsarDescripcion;
	private String empresaBAL;

	public String getEmpresaBAL() {
		return empresaBAL;
	}

	public void setEmpresaBAL(String empresaBAL) {
		this.empresaBAL = empresaBAL;
	}

	public boolean isTrabajadoEnMismoGiro() {
		return trabajadoEnMismoGiro;
	}

	public void setTrabajadoEnMismoGiro(boolean trabajadoEnMismoGiro) {
		this.trabajadoEnMismoGiro = trabajadoEnMismoGiro;
	}

	public String getTrabajadoEnMismoGiroDescripcion() {
		return trabajadoEnMismoGiroDescripcion;
	}

	public void setTrabajadoEnMismoGiroDescripcion(String trabajadoEnMismoGiroDescripcion) {
		this.trabajadoEnMismoGiroDescripcion = trabajadoEnMismoGiroDescripcion;
	}

	public boolean isTrabajadoEnEmpresaBAL() {
		return trabajadoEnEmpresaBAL;
	}

	public void setTrabajadoEnEmpresaBAL(boolean trabajadoEnEmpresaBAL) {
		this.trabajadoEnEmpresaBAL = trabajadoEnEmpresaBAL;
	}

	public int getIdTrabajadoEnEmpresaBAL() {
		return idTrabajadoEnEmpresaBAL;
	}

	public void setIdTrabajadoEnEmpresaBAL(int idTrabajadoEnEmpresaBAL) {
		this.idTrabajadoEnEmpresaBAL = idTrabajadoEnEmpresaBAL;
	}

	public boolean isFamiliaresConocidosBAL() {
		return familiaresConocidosBAL;
	}

	public void setFamiliaresConocidosBAL(boolean familiaresConocidosBAL) {
		this.familiaresConocidosBAL = familiaresConocidosBAL;
	}

	public String getFamiliaresConocidosBALNombre() {
		return familiaresConocidosBALNombre;
	}

	public void setFamiliaresConocidosBALNombre(String familiaresConocidosBALNombre) {
		this.familiaresConocidosBALNombre = familiaresConocidosBALNombre;
	}

	public String getFamiliaresConocidosBALApellidoPaterno() {
		return familiaresConocidosBALApellidoPaterno;
	}

	public void setFamiliaresConocidosBALApellidoPaterno(String familiaresConocidosBALApellidoPaterno) {
		this.familiaresConocidosBALApellidoPaterno = familiaresConocidosBALApellidoPaterno;
	}

	public String getFamiliaresConocidosBALApellidoMaterno() {
		return familiaresConocidosBALApellidoMaterno;
	}

	public void setFamiliaresConocidosBALApellidoMaterno(String familiaresConocidosBALApellidoMaterno) {
		this.familiaresConocidosBALApellidoMaterno = familiaresConocidosBALApellidoMaterno;
	}

	public boolean isTrabajadoSegurosFinanzas() {
		return trabajadoSegurosFinanzas;
	}

	public void setTrabajadoSegurosFinanzas(boolean trabajadoSegurosFinanzas) {
		this.trabajadoSegurosFinanzas = trabajadoSegurosFinanzas;
	}

	public String getTrabajadoSegurosFinanzasDescripcion() {
		return trabajadoSegurosFinanzasDescripcion;
	}

	public void setTrabajadoSegurosFinanzasDescripcion(String trabajadoSegurosFinanzasDescripcion) {
		this.trabajadoSegurosFinanzasDescripcion = trabajadoSegurosFinanzasDescripcion;
	}

	public boolean isCertificacionConsar() {
		return certificacionConsar;
	}

	public void setCertificacionConsar(boolean certificacionConsar) {
		this.certificacionConsar = certificacionConsar;
	}

	public String getCertificacionConsarDescripcion() {
		return certificacionConsarDescripcion;
	}

	public void setCertificacionConsarDescripcion(String certificacionConsarDescripcion) {
		this.certificacionConsarDescripcion = certificacionConsarDescripcion;
	}
}
