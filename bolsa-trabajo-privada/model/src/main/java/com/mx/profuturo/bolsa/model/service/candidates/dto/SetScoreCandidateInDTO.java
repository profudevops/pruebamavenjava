package com.mx.profuturo.bolsa.model.service.candidates.dto;

import java.util.LinkedList;

public class SetScoreCandidateInDTO {
	private Integer id;
	private Integer calificacion;
	private Integer idCandidato;
	private int idCalificacion;
	private LinkedList<Integer> subCalificacion;

	public Integer getIdCandidato() {
		return idCandidato;
	}

	public void setIdCandidato(Integer idCandidato) {
		this.idCandidato = idCandidato;
	}

	public int getIdCalificacion() {
		return idCalificacion;
	}

	public void setIdCalificacion(int idCalificacion) {
		this.idCalificacion = idCalificacion;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(Integer calificacion) {
		this.calificacion = calificacion;
	}

	public LinkedList<Integer> getSubCalificacion() { return subCalificacion; }

	public void setSubCalificacion(LinkedList<Integer> subCalificacion) { this.subCalificacion = subCalificacion; }
}
