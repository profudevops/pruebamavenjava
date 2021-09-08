package com.mx.profuturo.bolsa.model.service.hiringform.vo;

public class CandidatoContratacionVO {

	private int idProceso;
	private String nombreCandidato;
	private int idCandidato;
    private int etapaFormulario;
    private int status;



	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getIdProceso() {
		return idProceso;
	}

	public void setIdProceso(int idProceso) {
		this.idProceso = idProceso;
	}

	public int getEtapaFormulario() {
        return etapaFormulario;
    }

    public void setEtapaFormulario(int etapaFormulario) {
        this.etapaFormulario = etapaFormulario;
    }

	public String getNombreCandidato() {
		return nombreCandidato;
	}
	public void setNombreCandidato(String nombreCandidato) {
		this.nombreCandidato = nombreCandidato;
	}
	public int getIdCandidato() {
		return idCandidato;
	}
	public void setIdCandidato(int idCandidato) {
		this.idCandidato = idCandidato;
	}
}
