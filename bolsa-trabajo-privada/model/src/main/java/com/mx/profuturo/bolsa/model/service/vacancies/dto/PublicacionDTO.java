package com.mx.profuturo.bolsa.model.service.vacancies.dto;

import com.mx.profuturo.bolsa.model.service.vacancies.base.Publicacion;

import java.util.ArrayList;

public class PublicacionDTO extends Publicacion {

    private Integer idPublicacion;
    private Integer idVacante;
    private ArrayList<Integer> idBolsasExternas;

    public Integer getIdPublicacion() {
        return idPublicacion;
    }

    public void setIdPublicacion(Integer idPublicacion) {
        this.idPublicacion = idPublicacion;
    }

    public ArrayList<Integer> getIdBolsasExternas() {
        return idBolsasExternas;
    }

    public void setIdBolsasExternas(ArrayList<Integer> idBolsasExternas) {
        this.idBolsasExternas = idBolsasExternas;
    }

	public Integer getIdVacante() {
		return idVacante;
	}

	public void setIdVacante(Integer idVacante) {
		this.idVacante = idVacante;
	}
    
}
