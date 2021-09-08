package com.mx.profuturo.bolsa.service.recruitment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.mx.profuturo.bolsa.model.recruitment.bean.StageModePairBean;
import com.mx.profuturo.bolsa.model.recruitment.constants.RecruitmentStages;
import com.mx.profuturo.bolsa.model.recruitment.dto.CompletePhaseDTO;
import com.mx.profuturo.bolsa.model.recruitment.dto.GenericStageQueryDTO;
import com.mx.profuturo.bolsa.model.recruitment.dto.form.BasicForm;
import com.mx.profuturo.bolsa.model.recruitment.dto.form.EXCStageForm;
import com.mx.profuturo.bolsa.model.recruitment.dto.form.EXPStageForm;
import com.mx.profuturo.bolsa.model.recruitment.dto.form.FRStageForm;
import com.mx.profuturo.bolsa.model.recruitment.dto.form.FTStageForm;
import com.mx.profuturo.bolsa.model.recruitment.dto.form.PEAGStageForm;
import com.mx.profuturo.bolsa.model.recruitment.dto.form.PEREStageForm;
import com.mx.profuturo.bolsa.model.recruitment.dto.form.SEAGStageForm;
import com.mx.profuturo.bolsa.model.recruitment.dto.form.SERMRStageForm;
import com.mx.profuturo.bolsa.model.recruitment.response.RegistroProceso;
import com.mx.profuturo.bolsa.model.recruitment.service.dto.FolioDTO;
import com.mx.profuturo.bolsa.model.recruitment.service.dto.InterviewAppointmentDTO;
import com.mx.profuturo.bolsa.model.recruitment.service.dto.InterviewResponseDTO;
import com.mx.profuturo.bolsa.model.recruitment.service.dto.PsychometricTestDTO;
import com.mx.profuturo.bolsa.model.recruitment.service.dto.TrustTestDTO;
import com.mx.profuturo.bolsa.util.exception.custom.GenericStatusException;


@Service
@Scope("request")
public class SaveStepsServiceImpl implements SaveStepsService{

	@Autowired
	OperationsService operationsService;
	
	@Override
	public void saveStageData(CompletePhaseDTO query) throws GenericStatusException {
		
		String code = query.getCodigoFase();
		
		
		if("FR".equals(code)) {
			code = "FR-GD";
		}
		
		
		if(null == code) {
			//ARROJA EXCEPCION
			throw new GenericStatusException();
		}
		if("SERE".equals(code)) {
			code = "SE-RE";
			query.setCodigoFase(code);
		}	

		if(RecruitmentStages.P_ENT_AG.equals(code)) {
			PEAGStageForm dto = query.getPeag();
			InterviewAppointmentDTO iDto = this.buildPEInterviewAppointmentDTO(query.getIdReclutamiento(),query.getIdUsuario(), dto);
			operationsService.saveInterviewAppointment(iDto);
			
		}else if( RecruitmentStages.P_ENT_AG_G.equals(code)) {
			PEAGStageForm dto = query.getPeag();
			dto.setTipoEntrevistador(1029);
			dto.setIdUsuario(query.getIdUsuario());
			InterviewAppointmentDTO iDto = this.buildPEInterviewAppointmentDTO(query.getIdReclutamiento(), query.getIdUsuario(), dto);
			operationsService.saveInterviewAppointment(iDto);
		}else if(RecruitmentStages.S_ENT_AG.equals(code)
				|| RecruitmentStages.S_ENT_AG_G.equals(code)
				|| RecruitmentStages.S_ENT_AGSNR.equals(code)) {
			SEAGStageForm dto = query.getSeag();
			InterviewAppointmentDTO iDto = this.buildSEInterviewDateDTO(query.getIdReclutamiento(), query.getIdUsuario(), dto);
			operationsService.saveInterviewAppointment(iDto);
			
		}else if(RecruitmentStages.S_ENT_MR.equals(code)||RecruitmentStages.S_ENT_MR_G.equals(code)) {
			this.handleAsesorMR(query);
			
		}else if(RecruitmentStages.P_ENT_RE.equals(code) || RecruitmentStages.S_ENT_RE.equals(code)) {
			PEREStageForm data = (PEREStageForm) (RecruitmentStages.P_ENT_RE.equals(code)?query.getPere():query.getSere());
			InterviewResponseDTO iRDto = this.buildInterviewResponseDTO(query, data);
			operationsService.saveInterviewResponse(iRDto);
			
		}else if(RecruitmentStages.EX_PSICO.equals(code) || RecruitmentStages.EX_PSICO_G.equals(code)) {
			
			PsychometricTestDTO pDto = this.buildPsychometricTestDTO(query);
			operationsService.savePsychometricTest(pDto);
			
		}else if(RecruitmentStages.EX_CONF.equals(code)) {
			TrustTestDTO tDto = this.buildTrustTestDTO(query);
			
			operationsService.saveTrustTest(tDto);
			
		}else if(RecruitmentStages.FOLIO_GD.equals(code)) {
			FolioDTO fDto = this.buildFolioDTO(query);
			operationsService.saveFolio(fDto);
			DocsAugustfix(query);

		}/*else if(RecruitmentStages.FORM_CONT_RC.equals(code)){
			handleFormContRC(query);
		}*/else if(RecruitmentStages.CONT_DE.equals(code) || RecruitmentStages.CONT_DE_G.equals(code) ){
			RegistroProceso record = this.buildBaseRecordDTO(query);
			if(!isREP(query)) {
				record.setModo("OK");
			}else {
				record.setModo("REP");
				record.setEstatus(1);
			}
			operationsService.saveGenericStage(new GenericStageQueryDTO(record));
		}else {
			this.performGenericStageSave(query);
		}
	}
	
	
	private boolean isREP(CompletePhaseDTO query) {
		boolean resp = false;
		if("repostular".equals(query.getCnde().getDecision())) {
			resp = true;
			
		}
		
		return resp;
	}


