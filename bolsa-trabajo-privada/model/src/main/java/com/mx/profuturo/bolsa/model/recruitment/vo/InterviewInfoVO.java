package com.mx.profuturo.bolsa.model.recruitment.vo;

public class InterviewInfoVO {

    private String fecha;
    private String hora;
    private String candidato;
    private String vacante;
    private String lugar;
    private int idReclutamiento;
    private int idCandidato;


    public int getIdReclutamiento() {
        return idReclutamiento;
    }

    public void setIdReclutamiento(int idReclutamiento) {
        this.idReclutamiento = idReclutamiento;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getCandidato() {
        return candidato;
    }

    public void setCandidato(String candidato) {
        this.candidato = candidato;
    }

    public String getVacante() {
        return vacante;
    }

    public void setVacante(String vacante) {
        this.vacante = vacante;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public int getIdCandidato() {
        return idCandidato;
    }

    public void setIdCandidato(int idCandidato) {
        this.idCandidato = idCandidato;
    }
}
