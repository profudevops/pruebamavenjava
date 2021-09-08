package com.mx.profuturo.bolsa.service.reports;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.mx.profuturo.bolsa.model.reports.vo.FilterVO;
import com.mx.profuturo.bolsa.model.restclient.RequestBean;
import com.mx.profuturo.bolsa.model.role.Branches;
import com.mx.profuturo.bolsa.model.service.areasinteres.dto.AreaInteresDTO;
import com.mx.profuturo.bolsa.model.vo.common.CatalogoVO;
import com.mx.profuturo.bolsa.model.vo.common.SCatalogoVO;
import com.mx.profuturo.bolsa.service.area.AreaFormService;
import com.mx.profuturo.bolsa.service.vacantes.VacancyService;
import com.mx.profuturo.bolsa.util.exception.custom.GenericStatusException;

public abstract class FilterServiceBase implements FilterService {
	
	
	@Autowired
	VacancyService vacancyService;

	@Autowired
	AreaFormService areaService;
	

	
	@Override
	public FilterVO getFiltersData(String idUsuario, Integer idRama, String code, String branch) throws GenericStatusException { 
		FilterVO vo = new FilterVO();
		vo.setRama(branch);
		if(Branches.CORPORATE.equals(branch)) {
			vo.setCentroCostos(this.getCentroCostos());
			vo.setRangoVacante(this.getRangoVacante());
			vo.setTipoVacante(this.getTipoVacante());
			vo.setAreaInteres(areaService.getInterestAreas());
			vo.setAnalistas(vacancyService.searchCollaborator(idUsuario, "",idRama));
			vo.setNivelPuesto(this.getRangoVacante());
		}else {
			vo.setDivisiones(vacancyService.getDivisions(idRama));
			vo.setObservador(this.getObservadores());
		}
	
		 
		vo.setPeriodo(this.getPeriodos());
		
		return vo;
		
	}

	@Override
	public ArrayList<CatalogoVO> getAnalistasCorporativo() throws GenericStatusException { //sí
		return this.__callgetAnalistasCorporativo();
	}
	

	public abstract ArrayList<CatalogoVO> __callgetAnalistasCorporativo() throws GenericStatusException;
	
	

	@Override
	public ArrayList<CatalogoVO> getPeriodos() throws GenericStatusException { //sí
		return this.__callGetPeriodos();
	}

	public abstract ArrayList<CatalogoVO> __callGetPeriodos() throws GenericStatusException;


	@Override
	public ArrayList<CatalogoVO> getTipoVacante() throws GenericStatusException { //sí
		return this.__callGetTipoVacante();
	}
	
	public abstract ArrayList<CatalogoVO> __callGetTipoVacante() throws GenericStatusException;


	@Override
	public ArrayList<CatalogoVO> getRangoVacante() throws GenericStatusException { //nivel de puesto?
		return this.__callGetNivelPuesto();
	}

	public abstract ArrayList<CatalogoVO> __callGetNivelPuesto() throws GenericStatusException;

	@Override
	public ArrayList<CatalogoVO> getCentroCostos() throws GenericStatusException { //sí
		return this.__callGetCentroCostos();
	}
	
	
	public abstract ArrayList<CatalogoVO> __callGetCentroCostos() throws GenericStatusException;


	@Override
	public ArrayList<SCatalogoVO> getObservadores() throws GenericStatusException{ //sí
		return this.__callGetObservadores();
	}
	
	
	public abstract ArrayList<SCatalogoVO> __callGetObservadores() throws GenericStatusException;
	
	
	
	@Override
	public ArrayList<CatalogoVO> getAnalystByGerencia(Integer id) throws GenericStatusException { //no
		RequestBean<Integer> rq = new RequestBean<Integer>();
		Map<String, Integer> uriVariables = new LinkedHashMap<String, Integer>();
		uriVariables.put("idGerencia", id);
		rq.setUriVariables(uriVariables);
		return this.__callAnalystByGerencia(rq);
	}
	
	public abstract ArrayList<CatalogoVO> __callAnalystByGerencia(RequestBean<Integer> id) throws GenericStatusException;
	


}
