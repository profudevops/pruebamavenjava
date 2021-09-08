package com.mx.profuturo.bolsa.model.service.dto;

import com.mx.profuturo.bolsa.model.vo.home.Galeria;
import com.mx.profuturo.bolsa.model.vo.home.SeccionVO;
import com.mx.profuturo.bolsa.model.vo.search.BuscadorVacanteVO;

import java.util.Map;

public class ContenidoHomeDTO {

    private SeccionVO cabecera;
    private BuscadorVacanteVO buscador;
    private SeccionVO buscadorCodigo;
    private SeccionVO quienesSomos;
    private SeccionVO cultura;
    private Galeria galeria;


    public SeccionVO getCabecera() {
        return cabecera;
    }

    public void setCabecera(SeccionVO cabecera) {
        this.cabecera = cabecera;
    }

    public BuscadorVacanteVO getBuscador() {
        return buscador;
    }

    public void setBuscador(BuscadorVacanteVO buscador) {
        this.buscador = buscador;
    }

    public SeccionVO getBuscadorCodigo() {
        return buscadorCodigo;
    }

    public void setBuscadorCodigo(SeccionVO buscadorCodigo) {
        this.buscadorCodigo = buscadorCodigo;
    }

    public SeccionVO getQuienesSomos() {
        return quienesSomos;
    }

    public void setQuienesSomos(SeccionVO quienesSomos) {
        this.quienesSomos = quienesSomos;
    }

    public SeccionVO getCultura() {
        return cultura;
    }

    public void setCultura(SeccionVO cultura) {
        this.cultura = cultura;
    }

    public Galeria getGaleria() {
        return galeria;
    }

    public void setGaleria(Galeria galeria) {
        this.galeria = galeria;
    }
}
