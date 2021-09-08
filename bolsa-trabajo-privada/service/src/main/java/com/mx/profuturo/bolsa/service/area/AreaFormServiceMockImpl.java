package com.mx.profuturo.bolsa.service.area;

import java.util.ArrayList;
import java.util.List;

import com.mx.profuturo.bolsa.model.restclient.RequestBean;
import com.mx.profuturo.bolsa.model.service.areasinteres.dto.*;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.mx.profuturo.bolsa.model.service.auth.dto.BasicDTO;
import com.mx.profuturo.bolsa.util.exception.custom.GenericStatusException;

@Service
@Scope("request")
@Profile("mock")
public class AreaFormServiceMockImpl extends AreaFormServiceBase {

	@Override
	protected BasicDTO __callCreateInterestArea(RequestBean<AreaInteresDTO> areaDto) throws GenericStatusException {
		BasicDTO response = new BasicDTO();
		response.setId(1);
		return response;
	}

	@Override
	public Boolean updateSubArea(SubAreaInteresDTO subDto) throws GenericStatusException {
		Boolean sucess = true;

		return sucess;
	}


	@Override
	protected AreaInteresDTO __callgetInterestArea(RequestBean<GetAreaInteres> genericRequestBean) throws GenericStatusException {
		// crear la lista
		ArrayList<SubAreaInteresDTO> listsub = new ArrayList<SubAreaInteresDTO>();
		SubAreaInteresDTO sai = new SubAreaInteresDTO();
		SubAreaInteresDTO sai1 = new SubAreaInteresDTO();
		SubAreaInteresDTO sai2 = new SubAreaInteresDTO();

		sai.setIdArea(1);
		sai.setDescripcion("descripcion");
		sai.setIdSubArea(1);
		sai.setNombre("Desarrollo");
		listsub.add(sai);

		sai1.setIdArea(1);
		sai1.setDescripcion("descripcion");
		sai1.setIdSubArea(2);
		sai1.setNombre("Arquitectura");
		listsub.add(sai1);

		sai2.setIdArea(1);
		sai2.setDescripcion("descripcion");
		sai2.setIdSubArea(3);
		sai2.setNombre("Seguridad");
		listsub.add(sai1);

		AreaInteresDTO ai = new AreaInteresDTO();
		ai.setDescripcion("Área de sistemas");
		ai.setIdArea(1);
		ai.setNombre("Sistemas");
		ai.setSubareas(listsub);


		return ai;
	}


	@Override
	protected List<AreaInteresDTO> __callgetInterestAreas(RequestBean<GetAreaInteres> genericRequestBean) throws GenericStatusException {
		// crear la lista
		ArrayList<AreaInteresDTO> listInteres = new ArrayList<AreaInteresDTO>();
		ArrayList<SubAreaInteresDTO> listsub = new ArrayList<SubAreaInteresDTO>();
		SubAreaInteresDTO sai = new SubAreaInteresDTO();
		SubAreaInteresDTO sai1 = new SubAreaInteresDTO();
		SubAreaInteresDTO sai2 = new SubAreaInteresDTO();

		ArrayList<SubAreaInteresDTO> rhsub = new ArrayList<SubAreaInteresDTO>();
		SubAreaInteresDTO rh1 = new SubAreaInteresDTO();
		SubAreaInteresDTO rh2 = new SubAreaInteresDTO();

		sai.setIdArea(1);
		sai.setDescripcion("descripcion");
		sai.setIdSubArea(1);
		sai.setNombre("Desarrollo");
		listsub.add(sai);

		sai1.setIdArea(1);
		sai1.setDescripcion("descripcion");
		sai1.setIdSubArea(2);
		sai1.setNombre("Arquitectura");
		listsub.add(sai1);

		sai2.setIdArea(1);
		sai2.setDescripcion("descripcion");
		sai2.setIdSubArea(3);
		sai2.setNombre("Seguridad");
		listsub.add(sai1);

		AreaInteresDTO ai = new AreaInteresDTO();
		ai.setDescripcion("Área de sistemas");
		ai.setIdArea(1);
		ai.setNombre("Sistemas");
		ai.setSubareas(listsub);
		listInteres.add(ai);


		rh1.setIdArea(2);
		rh1.setDescripcion("descripcion");
		rh1.setIdSubArea(4);
		rh1.setNombre("Reclutamiento");
		rhsub.add(rh1);

		rh2.setIdArea(2);
		rh2.setDescripcion("descripcion");
		rh2.setIdSubArea(5);
		rh2.setNombre("Relaciones Laborales");
		rhsub.add(rh2);

		AreaInteresDTO rh = new AreaInteresDTO();
		rh.setDescripcion("Área de Recursos Humanos");
		rh.setIdArea(2);
		rh.setNombre("Recursos Humanos");
		rh.setSubareas(listsub);
		listInteres.add(rh);

		return listInteres;
	}

	@Override
	protected BasicDTO __callCreateSubtArea(RequestBean<SubAreaInteresDTO> genericRequestBean) throws GenericStatusException {
		 BasicDTO sucess = new BasicDTO();
		 sucess.setId(1);

		return sucess;
	}

	@Override
	protected Boolean __callupdateSubArea(SubAreaInteresDTO subDto) throws GenericStatusException {
		Boolean sucess = true;

		return sucess;
	}

	@Override
	protected Boolean __calldeleteSubArea(int idsubDto) throws GenericStatusException {
		Boolean sucess = true;

		return sucess;
	}

	@Override
	protected GenericTextResponseBean __callUpdateInterestArea(RequestBean<AreaInteresDTO> genericRequestBean) throws GenericStatusException{
		GenericTextResponseBean response = new GenericTextResponseBean();
		response.setRespuesta("Exito");
		return response;
	}

	@Override
	protected UpdateInterestSubareaResponseBean callUpdateInterestSubarea(RequestBean<SubAreaInteresDTO> genericRequestBean) throws GenericStatusException{
		UpdateInterestSubareaResponseBean response = new UpdateInterestSubareaResponseBean();
		response.setRespuesta("Exito");
		return response;
	}

	@Override
	protected GenericTextResponseBean callDropInterestaArea(RequestBean<DropInterestAreaRequestBean> genericRequestBean) throws GenericStatusException{
		GenericTextResponseBean response = new GenericTextResponseBean();
		response.setRespuesta("Exito");
		return response;
	}

	@Override
	protected GenericTextResponseBean callDropInterestSubarea(RequestBean<DropInterestSubareaRequestBean> genericRequestBean) throws GenericStatusException{
		GenericTextResponseBean response = new GenericTextResponseBean();
		response.setRespuesta("Exito");
		return response;
	}
}
