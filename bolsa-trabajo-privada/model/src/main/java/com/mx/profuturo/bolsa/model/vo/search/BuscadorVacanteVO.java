package com.mx.profuturo.bolsa.model.vo.search;

public class BuscadorVacanteVO {
    protected int seleccionRegion;
    protected int seleccionAreaInteres;


    public int getSeleccionRegion() {
        return seleccionRegion;
    }

    public void setSeleccionRegion(int seleccionRegion) {
        this.seleccionRegion = seleccionRegion;
    }

    public int getSeleccionAreaInteres() {
        return seleccionAreaInteres;
    }

    public void setSeleccionAreaInteres(int seleccionAreaInteres) {
        this.seleccionAreaInteres = seleccionAreaInteres;
    }

    public BuscadorVacanteVO(int seleccionRegion, int seleccionAreaInteres) {
        this.seleccionRegion = seleccionRegion;
        this.seleccionAreaInteres = seleccionAreaInteres;
    }

    public BuscadorVacanteVO() {
    }
}
