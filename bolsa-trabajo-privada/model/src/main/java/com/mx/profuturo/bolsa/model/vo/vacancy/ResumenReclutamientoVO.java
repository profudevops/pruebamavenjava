package com.mx.profuturo.bolsa.model.vo.vacancy;

public class ResumenReclutamientoVO extends ResumenPostulacionVO {
    private Integer idReclutamiento;
    private String descripcionStatus;

    public Integer getIdReclutamiento() {
        return idReclutamiento;
    }

    public void setIdReclutamiento(Integer idReclutamiento) {
        this.idReclutamiento = idReclutamiento;
    }

    public String getDescripcionStatus() {
        return descripcionStatus;
    }

    public void setDescripcionStatus(String descripcionStatus) {
        this.descripcionStatus = descripcionStatus;
    }
}
