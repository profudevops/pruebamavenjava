package com.mx.profuturo.bolsa.model.service.vacancies.vo;

import com.mx.profuturo.bolsa.model.vo.common.BasicCatalogVO;

public class CodigoVO extends BasicCatalogVO {

    private int idPublicacion;
    private int idBolsaExterna;
    private boolean activo;
    private String vigencia;

    public int getIdPublicacion() {
        return idPublicacion;
    }

    public void setIdPublicacion(int idPublicacion) {
        this.idPublicacion = idPublicacion;
    }

    public int getIdBolsaExterna() {
        return idBolsaExterna;
    }

    public void setIdBolsaExterna(int idBolsaExterna) {
        this.idBolsaExterna = idBolsaExterna;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getVigencia() {
        return vigencia;
    }

    public void setVigencia(String vigencia) {
        this.vigencia = vigencia;
    }
}

