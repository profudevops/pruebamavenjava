package com.mx.profuturo.bolsa.service.recruitment;

import com.mx.profuturo.bolsa.model.recruitment.dto.CompletePhaseDTO;
import com.mx.profuturo.bolsa.model.recruitment.dto.GenericStageQueryDTO;
import com.mx.profuturo.bolsa.model.recruitment.dto.InterviewDTO;
import com.mx.profuturo.bolsa.model.recruitment.dto.SaveStepDTO;
import com.mx.profuturo.bolsa.model.recruitment.service.dto.TrustTestDTO;
import com.mx.profuturo.bolsa.model.recruitment.dto.form.InterviewResume;
import com.mx.profuturo.bolsa.model.recruitment.dto.form.PEAGStageForm;
import com.mx.profuturo.bolsa.model.recruitment.dto.form.SEAGStageForm;
import com.mx.profuturo.bolsa.model.recruitment.dto.form.SEESGStageForm;
import com.mx.profuturo.bolsa.model.recruitment.response.DatosBasicos;
import com.mx.profuturo.bolsa.model.recruitment.response.DatosEntrevistaResponseBean;
import com.mx.profuturo.bolsa.model.recruitment.response.Estrategia;
import com.mx.profuturo.bolsa.model.recruitment.response.ProcesoReclutamientoResponseBean;
import com.mx.profuturo.bolsa.model.recruitment.response.RegistroProceso;
import com.mx.profuturo.bolsa.model.recruitment.service.dto.FolioDTO;
import com.mx.profuturo.bolsa.model.recruitment.service.dto.InterviewAppointmentDTO;
import com.mx.profuturo.bolsa.model.recruitment.service.dto.InterviewResponseDTO;
import com.mx.profuturo.bolsa.model.recruitment.service.dto.PsychometricTestDTO;
import com.mx.profuturo.bolsa.model.recruitment.vo.FolioVO;
import com.mx.profuturo.bolsa.model.recruitment.vo.RecruitmentDetailVO;
import com.mx.profuturo.bolsa.model.recruitment.vo.RecruitmentPhaseCompletedVO;
import com.mx.profuturo.bolsa.model.recruitment.vo.RecruitmentStageVO;
import com.mx.profuturo.bolsa.model.recruitment.vo.RecruitmentStepDataVO;
import com.mx.profuturo.bolsa.model.restclient.RequestBean;
import com.mx.profuturo.bolsa.model.service.areasinteres.dto.GenericTextResponseBean;
import com.mx.profuturo.bolsa.model.service.auth.dto.BasicDTO;
import com.mx.profuturo.bolsa.model.service.candidates.dto.IniciarReclutamientoDTO;
import com.mx.profuturo.bolsa.model.service.common.GenericRequestBean;
import com.mx.profuturo.bolsa.model.vo.Candidato.ScoreVO;
import com.mx.profuturo.bolsa.model.vo.common.CatalogoVO;

import java.text.ParseException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.mx.profuturo.bolsa.util.exception.custom.GenericStatusException;
import com.mx.profuturo.bolsa.util.exception.custom.InterviewException;
import com.mx.profuturo.bolsa.util.formatter.DateUtil;

import org.springframework.beans.factory.annotation.Autowired;


import com.mx.profuturo.bolsa.model.recruitment.constants.RecruitmentStages;


public abstract class OperationsServiceBase implements OperationsService {
	


	public RecruitmentStepDataVO handleGetStep(String codigo, Integer IdEtapa) {
		RecruitmentStepDataVO vo = null;
		if(null == codigo) {
			//ARROJA EXCEPCION
		}
		
		if(RecruitmentStages.FIL_TEL.equals(codigo)) {
		}
		return vo;
	}
	
	
	private void handleReject(CompletePhaseDTO query) {

	}

	public void handleSaveStep(CompletePhaseDTO dto){}
	
	private void handleSaveFT(CompletePhaseDTO query) {
		
		RecruitmentPhaseCompletedVO vo; 
		
		if(query.getRechazo()) {
			this.handleReject(query);
		}else {
		
			//TODO: call service
		}
		
	}
			
	
	private RecruitmentDetailVO getBasicData(ProcesoReclutamientoResponseBean response) {
		RecruitmentDetailVO vo = new RecruitmentDetailVO();
		vo.setIdCandidato(response.getDatosBasicos().getIdCandidato());
		vo.setNombreCandidato(response.getDatosBasicos().getNombreCandidato());
		vo.setIdPublicacion(response.getDatosBasicos().getIdPublicacion());
		vo.setTituloVacante(response.getDatosBasicos().getNombreVacante());
		return vo ;
	}


	private void getHistory(LinkedList<RegistroProceso> registros) {
		// TODO Auto-generated method stub
		
	}

	
	private RequestBean<Integer> getIdReclutamientoRequest(Integer idReclutamiento){
	
		return this.getSingleParmURIRequest("idReclutamiento",idReclutamiento);
	}
	
	