	private void handleFormContRC(CompletePhaseDTO query) throws GenericStatusException {
		RegistroProceso record = this.buildBaseRecordDTO(query);
		record.setModo("OK");
		operationsService.saveGenericStage(new GenericStageQueryDTO(record));
		if(record.getEstatus() == 1) {
			//this.OPENHRJanuaryfix(query);
		}
		
	}


	private void handleAsesorMR(CompletePhaseDTO query) throws GenericStatusException {
		
		SERMRStageForm form = query.getSemr();
		Boolean aproved = false;
		Integer status = -1;
		
		if(this.getYesAsTrue(form.getContinuar())) {
			aproved = true;
			status = 1;
		}
		
		
		
		GenericStageQueryDTO dto = new GenericStageQueryDTO();
		dto.setModo("MR");
		dto.setCodigo("SE");
		
		dto.setAprobado(aproved);
		dto.setEstatus(status);
		dto.setIdUsuario(query.getIdUsuario());
		dto.setIdRechazo(form.getMotivoRechazo());
		dto.setIdReclutamiento(query.getIdReclutamiento());
			
		operationsService.saveGenericStage(dto);
		
	}


	private InterviewAppointmentDTO buildSEInterviewDateDTO(Integer idReclutamiento,
			String idUsuario, SEAGStageForm form) {
		
		InterviewAppointmentDTO dto = new InterviewAppointmentDTO();
	
		Boolean answer = true;
		String mode = "AG";
		
		
		if(form.getTipoEntrevistador()== null) {
			form.setTipoEntrevistador(1030);
		}else if(form.getTipoEntrevistador()==1031 || "AGSNR".equals(dto.getModo())) {
			answer = false;
			mode = "AGSNR";
		}
		
		
		String idEntrevistador = form.getIdEntrevistador();
		dto.setCodigoEtapa("SE");
		dto.setFecha(form.getFecha());
		dto.setHora(form.getHora());
		dto.setIdEntrevista(form.getIdEntrevista());
		dto.setIdEntrevistador(idEntrevistador);
		dto.setIdReclutamiento(idReclutamiento);
		
		dto.setNecesitaRespuesta(answer);
		
		dto.setModo(mode);
		dto.setSede(form.getSede());
		dto.setTipoEntrevistador(form.getTipoEntrevistador());
		dto.setTipoEntrevista(form.getTipoEntrevista());
		dto.setIdUsuario(idUsuario);
	
		return dto;
	}


