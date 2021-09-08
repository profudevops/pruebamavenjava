package com.mx.profuturo.bolsa.model.vo.vacancy;

import com.mx.profuturo.bolsa.model.vo.common.CatalogoVO;


import java.util.ArrayList;

public class RazonesVacante {

    private String razones;
    private ArrayList<CatalogoVO> beneficios;

    public String getRazones() {
        return razones;
    }

    public void setRazones(String razones) {
        this.razones = razones;
    }

    public ArrayList<CatalogoVO> getBeneficios() {
        return beneficios;
    }

    public void setBeneficios(ArrayList<CatalogoVO> beneficios) {
        this.beneficios = beneficios;
    }
}
