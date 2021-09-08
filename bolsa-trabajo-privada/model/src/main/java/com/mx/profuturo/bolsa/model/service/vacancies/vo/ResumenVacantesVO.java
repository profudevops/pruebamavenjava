package com.mx.profuturo.bolsa.model.service.vacancies.vo;

import com.mx.profuturo.bolsa.model.service.vacancies.dto.InformacionGeneralVacanteMaestraVO;
import com.mx.profuturo.bolsa.model.vo.vacancy.ResumenPublicacionVO;
import com.mx.profuturo.bolsa.model.vo.vacancy.ResumenReclutadorComercialVO;
import com.mx.profuturo.bolsa.model.vo.vacancy.VacanteGerenteComercialVO;

import java.util.LinkedList;

public class ResumenVacantesVO {
    private ResumenPublicacionesVO resumenPublicaciones;
    private LinkedList<InformacionGeneralVacanteMaestraVO> vacantesMaestras;
    private VacanteGerenteComercialVO vacantesReclutadorComercial;
    private LinkedList<ResumenVacanteMaestraAnalistaVO> vacantesMaestrasAnalista;
    private LinkedList<ResumenVacanteMaestraAnalistaVO> vacantesMaestrasAtnClientes;
    private ResumenVacantesAdminCorpVO resumenVacantesAdminCorpVO;
    private ResumenVacantesAdminCorpVO resumenObservadorComercial;

    public ResumenPublicacionesVO getResumenPublicaciones() {
        return resumenPublicaciones;
    }

    public void setResumenPublicaciones(ResumenPublicacionesVO resumenPublicaciones) {
        this.resumenPublicaciones = resumenPublicaciones;
    }

    public LinkedList<InformacionGeneralVacanteMaestraVO> getVacantesMaestras() {
        return vacantesMaestras;
    }

    public void setVacantesMaestras(LinkedList<InformacionGeneralVacanteMaestraVO> vacantesMaestras) {
        this.vacantesMaestras = vacantesMaestras;
    }

    public VacanteGerenteComercialVO getVacantesReclutadorComercial() {
        return vacantesReclutadorComercial;
    }

    public void setVacantesReclutadorComercial(VacanteGerenteComercialVO vacantesReclutadorComercial) {
        this.vacantesReclutadorComercial = vacantesReclutadorComercial;
    }

    public LinkedList<ResumenVacanteMaestraAnalistaVO> getVacantesMaestrasAnalista() {
        return vacantesMaestrasAnalista;
    }

    public void setVacantesMaestrasAnalista(LinkedList<ResumenVacanteMaestraAnalistaVO> vacantesMaestrasAnalista) {
        this.vacantesMaestrasAnalista = vacantesMaestrasAnalista;
    }

    public LinkedList<ResumenVacanteMaestraAnalistaVO> getVacantesMaestrasAtnClientes() {
        return vacantesMaestrasAtnClientes;
    }

    public void setVacantesMaestrasAtnClientes(LinkedList<ResumenVacanteMaestraAnalistaVO> vacantesMaestrasAtnClientes) {
        this.vacantesMaestrasAtnClientes = vacantesMaestrasAtnClientes;
    }

    public ResumenVacantesAdminCorpVO getResumenVacantesAdminCorpVO() {
        return resumenVacantesAdminCorpVO;
    }

    public void setResumenVacantesAdminCorpVO(ResumenVacantesAdminCorpVO resumenVacantesAdminCorpVO) {
        this.resumenVacantesAdminCorpVO = resumenVacantesAdminCorpVO;
    }

    public ResumenVacantesAdminCorpVO getResumenObservadorComercial() {
        return resumenObservadorComercial;
    }

    public void setResumenObservadorComercial(ResumenVacantesAdminCorpVO resumenObservadorComercial) {
        this.resumenObservadorComercial = resumenObservadorComercial;
    }
}

