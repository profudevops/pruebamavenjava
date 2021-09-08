package com.mx.profuturo.bolsa.model.service.vacancies.vo;

public class ResumenVacanteCorp {

    private int idVacante;
    private int idPublicacion;
    private String tituloVacante;
    private int diasActiva;
    private int candidatos;
    private String reclutadora;
    private String estatus;



    public int getIdVacante() {
        return idVacante;
    }

    public void setIdVacante(int idVacante) {
        this.idVacante = idVacante;
    }

    public int getIdPublicacion() {
        return idPublicacion;
    }

    public void setIdPublicacion(int idPublicacion) {
        this.idPublicacion = idPublicacion;
    }

    public String getTituloVacante() {
        return tituloVacante;
    }

    public void setTituloVacante(String tituloVacante) {
        this.tituloVacante = tituloVacante;
    }

    public int getDiasActiva() {
        return diasActiva;
    }

    public void setDiasActiva(int diasActiva) {
        this.diasActiva = diasActiva;
    }

    public int getCandidatos() {
        return candidatos;
    }

    public void setCandidatos(int candidatos) {
        this.candidatos = candidatos;
    }

    public String getReclutadora() {
        return reclutadora;
    }

    public void setReclutadora(String reclutadora) {
        this.reclutadora = reclutadora;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }
}
