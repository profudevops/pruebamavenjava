package com.mx.profuturo.bolsa.web.controller.corporate.areaform;

import java.util.ArrayList;
import java.util.List;

import com.mx.profuturo.bolsa.model.service.areasinteres.dto.DropInterestAreaRequestBean;
import com.mx.profuturo.bolsa.model.service.areasinteres.dto.DropInterestSubareaRequestBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.mx.profuturo.bolsa.model.service.BaseResponseBean;
import com.mx.profuturo.bolsa.model.service.EntityResponseBean;
import com.mx.profuturo.bolsa.model.service.areasinteres.dto.AreaInteresDTO;
import com.mx.profuturo.bolsa.model.service.areasinteres.dto.SubAreaInteresDTO;
import com.mx.profuturo.bolsa.model.service.auth.dto.BasicDTO;
import com.mx.profuturo.bolsa.service.area.AreaFormService;
import com.mx.profuturo.bolsa.util.exception.custom.GenericStatusException;
import com.mx.profuturo.bolsa.web.controller.common.BaseController;

@CrossOrigin
@Controller("controllerareaForm")
@Scope("request")
public class AreaInteresControllerImpl extends BaseController {

	@Autowired
	AreaFormService areaForm;

	public @ResponseBody @RequestMapping(value = "areaForm/crear-area-interes", method = RequestMethod.POST) EntityResponseBean<BasicDTO> crearArea(
			@RequestBody AreaInteresDTO areadto)
			throws GenericStatusException {
		BasicDTO responseService = areaForm.createInterestArea(areadto);

		return this.buildEntitySuccessResponse(responseService);

	}

	// updateInterestArea
	public @ResponseBody @RequestMapping(value = "areaForm/actualizar-area-interes", method = RequestMethod.POST) BaseResponseBean actualizarArea(
			@RequestBody AreaInteresDTO areadto)
			throws GenericStatusException {

		if (areadto.getIdArea()==0){

		}
		boolean responseService = areaForm.updateInterestArea(areadto);

		if(responseService) {
			return this.buildSuccessResponse();
		}
		throw new GenericStatusException();
	}

	// unsuscribeInterestArea
	public @ResponseBody @RequestMapping(value = "areaForm/baja-area-de-interes", method = RequestMethod.POST)
	BaseResponseBean bajaArea(@RequestBody BasicDTO id)
			throws GenericStatusException {
		DropInterestAreaRequestBean idArea = new DropInterestAreaRequestBean();
		idArea.setIdArea(id.getId());
		boolean responseService = areaForm.dropInterestaArea(idArea);
		if (responseService) {
			return this.buildSuccessResponse();
		}
		throw new GenericStatusException();
	}

	// unsuscribeInterestArea
	public
	@ResponseBody
	@RequestMapping(value = "areaForm/baja-subarea-de-interes", method = RequestMethod.POST)
	BaseResponseBean bajaSubArea(
			@RequestBody DropInterestSubareaRequestBean idSubArea)
			throws GenericStatusException {

		boolean responseService = areaForm.dropInterestSubarea(idSubArea);
		if (responseService) {
			return this.buildSuccessResponse();
		}
		throw new GenericStatusException();
	}

	public @ResponseBody @RequestMapping(value = "areaForm/obtener-area-interes", method = RequestMethod.GET) EntityResponseBean<ArrayList<AreaInteresDTO>> getSubArea(
			@RequestParam(value="idArea") int idArea)
			throws GenericStatusException {
		AreaInteresDTO responseService = areaForm.getInterestArea(idArea);
		if (responseService != null) {
			return this.buildEntitySuccessResponse(responseService);
		}
		return this.buildEntityErrorResponse("Error en servicio");
	}

	public @ResponseBody @RequestMapping(value = "areasInteres/obtener-area", method = RequestMethod.GET) EntityResponseBean<ArrayList<AreaInteresDTO>> getSubAreaInteres(
			@RequestParam int idArea)
			throws GenericStatusException {
		AreaInteresDTO responseService = areaForm.getInterestArea(idArea);
		if (responseService != null) {
			return this.buildEntitySuccessResponse(responseService);
		}
		return this.buildEntityErrorResponse("Error en servicio");
	}


	public @ResponseBody @RequestMapping(value = "areaForm/obtener-areas-interes", method = RequestMethod.GET)
	EntityResponseBean<List<AreaInteresDTO>> getAreasInteres()
			throws GenericStatusException {
		List<AreaInteresDTO> responseService = areaForm.getInterestAreas();
		if (responseService != null) {
			return this.buildEntitySuccessResponse(responseService);
		}
		return this.buildEntityErrorResponse("Error en servicio");
	}

	public @ResponseBody @RequestMapping(value = "areasInteres/obtener-areas", method = RequestMethod.GET)
	EntityResponseBean<ArrayList<AreaInteresDTO>> getAreasDeInteres()
			throws GenericStatusException {
		List<AreaInteresDTO> responseService = areaForm.getInterestAreas();
		if (responseService != null) {
			return this.buildEntitySuccessResponse(responseService);
		}
		return this.buildEntityErrorResponse("Error en servicio");
	}

	public @ResponseBody @RequestMapping(value = "crear-sub-area-interes", method = RequestMethod.POST) BaseResponseBean crearSubArea(
			@RequestBody SubAreaInteresDTO subareadto)
			throws GenericStatusException {
		BasicDTO responseService = areaForm.createSubtArea(subareadto);

		return this.buildEntitySuccessResponse(responseService);
	}

	public @ResponseBody @RequestMapping(value = "areaForm/actualizar-sub-area-interes", method = RequestMethod.POST) BaseResponseBean crearArea(
			@RequestBody SubAreaInteresDTO subareadto)
			throws GenericStatusException {
		boolean responseService = areaForm.updateSubArea(subareadto);
		if (responseService) {
			return this.buildSuccessResponse();
		}
		return this.buildEntityErrorResponse("Error en servicio");
	}

}
