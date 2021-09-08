package com.mx.profuturo.bolsa.web.controller.common;

import java.security.GeneralSecurityException;
import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;

import com.mx.profuturo.bolsa.util.exception.custom.GenericStatusException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mx.profuturo.bolsa.model.recruitment.dto.CompletePhaseDTO;
import com.mx.profuturo.bolsa.model.recruitment.dto.GenericStageQueryDTO;
import com.mx.profuturo.bolsa.model.recruitment.dto.form.SEAGStageForm;
import com.mx.profuturo.bolsa.model.recruitment.dto.form.SEESGStageForm;
import com.mx.profuturo.bolsa.model.recruitment.response.DatosEntrevistaResponseBean;
import com.mx.profuturo.bolsa.model.recruitment.response.ProcesoReclutamientoResponseBean;
import com.mx.profuturo.bolsa.model.recruitment.service.dto.FolioDTO;
import com.mx.profuturo.bolsa.model.recruitment.service.dto.InterviewAppointmentDTO;
import com.mx.profuturo.bolsa.model.recruitment.service.dto.InterviewResponseDTO;
import com.mx.profuturo.bolsa.model.recruitment.service.dto.PsychometricTestDTO;
import com.mx.profuturo.bolsa.model.recruitment.service.dto.TrustTestDTO;
import com.mx.profuturo.bolsa.model.recruitment.vo.FolioVO;
import com.mx.profuturo.bolsa.model.vo.common.CatalogoVO;
import com.mx.profuturo.bolsa.service.recruitment.AuxiliarService;
import com.mx.profuturo.bolsa.service.recruitment.FacadeService;
import com.mx.profuturo.bolsa.service.recruitment.OperationsService;

@Controller
@RequestMapping(value="reclutamiento")
@Scope("request")
public class RecruitmentBackFakeControllerImpl{

	@Autowired
	OperationsService operationsService;
	
	@Autowired
	AuxiliarService auxiliarService;
	
	public void handleSaveStep(CompletePhaseDTO dto) {
		// TODO Auto-generated method stub
		
	}

	
	@ResponseBody
    @RequestMapping(value="/proceso/getbyid", method = RequestMethod.GET)
	public ProcesoReclutamientoResponseBean getDataById(@RequestParam Integer idReclutamiento) throws GenericStatusException {
		// TODO Auto-generated method stub
		return operationsService.getDataById(idReclutamiento);
	}

	
	@ResponseBody
    @RequestMapping(value="/entrevistas/getNoHandledInterviewsData", method = RequestMethod.GET)
	public LinkedList<DatosEntrevistaResponseBean> getNoHandledInterviewsData(@RequestParam Integer idReclutamiento) throws GenericStatusException {
		// TODO Auto-generated method stub
		return operationsService.callGetNoHandledInterviewData(idReclutamiento);
	}

	
	@ResponseBody
    @RequestMapping(value="/entrevistas/getInterviewDataById", method = RequestMethod.GET)
	public  SEAGStageForm getHandledInterviewData(@RequestParam Integer idReclutamiento,@RequestParam Integer idEntrevista, @RequestParam String codigo) throws GenericStatusException {
		// TODO Auto-generated method stub
		return operationsService.getInterviewDataByStage(idReclutamiento,idEntrevista, codigo);
	}

	
	@ResponseBody
    @RequestMapping(value="/proceso/saveGenericStage", method = RequestMethod.POST)
	public void saveGenericStage(@RequestBody GenericStageQueryDTO genericStageQuery) {
		// TODO Auto-generated method stub
		
	}

	
	@ResponseBody
    @RequestMapping(value="/proceso/savePsychometricTest", method = RequestMethod.POST)
	public void savePsychometricTest(@RequestBody PsychometricTestDTO pDto) {
		// TODO Auto-generated method stub
		
	}

	
	@ResponseBody
    @RequestMapping(value="/proceso/saveTrustTest", method = RequestMethod.POST)
	public void saveTrustTest(@RequestBody TrustTestDTO tDto) {
		// TODO Auto-generated method stub
		
	}

	
	@ResponseBody
    @RequestMapping(value="/proceso/saveFolio", method = RequestMethod.POST)
	public void saveFolio(@RequestBody FolioDTO fDto) {
		// TODO Auto-generated method stub
		
	}

	
	@ResponseBody
    @RequestMapping(value="/entrevistas/saveInterviewAppointment", method = RequestMethod.POST)
	public void saveInterviewAppointment(@RequestBody InterviewAppointmentDTO iDto) {
		// TODO Auto-generated method stub
		
	}

	
	@ResponseBody
    @RequestMapping(value="/entrevistas/saveInterviewResponse", method = RequestMethod.POST)
	public void saveInterviewResponse(@RequestBody InterviewResponseDTO iRDto) {
		// TODO Auto-generated method stub
		
	}

	
	@ResponseBody
    @RequestMapping(value="/proceso/searchFolio", method = RequestMethod.GET)
	public LinkedList<FolioVO> searchFolio(@RequestParam String folio) {
		// TODO Auto-generated method stub
		return operationsService.searchFolio(folio);
	}

	
	@ResponseBody
    @RequestMapping(value="/catalogos/getStateProperties", method = RequestMethod.GET)
	public LinkedList<CatalogoVO> getStateProperties() {
		// TODO Auto-generated method stub
		return operationsService.getStateProperties();
	}

	
	@ResponseBody
    @RequestMapping(value="/catalogos/getInterviewType", method = RequestMethod.GET)
	public LinkedList<CatalogoVO> getInterviewType() throws GenericStatusException {
		// TODO Auto-generated method stub
		return auxiliarService.getInterviewType();
	}


	@ResponseBody
    @RequestMapping(value="/proceso/getbyid-mock", method = RequestMethod.GET)
	public ProcesoReclutamientoResponseBean getDataById(@RequestParam Integer idReclutamiento,@RequestParam Integer step) throws GenericStatusException {
		// TODO Auto-generated method stub
		return operationsService.getDataById(idReclutamiento, step);
	}
	
	
	@ResponseBody
    @RequestMapping(value="/proceso/hay-posiciones-disponibles", method = RequestMethod.GET)
	public Boolean publicacionTienePosicionesDisponibles(@RequestParam Integer idReclutamiento, @RequestParam Integer idPublicacion) throws GenericStatusException {
		return operationsService.hasPublicationSpaces(idReclutamiento, idPublicacion);
	}
	


    
}
