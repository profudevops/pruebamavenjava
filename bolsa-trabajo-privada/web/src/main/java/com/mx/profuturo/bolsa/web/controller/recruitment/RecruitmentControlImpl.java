package com.mx.profuturo.bolsa.web.controller.recruitment;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mx.profuturo.bolsa.configuration.TokenService;
import com.mx.profuturo.bolsa.model.recruitment.constants.RecruitmentStages;
import com.mx.profuturo.bolsa.model.recruitment.constants.RecruitmentStagesStatus;
import com.mx.profuturo.bolsa.model.recruitment.dto.CompletePhaseDTO;
import com.mx.profuturo.bolsa.model.recruitment.dto.InterviewDTO;
import com.mx.profuturo.bolsa.model.recruitment.dto.InterviewHoursDTO;
import com.mx.profuturo.bolsa.model.recruitment.dto.form.PEAGStageForm;
import com.mx.profuturo.bolsa.model.recruitment.dto.form.SEAGStageForm;
import com.mx.profuturo.bolsa.model.recruitment.dto.form.SEESGStageForm;
import com.mx.profuturo.bolsa.model.recruitment.response.DatosEntrevistaResponseBean;
import com.mx.profuturo.bolsa.model.recruitment.vo.EventVO;
import com.mx.profuturo.bolsa.model.recruitment.vo.FolioVO;
import com.mx.profuturo.bolsa.model.recruitment.vo.InterviewVO;
import com.mx.profuturo.bolsa.model.recruitment.vo.RecruitmentDetailVO;
import com.mx.profuturo.bolsa.model.recruitment.vo.RecruitmentPhaseDataVO;
import com.mx.profuturo.bolsa.model.recruitment.vo.RecruitmentPhaseDetailVO;
import com.mx.profuturo.bolsa.model.recruitment.vo.RecruitmentStageVO;
import com.mx.profuturo.bolsa.model.recruitment.vo.RecruitmentPhaseCompletedVO;
import com.mx.profuturo.bolsa.model.service.BaseResponseBean;
import com.mx.profuturo.bolsa.model.service.EntityResponseBean;
import com.mx.profuturo.bolsa.model.service.auth.response.UserCredentialsVO;
import com.mx.profuturo.bolsa.model.service.candidates.dto.IniciarReclutamientoDTO;
import com.mx.profuturo.bolsa.model.vo.common.CatalogoVO;
import com.mx.profuturo.bolsa.security.GrantingUserSettings;
import com.mx.profuturo.bolsa.service.recruitment.AuxiliarService;
import com.mx.profuturo.bolsa.service.recruitment.FacadeService;
import com.mx.profuturo.bolsa.service.recruitment.OperationsService;
import com.mx.profuturo.bolsa.service.vacantes.VacancyService;
import com.mx.profuturo.bolsa.util.exception.custom.GenericStatusException;
import com.mx.profuturo.bolsa.web.controller.common.BaseController;

@Controller("recruitmentControl")

@Scope("request")
@CrossOrigin
public class RecruitmentControlImpl extends BaseController {
	
	
	@Autowired
	FacadeService facadeService;
	
	@Autowired
    private VacancyService vacancyService;
	
	@Autowired
	private TokenService tokenService;
	
	//iniciar-proceso
	public @ResponseBody
	@RequestMapping(value="iniciar-proceso-reclutamiento", method = RequestMethod.POST)
	BaseResponseBean assignToRecruitment(@RequestBody IniciarReclutamientoDTO dto){
	    return this.buildSuccessResponse();
	 }
		
	@ResponseBody@RequestMapping(value="reclutamiento/obtener-datos", method = RequestMethod.GET)
	public EntityResponseBean<RecruitmentDetailVO> getData(@RequestHeader("Authorization")String token, @RequestParam Integer idReclutamiento, @RequestParam String step, @RequestParam String role) throws GenericStatusException {
		String idUsuario = tokenService.getUserIdFromToken(this.getCleanToken(token));
		Integer iStep = Integer.valueOf(step);
		iStep = (iStep != null && iStep > 0)?iStep:0;
		RecruitmentDetailVO vo = this.facadeService.getData(idReclutamiento, role, iStep, idUsuario);
		return this.buildEntitySuccessResponse(vo);
	}
	
