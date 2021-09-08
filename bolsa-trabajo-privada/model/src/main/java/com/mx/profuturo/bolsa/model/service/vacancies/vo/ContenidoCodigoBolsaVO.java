package com.mx.profuturo.bolsa.model.service.vacancies.vo;

import com.mx.profuturo.bolsa.model.vo.common.BasicCatalogVO;

public class ContenidoCodigoBolsaVO {

    private String bolsaTrabajo;
    private String codigo;
    private String contenido;

    public String getBolsaTrabajo() {
        return bolsaTrabajo;
    }

    public void setBolsaTrabajo(String bolsaTrabajo) {
        this.bolsaTrabajo = bolsaTrabajo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
}
