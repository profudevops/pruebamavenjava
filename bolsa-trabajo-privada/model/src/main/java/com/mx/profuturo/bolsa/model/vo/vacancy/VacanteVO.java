package com.mx.profuturo.bolsa.model.vo.vacancy;

import java.util.ArrayList;

public class VacanteVO {

    InformacionBasicaVacante informacionBasicaVacante;
    InformacionDetalladaVacante informacionDetalladaVacante;
    ArrayList<InformacionBasicaVacante> otrasSugerencias;

    public VacanteVO(InformacionBasicaVacante informacionBasicaVacante, InformacionDetalladaVacante informacionDetalladaVacante, ArrayList<InformacionBasicaVacante> otrasSugerencias) {
        this.informacionBasicaVacante = informacionBasicaVacante;
        this.informacionDetalladaVacante = informacionDetalladaVacante;
        this.otrasSugerencias = otrasSugerencias;
    }

    public InformacionBasicaVacante getInformacionBasicaVacante() {
        return informacionBasicaVacante;
    }

    public void setInformacionBasicaVacante(InformacionBasicaVacante informacionBasicaVacante) {
        this.informacionBasicaVacante = informacionBasicaVacante;
    }

    public InformacionDetalladaVacante getInformacionDetalladaVacante() {
        return informacionDetalladaVacante;
    }

    public void setInformacionDetalladaVacante(InformacionDetalladaVacante informacionDetalladaVacante) {
        this.informacionDetalladaVacante = informacionDetalladaVacante;
    }

    public ArrayList<InformacionBasicaVacante> getOtrasSugerencias() {
        return otrasSugerencias;
    }

    public void setOtrasSugerencias(ArrayList<InformacionBasicaVacante> otrasSugerencias) {
        this.otrasSugerencias = otrasSugerencias;
    }
}
