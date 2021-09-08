package com.mx.profuturo.bolsa.model.service.vacancies.vo;

import java.util.ArrayList;

public class ReclutamientoPublicacionVO {

    private Integer idPublicacion;
    private Integer cantidadCandidatosPostulados;
    private Integer cantidadCandidatosProceso;
    private ArrayList<CandidatoDatosBasicosVO> candidatosPostulados;
    private ArrayList<CandidatoDatosBasicosVO> candidatosSugeridos;
    private ArrayList<CandidatoDatosBasicosVO> candidatosProceso;

    public Integer getIdPublicacion() {
        return idPublicacion;
    }

    public void setIdPublicacion(Integer idPublicacion) {
        this.idPublicacion = idPublicacion;
    }

    public Integer getCantidadCandidatosPostulados() {
        return cantidadCandidatosPostulados;
    }

    public void setCantidadCandidatosPostulados(Integer cantidadCandidatosPostulados) {
        this.cantidadCandidatosPostulados = cantidadCandidatosPostulados;
    }

    public Integer getCantidadCandidatosProceso() {
        return cantidadCandidatosProceso;
    }

    public void setCantidadCandidatosProceso(Integer cantidadCandidatosProceso) {
        this.cantidadCandidatosProceso = cantidadCandidatosProceso;
    }

    public ArrayList<CandidatoDatosBasicosVO> getCandidatosPostulados() {
        return candidatosPostulados;
    }

    public void setCandidatosPostulados(ArrayList<CandidatoDatosBasicosVO> candidatosPostulados) {
        this.candidatosPostulados = candidatosPostulados;
    }

    public ArrayList<CandidatoDatosBasicosVO> getCandidatosSugeridos() {
        return candidatosSugeridos;
    }

    public void setCandidatosSugeridos(ArrayList<CandidatoDatosBasicosVO> candidatosSugeridos) {
        this.candidatosSugeridos = candidatosSugeridos;
    }


}