	private void DocsAugustfix(CompletePhaseDTO query) throws GenericStatusException {
		// TODO Auto-generated method stub
				
		RegistroProceso r13 = new RegistroProceso();
		r13.setAprobado(true);
		r13.setCodigo(RecruitmentStages.FORM_DOCTOS);
		r13.setComentario("");
		r13.setEstatus(1);
		r13.setModo("EN");
		r13.setIdUsuario(query.getIdUsuario());
		r13.setIdReclutamiento(query.getIdReclutamiento());
		r13.setRepostulacion(query.getRepostulacion());

		//r.add(r13);
		
		operationsService.saveGenericStage(new GenericStageQueryDTO(r13));
		
		
		
		RegistroProceso r14 = new RegistroProceso();
		r14.setAprobado(true);
		r14.setCodigo(RecruitmentStages.FORM_DOCTOS);
		r14.setComentario("");
		r14.setEstatus(1);
		r14.setModo("GD");
		r14.setIdUsuario(query.getIdUsuario());
		r14.setIdReclutamiento(query.getIdReclutamiento());
		r14.setRepostulacion(query.getRepostulacion());

		//r.add(r14);
		
		operationsService.saveGenericStage(new GenericStageQueryDTO(r14));
		
		RegistroProceso r15 = new RegistroProceso();
		r15.setAprobado(true);
		r15.setCodigo(RecruitmentStages.FORM_DOCTOS);
		r15.setComentario("");
		r15.setEstatus(1);
		r15.setModo("OK");
		r15.setIdRechazo(0);
		r15.setIdUsuario(query.getIdUsuario());
		r15.setIdReclutamiento(query.getIdReclutamiento());
		r15.setRepostulacion(query.getRepostulacion());

		//r.add(r15);
		
		
		operationsService.saveGenericStage(new GenericStageQueryDTO(r15));
	}


	private Boolean performGenericStageSave(CompletePhaseDTO query) throws GenericStatusException {
		RegistroProceso record = this.buildBaseRecordDTO(query);
		operationsService.saveGenericStage(new GenericStageQueryDTO(record));
		return record.getAprobado();
	}
	
	
	
	
	
	private RegistroProceso buildBaseRecordDTO(CompletePhaseDTO query) {
		
		Boolean reject = (query.getRechazo()!=null)?query.getRechazo():false;
		StageModePairBean stageMode = this.proccesStageModePair(query.getCodigoFase());
		RegistroProceso dto = new RegistroProceso();
		dto.setCodigo(stageMode.getCode());
		dto.setModo(stageMode.getMode());
		dto.setIdUsuario(query.getIdUsuario());
		dto.setIdReclutamiento(query.getIdReclutamiento());
		dto.setRepostulacion(query.getRepostulacion());
		BasicForm data = this.extractBasicFormData(query);
		if(data != null) {
			dto.setEstatus(this.getStatusFromForm(data.getContinuar()));		
			dto.setAprobado(this.getYesAsTrue(data.getContinuar()));
			dto.setIdRechazo(data.getMotivoRechazo());
			dto.setComentario(data.getComentarios());
			if("RC".equals(dto.getModo())){
				dto.setModo("OK");
			}
		}
		
		return dto;
	}
	
	private BasicForm extractBasicFormData(CompletePhaseDTO query) {
		BasicForm data = null;
		// TODO Auto-generated method stub
		if(query.getCodigoFase().equals(RecruitmentStages.FIL_TEL)) {
			data  = query.getFt();
		}else if(query.getCodigoFase().equals(RecruitmentStages.P_ENT_RE)) {
			data = query.getPere();
		}else if(query.getCodigoFase().equals(RecruitmentStages.EX_CONF)) {
			data = query.getExc();
		}else if(query.getCodigoFase().equals(RecruitmentStages.EX_PSICO)||query.getCodigoFase().equals(RecruitmentStages.EX_PSICO_G)) {
			data = query.getExp();
		}else if(query.getCodigoFase().equals(RecruitmentStages.S_ENT_RE)) {
			data = query.getSere();
		}else if(query.getCodigoFase().equals(RecruitmentStages.OFE)) {
			data = query.getOf();
		}else if(query.getCodigoFase().equals(RecruitmentStages.FORM_CONT_RC)) {
			data = query.getFcrc();
		}else if(query.getCodigoFase().equals(RecruitmentStages.EST_SOC)) {
			data = query.getEs();
		}else if(query.getCodigoFase().equals(RecruitmentStages.CONT_DE) || query.getCodigoFase().equals(RecruitmentStages.CONT_DE_G)) { //OK
			data = query.getCnde();
		}else if(query.getCodigoFase().equals(RecruitmentStages.FOLIO_GD)) {
			data = query.getFr();
		}
		return data;
	}
	
