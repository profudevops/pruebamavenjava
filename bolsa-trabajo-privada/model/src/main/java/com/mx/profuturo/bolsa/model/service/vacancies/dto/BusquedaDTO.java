package com.mx.profuturo.bolsa.model.service.vacancies.dto;

import com.mx.profuturo.bolsa.model.service.auth.dto.BasicDTO;

public class BusquedaDTO{
    private Integer idPropietario;
    private String consulta;
    private String rol;

    public String getConsulta() {
        return consulta;
    }

    public void setConsulta(String consulta) {
        this.consulta = consulta;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Integer getIdPropietario() {
        return idPropietario;
    }

    public void setIdPropietario(Integer idPropietario) {
        this.idPropietario = idPropietario;
    }
}
