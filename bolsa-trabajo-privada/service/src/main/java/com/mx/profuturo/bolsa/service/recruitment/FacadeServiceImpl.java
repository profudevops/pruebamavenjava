package com.mx.profuturo.bolsa.service.recruitment;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.mx.profuturo.bolsa.model.recruitment.bean.StageModePairBean;
import com.mx.profuturo.bolsa.model.recruitment.constants.RecruitmentStages;
import com.mx.profuturo.bolsa.model.recruitment.dto.CompletePhaseDTO;
import com.mx.profuturo.bolsa.model.recruitment.dto.GenericStageQueryDTO;
import com.mx.profuturo.bolsa.model.recruitment.dto.InterviewDTO;
import com.mx.profuturo.bolsa.model.recruitment.dto.InterviewHoursDTO;
import com.mx.profuturo.bolsa.model.recruitment.dto.InterviewInServiceDTO;
import com.mx.profuturo.bolsa.model.recruitment.dto.form.PEAGStageForm;
import com.mx.profuturo.bolsa.model.recruitment.dto.form.SEAGStageForm;
import com.mx.profuturo.bolsa.model.recruitment.dto.form.SEESGStageForm;
import com.mx.profuturo.bolsa.model.recruitment.response.DatosEntrevistaResponseBean;
import com.mx.profuturo.bolsa.model.recruitment.response.ProcesoReclutamientoResponseBean;
import com.mx.profuturo.bolsa.model.recruitment.response.RegistroProceso;
import com.mx.profuturo.bolsa.model.recruitment.vo.EventVO;
import com.mx.profuturo.bolsa.model.recruitment.vo.FolioVO;
import com.mx.profuturo.bolsa.model.recruitment.vo.RecruitmentDetailVO;
import com.mx.profuturo.bolsa.model.recruitment.vo.RecruitmentPhaseCompletedVO;
import com.mx.profuturo.bolsa.model.service.candidates.dto.IniciarReclutamientoDTO;
import com.mx.profuturo.bolsa.model.vo.common.CatalogoVO;
import com.mx.profuturo.bolsa.util.exception.custom.GenericStatusException;

@Service
@Scope("request")
public class FacadeServiceImpl implements FacadeService {

	@Autowired
	OperationsService operationsService;
	
	@Autowired
	@Qualifier("Proc")
	ProcessService asesorService;
	
	@Autowired
	HistoryService historyService;
	
	@Autowired
	SaveStepsService savesStepsService;
	
	@Autowired
	AuxiliarService auxiliarService;
	
	@Override
	public void create(IniciarReclutamientoDTO dto) {
		operationsService.create(dto);
	}
	
	@Override
	public RecruitmentDetailVO getData(Integer idReclutamiento, String role, Integer step, String idUsuario) throws GenericStatusException {
		ProcesoReclutamientoResponseBean data = operationsService.getDataById(idReclutamiento, step);
		
		data = asesorService.handleStrategy(data);
		
		RecruitmentDetailVO vo = asesorService.processStages(data, role, idUsuario);
		if(vo != null) {
			LinkedList<EventVO> history = historyService.getHistory(data, vo.getProcessMap(), vo.getEstado(), role);
			vo.setIdCandidato(data.getDatosBasicos().getIdCandidato());
			vo.setIdPublicacion(data.getDatosBasicos().getIdPublicacion());
			vo.setTituloVacante(data.getDatosBasicos().getNombreVacante());
			vo.setNombreCandidato(data.getDatosBasicos().getNombreCandidato());
			vo.setHistory(history);
		}
		return vo;
	}
	
	@Override
	public Object getStageData(Integer idReclutamiento, String codigo, Integer idEtapa, Integer idEntrevista) throws GenericStatusException {
		
		Object entity = null;
		
		if(RecruitmentStages.P_ENT_AG.equals(codigo) || RecruitmentStages.P_ENT_AG_G.equals(codigo)) {
			
			
			entity = operationsService.getInterviewDataByStage(idReclutamiento, idEntrevista,codigo);
			
		}else if(RecruitmentStages.S_ENT_AG.equals(codigo)) {
			
			
			entity = operationsService.getInterviewDataByStage(idReclutamiento, idEntrevista,codigo);
			
		}else if(RecruitmentStages.S_ENT_ES_G.equals(codigo)) {
			
			
			entity = operationsService.getNoHandledInterviewData(idReclutamiento);
		}
		
		return entity;
		
	}
	
	@Override
	public void saveStageData(CompletePhaseDTO query) throws GenericStatusException {
	
		savesStepsService.saveStageData(query);
	}
	
	@Override
	public LinkedList<EventVO> getHistory(Integer idReclutamiento, Integer step) throws GenericStatusException {
		LinkedList<EventVO> list = historyService.getHistory(idReclutamiento, step, null);
		return list;
	}

	@Override
	public LinkedList<FolioVO> searchFolio(String folio) throws GenericStatusException {
		return auxiliarService.searchFolio(folio);
	}
	
	@Override
	public LinkedHashMap<String,LinkedList<CatalogoVO>>  getCatalogsByStage(String idUsuario, String codigo) throws GenericStatusException {
		return auxiliarService.getCatalogsByStage(idUsuario, codigo);
	}
	
	@Override
	public SEAGStageForm getInterviewDataByStage(@RequestParam Integer idReclutamiento,
			@RequestParam Integer idEntrevista,  
			@RequestParam String codigo) throws GenericStatusException {
		// TODO Auto-generated method stub
		SEAGStageForm response = operationsService.getInterviewDataByStage(idReclutamiento,
				 idEntrevista, codigo);
		
		
		/*CatalogoVO h = new CatalogoVO();
		h.setId(response.geHora());
		h.setDescripcion(response.getHora());

		response.setHora(h);
		*/
		
		return response;
	}
	
	@Override
	public List<CatalogoVO> getRejectReassons() throws GenericStatusException{
		return auxiliarService.getRejectReassons();
	}
	@Override
	public List<CatalogoVO> getInterviewHours(String appUser, InterviewHoursDTO dto) throws GenericStatusException{
		String idUsuario = null;
		Integer idEtapa = null;
		InterviewInServiceDTO inDTO = new InterviewInServiceDTO();
		if(dto.getCodigoFase().equals("PE-AG")) {
			idEtapa = 687;
			idUsuario = (null != dto.getTipoEntrevistador() || dto.getTipoEntrevistador() == 1028)?dto.getIdUsuario():dto.getIdConsultor();
		}else if(dto.getCodigoFase().equals("PE-AGG")) {
			idEtapa = 687;
			idUsuario = appUser;
		}
		else if(dto.getCodigoFase().equals("SE-AG")){
			idEtapa = 690;
			idUsuario = dto.getIdUsuario();
		}else if(dto.getCodigoFase().equals("SE-AGG")) {
			idEtapa = 690;
			idUsuario = dto.getIdUsuario();
		}else if(dto.getCodigoFase().equals("SE-AGSNR")) {
			idEtapa = 690;
			idUsuario = dto.getIdUsuario();
		}
		
		inDTO.setIdUsuario(idUsuario);
		inDTO.setIdEtapa(idEtapa);
		inDTO.setFecha(dto.getFecha());
		
		List<CatalogoVO> hours = auxiliarService.getInterviewHours(inDTO);
		
		return hours;
	}

}
