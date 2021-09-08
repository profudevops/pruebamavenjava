package com.mx.profuturo.bolsa.model.service.hiringform.dto;

import com.mx.profuturo.bolsa.model.vo.common.BasicCatalogVO;

public class DocumentoDTO extends BasicCatalogVO {

    private int partes;
    private int min;


    public int getPartes() {
        return partes;
    }

    public void setPartes(int partes) {
        this.partes = partes;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }
}
