package com.mx.profuturo.bolsa.model.service.candidates.dto;

import java.io.Serializable;

public class CandidateDetailResponseBean implements Serializable {

    private int id;
    private String nombre;
    private String correo;
    private String telefono;
    private String fechaNacimiento;
    private String estado;
    private String curriculum;
    private String urlCurriculum;
    private Grade calificacion;
    private boolean tieneCalificacion;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCurriculum() {
        return curriculum;
    }

    public void setCurriculum(String curriculum) {
        this.curriculum = curriculum;
    }

    public String getUrlCurriculum() {
        return urlCurriculum;
    }

    public void setUrlCurriculum(String urlCurriculum) {
        this.urlCurriculum = urlCurriculum;
    }

    public Grade getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Grade calificacion) {
        this.calificacion = calificacion;
    }

    public boolean getTieneCalificacion() {
        return tieneCalificacion;
    }

    public void setTieneCalificacion(boolean tieneCalificacion) {
        this.tieneCalificacion = tieneCalificacion;
    }
}
