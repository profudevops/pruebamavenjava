package com.mx.profuturo.bolsa.model.vo.vacancy;

import com.mx.profuturo.bolsa.model.service.vacancies.vo.ResumenPublicacionesVO;

public class ResumenReclutadorComercialVO extends ResumenPublicacionVO {


    private String sede;
    private int idSede;

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    public int getIdSede() {
        return idSede;
    }

    public void setIdSede(int idSede) {
        this.idSede = idSede;
    }
}
