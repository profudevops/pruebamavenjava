package com.mx.profuturo.bolsa.model.service.vacancies.dto;

public class PostuladosBean {

    private String nombrePostulado;
    private String correo;
    private String estatus;
    private int idCandidato;
    private String telefono;

    public String getNombrePostulado() {
        return nombrePostulado;
    }

    public void setNombrePostulado(String nombrePostulado) {
        this.nombrePostulado = nombrePostulado;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public int getIdCandidato() {
        return idCandidato;
    }

    public void setIdCandidato(int idCandidato) {
        this.idCandidato = idCandidato;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