	@ResponseBody@RequestMapping(value="reclutamiento/obtener-datos-basicos", method = RequestMethod.GET)
	public EntityResponseBean<RecruitmentDetailVO> getBasicData(@RequestParam Integer idReclutamiento) {
		RecruitmentDetailVO vo = new RecruitmentDetailVO();
		
		vo.setEstado("EDITABLE");
		vo.setNombreCandidato("Julio Llamas Moctezuma");
		vo.setTituloVacante("Desarollador Fullstack");
		
		LinkedHashMap<String,LinkedList<RecruitmentStageVO>> map 
		= new LinkedHashMap<String, LinkedList<RecruitmentStageVO>>();
		
		
		LinkedList<RecruitmentStageVO> contacto = new LinkedList<>();
		LinkedList<RecruitmentStageVO> p_entrevistas = new LinkedList<>();
		LinkedList<RecruitmentStageVO> s_entrevistas = new LinkedList<>();
		LinkedList<RecruitmentStageVO> expendiente = new LinkedList<>();
		LinkedList<RecruitmentStageVO> contratacion = new LinkedList<>();
		
		
		RecruitmentStageVO f1 = new RecruitmentStageVO();
		f1.setCodigo(RecruitmentStages.FIL_TEL);
		f1.setDescripcion("Filtro Télefonico");
		f1.setStatus(RecruitmentStagesStatus.PEND);
		contacto.add(f1);
		
		
		
		RecruitmentStageVO f2 = new RecruitmentStageVO();
		f2.setCodigo(RecruitmentStages.P_ENT);
		f2.setDescripcion("Primera Entrevista");
		f2.setStatus(RecruitmentStagesStatus.PEND);
		p_entrevistas.add(f2);
		
		RecruitmentStageVO f3 = new RecruitmentStageVO();
		f3.setCodigo(RecruitmentStages.EX_PSICO);
		f3.setDescripcion("Examenes Psicometricos");
		f3.setStatus(RecruitmentStagesStatus.PEND);
		p_entrevistas.add(f3);
		
		
		RecruitmentStageVO f4 = new RecruitmentStageVO();
		f4.setCodigo(RecruitmentStages.EX_CONF);
		f4.setDescripcion("Examen Confianza");
		f4.setStatus(RecruitmentStagesStatus.PEND);
		p_entrevistas.add(f4);
		
		
		RecruitmentStageVO f5 = new RecruitmentStageVO();
		f5.setCodigo(RecruitmentStages.S_ENT);
		f5.setDescripcion("Segundas Entrevistas");
		f5.setStatus(RecruitmentStagesStatus.PEND);
		s_entrevistas.add(f5);
		
	
		
		RecruitmentStageVO e2 = new RecruitmentStageVO();
		e2.setCodigo(RecruitmentStages.OFE);
		e2.setDescripcion("Oferta");
		e2.setStatus(RecruitmentStagesStatus.PEND);
		expendiente.add(e2);
		
		RecruitmentStageVO e3 = new RecruitmentStageVO();
		e3.setCodigo(RecruitmentStages.FORM_CONT);
		e3.setDescripcion("Solicitud de empleo");
		e3.setStatus(RecruitmentStagesStatus.PEND);
		expendiente.add(e3);
		
		
		RecruitmentStageVO e4 = new RecruitmentStageVO();
		e4.setCodigo(RecruitmentStages.FOLIO);
		e4.setDescripcion("Folio");
		e4.setStatus(RecruitmentStagesStatus.PEND);
		expendiente.add(e4);
		
		
		RecruitmentStageVO e5 = new RecruitmentStageVO();
		e5.setCodigo(RecruitmentStages.FORM_DOCTOS);
		e5.setDescripcion("Formulario Documentos");
		e5.setStatus(RecruitmentStagesStatus.PEND);
		expendiente.add(e5);
		
		RecruitmentStageVO e6 = new RecruitmentStageVO();
		e6.setCodigo(RecruitmentStages.CONT);
		e6.setDescripcion("Contratacion");
		e6.setStatus(RecruitmentStagesStatus.PEND);
		contratacion.add(e6);
		
		map.put("CONTACTO", contacto);
		map.put("PRIMERAS_ENTREVISTAS", p_entrevistas);
		
		
		map.put("SEGUNDAS_ENTREVISTAS", s_entrevistas);
		map.put("EXPEDIENTE", expendiente);
		
		map.put("CONTRATACION", contratacion);
		
		vo.setEtapas(map);
		
		return this.buildEntitySuccessResponse(vo);
	} 
	
