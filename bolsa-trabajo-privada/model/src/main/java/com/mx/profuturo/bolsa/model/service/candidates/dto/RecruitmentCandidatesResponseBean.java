package com.mx.profuturo.bolsa.model.service.candidates.dto;

import java.io.Serializable;
import java.util.List;

public class RecruitmentCandidatesResponseBean implements Serializable {

    private List<CandidatesBean> Respuesta;

    public List<CandidatesBean> getRespuesta() {
        return Respuesta;
    }

    public void setRespuesta(List<CandidatesBean> respuesta) {
        Respuesta = respuesta;
    }

}
