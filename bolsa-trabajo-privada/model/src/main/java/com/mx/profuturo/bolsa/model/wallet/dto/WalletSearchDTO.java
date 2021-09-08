package com.mx.profuturo.bolsa.model.wallet.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mx.profuturo.bolsa.model.vo.common.CatalogoVO;

import java.util.ArrayList;
import java.util.LinkedList;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class WalletSearchDTO {
	
	
	private String modo;

	private String busqueda;
	private ArrayList<Integer> calificacion;
	private ArrayList<Integer> areaInteres;
	private ArrayList<Integer> subAreaInteres;
	private ArrayList<Integer> tiempoEnCartera;
	private ArrayList<Integer> edad;
	private ArrayList<Integer> escolaridad;
	private ArrayList<Integer> ubicacion;
	private Integer paginaActual;

	public Integer getPaginaActual() {
		return paginaActual;
	}

	public void setPaginaActual(Integer paginaActual) {
		this.paginaActual = paginaActual;
	}

	public String getBusqueda() {
		return busqueda;
	}

	public void setBusqueda(String busqueda) {
		this.busqueda = busqueda;
	}

	public String getModo() {
		return modo;
	}

	public void setModo(String modo) {
		this.modo = modo;
	}

	public ArrayList<Integer> getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(ArrayList<Integer> calificacion) {
		this.calificacion = calificacion;
	}

	public ArrayList<Integer> getAreaInteres() {
		return areaInteres;
	}

	public void setAreaInteres(ArrayList<Integer> areaInteres) {
		this.areaInteres = areaInteres;
	}

	public ArrayList<Integer> getSubAreaInteres() {
		return subAreaInteres;
	}

	public void setSubAreaInteres(ArrayList<Integer> subAreaInteres) {
		this.subAreaInteres = subAreaInteres;
	}

	public ArrayList<Integer> getTiempoEnCartera() {
		return tiempoEnCartera;
	}

	public void setTiempoEnCartera(ArrayList<Integer> tiempoEnCartera) {
		this.tiempoEnCartera = tiempoEnCartera;
	}

	public ArrayList<Integer> getEdad() {
		return edad;
	}

	public void setEdad(ArrayList<Integer> edad) {
		this.edad = edad;
	}

	public ArrayList<Integer> getEscolaridad() {
		return escolaridad;
	}

	public void setEscolaridad(ArrayList<Integer> escolaridad) {
		this.escolaridad = escolaridad;
	}

	public ArrayList<Integer> getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(ArrayList<Integer> ubicacion) {
		this.ubicacion = ubicacion;
	}
}
