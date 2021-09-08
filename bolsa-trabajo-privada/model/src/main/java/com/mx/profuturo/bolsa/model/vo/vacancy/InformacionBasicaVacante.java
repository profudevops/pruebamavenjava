package com.mx.profuturo.bolsa.model.vo.vacancy;

import com.mx.profuturo.bolsa.model.vo.common.CatalogoVO;

public class InformacionBasicaVacante {
    private int idVacante;
    private String titulo;
    private CatalogoVO areaInteres;
    private String descripcionVacante;
    private String salario;
    private String fechaPublicacion;
    private String turno;
    private String division;

    public int getIdVacante() {
        return idVacante;
    }

    public void setIdVacante(int idVacante) {
        this.idVacante = idVacante;
    }

    public String getDescripcionVacante() {
        return descripcionVacante;
    }

    public void setDescripcionVacante(String descripcionVacante) {
        this.descripcionVacante = descripcionVacante;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSalario() {
        return salario;
    }

    public void setSalario(String salario) {
        this.salario = salario;
    }

    public String getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(String fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public CatalogoVO getAreaInteres() {
        return areaInteres;
    }

    public void setAreaInteres(CatalogoVO areaInteres) {
        this.areaInteres = areaInteres;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public InformacionBasicaVacante(int idVacante, String descripcionVacante, String titulo, String salario, String fechaPublicacion, CatalogoVO areaInteres, String turno, String division) {
        this.idVacante = idVacante;
        this.descripcionVacante = descripcionVacante;
        this.titulo = titulo;
        this.salario = salario;
        this.fechaPublicacion = fechaPublicacion;
        this.areaInteres = areaInteres;
        this.turno = turno;
        this.division = division;
    }


    public InformacionBasicaVacante() {
    }
}

