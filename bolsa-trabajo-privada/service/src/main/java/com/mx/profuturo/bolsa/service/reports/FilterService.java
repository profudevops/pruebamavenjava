package com.mx.profuturo.bolsa.service.reports;

import java.util.ArrayList;
import java.util.LinkedList;

import com.mx.profuturo.bolsa.model.reports.vo.FilterVO;
import com.mx.profuturo.bolsa.model.vo.common.CatalogoVO;
import com.mx.profuturo.bolsa.model.vo.common.SCatalogoVO;
import com.mx.profuturo.bolsa.util.exception.custom.GenericStatusException;

public interface FilterService {

	ArrayList<CatalogoVO> getAnalistasCorporativo() throws GenericStatusException;

	ArrayList<CatalogoVO> getPeriodos()throws GenericStatusException;

	ArrayList<CatalogoVO> getTipoVacante()throws GenericStatusException;

	ArrayList<CatalogoVO> getRangoVacante()throws GenericStatusException;

	ArrayList<SCatalogoVO> getObservadores()throws GenericStatusException;

	ArrayList<CatalogoVO> getCentroCostos()throws GenericStatusException;

	ArrayList<CatalogoVO> getAnalystByGerencia(Integer id) throws GenericStatusException;

	FilterVO getFiltersData(String idUsuario, Integer idRama, String code, String branch) throws GenericStatusException;



}
