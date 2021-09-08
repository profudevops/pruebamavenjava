package com.mx.profuturo.bolsa.model.recruitment.dto.form;

public class Reject {

protected String continuar;
	
	public String getContinuar() {
		return continuar;
	}

	public void setContinuar(String continuar) {
		this.continuar = continuar;
	}

	
	protected Integer motivoRechazo = 0;
	
	public Integer getMotivoRechazo() {
		return  (!("SI".equals(this.continuar)
				|| "n/a".equals(this.continuar)))
				?motivoRechazo:0;
	}

	public void setMotivoRechazo(Integer motivoRechazo) {
		this.motivoRechazo = motivoRechazo;
	}
}
