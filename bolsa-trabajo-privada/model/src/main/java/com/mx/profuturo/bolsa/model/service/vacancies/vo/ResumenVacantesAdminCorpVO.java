package com.mx.profuturo.bolsa.model.service.vacancies.vo;

import com.mx.profuturo.bolsa.model.service.vacancies.dto.InformacionGeneralVacanteMaestraVO;

import java.util.LinkedList;

public class ResumenVacantesAdminCorpVO {

    private LinkedList<ResumenVacanteCorp> vacantes;
    private LinkedList<InformacionGeneralVacanteMaestraVO> vacantesMaestrasAnalistaClientes;
    private int paginaActual;
    private int totalPaginas;


    public int getPaginaActual() {
        return paginaActual;
    }

    public void setPaginaActual(int paginaActual) {
        this.paginaActual = paginaActual;
    }

    public int getTotalPaginas() {
        return totalPaginas;
    }

    public void setTotalPaginas(int totalPaginas) {
        this.totalPaginas = totalPaginas;
    }

    public LinkedList<ResumenVacanteCorp> getVacantes() {
        return vacantes;
    }

    public void setVacantes(LinkedList<ResumenVacanteCorp> vacantes) {
        this.vacantes = vacantes;
    }

    public LinkedList<InformacionGeneralVacanteMaestraVO> getVacantesMaestrasAnalistaClientes() {
        return vacantesMaestrasAnalistaClientes;
    }

    public void setVacantesMaestrasAnalistaClientes(LinkedList<InformacionGeneralVacanteMaestraVO> vacantesMaestrasAnalistaClientes) {
        this.vacantesMaestrasAnalistaClientes = vacantesMaestrasAnalistaClientes;
    }
}
