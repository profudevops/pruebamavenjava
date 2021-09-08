package com.mx.profuturo.bolsa.model.vo.vacancy;

import com.mx.profuturo.bolsa.model.vo.common.CatalogoVO;

public class VacanteListaVacanteVO extends CatalogoVO {

    private int idInmueble;

    public int getIdSede() {
        return idInmueble;
    }

    public void setIdSede(int idSede) {
        this.idInmueble = idSede;
    }

    public VacanteListaVacanteVO(Integer i, String s, int idInmueble) {
        super(i, s);
        this.idInmueble = idInmueble;
    }

    public VacanteListaVacanteVO(int idInmueble) {
        this.idInmueble = idInmueble;
    }

    public VacanteListaVacanteVO() { }

}