	/*
	//reclutamiento/obtener-etapas-proceso
	@ResponseBody
    @RequestMapping(value="reclutamiento/obtener-etapas-proceso", method = RequestMethod.GET)
	public EntityResponseBean<LinkedHashMap<String,LinkedList<RecruitmentPhaseVO>>> 
	getProcess(@RequestParam Integer idReclutamiento) {	
		
		LinkedHashMap<String,LinkedList<RecruitmentPhaseVO>> map 
		= new LinkedHashMap<String, LinkedList<RecruitmentPhaseVO>>();
		
		
		LinkedList<RecruitmentPhaseVO> contacto = new LinkedList<>();
		LinkedList<RecruitmentPhaseVO> p_entrevistas = new LinkedList<>();
		
		RecruitmentPhaseVO f1 = new RecruitmentPhaseVO();
		f1.setCodigo("FT");
		f1.setDescripcion("Filtro Télefonico");
		f1.setStatus("COMPLETED");
		contacto.add(f1);
		
		RecruitmentPhaseVO f2 = new RecruitmentPhaseVO();
		f2.setCodigo("PE");
		f2.setDescripcion("Primera Entrevista");
		f2.setStatus("PENDING");
		p_entrevistas.add(f2);
		
		RecruitmentPhaseVO f3 = new RecruitmentPhaseVO();
		f3.setCodigo("EP");
		f3.setDescripcion("Examenes Psicometricos");
		f3.setStatus("REJECTED");
		p_entrevistas.add(f3);
		
		map.put("CONTACTO", contacto);
		map.put("PRIMERAS_ENTREVISTAS", p_entrevistas);
		
		
		return this.buildEntitySuccessResponse(map);
	}*/
	
	@ResponseBody
	@RequestMapping(value="reclutamiento/obtener-motivos-rechazo-etapa", method = RequestMethod.GET)
	public EntityResponseBean<LinkedList<CatalogoVO>> getRejectReasonsByStep(@RequestParam String codigo) throws GenericStatusException {	
			List<CatalogoVO> list = facadeService.getRejectReassons();
			return this.buildEntitySuccessResponse(list);
	}
		
