package com.mx.profuturo.bolsa.model.service.areasinteres.dto;

import java.io.Serializable;

import com.mx.profuturo.bolsa.model.service.BaseResponseBean;

public class GenericTextResponseBean extends BaseResponseBean implements Serializable {

    private String respuesta;
 
    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

}
