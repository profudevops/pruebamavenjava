package com.mx.profuturo.bolsa.model.service.hiringform.dto;

import java.util.ArrayList;

public class FormularioEtapa3DTO {

	private String nombre;
	 private String nombreContacto;
	 private String apellidoPaterno;
	 private String apellidoMaterno;
	 private int idParentesco;
	 private String parentesco;
	 private String telefonoCelular;
	 private String telefonoParticular;
	 private ArrayList<FormularioBeneficiarioDTO> beneficiarios;

	public String getParentesco() {
		return parentesco;
	}

	public void setParentesco(String parentesco) {
		this.parentesco = parentesco;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombreContacto() {
		return nombreContacto;
	}

	public void setNombreContacto(String nombreContacto) {
		this.nombreContacto = nombreContacto;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public int getIdParentesco() {
		return idParentesco;
	}

	public void setIdParentesco(int idParentesco) {
		this.idParentesco = idParentesco;
	}

	public String getTelefonoCelular() {
		return telefonoCelular;
	}

	public void setTelefonoCelular(String telefonoCelular) {
		this.telefonoCelular = telefonoCelular;
	}

	public String getTelefonoParticular() {
		return telefonoParticular;
	}

	public void setTelefonoParticular(String telefonoParticular) {
		this.telefonoParticular = telefonoParticular;
	}

	public ArrayList<FormularioBeneficiarioDTO> getBeneficiarios() {
		return beneficiarios;
	}

	public void setBeneficiarios(ArrayList<FormularioBeneficiarioDTO> beneficiarios) {
		this.beneficiarios = beneficiarios;
	}
}
