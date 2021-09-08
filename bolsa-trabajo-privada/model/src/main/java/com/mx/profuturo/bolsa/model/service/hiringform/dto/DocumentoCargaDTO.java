package com.mx.profuturo.bolsa.model.service.hiringform.dto;

public class DocumentoCargaDTO {

    private int idReclutamiento;
    private int idDocumento;
    private String documento;
    private int parte;


    public int getIdReclutamiento() {
        return idReclutamiento;
    }

    public void setIdReclutamiento(int idReclutamiento) {
        this.idReclutamiento = idReclutamiento;
    }

    public int getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(int idDocumento) {
        this.idDocumento = idDocumento;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public int getParte() {
        return parte;
    }

    public void setParte(int parte) {
        this.parte = parte;
    }
}
