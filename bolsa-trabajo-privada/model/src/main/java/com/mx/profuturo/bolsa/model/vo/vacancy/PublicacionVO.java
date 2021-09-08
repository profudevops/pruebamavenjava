package com.mx.profuturo.bolsa.model.vo.vacancy;


import com.mx.profuturo.bolsa.model.vo.common.RegionVO;

import java.util.ArrayList;

public class PublicacionVO {

    private Integer idPublicacion;
    private ArrayList<RegionVO> sucursales;
    private VacanteVO vacanteVO;
    private ArrayList<InformacionBasicaVacante> sugerencias;

    public Integer getIdPublicacion() {
        return idPublicacion;
    }

    public void setIdPublicacion(Integer idPublicacion) {
        this.idPublicacion = idPublicacion;
    }

    public ArrayList<RegionVO> getSucursales() {
        return sucursales;
    }

    public void setSucursales(ArrayList<RegionVO> sucursales) {
        this.sucursales = sucursales;
    }

    public VacanteVO getVacanteVO() {
        return vacanteVO;
    }

    public void setVacanteVO(VacanteVO vacanteVO) {
        this.vacanteVO = vacanteVO;
    }

    public ArrayList<InformacionBasicaVacante> getSugerencias() {
        return sugerencias;
    }

    public void setSugerencias(ArrayList<InformacionBasicaVacante> sugerencias) {
        this.sugerencias = sugerencias;
    }
}