	private RequestBean<Integer> getSingleParmURIRequest(String param, Integer value){
		RequestBean<Integer> rq = new RequestBean<Integer>();
		Map<String, Integer> uri = new HashMap<>();
		uri.put(param, value);
		rq.setUriVariables(uri);
		return rq ;
	}
	
	
	
	@Override
	public ProcesoReclutamientoResponseBean getDataById(Integer idReclutamiento, Integer step) throws GenericStatusException {
		
		step = (step <= 0)?1:step;
		
		RequestBean<Integer> request = new RequestBean<Integer>();
		Map<String, Integer> uriVariables = new LinkedHashMap<String, Integer>();
		//request.setPayload(idReclutamiento);
		uriVariables.put("idReclutamiento", idReclutamiento);
		request.setUriVariables(uriVariables);
		return this.__getRecruitmentData(this.getIdReclutamientoRequest(idReclutamiento),step);
	}


	@Override
	public ProcesoReclutamientoResponseBean getDataById(Integer idReclutamiento) throws GenericStatusException {

		return null;
	}
	
	
	@Override
	public SEESGStageForm getNoHandledInterviewData(Integer idReclutamiento) throws GenericStatusException {
	
		
		LinkedList<DatosEntrevistaResponseBean> list =  this.__getNoHandledInterviewData(this.getIdReclutamientoRequest(idReclutamiento));
		LinkedList<InterviewResume> entrevistas = new LinkedList<InterviewResume>();
		for(DatosEntrevistaResponseBean e:list) {
			entrevistas.add(this.buildInterviewResume(e));
		}
		SEESGStageForm form = new SEESGStageForm();
		form.setEntrevistas(entrevistas);
		return form ;
	}
	
	
	@Override
	public LinkedList<DatosEntrevistaResponseBean> callGetNoHandledInterviewData(Integer idReclutamiento) throws GenericStatusException {
	
		
		LinkedList<DatosEntrevistaResponseBean> list =  this.__getNoHandledInterviewData(this.getIdReclutamientoRequest(idReclutamiento));
		
		return list ;
	}
		
	private InterviewResume buildInterviewResume(DatosEntrevistaResponseBean e) {
		InterviewResume resume = new InterviewResume();
		resume.setDetalle(new StringBuilder("Entrevista con ").append(e.getEntrevistador()).append(" el " + e.getFecha()).append(" a las ").append( e.getFecha()).toString());
		resume.setIdEntrevista(e.getIdEntrevista());
		return resume ;
	}


	@Override
	public SEAGStageForm getInterviewDataByStage(Integer idReclutamiento, 
			Integer idEntrevista, String modo) throws GenericStatusException{	
		RequestBean<Integer> requestBean = new RequestBean<>();
		Map<String, Integer> uriVariables = new HashMap<String, Integer>();
		uriVariables.put("idReclutamiento", idReclutamiento);
		uriVariables.put("idEntrevista", idEntrevista);
		requestBean.setUriVariables(uriVariables );
		DatosEntrevistaResponseBean entity = __getInterviewData(requestBean, modo);	
		SEAGStageForm transform = this.proccessInterviewData(entity);
		return transform;
		
	}



	private SEAGStageForm proccessInterviewData(DatosEntrevistaResponseBean entity) throws GenericStatusException {
		SEAGStageForm f = new SEAGStageForm();
		f.setEntrevistador(entity.getEntrevistador());
		f.setHora(entity.getIdHora());
		f.setSede(entity.getIdSede());
		f.setIdEntrevista(entity.getIdEntrevista());
		f.setTipoEntrevista(entity.getIdTipoEntrevista());
		f.setIdDivision(entity.getIdDivision());
		f.setIdRegional(entity.getIdRegional());
		f.setIdGerencia(entity.getIdGerencia());
		f.setIdEntrevistador(entity.getIdEntrevistador());
		f.setTipoEntrevistador(entity.getIdTipoEntrevistador());
		f.setCorreoEntrevistador(entity.getCorreoEntrevistador());
		f.setDescripcionHora(entity.getHora());
		if(null != entity.getFecha()) {
			try {
				f.setFecha(DateUtil.formatFrontComponentDate(entity.getFecha()));
			} catch (ParseException e) {
				throw new GenericStatusException();
			}
		}
		
		return f;
	}


	@Override
	public void saveGenericStage(GenericStageQueryDTO genericStageQuery) throws GenericStatusException {
		
		if(genericStageQuery.getRepostulacion()) {
			genericStageQuery.setCodigo("REP-"+genericStageQuery.getCodigo());
		}
		
		RequestBean<GenericStageQueryDTO> requestBean = new RequestBean<>();
		requestBean.setPayload(genericStageQuery);
		GenericTextResponseBean entity = __callSaveGenericStage(requestBean);	
		
	}
	
