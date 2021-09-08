package com.mx.profuturo.bolsa.model.service.candidates.dto;

import java.util.List;

public class ListCandidateInformation {

    private List<CandidateComInfo> resultadoscom = null;
    private List<CandidateCorpInfo> resultadoscorp = null;

    public List<CandidateComInfo> getResultadoscom() {
        return resultadoscom;
    }

    public void setResultadoscom(List<CandidateComInfo> resultadoscom) {
        this.resultadoscom = resultadoscom;
    }

    public List<CandidateCorpInfo> getResultadoscorp() {
        return resultadoscorp;
    }

    public void setResultadoscorp(List<CandidateCorpInfo> resultadoscorp) {
        this.resultadoscorp = resultadoscorp;
    }

}
