package com.mx.profuturo.bolsa.model.service.hiringform.dto;

import com.mx.profuturo.bolsa.model.vo.common.BasicCatalogVO;
import com.mx.profuturo.bolsa.model.vo.common.CatalogoVO;

import java.util.LinkedList;

public class DocumentoProfuturoDTO extends BasicCatalogVO {

    private Integer idReclutamiento;
    private LinkedList<DocumentoDTO> options;


    public Integer getIdReclutamiento() {
        return idReclutamiento;
    }

    public void setIdReclutamiento(Integer idReclutamiento) {
        this.idReclutamiento = idReclutamiento;
    }

    public LinkedList<DocumentoDTO> getOptions() {
        return options;
    }

    public void setOptions(LinkedList<DocumentoDTO> options) {
        this.options = options;
    }
}
