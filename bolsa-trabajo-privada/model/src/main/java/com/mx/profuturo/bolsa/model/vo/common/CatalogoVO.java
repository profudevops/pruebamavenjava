package com.mx.profuturo.bolsa.model.vo.common;

public class CatalogoVO {

    protected Integer id;
    protected String descripcion;
    protected String codigo;

    public CatalogoVO(Integer i, String s) {
        this.id = i;
        this.descripcion = s;
    }

    public CatalogoVO() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
