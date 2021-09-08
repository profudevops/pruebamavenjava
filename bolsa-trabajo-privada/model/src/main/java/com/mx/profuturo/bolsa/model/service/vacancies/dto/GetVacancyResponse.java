package com.mx.profuturo.bolsa.model.service.vacancies.dto;

import java.io.Serializable;
import java.util.List;

public class GetVacancyResponse implements Serializable {

    private List<PostuladosBean> postulados;

    public List<PostuladosBean> getPostulados() {
        return postulados;
    }

    public void setPostulados(List<PostuladosBean> postulados) {
        this.postulados = postulados;
    }
}
