package com.mx.profuturo.bolsa.model.wallet.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mx.profuturo.bolsa.model.vo.common.CatalogoVO;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class WalletFiltersVO implements Serializable {

	private String modo;
	private ArrayList<CatalogoVO> calificaciones;
	private ArrayList<CatalogoVO> areasInteres;
	private ArrayList<CatalogoVO> subAreasInteres;
	private ArrayList<CatalogoVO> tiempoEnCartera;
	private ArrayList<CatalogoVO> edad;
	private ArrayList<CatalogoVO> escolaridad;
	private ArrayList<CatalogoVO> ubicacion;
	private Integer paginaActual;
	private Integer totalPaginas;

	public String getModo() {
		return modo;
	}

	public void setModo(String modo) {
		this.modo = modo;
	}

	public ArrayList<CatalogoVO> getCalificaciones() {
		return calificaciones;
	}

	public void setCalificaciones(ArrayList<CatalogoVO> calificaciones) {
		this.calificaciones = calificaciones;
	}

	public ArrayList<CatalogoVO> getAreasInteres() {
		return areasInteres;
	}

	public void setAreasInteres(ArrayList<CatalogoVO> areasInteres) {
		this.areasInteres = areasInteres;
	}

	public ArrayList<CatalogoVO> getSubAreasInteres() {
		return subAreasInteres;
	}

	public void setSubAreasInteres(ArrayList<CatalogoVO> subAreasInteres) {
		this.subAreasInteres = subAreasInteres;
	}

	public ArrayList<CatalogoVO> getTiempoEnCartera() {
		return tiempoEnCartera;
	}

	public void setTiempoEnCartera(ArrayList<CatalogoVO> tiempoEnCartera) {
		this.tiempoEnCartera = tiempoEnCartera;
	}

	public ArrayList<CatalogoVO> getEdad() {
		return edad;
	}

	public void setEdad(ArrayList<CatalogoVO> edad) {
		this.edad = edad;
	}

	public ArrayList<CatalogoVO> getEscolaridad() {
		return escolaridad;
	}

	public void setEscolaridad(ArrayList<CatalogoVO> escolaridad) {
		this.escolaridad = escolaridad;
	}

	public ArrayList<CatalogoVO> getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(ArrayList<CatalogoVO> ubicacion) {
		this.ubicacion = ubicacion;
	}

	public Integer getPaginaActual() {
		return paginaActual;
	}

	public void setPaginaActual(Integer paginaActual) {
		this.paginaActual = paginaActual;
	}

	public Integer getTotalPaginas() {
		return totalPaginas;
	}

	public void setTotalPaginas(Integer totalPaginas) {
		this.totalPaginas = totalPaginas;
	}
}
