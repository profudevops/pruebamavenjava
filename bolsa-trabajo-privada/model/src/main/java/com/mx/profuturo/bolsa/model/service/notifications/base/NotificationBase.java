package com.mx.profuturo.bolsa.model.service.notifications.base;

public class NotificationBase {

    private String codigo;
    private String modo;
    private int idCandidato;
    private int idReclutamiento;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getModo() {
        return modo;
    }

    public void setModo(String modo) {
        this.modo = modo;
    }

    public int getIdCandidato() {
        return idCandidato;
    }

    public void setIdCandidato(int idCandidato) {
        this.idCandidato = idCandidato;
    }

    public int getIdReclutamiento() {
        return idReclutamiento;
    }

    public void setIdReclutamiento(int idReclutamiento) {
        this.idReclutamiento = idReclutamiento;
    }
}
