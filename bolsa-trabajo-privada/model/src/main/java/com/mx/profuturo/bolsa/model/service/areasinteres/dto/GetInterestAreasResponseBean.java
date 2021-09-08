package com.mx.profuturo.bolsa.model.service.areasinteres.dto;

import java.io.Serializable;
import java.util.List;

public class GetInterestAreasResponseBean implements Serializable {

    private List<AreaInteresDTO> areas;

    public List<AreaInteresDTO> getAreas() {
        return areas;
    }

    public void setAreas(List<AreaInteresDTO> areas) {
        this.areas = areas;
    }
}
