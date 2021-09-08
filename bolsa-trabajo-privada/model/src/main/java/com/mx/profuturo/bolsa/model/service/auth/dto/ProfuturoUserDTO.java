package com.mx.profuturo.bolsa.model.service.auth.dto;

public class ProfuturoUserDTO extends BasicDTO {

    private int idCusp;
    private String cusp;

    public int getIdCusp() {
        return idCusp;
    }

    public void setIdCusp(int idCusp) {
        this.idCusp = idCusp;
    }

    public String getCusp() {
        return cusp;
    }

    public void setCusp(String cusp) {
        this.cusp = cusp;
    }
}
