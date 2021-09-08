package com.mx.profuturo.bolsa.model.service.vacancies.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mx.profuturo.bolsa.model.service.auth.dto.BasicDTO;
import com.mx.profuturo.bolsa.model.service.vacancies.dto.GuardarEstrategiaCorpDTO;
import com.mx.profuturo.bolsa.model.vo.common.CatalogoVO;


import java.util.ArrayList;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class EstrategiaVO extends GuardarEstrategiaCorpDTO {



    private String correoObservador;
    private Integer idPublicacion;
    private String numeroEmpleadoObservador;

    public String getCorreoObservador() {
        return correoObservador;
    }

    public void setCorreoObservador(String correoObservador) {
        this.correoObservador = correoObservador;
    }

    public Integer getIdPublicacion() {
        return idPublicacion;
    }

    public void setIdPublicacion(Integer idPublicacion) {
        this.idPublicacion = idPublicacion;
    }

    public String getNumeroEmpleadoObservador() {
        return numeroEmpleadoObservador;
    }

    public void setNumeroEmpleadoObservador(String numeroEmpleadoObservador) {
        this.numeroEmpleadoObservador = numeroEmpleadoObservador;
    }
}