	@ResponseBody
	@RequestMapping(value="reclutamiento/obtener-operaciones-disponibles-usuario", method = RequestMethod.GET)
	public EntityResponseBean<LinkedList<RecruitmentStageVO>> getOperationsByUser(@RequestParam Integer idReclutamiento) {	
		LinkedList<RecruitmentStageVO> list = new LinkedList<>();
		
	
		if(idReclutamiento == 1) {
			RecruitmentStageVO f1 = new RecruitmentStageVO();
			f1.setCodigo(RecruitmentStages.FIL_TEL);
			f1.setDescripcion("Filtro Télefonico");
			list.add(f1);
		
			RecruitmentStageVO f21 = new RecruitmentStageVO();
			f21.setCodigo(RecruitmentStages.P_ENT_AG);
			f21.setDescripcion("Primera Entrevista (AG)");
			list.add(f21);
	
			RecruitmentStageVO f23 = new RecruitmentStageVO();
			f23.setCodigo(RecruitmentStages.P_ENT_RE);
			f23.setDescripcion("Primera Entrevista (RE)");
			list.add(f23);
			
			RecruitmentStageVO f3 = new RecruitmentStageVO();
			f3.setCodigo(RecruitmentStages.EX_PSICO);
			f3.setDescripcion("Examenes Psicometricos");
			list.add(f3);
			
			
			RecruitmentStageVO f4 = new RecruitmentStageVO();
			f4.setCodigo(RecruitmentStages.EX_CONF);
			f4.setDescripcion("Examen Confianza");
			list.add(f4);
			
			
			RecruitmentStageVO f51 = new RecruitmentStageVO();
			f51.setCodigo(RecruitmentStages.S_ENT_AG);
			f51.setDescripcion("Segundas Entrevistas (AG)");
			list.add(f51);
			RecruitmentStageVO f53 = new RecruitmentStageVO();
			f53.setCodigo(RecruitmentStages.S_ENT_RE);
			f53.setDescripcion("Segundas Entrevistas (RE)");
			list.add(f53);
			RecruitmentStageVO f54 = new RecruitmentStageVO();
			f54.setCodigo(RecruitmentStages.S_ENT_ES);
			f54.setDescripcion("Segundas Entrevistas (ES)");
			list.add(f54);
			RecruitmentStageVO f55 = new RecruitmentStageVO();
			f55.setCodigo(RecruitmentStages.S_ENT_NT);
			f55.setDescripcion("Segundas Entrevistas (NT)");
			list.add(f55);
			RecruitmentStageVO f56 = new RecruitmentStageVO();
			f56.setCodigo(RecruitmentStages.S_ENT_MR);
			f56.setDescripcion("Segundas Entrevistas (MR)");
			list.add(f56);
			
		
			
			RecruitmentStageVO e2 = new RecruitmentStageVO();
			e2.setCodigo(RecruitmentStages.OFE);
			e2.setDescripcion("Oferta");
			list.add(e2);
			
			RecruitmentStageVO e31 = new RecruitmentStageVO();
			e31.setCodigo(RecruitmentStages.FORM_CONT_ES);
			e31.setDescripcion("Solicitud de empleo");
			list.add(e31);
			RecruitmentStageVO e32 = new RecruitmentStageVO();
			e32.setCodigo(RecruitmentStages.FORM_CONT_RC);
			e32.setDescripcion("Solicitud de empleo");
			list.add(e32);
			
			RecruitmentStageVO e4 = new RecruitmentStageVO();
			e4.setCodigo(RecruitmentStages.FOLIO);
			e4.setDescripcion("Folio");
			list.add(e4);
			
			
			RecruitmentStageVO e51 = new RecruitmentStageVO();
			e51.setCodigo(RecruitmentStages.FORM_DOCTOS_EN);
			e51.setDescripcion("Formulario Documentos");
			list.add(e51);
			RecruitmentStageVO e52 = new RecruitmentStageVO();
			e52.setCodigo(RecruitmentStages.FORM_DOCTOS_ES);
			e52.setDescripcion("Formulario Documentos");
			list.add(e52);
			RecruitmentStageVO e53 = new RecruitmentStageVO();
			e53.setCodigo(RecruitmentStages.FORM_DOCTOS_RC);
			e53.setDescripcion("Formulario Documentos");
			list.add(e53);
			RecruitmentStageVO e54 = new RecruitmentStageVO();
			e54.setCodigo(RecruitmentStages.EST_SOC);
			e54.setDescripcion("Estudio Socioeconómico");
			list.add(e54);
			
			
			RecruitmentStageVO e61 = new RecruitmentStageVO();
			e61.setCodigo(RecruitmentStages.CONT_DE);
			e61.setDescripcion("Contratacion (DE)");
			list.add(e61);
			RecruitmentStageVO e62 = new RecruitmentStageVO();
			e62.setCodigo(RecruitmentStages.CONT_PC);
			e62.setDescripcion("Contratacion");
			list.add(e62);
		}else if(idReclutamiento == 100) {
			RecruitmentStageVO f1 = new RecruitmentStageVO();
			f1.setCodigo(RecruitmentStages.FIL_TEL);
			f1.setDescripcion("Filtro Télefonico");
			list.add(f1);
		
			RecruitmentStageVO f21 = new RecruitmentStageVO();
			f21.setCodigo(RecruitmentStages.P_ENT_AG_G);
			f21.setDescripcion("Primera Entrevista (AG)");
			list.add(f21);
			RecruitmentStageVO f23 = new RecruitmentStageVO();
			f23.setCodigo(RecruitmentStages.P_ENT_RE);
			f23.setDescripcion("Primera Entrevista (RE)");
			list.add(f23);
			
			RecruitmentStageVO f3 = new RecruitmentStageVO();
			f3.setCodigo(RecruitmentStages.EX_PSICO_G);
			f3.setDescripcion("Examenes Psicometricos");
			list.add(f3);
			
			
			RecruitmentStageVO f4 = new RecruitmentStageVO();
			f4.setCodigo(RecruitmentStages.EX_CONF);
			f4.setDescripcion("Examen Confianza");
			list.add(f4);
			
			
			RecruitmentStageVO f51 = new RecruitmentStageVO();
			f51.setCodigo(RecruitmentStages.S_ENT_AG_G);
			f51.setDescripcion("Segundas Entrevistas (AG)");
			list.add(f51);
			RecruitmentStageVO f53 = new RecruitmentStageVO();
			f53.setCodigo(RecruitmentStages.S_ENT_RE);
			f53.setDescripcion("Segundas Entrevistas (RE)");
			list.add(f53);
			RecruitmentStageVO f54 = new RecruitmentStageVO();
			f54.setCodigo(RecruitmentStages.S_ENT_ES_G);
			f54.setIdFase(1);
			f54.setDescripcion("Segundas Entrevistas (ES)");
			list.add(f54);
			RecruitmentStageVO f55 = new RecruitmentStageVO();
			f55.setCodigo(RecruitmentStages.S_ENT_NT);
			f55.setDescripcion("Segundas Entrevistas (NT)");
			list.add(f55);
			
	
			
			RecruitmentStageVO e2 = new RecruitmentStageVO();
			e2.setCodigo(RecruitmentStages.OFE);
			e2.setDescripcion("Oferta");
			list.add(e2);
			
			RecruitmentStageVO e31 = new RecruitmentStageVO();
			e31.setCodigo(RecruitmentStages.FORM_CONT_ES);
			e31.setDescripcion("Solicitud de empleo");
			list.add(e31);
			RecruitmentStageVO e32 = new RecruitmentStageVO();
			e32.setCodigo(RecruitmentStages.FORM_CONT_RC);
			e32.setDescripcion("Solicitud de empleo");
			list.add(e32);
			
			RecruitmentStageVO e4 = new RecruitmentStageVO();
			e4.setCodigo(RecruitmentStages.FOLIO);
			e4.setDescripcion("Folio");
			list.add(e4);
			
			
			RecruitmentStageVO e51 = new RecruitmentStageVO();
			e51.setCodigo(RecruitmentStages.FORM_DOCTOS_EN);
			e51.setDescripcion("Formulario Documentos");
			list.add(e51);
			RecruitmentStageVO e52 = new RecruitmentStageVO();
			e52.setCodigo(RecruitmentStages.FORM_DOCTOS_ES);
			e52.setDescripcion("Formulario Documentos");
			list.add(e52);
			RecruitmentStageVO e53 = new RecruitmentStageVO();
			e53.setCodigo(RecruitmentStages.FORM_DOCTOS_RC);
			e53.setDescripcion("Formulario Documentos");
			list.add(e53);
			RecruitmentStageVO e54 = new RecruitmentStageVO();
			e54.setCodigo(RecruitmentStages.EST_SOC);
			e54.setDescripcion("Estudio Socioeconómico");
			list.add(e54);
			
			
			RecruitmentStageVO e61 = new RecruitmentStageVO();
			e61.setCodigo(RecruitmentStages.CONT_DE_G);
			e61.setDescripcion("Contratacion (DE)");
			list.add(e61);
			RecruitmentStageVO e62 = new RecruitmentStageVO();
			e62.setCodigo(RecruitmentStages.CONT_PC);
			e62.setDescripcion("Contratacion");
			list.add(e62);
		}
		
		return this.buildEntitySuccessResponse(list);
}

