package com.mx.profuturo.bolsa.model.vo.common;

public class SucursalVO extends CatalogoVO {

    private String coordenadas;

    public String getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(String coordenadas) {
        this.coordenadas = coordenadas;
    }

    public SucursalVO(int i, String s, String coordenadas) {
        super(i, s);
        this.coordenadas = coordenadas;
    }

    public SucursalVO(String coordenadas) {
        super();
        this.coordenadas = coordenadas;
    }
}

