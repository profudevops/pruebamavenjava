package com.mx.profuturo.bolsa.model.vo.output;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.mx.profuturo.bolsa.model.vo.common.CatalogoVO;
import com.mx.profuturo.bolsa.model.vo.common.OpcionesFormularioVO;
import com.mx.profuturo.bolsa.model.vo.common.RegionVO;
import com.mx.profuturo.bolsa.model.vo.home.ContenidoVO;
import com.mx.profuturo.bolsa.model.vo.search.PaginaResultadosVacanteVO;
import com.mx.profuturo.bolsa.model.vo.vacancy.PublicacionVO;


import java.util.ArrayList;

@JsonInclude(Include.NON_NULL)
public class Respuesta {

    private String status;
    private String statusText;
    private PaginaResultadosVacanteVO vacantes;
    private ArrayList<CatalogoVO> areasInteres;
    private ArrayList<RegionVO> regiones;
    private PublicacionVO publicacionVO;
    private ContenidoVO home;
    private ArrayList<CatalogoVO> estadosMexicanos;
    private OpcionesFormularioVO opcionesFormularioVO;
    private boolean estadoAplicacion;

    public boolean isEstadoAplicacion() {
        return estadoAplicacion;
    }

    public void setEstadoAplicacion(boolean estadoAplicacion) {
        this.estadoAplicacion = estadoAplicacion;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public PaginaResultadosVacanteVO getVacantes() {
        return vacantes;
    }

    public void setVacantes(PaginaResultadosVacanteVO vacantes) {
        this.vacantes = vacantes;
    }

    public ArrayList<CatalogoVO> getAreasInteres() {
        return areasInteres;
    }

    public void setAreasInteres(ArrayList<CatalogoVO> areasInteres) {
        this.areasInteres = areasInteres;
    }

    public ArrayList<RegionVO> getRegiones() {
        return regiones;
    }

    public void setRegiones(ArrayList<RegionVO> regiones) {
        this.regiones = regiones;
    }

    public PublicacionVO getPublicacionVO() {
        return publicacionVO;
    }

    public void setPublicacionVO(PublicacionVO publicacionVO) {
        this.publicacionVO = publicacionVO;
    }

    public ContenidoVO getHome() {
        return home;
    }

    public void setHome(ContenidoVO home) {
        this.home = home;
    }

    public ArrayList<CatalogoVO> getEstadosMexicanos() {
        return estadosMexicanos;
    }

    public void setEstadosMexicanos(ArrayList<CatalogoVO> estadosMexicanos) {
        this.estadosMexicanos = estadosMexicanos;
    }

    public OpcionesFormularioVO getOpcionesFormularioVO() {
        return opcionesFormularioVO;
    }

    public void setOpcionesFormularioVO(OpcionesFormularioVO opcionesFormularioVO) {
        this.opcionesFormularioVO = opcionesFormularioVO;
    }
}