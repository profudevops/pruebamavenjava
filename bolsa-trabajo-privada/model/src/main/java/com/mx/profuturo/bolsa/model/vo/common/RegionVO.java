package com.mx.profuturo.bolsa.model.vo.common;

import java.util.ArrayList;

public class RegionVO extends CatalogoVO {

    private ArrayList<SucursalVO> sucursales;

    public ArrayList<SucursalVO> getSucursales() {
        return sucursales;
    }

    public void setSucursales(ArrayList<SucursalVO> sucursales) {
        this.sucursales = sucursales;
    }

    public RegionVO(Integer i, String s, ArrayList<SucursalVO> sucursales) {
        super(i, s);
        this.sucursales = sucursales;
    }


}
