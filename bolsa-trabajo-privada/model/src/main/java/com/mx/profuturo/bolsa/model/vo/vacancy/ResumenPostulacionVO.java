package com.mx.profuturo.bolsa.model.vo.vacancy;

import com.mx.profuturo.bolsa.model.vo.Candidato.CandidatoResumenVO;

public class ResumenPostulacionVO extends CandidatoResumenVO {

    private Integer idPostulacion;
    private Integer idPublicacion;

    public Integer getIdPostulacion() {
        return idPostulacion;
    }

    public void setIdPostulacion(Integer idPostulacion) {
        this.idPostulacion = idPostulacion;
    }

    public Integer getIdPublicacion() {
        return idPublicacion;
    }

    public void setIdPublicacion(Integer idPublicacion) {
        this.idPublicacion = idPublicacion;
    }
}