	@ResponseBody
	@RequestMapping(value="reclutamiento/obtener-datos-fase", method = RequestMethod.GET)
	public EntityResponseBean<Object> getPhaseData(@RequestParam Integer idReclutamiento, 
			@RequestParam Integer idFase,  @RequestParam String codigoFase) {	
		Object entity = null;
		 	
		if(RecruitmentStages.P_ENT_AG.equals(codigoFase)) {
			PEAGStageForm f = new PEAGStageForm();
			f.setEntrevistador("CONSULTOR");
			f.setFecha("10/10/2019");
			f.setHora(12);
			f.setSede(1);
			f.setTipoEntrevista(1);
			entity = f;
		}else if(RecruitmentStages.S_ENT_AG.equals(codigoFase)) {
			SEAGStageForm f = new SEAGStageForm();
			f.setEntrevistador("CONSULTOR");
			f.setFecha("10/10/2019");
			f.setHora(12);
			f.setSede(1);
			f.setTipoEntrevista(1);
			f.setIdEntrevistador("1");
			entity = f;
		}else if(RecruitmentStages.S_ENT_ES_G.equals(codigoFase)) {
			SEESGStageForm f = new SEESGStageForm();
			String e1 = "Entrevista con Juan Mendéz el 25/10/2018";
			String e2 = "Entrevista con Jorge Herrera el 28/10/2018";
			String e3 = "Entrevista con Rodolfo Milla el 29/10/2018";
			//f.getEntrevistas().add(e1);
			//f.getEntrevistas().add(e2);
			//f.getEntrevistas().add(e3);
			entity = f;
		}
		 	
			
		return this.buildEntitySuccessResponse(entity);
	}
	
	
	@ResponseBody
	@RequestMapping(value="reclutamiento/obtener-datos-etapa", method = RequestMethod.GET)
	public EntityResponseBean<Object> getStageData(@RequestParam Integer idReclutamiento, 
			 @RequestParam String codigo, @RequestParam Integer idEtapa, @RequestParam Integer idEntrevista) throws GenericStatusException {	
		Object entity = this.facadeService.getStageData(idReclutamiento, codigo, idEtapa, idEntrevista);
			
		return this.buildEntitySuccessResponse(entity);
	}
	
