package com.mx.profuturo.bolsa.model.vo.input;

public class FormularioAplicacionVacante {


    private int idVacante;
    private String nombre;
    private int nivelEstudios;
    private int idAreaInteres;
    private String celular;
    private String email;
    private int idOrigen;
    private int idEstado;
    private String genero;
    private String fechaNacimiento;
    private String cv;
    private String celularRaw;
    private String getFechaNacimientoRaw;

    public int getIdVacante() {
        return idVacante;
    }

    public void setIdVacante(int idVacante) {
        this.idVacante = idVacante;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNivelEstudios() {
        return nivelEstudios;
    }

    public void setNivelEstudios(int nivelEstudios) {
        this.nivelEstudios = nivelEstudios;
    }

    public int getIdAreaInteres() {
        return idAreaInteres;
    }

    public void setIdAreaInteres(int idAreaInteres) {
        this.idAreaInteres = idAreaInteres;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdOrigen() {
        return idOrigen;
    }

    public void setIdOrigen(int idOrigen) {
        this.idOrigen = idOrigen;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public String getCelularRaw() {
        return celularRaw;
    }

    public void setCelularRaw(String celularRaw) {
        this.celularRaw = celularRaw;
    }

    public String getGetFechaNacimientoRaw() {
        return getFechaNacimientoRaw;
    }

    public void setGetFechaNacimientoRaw(String getFechaNacimientoRaw) {
        this.getFechaNacimientoRaw = getFechaNacimientoRaw;
    }

    public FormularioAplicacionVacante(int idVacante, String nombre, int nivelEstudios, int idAreaInteres, String celular, String email, int idOrigen, int idEstado, String genero, String fechaNacimiento, String cv, String celularRaw, String getFechaNacimientoRaw) {
        this.idVacante = idVacante;
        this.nombre = nombre;
        this.nivelEstudios = nivelEstudios;
        this.idAreaInteres = idAreaInteres;
        this.celular = celular;
        this.email = email;
        this.idOrigen = idOrigen;
        this.idEstado = idEstado;
        this.genero = genero;
        this.fechaNacimiento = fechaNacimiento;
        this.cv = cv;
        this.celularRaw = celularRaw;
        this.getFechaNacimientoRaw = getFechaNacimientoRaw;
    }

    public FormularioAplicacionVacante() {

    }
}

