package com.mx.profuturo.bolsa.model.service.sample;

public class UserInfoRequestBean {

    private Integer idClienteUnico;
    private String nombre;
    private String clave;

    public void setIdClienteUnico(Integer idClienteUnico) {
        this.idClienteUnico = idClienteUnico;
    }

    public Integer getIdClienteUnico() {
        return idClienteUnico;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
}