	//reclutamiento/completar-fase
	@ResponseBody
    @RequestMapping(value="reclutamiento/guardar-datos-fase", method = RequestMethod.POST)
	public BaseResponseBean savePhaseData(@RequestHeader("Authorization")String token, @RequestBody CompletePhaseDTO dto) throws GenericStatusException {
		String idUsuario = tokenService.getUserIdFromToken(this.getCleanToken(token));
		dto.setIdUsuario(idUsuario);
		this.facadeService.saveStageData(dto);
		return this.buildSuccessResponse();
	}
	
	//reclutamiento/rechazar-candidato
	/*
	@ResponseBody
    @RequestMapping(value="reclutamiento/rechazar-candidato", method = RequestMethod.POST)public BaseResponseBean rejectCandidate(@RequestParam Integer idReclutamiento) {
		 
		return this.buildSuccessResponse();
	}
	
	
	@ResponseBody
	@RequestMapping(value="reclutamiento/obtener-detalle-fase", method = RequestMethod.GET)
	private EntityResponseBean<RecruitmentPhaseDetailVO> getPhaseDetail(@RequestParam Integer idFase) {	
			
		 	RecruitmentPhaseDetailVO f1 = new RecruitmentPhaseDetailVO();
			f1.setCodigo("FT");
			f1.setDescripcion("Filtro Télefonico");
			
			return this.buildEntitySuccessResponse(f1);
	}
	
	*/
	
	@ResponseBody
    @RequestMapping(value="reclutamiento/obtener-historial-proceso", method = RequestMethod.GET)
	public EntityResponseBean<LinkedList<EventVO>> getProccessHistory(@RequestParam Integer idReclutamiento, @RequestParam String step) throws GenericStatusException {
		Integer iStep = Integer.valueOf(step);
		iStep = (iStep != null && iStep > 0)?iStep:0;
		LinkedList<EventVO> entity = this.facadeService.getHistory(idReclutamiento,iStep);
		return this.buildEntitySuccessResponse(entity);
	}
	