	abstract protected GenericTextResponseBean __callSaveGenericStage(RequestBean<GenericStageQueryDTO> genericRequestBean) throws GenericStatusException;
	

	@Override
	public void savePsychometricTest(PsychometricTestDTO pDto) throws GenericStatusException {
		RequestBean<PsychometricTestDTO> requestBean = new RequestBean<>();
		requestBean.setPayload(pDto);
		GenericTextResponseBean entity = __callSavePsychometricTest(requestBean);
	}

	abstract protected GenericTextResponseBean __callSavePsychometricTest(RequestBean<PsychometricTestDTO> genericRequestBean) throws GenericStatusException;
	

	@Override
	public void saveTrustTest(TrustTestDTO tDto) throws GenericStatusException {
		RequestBean<TrustTestDTO> requestBean = new RequestBean<>();
		requestBean.setPayload(tDto);
		GenericTextResponseBean entity = __callSaveTrustTest(requestBean);
	}

	abstract protected GenericTextResponseBean __callSaveTrustTest(RequestBean<TrustTestDTO> genericRequestBean) throws GenericStatusException;
	

	@Override
	public void saveFolio(FolioDTO fDto) throws GenericStatusException {
		RequestBean<FolioDTO> requestBean = new RequestBean<>();

		requestBean.setPayload(fDto);
		GenericTextResponseBean entity = __callSaveFolio(requestBean);
		
	}

	abstract protected GenericTextResponseBean __callSaveFolio(RequestBean<FolioDTO> genericRequestBean) throws GenericStatusException;
	

	@Override
	public void saveInterviewAppointment(InterviewAppointmentDTO iDto) throws InterviewException {
	
		RequestBean<InterviewAppointmentDTO> requestBean = new RequestBean<>();
		requestBean.setPayload(iDto);
		GenericTextResponseBean entity = __callSaveInterviewAppointmentDTO(requestBean);
		
	}
	
	abstract protected GenericTextResponseBean __callSaveInterviewAppointmentDTO(RequestBean<InterviewAppointmentDTO> genericRequestBean) throws InterviewException;
	

	@Override
	public void saveInterviewResponse(InterviewResponseDTO iRDto) throws GenericStatusException {
		RequestBean<InterviewResponseDTO> requestBean = new RequestBean<>();
		requestBean.setPayload(iRDto);
		GenericTextResponseBean entity = __callSaveInterviewResponse(requestBean);
		
	}
	abstract protected GenericTextResponseBean __callSaveInterviewResponse(RequestBean<InterviewResponseDTO> genericRequestBean) throws GenericStatusException;
	
	
	@Override
	public LinkedList<FolioVO> searchFolio(String folio) {
		return null;
	}
	
	
	abstract protected LinkedList<FolioVO> __callSearchFolio(RequestBean<String> genericRequestBean) throws GenericStatusException;
	
		
	@Override
	public LinkedList<CatalogoVO> getStateProperties() {
		
		LinkedList<CatalogoVO> list = new LinkedList<CatalogoVO>();
		CatalogoVO h1 = new CatalogoVO();
		h1.setId(1);
		h1.setDescripcion("Corporativo");
		list.add(h1);
		
		CatalogoVO h2 = new CatalogoVO();
		h2.setId(2);
		h2.setDescripcion("Montevideo");
		list.add(h2);
		
		CatalogoVO h3 = new CatalogoVO();
		h3.setId(3);
		h3.setDescripcion("Monterrey 1");
		list.add(h3);
		
		return list;
	}
	
	
	/*
	@Override
	public void create(IniciarReclutamientoDTO dto) {
		
		
	}*/

	
	@Override
	public Boolean hasPublicationSpaces(Integer idReclutamiento, Integer idPublicacion) throws GenericStatusException {
		
		RequestBean<Integer> rqb = new RequestBean<>();
		Map<String, Integer> uriVariables = new HashMap<String, Integer>();
		uriVariables.put("idReclutamiento", idReclutamiento);
		uriVariables.put("idPublicacion", idPublicacion);
		rqb.setUriVariables(uriVariables);
		GenericTextResponseBean response = __callHasPublicationSpaces(rqb);
		return(response.getRespuesta().equals("Exito"));
	}

	abstract protected ProcesoReclutamientoResponseBean __getRecruitmentData(RequestBean<Integer> genericRequestBean, Integer step) throws GenericStatusException;
	
	abstract LinkedList<DatosEntrevistaResponseBean> __getNoHandledInterviewData(RequestBean<Integer> genericRequestBean) throws GenericStatusException;
	abstract DatosEntrevistaResponseBean __getInterviewData(RequestBean<Integer> genericRequestBean, String modo) throws GenericStatusException;
	protected abstract GenericTextResponseBean __callHasPublicationSpaces(RequestBean<Integer> genericRequestBean) throws GenericStatusException;
	//protected abstract List<CatalogoVO> __callgetInterviewType(RequestBean<GenericRequestBean> genericRequestBean)throws GenericStatusException;

	
}
