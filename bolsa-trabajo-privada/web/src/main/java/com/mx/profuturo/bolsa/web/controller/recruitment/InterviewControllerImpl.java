package com.mx.profuturo.bolsa.web.controller.recruitment;

import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.LinkedList;

import com.mx.profuturo.bolsa.configuration.TokenService;
import com.mx.profuturo.bolsa.model.interview.InterviewAvailableHoursRequestDTO;
import com.mx.profuturo.bolsa.model.recruitment.vo.InterviewInfoVO;
import com.mx.profuturo.bolsa.model.recruitment.vo.InterviewsListVO;
import com.mx.profuturo.bolsa.model.vo.common.CatalogoVO;
import com.mx.profuturo.bolsa.service.interview.InterviewService;
import com.mx.profuturo.bolsa.util.exception.custom.GenericStatusException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.mx.profuturo.bolsa.model.recruitment.dto.InterviewDTO;
import com.mx.profuturo.bolsa.model.recruitment.vo.InterviewVO;
import com.mx.profuturo.bolsa.model.service.BaseResponseBean;
import com.mx.profuturo.bolsa.model.service.EntityResponseBean;
import com.mx.profuturo.bolsa.model.service.auth.response.UserCredentialsVO;
import com.mx.profuturo.bolsa.web.controller.common.BaseController;
import sun.awt.image.ImageWatched;

@Controller("InterviewController")
@Scope("request")
@CrossOrigin
public class InterviewControllerImpl  extends BaseController{

	@Autowired
	private TokenService tokenService;
	
	@Autowired
	InterviewService interviewService;

	@ResponseBody
    @RequestMapping(value="reclutamiento/obtener-horas-disponibles-entrevista", method = RequestMethod.POST)
	public EntityResponseBean<LinkedList<CatalogoVO>> getInterviewAvailableHours
			(@RequestBody InterviewAvailableHoursRequestDTO interviewHourDTO) throws GenericStatusException {

		LinkedList<CatalogoVO> response =  interviewService.getAvailableShcedule(interviewHourDTO);
		return this.buildEntitySuccessResponse(response);
	}

/*
	//reclutamiento/agendar-entrevista
		@ResponseBody
	    @RequestMapping(value="reclutamiento/guardar-entrevista", method = RequestMethod.POST)
		private BaseResponseBean saveInterview(@RequestBody InterviewDTO dto) {
			return this.buildSuccessResponse();
		}

		@ResponseBody
	    @RequestMapping(value="reclutamiento/obtener-entrevista", method = RequestMethod.GET)
		private EntityResponseBean<InterviewVO> getInterviewById(@RequestParam Integer id) {
			InterviewVO vo = new InterviewVO();
			vo.setFecha("10/04/2020");
			vo.setHora("20:30");
			vo.setNombreEntrevistador("Ricky Llamas");
			vo.setIdSede(1);
			vo.setTipoEntrevista(1);
			vo.setNombreSede("Profuturo Corporativo");
			return this.buildEntitySuccessResponse(vo);
		}
		
		
		@ResponseBody
	    @RequestMapping(value="reclutamiento/responder-entrevista", method = RequestMethod.POST)
		private BaseResponseBean answerInterview(@RequestBody InterviewDTO dto) {
			return this.buildSuccessResponse();
		}
			
*/

		@ResponseBody
		@RequestMapping(value="entrevistas/obtener-entrevistas", method = RequestMethod.GET)
		private EntityResponseBean<InterviewsListVO> getInterviewsList(@RequestHeader(value="Authorization")String token,@RequestParam(value="paginaActual") int paginaActual) throws GenericStatusException {

			UserCredentialsVO user = tokenService.getUserFromToken(this.getCleanToken(token));
			Integer idRama = tokenService.getBranchId(this.getCleanToken(token));
			InterviewsListVO entrevistas = interviewService.getInterviews(user, paginaActual, idRama);

			return this.buildEntitySuccessResponse(entrevistas);
		}


		
}