	@ResponseBody
    @RequestMapping(value="reclutamiento/obtener-historial-proceso-old", method = RequestMethod.GET)
	public EntityResponseBean<LinkedList<EventVO>> getProccessHistory(@RequestParam Integer idReclutamiento) {
		LinkedList<EventVO> list = new LinkedList<>();
		
		
		EventVO e = new EventVO();
		e.setCodigoFase("FT");
		e.setIdReclutamiento(1);
		e.setIdFase(1);
		e.setTitulo("Filtro Telefonico");
		e.setFecha("20 de septiembre de 2019");
		e.setContenido("Completado exitosamente");
		e.setAutor("Karen Padierna");
		e.setIcono("INICIO");
		e.setEditable(false);
		
		
		
		EventVO e1 = new EventVO();
		e1.setCodigoFase("FT");
		e1.setIdReclutamiento(1);
		e1.setIdFase(898);
		e1.setTitulo("Filtro Telefonico");
		e1.setFecha("20 de septiembre de 2019");
		e1.setContenido("Completado exitosamente");
		e1.setAutor("Karen Padierna");
		e1.setEditable(false);
		
		
		EventVO e2 = new EventVO();
		e2.setCodigoFase("PEAG");
		e2.setIdReclutamiento(1);
		e2.setIdFase(31);
		e2.setTitulo("Primera entrevista");
		e2.setFecha("20 de septiembre de 2019");
		e2.setContenido("Entrevista presencial agendada el 1/10/19 a las 10:00 am");
		e2.setAutor("Karen Padierna");
		e.setIcono("AGENDA");
		e2.setEditable(true);
		
		list.add(e2);
		list.add(e1);
		list.add(e);
		
		return this.buildEntitySuccessResponse(list);
	}
		
	@ResponseBody
    @RequestMapping(value="reclutamiento/obtener-catalogos-etapa", method = RequestMethod.GET)
	public EntityResponseBean<LinkedHashMap<String,LinkedList<CatalogoVO>>> getCatalogsByStage(@RequestHeader("Authorization")String token, @RequestParam String codigo) throws GenericStatusException {
		String idUsuario = tokenService.getUserIdFromToken(this.getCleanToken(token));
		LinkedHashMap<String, LinkedList<CatalogoVO>> catalogos = facadeService.getCatalogsByStage(idUsuario, codigo);
		
		return this.buildEntitySuccessResponse(catalogos);
	}
	
	@ResponseBody
    @RequestMapping(value="reclutamiento/buscar-folio", method = RequestMethod.GET)
	public EntityResponseBean<LinkedList<CatalogoVO>> searchFolio(@RequestParam String folio) throws GenericStatusException {
		LinkedList<FolioVO> folios = facadeService.searchFolio(folio);
		return this.buildEntitySuccessResponse(folios);
	}
	@ResponseBody
    @RequestMapping(value="reclutamiento/obtener-horas-disponibles", method = RequestMethod.POST)
	public List<CatalogoVO> getInterviewHours(@RequestHeader("Authorization")String token, @RequestBody InterviewHoursDTO dto) throws GenericStatusException{
		String idUsuario = tokenService.getUserIdFromToken(this.getCleanToken(token));
		return facadeService.getInterviewHours(idUsuario, dto);
	}
	
	
	@ResponseBody
    @RequestMapping(value="reclutamiento/obtener-divisiones", method = RequestMethod.GET)
	public EntityResponseBean<LinkedList<CatalogoVO>> getDivisions(@RequestHeader("Authorization")String token, @RequestParam Boolean masterVacancy) throws GenericStatusException{
		UserCredentialsVO user = tokenService.getUserFromToken(this.getCleanToken(token));
		int idRama = user.getIdBranch();
		if(masterVacancy && idRama == 511) {
			idRama = 659;
		}
		LinkedList<CatalogoVO> response = vacancyService.getDivisions(idRama, false);
		return this.buildEntitySuccessResponse(response);
	}
	
	@ResponseBody
    @RequestMapping(value="reclutamiento/obtener-datos-entrevista-por-etapa", method = RequestMethod.GET)
	public EntityResponseBean<SEAGStageForm> getHandledInterviewData(@RequestParam Integer idReclutamiento,
			@RequestParam Integer idEntrevista, @RequestParam String codigo) throws GenericStatusException {
		// TODO Auto-generated method stub
		SEAGStageForm response = facadeService.getInterviewDataByStage(idReclutamiento,idEntrevista,codigo);
		return this.buildEntitySuccessResponse(response);
	}
}
