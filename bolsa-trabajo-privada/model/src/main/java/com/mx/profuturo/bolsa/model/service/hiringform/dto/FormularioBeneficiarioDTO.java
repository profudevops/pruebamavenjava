package com.mx.profuturo.bolsa.model.service.hiringform.dto;

public class FormularioBeneficiarioDTO {

    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private int idParentesco;
    private String parentesco;

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

	public int getIdParentesco() {
		return idParentesco;
	}

	public void setIdParentesco(int idParentesco) {
		this.idParentesco = idParentesco;
	}

  

}
