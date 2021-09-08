package com.mx.profuturo.bolsa.model.service.hiringform.dto;

import com.mx.profuturo.bolsa.model.service.hiringform.vo.DatosFormularioVO;

public class TerminarFormularioDTO {

    private int idProceso;
    private DatosFormularioVO datosFormularioVO;


    public int getIdProceso() {
        return idProceso;
    }

    public void setIdProceso(int idProceso) {
        this.idProceso = idProceso;
    }

    public DatosFormularioVO getDatosFormularioVO() {
        return datosFormularioVO;
    }

    public void setDatosFormularioVO(DatosFormularioVO datosFormularioVO) {
        this.datosFormularioVO = datosFormularioVO;
    }
}
