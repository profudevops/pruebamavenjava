package com.mx.profuturo.bolsa.model.recruitment.vo;

public class RecruitmentPhaseDetailVO extends RecruitmentStageVO {
	
	private Boolean obligatoria;
	private Boolean puedeTerminarse;
	private Boolean cambiaStatus;
	private Boolean esEntrevista;
	private Integer statusEntrevista; //por agendar, por documentar, completada por alguien mas, 
	protected Boolean terminada;
	protected Boolean habilitada;

	
	private Boolean aprobado;
	private Integer idMotivoRechazo;
	
	
	public Boolean getObligatoria() {
		return obligatoria;
	}
	public void setObligatoria(Boolean obligatoria) {
		this.obligatoria = obligatoria;
	}
	public Boolean getPuedeTerminarse() {
		return puedeTerminarse;
	}
	public void setPuedeTerminarse(Boolean puedeTerminarse) {
		this.puedeTerminarse = puedeTerminarse;
	}
	public Boolean getCambiaStatus() {
		return cambiaStatus;
	}
	public void setCambiaStatus(Boolean cambiaStatus) {
		this.cambiaStatus = cambiaStatus;
	}
	public Boolean getEsEntrevista() {
		return esEntrevista;
	}
	public void setEsEntrevista(Boolean esEntrevista) {
		this.esEntrevista = esEntrevista;
	}
	public Integer getStatusEntrevista() {
		return statusEntrevista;
	}
	public void setStatusEntrevista(Integer statusEntrevista) {
		this.statusEntrevista = statusEntrevista;
	}
	public Boolean getAprobado() {
		return aprobado;
	}
	public void setAprobado(Boolean aprobado) {
		this.aprobado = aprobado;
	}
	public Integer getIdMotivoRechazo() {
		return idMotivoRechazo;
	}
	public void setIdMotivoRechazo(Integer idMotivoRechazo) {
		this.idMotivoRechazo = idMotivoRechazo;
	}


}
