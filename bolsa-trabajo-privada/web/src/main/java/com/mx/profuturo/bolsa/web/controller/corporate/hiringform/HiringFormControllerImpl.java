package com.mx.profuturo.bolsa.web.controller.corporate.hiringform;

import java.util.ArrayList;
import java.util.List;

import com.mx.profuturo.bolsa.model.service.BaseResponseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.mx.profuturo.bolsa.model.service.EntityResponseBean;
import com.mx.profuturo.bolsa.model.service.hiringform.dto.GuardarEtapaFormularioDTO;
import com.mx.profuturo.bolsa.model.service.hiringform.dto.RecuperarFormularioDTO;
import com.mx.profuturo.bolsa.model.service.hiringform.dto.RejectDTO;
import com.mx.profuturo.bolsa.model.service.hiringform.dto.TerminarFormularioDTO;
import com.mx.profuturo.bolsa.model.service.hiringform.dto.AcceptDTO;
import com.mx.profuturo.bolsa.model.service.hiringform.dto.AccesoFormularioContratacionDTO;
import com.mx.profuturo.bolsa.model.service.hiringform.vo.DatosFormularioVO;
import com.mx.profuturo.bolsa.model.service.hiringform.vo.StatusVO;
import com.mx.profuturo.bolsa.model.service.hiringform.vo.CandidatoContratacionVO;
import com.mx.profuturo.bolsa.service.hiring.HiringFormService;
import com.mx.profuturo.bolsa.util.exception.custom.GenericStatusException;
import com.mx.profuturo.bolsa.web.controller.common.BaseController;

@RequestMapping(value="hiringForm")
@Controller("controllerHiringForm")
@Scope("request")
@CrossOrigin
public class HiringFormControllerImpl extends BaseController {

	@Autowired
	HiringFormService hiringForm;

	@CrossOrigin
	public @ResponseBody
    @RequestMapping(value="validar-credenciales-candidato", method = RequestMethod.POST)
    EntityResponseBean <CandidatoContratacionVO> validateCredentials(@RequestBody  AccesoFormularioContratacionDTO dto)throws GenericStatusException {

	 //dto.setDato1("");
	 //dto.setDato2("");
	 CandidatoContratacionVO vo =hiringForm.validateHiringForm(dto);
	 return this.buildEntitySuccessResponse(vo);
   }
	
	@CrossOrigin
	public @ResponseBody
	@RequestMapping(value="guardar-avance-formulario", method = RequestMethod.POST)
	BaseResponseBean guardarAvance (@RequestBody GuardarEtapaFormularioDTO guardaretapadto) throws GenericStatusException {

		boolean responseService = hiringForm.personalData(guardaretapadto);
		if(responseService) {
			return this.buildSuccessResponse();
		}
		
		return this.buildEntityErrorResponse("Error en servicio");
	}

	@CrossOrigin
	public @ResponseBody
    @RequestMapping(value="recuperar-avance-formulario", method = RequestMethod.POST)
    EntityResponseBean<DatosFormularioVO> getPersonalData (@RequestBody RecuperarFormularioDTO recudto) throws GenericStatusException {
		DatosFormularioVO responseService = hiringForm.getPersonalDataHiring(recudto);		

			return this.buildEntitySuccessResponse(responseService);

	}


	public @ResponseBody
	@RequestMapping(value="terminar-formulario", method = RequestMethod.POST)
	BaseResponseBean guardar (@RequestBody TerminarFormularioDTO endForm) throws GenericStatusException {
		Boolean success = false;
		boolean responseService = hiringForm.endHiringForm(endForm);
		if(responseService) {
			return this.buildSuccessResponse();
		}
		return this.buildEntityErrorResponse("Error en servicio");
	}
	
	
	public @ResponseBody
	@RequestMapping(value="obtener-status", method = RequestMethod.GET)
	EntityResponseBean<StatusVO> getFormStatus(@RequestParam Integer IdReclutamiento) throws GenericStatusException {
		StatusVO vo = new StatusVO();
		vo.setId(2);
		vo.setDescripcion("Cerrado");
		return this.buildEntitySuccessResponse(vo);
	}
	
	
	public @ResponseBody
	@RequestMapping(value="obtener-aplicacion", method = RequestMethod.GET)
	EntityResponseBean<DatosFormularioVO> getFormAplication(@RequestParam Integer IdReclutamiento) throws GenericStatusException {
		RecuperarFormularioDTO recudto = new RecuperarFormularioDTO();
		recudto.setIdProceso(IdReclutamiento);
		DatosFormularioVO responseService = hiringForm.getPersonalDataHiring(recudto );		
		return this.buildEntitySuccessResponse(responseService);
	}
	
	
	public @ResponseBody
	@RequestMapping(value="rechazar-formulario", method = RequestMethod.POST)
	BaseResponseBean rejectForm(@RequestBody RejectDTO IdReclutamiento) throws GenericStatusException {
		return this.buildEntitySuccessResponse(null);
	}
	
	public @ResponseBody
	@RequestMapping(value="aceptar-formulario", method = RequestMethod.POST)
	BaseResponseBean acceptForm(@RequestBody AcceptDTO IdReclutamiento) throws GenericStatusException {
		return this.buildEntitySuccessResponse(null);
	}
	
	
		
}
