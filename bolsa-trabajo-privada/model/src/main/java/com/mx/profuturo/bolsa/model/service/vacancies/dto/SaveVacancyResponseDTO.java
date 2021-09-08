package com.mx.profuturo.bolsa.model.service.vacancies.dto;

import com.mx.profuturo.bolsa.model.service.auth.dto.BasicDTO;

public class SaveVacancyResponseDTO extends BasicDTO {
    private Integer idPublicacion;


    public Integer getIdPublicacion() {
        return idPublicacion;
    }

    public void setIdPublicacion(Integer idPublicacion) {
        this.idPublicacion = idPublicacion;
    }
}
