package com.mx.profuturo.bolsa.model.vo.vacancy;


public class ResumenPublicacionVO {

    private Integer idVacante;
    private Integer idPublicacion;
    private String tituloVacante;
    private Integer diasActiva;
    private Integer candidatos;
    private String estatus;


    public Integer getIdVacante() {
        return idVacante;
    }

    public void setIdVacante(Integer idVacante) {
        this.idVacante = idVacante;
    }

    public Integer getIdPublicacion() {
        return idPublicacion;
    }

    public void setIdPublicacion(Integer idPublicacion) {
        this.idPublicacion = idPublicacion;
    }


    public String getTituloVacante() {
        return tituloVacante;
    }

    public Integer getDiasActiva() {
        return diasActiva;
    }

    public void setDiasActiva(Integer diasActiva) {
        this.diasActiva = diasActiva;
    }

    public Integer getCandidatos() {
        return candidatos;
    }

    public void setCandidatos(Integer candidatos) {
        this.candidatos = candidatos;
    }

    public void setTituloVacante(String tituloVacante) {
        this.tituloVacante = tituloVacante;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }
}

