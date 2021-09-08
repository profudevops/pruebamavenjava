package com.mx.profuturo.bolsa.service.area;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mx.profuturo.bolsa.model.restclient.RequestBean;
import com.mx.profuturo.bolsa.model.service.areasinteres.dto.*;
import com.mx.profuturo.bolsa.model.service.auth.dto.BasicDTO;
import com.mx.profuturo.bolsa.model.service.candidates.dto.GenericIdCandidateRequestBean;
import com.mx.profuturo.bolsa.util.exception.custom.GenericStatusException;

public abstract class AreaFormServiceBase implements AreaFormService {

	@Override
	public BasicDTO createInterestArea(AreaInteresDTO areaDto) throws GenericStatusException {
		RequestBean<AreaInteresDTO> genericRequestBean = new RequestBean<>();
		genericRequestBean.setPayload(areaDto);
		BasicDTO respuesta = this.__callCreateInterestArea(genericRequestBean);

		if(respuesta.getId()!=0){
		return respuesta;}

		throw new GenericStatusException();

	}

	@Override
	public boolean updateInterestArea(AreaInteresDTO areaDto) throws GenericStatusException {
		RequestBean<AreaInteresDTO> genericRequestBean = new RequestBean<>();
		genericRequestBean.setPayload(areaDto);
		GenericTextResponseBean respuesta = this.__callUpdateInterestArea(genericRequestBean);
		String responseText = respuesta.getRespuesta();
		if("Exito".equals(responseText)){
			return true;
		}
		throw new GenericStatusException();
	}



	@Override
	public AreaInteresDTO getInterestArea(int areaDto) throws GenericStatusException {
		GetAreaInteres request = new GetAreaInteres();
		Map<String,Integer> m = new HashMap<>();
		m.put("idArea",areaDto);
		request.setIdArea(areaDto);
		RequestBean<GetAreaInteres> genericRequestBean = new RequestBean<>();
		genericRequestBean.setUriVariables(m);
		return this.__callgetInterestArea(genericRequestBean);
	}

	@Override
	public List<AreaInteresDTO> getInterestAreas() throws GenericStatusException {

		GetAreaInteres request = new GetAreaInteres();
		RequestBean<GetAreaInteres> genericRequestBean = new RequestBean<>();
		return this.__callgetInterestAreas(genericRequestBean);
	}

	@Override
	public BasicDTO createSubtArea(SubAreaInteresDTO subDto) throws GenericStatusException {

		RequestBean<SubAreaInteresDTO> genericRequestBean = new RequestBean<>();
		genericRequestBean.setPayload(subDto);
		BasicDTO respuesta = this.__callCreateSubtArea(genericRequestBean);

		if(respuesta.getId()!=0){
			return respuesta;}

		throw new GenericStatusException();
	}

	public Boolean deleteSubArea(int idsubDto) throws GenericStatusException {
		return this.__calldeleteSubArea(idsubDto);
	}


	@Override
	public UpdateInterestSubareaResponseBean updateInterestSubarea(String descripcion, int idArea, int idSubArea, String nombre) throws GenericStatusException{
		SubAreaInteresDTO request = new SubAreaInteresDTO();
		request.setDescripcion(descripcion);
		request.setIdArea(idArea);
		request.setIdSubArea(idSubArea);
		request.setNombre(nombre);
		RequestBean<SubAreaInteresDTO> genericRequestBean = new RequestBean<>();
		genericRequestBean.setPayload(request);
		return callUpdateInterestSubarea(genericRequestBean);
	}

	@Override
	public boolean dropInterestaArea(DropInterestAreaRequestBean idArea) throws GenericStatusException{

		RequestBean<DropInterestAreaRequestBean> genericRequestBean = new RequestBean<>();
		genericRequestBean.setPayload(idArea);
		GenericTextResponseBean respuesta = this.callDropInterestaArea(genericRequestBean);
		String responseText = respuesta.getRespuesta();
		if("Exito".equals(responseText)){
			return true;
		}
		throw new GenericStatusException();
	}

	@Override
	public boolean dropInterestSubarea(DropInterestSubareaRequestBean idSubArea) throws GenericStatusException{
		RequestBean<DropInterestSubareaRequestBean> genericRequestBean = new RequestBean<>();
        genericRequestBean.setPayload(idSubArea);
        GenericTextResponseBean respuesta = this.callDropInterestSubarea(genericRequestBean);
        String responseText = respuesta.getRespuesta();
        if("Exito".equals(responseText)){
            return true;
        }
        throw new GenericStatusException();
	}



	protected abstract BasicDTO __callCreateInterestArea(RequestBean<AreaInteresDTO> genericRequestBean) throws GenericStatusException;

	protected abstract GenericTextResponseBean __callUpdateInterestArea(RequestBean<AreaInteresDTO> genericRequestBean) throws GenericStatusException;


	protected abstract AreaInteresDTO __callgetInterestArea(RequestBean<GetAreaInteres> genericRequestBean) throws GenericStatusException;

	protected abstract BasicDTO __callCreateSubtArea(RequestBean<SubAreaInteresDTO> genericRequestBean) throws GenericStatusException;

	protected abstract Boolean __callupdateSubArea(SubAreaInteresDTO subDto) throws GenericStatusException;

	protected abstract Boolean __calldeleteSubArea(int idsubDto) throws GenericStatusException;

	protected abstract List<AreaInteresDTO> __callgetInterestAreas(RequestBean<GetAreaInteres> genericRequestBean) throws GenericStatusException;


	protected abstract UpdateInterestSubareaResponseBean callUpdateInterestSubarea(RequestBean<SubAreaInteresDTO> genericRequestBean) throws GenericStatusException;

	protected abstract GenericTextResponseBean callDropInterestaArea(RequestBean<DropInterestAreaRequestBean> genericRequestBean) throws GenericStatusException;

	protected abstract GenericTextResponseBean callDropInterestSubarea(RequestBean<DropInterestSubareaRequestBean> genericRequestBean) throws GenericStatusException;
}
