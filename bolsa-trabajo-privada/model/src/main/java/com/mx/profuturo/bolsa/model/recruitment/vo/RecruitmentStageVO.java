package com.mx.profuturo.bolsa.model.recruitment.vo;

public class RecruitmentStageVO {

	protected Integer idFase;
	protected String descripcion;
	protected String codigo;
	protected String status;
	protected String signal;

	
	
	public Integer getIdFase() {
		return idFase;
	}
	public void setIdFase(Integer idFase) {
		this.idFase = idFase;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public RecruitmentStageVO() {
		
	}
	
	public RecruitmentStageVO(String codigo, String descripcion){
		this.codigo = codigo;
		this.descripcion = descripcion;
	}
	
	
	
	public String getSignal() {
		return signal;
	}
	public void setSignal(String signal) {
		this.signal = signal;
	}
	
	public RecruitmentStageVO(String codeMode, String descripcion, String signal) {
		this(codeMode, descripcion);
		this.signal = signal;
	}
	
	 
}
