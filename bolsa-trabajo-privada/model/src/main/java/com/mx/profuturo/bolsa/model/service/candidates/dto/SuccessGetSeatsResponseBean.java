package com.mx.profuturo.bolsa.model.service.candidates.dto;

import java.util.List;

import com.mx.profuturo.bolsa.model.service.vacancies.dto.ObtenerPlazasAbiertasPorSedeDTO;
import com.mx.profuturo.bolsa.model.vo.Candidato.CandidatoDetallesVO;

public class SuccessGetSeatsResponseBean {
	private boolean success;
	private List<ObtenerPlazasAbiertasPorSedeDTO> data;
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public List<ObtenerPlazasAbiertasPorSedeDTO> getData() {
		return data;
	}
	public void setData(List<ObtenerPlazasAbiertasPorSedeDTO> data) {
		this.data = data;
	}
	
}