	private Boolean getYesAsTrue(String s) {
		return ("SI".equals(s) || "YES".equals(s) || "n/a".equals(s))?true:false;
	}
	
	private Integer getStatusFromForm(String s) {
	
		Integer i;
		if("SI".equals(s) || "YES".equals(s)|| "n/a".equals(s)) {
			i = 1;
		}else if("NO".equals(s)) {
			i = -1;
		}else {
			i = 0;
		}
		
		return i;
	}


	private StageModePairBean proccesStageModePair(String stageCode) {
		StageModePairBean pair = new StageModePairBean();
		if(stageCode != null) {
			String[] result = stageCode.split("-");
			pair.setCode(result[0]);
			if(result.length > 1) {
				pair.setMode(result[1]);
			}
		}
		return pair ;
	}


	
	private FolioDTO buildFolioDTO(CompletePhaseDTO query) {
		query.getFr().setContinuar("SI");
		RegistroProceso r = this.buildBaseRecordDTO(query);
		FRStageForm data = query.getFr();
		//FolioDTO dto = new FolioDTO(record);
		FolioDTO dto = new FolioDTO();
		
		dto.setAprobado(r.getAprobado());
		dto.setCodigo(r.getCodigo());
		dto.setFecha(r.getFecha());
		dto.setIdUsuario(r.getIdUsuario());
		dto.setModo(r.getModo());
		dto.setIdReclutamiento(r.getIdReclutamiento());
		dto.setFolio(data.getFolio());
		dto.setAprobado(true);
		dto.setEstatus(1);
		dto.setModo("GD");
		dto.setRepostulacion(r.getRepostulacion());
		
		if(null!=query.getRepostulacion() && query.getRepostulacion()) {
			dto.setCodigo("REP-FR");
		}
		
		
		return dto;
	}

	private TrustTestDTO buildTrustTestDTO(CompletePhaseDTO query) {
		EXCStageForm data = query.getExc();
		RegistroProceso record = this.buildBaseRecordDTO(query);
		TrustTestDTO dto = new TrustTestDTO(record);
		dto.setAprobado(this.getYesAsTrue(data.getContinuar()));
		dto.setResultado(data.getResultado());
		return dto;
	}

	private PsychometricTestDTO buildPsychometricTestDTO(CompletePhaseDTO query) {
		RegistroProceso record = this.buildBaseRecordDTO(query);
		EXPStageForm data = query.getExp();
		PsychometricTestDTO dto = new PsychometricTestDTO(record);
		dto.setD(data.getD());
		dto.setI(data.getI());
		dto.setS(data.getS());
		dto.setC(data.getC());
		dto.setResultado(data.getResultado());
		dto.setAprobado(this.getYesAsTrue(data.getContinuar()));
		return dto ;
	}

	private InterviewResponseDTO buildInterviewResponseDTO(CompletePhaseDTO query, PEREStageForm  data) {

		RegistroProceso record = this.buildBaseRecordDTO(query);
		InterviewResponseDTO dto = new InterviewResponseDTO(record);
		dto.setIdEntrevista(data.getIdEntrevista());
		dto.setModo("RE");
		return dto;
	}

	private InterviewAppointmentDTO buildPEInterviewAppointmentDTO(Integer idReclutamiento,
			String idUsuarioLogin, PEAGStageForm form) {
		

		String idEntrevistador = null;
		InterviewAppointmentDTO dto = new InterviewAppointmentDTO();
		dto.setIdUsuario(idUsuarioLogin);
		idEntrevistador = (form.getTipoEntrevistador() == 1029)?form.getIdConsultor():form.getIdUsuario();
		
		if(idEntrevistador == null) {
			idEntrevistador = idUsuarioLogin;
		}
		
		dto.setCodigoEtapa("PE");
		dto.setFecha(form.getFecha());
		dto.setHora(form.getHora());
		dto.setIdEntrevista(form.getIdEntrevista());
		dto.setIdEntrevistador(idEntrevistador);
		//dto.setIdEtapa(idEtapa);
		dto.setIdReclutamiento(idReclutamiento);
		dto.setModo("AG");
		dto.setNecesitaRespuesta(true);
		dto.setSede(form.getSede());
		dto.setTipoEntrevistador(form.getTipoEntrevistador());
		dto.setTipoEntrevista(form.getTipoEntrevista());
		
		return dto;
				
	}

}
