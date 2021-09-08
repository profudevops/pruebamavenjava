package com.mx.profuturo.bolsa.model.reports.vo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.mx.profuturo.bolsa.model.service.areasinteres.dto.AreaInteresDTO;
import com.mx.profuturo.bolsa.model.service.vacancies.vo.PersonaProfuturoVO;
import com.mx.profuturo.bolsa.model.vo.common.CatalogoVO;
import com.mx.profuturo.bolsa.model.vo.common.SCatalogoVO;


public class FilterVO {
	
	private String rama;
	//CORP
	private  ArrayList<CatalogoVO> tipoVacante;
	private List<AreaInteresDTO> areaInteres;
	private ArrayList<CatalogoVO> subAreaInteres;
	private ArrayList<CatalogoVO> centroCostos;
	private ArrayList<CatalogoVO> rangoVacante;
	private LinkedList<PersonaProfuturoVO> analistas;
	private ArrayList<CatalogoVO> nivelPuesto;
	//COM
	private LinkedList<CatalogoVO> divisiones;
	private ArrayList<CatalogoVO> regionales;
	private ArrayList<CatalogoVO> gerencias;
	//MISC
	
	private ArrayList<CatalogoVO> periodo;
	private ArrayList<SCatalogoVO> observador;
	

	

	public ArrayList<SCatalogoVO> getObservador() {
		return observador;
	}
	public void setObservador(ArrayList<SCatalogoVO> observador) {
		this.observador = observador;
	}
	public ArrayList<CatalogoVO> getRangoVacante() {
		return rangoVacante;
	}
	public void setRangoVacante(ArrayList<CatalogoVO> rangoVacante) {
		this.rangoVacante = rangoVacante;
	}
	public ArrayList<CatalogoVO> getTipoVacante() {
		return tipoVacante;
	}
	public void setTipoVacante(ArrayList<CatalogoVO> tipoVacante) {
		this.tipoVacante = tipoVacante;
	}
	public ArrayList<CatalogoVO> getSubAreaInteres() {
		return subAreaInteres;
	}
	public void setSubAreaInteres(ArrayList<CatalogoVO> subAreaInteres) {
		this.subAreaInteres = subAreaInteres;
	}
	public ArrayList<CatalogoVO> getCentroCostos() {
		return centroCostos;
	}
	public void setCentroCostos(ArrayList<CatalogoVO> centroCostos) {
		this.centroCostos = centroCostos;
	}
	public LinkedList<CatalogoVO> getDivisiones() {
		return divisiones;
	}
	public void setDivisiones(LinkedList<CatalogoVO> divisiones) {
		this.divisiones = divisiones;
	}
	public ArrayList<CatalogoVO> getRegionales() {
		return regionales;
	}
	public void setRegionales(ArrayList<CatalogoVO> regionales) {
		this.regionales = regionales;
	}
	public ArrayList<CatalogoVO> getGerencias() {
		return gerencias;
	}
	public void setGerencias(ArrayList<CatalogoVO> gerencias) {
		this.gerencias = gerencias;
	}
	public String getRama() {
		return rama;
	}
	public void setRama(String rama) {
		this.rama = rama;
	}
	public ArrayList<CatalogoVO> getPeriodo() {
		return periodo;
	}
	public void setPeriodo(ArrayList<CatalogoVO> periodo) {
		this.periodo = periodo;
	}
	
	public ArrayList<CatalogoVO> getNivelPuesto() {
		return nivelPuesto;
	}
	public void setNivelPuesto(ArrayList<CatalogoVO> nivelPuesto) {
		this.nivelPuesto = nivelPuesto;
	}
	public List<AreaInteresDTO> getAreaInteres() {
		return areaInteres;
	}
	public void setAreaInteres(List<AreaInteresDTO> areaInteres) {
		this.areaInteres = areaInteres;
	}
	public LinkedList<PersonaProfuturoVO> getAnalistas() {
		return analistas;
	}
	public void setAnalistas(LinkedList<PersonaProfuturoVO> analistas) {
		this.analistas = analistas;
	}
	
	
	
}
