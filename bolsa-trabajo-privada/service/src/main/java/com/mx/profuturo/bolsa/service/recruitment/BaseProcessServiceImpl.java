package com.mx.profuturo.bolsa.service.recruitment;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.xmlbeans.impl.common.IdentityConstraint.IdRefState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.mx.profuturo.bolsa.model.recruitment.bean.AnalisisBean;
import com.mx.profuturo.bolsa.model.recruitment.bean.AsesorFlowUserOperations;
import com.mx.profuturo.bolsa.model.recruitment.bean.AsesorStageRoadConfiguration;
import com.mx.profuturo.bolsa.model.recruitment.bean.GeneralFlowUserOperations;
import com.mx.profuturo.bolsa.model.recruitment.bean.PhaseBean;
import com.mx.profuturo.bolsa.model.recruitment.bean.StageBean;
import com.mx.profuturo.bolsa.model.recruitment.bean.StageModePairBean;
import com.mx.profuturo.bolsa.model.recruitment.bean.StagesModesBean;
import com.mx.profuturo.bolsa.model.recruitment.constants.RecruitmentStages;
import com.mx.profuturo.bolsa.model.recruitment.response.Estrategia;
import com.mx.profuturo.bolsa.model.recruitment.response.ProcesoReclutamientoResponseBean;
import com.mx.profuturo.bolsa.model.recruitment.response.RegistroProceso;
import com.mx.profuturo.bolsa.model.recruitment.vo.RecruitmentDetailVO;
import com.mx.profuturo.bolsa.model.recruitment.vo.RecruitmentStageVO;
import com.mx.profuturo.bolsa.model.role.CommercialRoles;
import com.mx.profuturo.bolsa.model.role.CorporateRoles;
import com.mx.profuturo.bolsa.util.exception.custom.GenericStatusException;

@Scope("request")
@Service("Asesor")
public abstract class BaseProcessServiceImpl implements AsesorProcessService {
	
	@Autowired
	protected AsesorStageRoadConfiguration conf;
	protected String mode;
	@Autowired
	protected AsesorFlowUserOperations userOps;
	protected Boolean masterVacancy = false; 
	protected Boolean performedRepostulation = false; 
	protected Boolean isReadOnly = false;
	protected Integer completedPhases;
	protected String numeroProvisional;
	protected LinkedHashMap<String,PhaseBean> processMap;
	protected LinkedHashMap<String,PhaseBean> repostulationMap;
	protected Integer idPublicacion;
	protected Boolean secondInterviewPass=false;
	protected Integer idCurrentInterview;
	protected String observer; 
	protected String idUsuario;
	protected String idLastInterviewer;
	protected String role;
	protected String recruiter;
	protected String consultor;
	protected Long folio;
	
	@Autowired
	OperationsService operationsService;
	private Integer idReclutamiento;
	private Integer idPreReject;
	private String currentComment;
	
	public AnalisisBean doAnalysis(LinkedList<RegistroProceso> rs) {
		AnalisisBean a = new AnalisisBean();
		String phase;
		for(RegistroProceso r: rs) {
			phase = this.setDataByPasheStage(a,r);
			this.setMajorPhase(a, r.getCodigo());
			this.validatePhase(a, r);
			//this.createStageVO(a,phase,r);
			this.setReject(a,r);
		}
		
		this.setRepostulation(a);
		
		return a;
	}
		
	
	@Override
	public RecruitmentDetailVO processStages(ProcesoReclutamientoResponseBean data, String role, String idUsuario) {
		this.idUsuario = idUsuario;
		this.recruiter = data.getDatosBasicos().getIdUsuario();
		this.consultor = data.getDatosBasicos().getIdConsultor();
		this.role = role;
		this.observer = data.getDatosBasicos().getIdObservador();
		this.idPublicacion = data.getDatosBasicos().getIdPublicacion();
		this.idReclutamiento = data.getDatosBasicos().getIdReclutamiento();
		this.performedRepostulation = data.getDatosBasicos().getRepostulacion();
		this.userOps.setMode(this.mode);
		this.conf.setMode(mode);
		this.completedPhases = 0;
		this.numeroProvisional = data.getDatosBasicos().getNumeroProvicional();
		this.folio = data.getDatosBasicos().getFolio();
		RecruitmentDetailVO vo = new RecruitmentDetailVO();
		vo.setNumeroProvisional(this.numeroProvisional);
		vo.setFolio(this.folio);
		this.masterVacancy = data.getDatosBasicos().getVacanteMaestra();
		LinkedList<RecruitmentStageVO> operations = null;
		LinkedHashMap<String, LinkedList<RecruitmentStageVO>> stages = null;
		
		this.configureProccessMap(data);
	
		this.isReadOnly = (!data.getDatosBasicos().getAbierto())
				|| !this.userHasEditionAuth();
		
		
		if(!isReadOnly) {
			operations = this.setUpOperationsByUser(role);
			
			this.isReadOnly = (operations == null || operations.size() <= 0);			
		}
		
		
		handleStrategy(data.getEstrategia(),operations);
		
		vo.setEsVacanteMaestra(this.masterVacancy);
		vo.setIdUsuario(data.getDatosBasicos().getIdUsuario());
		vo.setIdConsultor(data.getDatosBasicos().getIdConsultor());
		
		vo.setEsRepostulacion(this.performedRepostulation);
		vo.setEstado((!isReadOnly)?"EDITABLE":"READONLY");
		this.prepareProcessMapOutput();
		vo.setProcessMap(this.processMap);
		vo.setIdObservador(data.getDatosBasicos().getIdObservador());
		vo.setCorreoObservador(data.getDatosBasicos().getCorreoObservador());
		vo.setOperaciones(operations);
		vo.setIdCurrentInterview(this.idCurrentInterview);
		vo.setIdPreRechazo(this.idPreReject);
		vo.setComentario(this.currentComment);
		
		return vo;
	}
	
	
	
	protected Boolean userHasEditionAuth() {
		
		Boolean auth = false;
		if(this.isUserAdmon() || (this.isUserInterviewer())
				||(this.isUserRecruiterConsultor())){
			auth = true;
		}
		return auth ;
	}
	
	private boolean isUserRecruiterConsultor() {
		return this.idUsuario.equals(this.recruiter)
				|| this.idUsuario.equals(this.consultor);
	}


	private boolean isUserInterviewer() {
		return this.idUsuario.equals(this.idLastInterviewer);
	}


	private boolean isUserAdmon() {
	
		return this.role.equals(CorporateRoles.ADMN)
				|| this.role.equals(CommercialRoles.ADMN);
	}


	private void handleStrategy(Estrategia stgy, LinkedList<RecruitmentStageVO> operations) {
		
		if(!stgy.getExamenConfianza()) {
			//operations.get(index)
		}
		
	}


	protected void prepareProcessMapOutput() {
		this.processMap.remove("INICIO");
		this.processMap.remove("FIN");
	}

	protected LinkedList<RecruitmentStageVO> setUpOperationsByUser(String role) {
		LinkedList<RecruitmentStageVO> result = new LinkedList<RecruitmentStageVO>();
		String stM = null;
		String phase = null;
		String stage = null;
		LinkedHashMap<String, LinkedHashMap<String, StagesModesBean>> ops = userOps.getOperationsByRole(role);
		for(Entry<String, LinkedHashMap<String, StagesModesBean>> fase:ops.entrySet()) {
			phase = fase.getKey();
			if(this.processMap.get(phase).getOpen()) {
				for(Entry<String, StagesModesBean> stages : fase.getValue().entrySet()) {
					stage = stages.getKey();
					StageBean stageData = this.processMap.get(phase).getStages().get(stage);
					if(!stageData.getComplete()) { // stage complete	
						stM = this.determinateStageMode(stageData, role);
						String stageCode = alterStage(stageData.getCode());
						if(null != stM) {
							result.add(this.createStageVO(stageCode,stM, stageData.getSignal()));
						}
					}
				}
				
			}else {
				break;
			}
		}
		
		return result;
	}




	protected abstract String alterStage(String stage);


	protected RecruitmentStageVO createStageVO(String code, String codeMode, String signal) {
		RecruitmentStageVO st = null;
		if(code.equals(RecruitmentStages.FIL_TEL)) {
			st = new RecruitmentStageVO(codeMode, "Filtro Telefonico");
		}else if(code.equals(RecruitmentStages.P_ENT)){
			st = new RecruitmentStageVO(codeMode, "Primera Entrevista");
		}else if(code.equals(RecruitmentStages.EX_CONF) ){
			st = new RecruitmentStageVO(codeMode, "Examenes de confianza");
		}else if(code.equals(RecruitmentStages.EX_PSICO)){
			st = new RecruitmentStageVO(codeMode, "Examenes Psicometricos");
		}else if(code.equals(RecruitmentStages.EX_PSICO_G)){
			st = new RecruitmentStageVO(RecruitmentStages.EX_PSICO_G, "Examenes Psicometricos");
		}else if(code.equals(RecruitmentStages.S_ENT)){
			st = new RecruitmentStageVO(codeMode, "Segundas entrevistas");
		}else if(code.equals(RecruitmentStages.OFE)){
			st = new RecruitmentStageVO(codeMode, "Oferta");
		}else if(code.equals(RecruitmentStages.FORM_CONT)){
			st = new RecruitmentStageVO(codeMode, "Solicitud de empleo");
		}else if(code.equals(RecruitmentStages.FORM_DOCTOS)){
			st = new RecruitmentStageVO(codeMode, "Solicitud de empleo");
		}else if(code.equals(RecruitmentStages.EST_SOC)){
			st = new RecruitmentStageVO(codeMode, "Estudio Socioeconómico");
		}else if(code.equals(RecruitmentStages.CONT)){
			st = new RecruitmentStageVO(codeMode, "Contratación", signal);
		}else if(code.equals(RecruitmentStages.FOLIO)){
			st = new RecruitmentStageVO(codeMode, "Folio"); 
		}
		
		return st;
	}


	protected String determinateStageMode(StageBean rst, String role) {
		String st = null;
		if(rst.getHasModes()) {
			st = chooseMode(rst, role);
		}else {
			st = (rst.getStatus() == 0)? rst.getCode():null;
		}
		return st;
	}


	protected String chooseMode(StageBean rst, String role) {
		String st = null;
		if(rst.getCode().equals(RecruitmentStages.P_ENT)) {
			st = choosePEMode(rst);
		}else if(rst.getCode().equals(RecruitmentStages.S_ENT)) {
			st = chooseSEMode(rst, role);
		}else if(rst.getCode().equals(RecruitmentStages.FORM_CONT)) {
			st = chooseFCMode(rst, role);
		}else if(rst.getCode().equals(RecruitmentStages.FOLIO)) {
			st = chooseFOLIOMode(rst, role);
		}else if(rst.getCode().equals(RecruitmentStages.FORM_DOCTOS)) {
			st = chooseFDMode(rst, role);
		}else if(rst.getCode().equals(RecruitmentStages.CONT)) {
			st = chooseCNMode(rst, role);
		}
		return st;
	}

	protected abstract String chooseEXPSICOMode(StageBean rst);


	abstract protected String chooseFOLIOMode(StageBean rst, String role);

	abstract protected String chooseCNMode(StageBean rst, String role);

	protected String handleCanHireSignals(StageBean rst, String role) {
		String st = null;
		if(!this.validatePreHiringCompletedPhases()) {
			st = "Antes de contratar al Candidato, debes completar las etapas pendientes en el proceso de reclutamiento";
		} else
			try {
				if(!this.validatePositionsAvialibility()) {
					//st = "El folio de requisición debe ser agregado por tu consultor. Por favor ponte en contacto con él para continuar con el proceso.";
					st = "No hay posiciones disponibles para realizar la contratación. Solicita a tu consultor la apertura de una posición";
				}
			} catch (GenericStatusException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return st;
	}

	protected boolean validatePreHiringCompletedPhases() {
		Integer max = this.processMap.size() - 2;
		return (max <= this.completedPhases);
	}

	protected Boolean validatePositionsAvialibility() throws GenericStatusException {
		Boolean validate = true;
		if(this.masterVacancy) {
			this.operationsService.hasPublicationSpaces(this.idReclutamiento, 
					this.idPublicacion);
		}
		return validate;
	}

	protected String chooseFDMode(StageBean rst, String role) {
		String st = null;
		if(rst.getCurrentMode() == null) {
			st = RecruitmentStages.FORM_DOCTOS_EN;
		}else if(rst.getCurrentMode().equals("EN")) {
			st = RecruitmentStages.FORM_DOCTOS_ES;
		}else if(rst.getCurrentMode().equals("GD")) {
			st = RecruitmentStages.FORM_DOCTOS_RC;
		}
		return st;
	}

	protected String chooseFCMode(StageBean rst, String role) {
		String st = null;
		if(rst.getCurrentMode() == null) {
			st = RecruitmentStages.FORM_CONT_ES;
		}else if(rst.getCurrentMode().equals("GD")) {
			st = RecruitmentStages.FORM_CONT_RC;
		} else if(rst.getCurrentMode().equals("OK") && rst.getStatus() == -1) {
			st = RecruitmentStages.FORM_CONT_ES;
		}
		return st;
	}

	abstract protected String chooseSEMode(StageBean rst, String role);

	abstract protected String choosePEMode(StageBean rst);

	protected void buildOutputData(ProcesoReclutamientoResponseBean process) {
	
		Boolean readOnly = isReadOnlyView(process);
		for(Entry<String, PhaseBean> e:processMap.entrySet()) {
			PhaseBean phase = e.getValue();
			if(phase.getCover()) {
				break;
			}else {
			
			}
			
		}
	}


	protected Boolean isReadOnlyView(ProcesoReclutamientoResponseBean process) {
		// TODO Auto-generated method stub
		return null;
	}

	public void configureProccessMap(ProcesoReclutamientoResponseBean process) {
		
		this.processMap = conf.instanceProccesMap();
		Boolean b = false;
		for(RegistroProceso r: process.getRegistros()) {
			
			sanitizeStatus(r);
			handlePreReject(r);
			sanitizeRepostulation(r);
			String phase = conf.getPhaseByStage(r.getCodigo());
			
			handleStageRecord(phase, r);
			handleInterviewData(phase, r);
			
			if(isStageCompleted(phase, r) && ! RecruitmentStages.RECH.equals(r.getCodigo())) {
				processMap.get(phase);
				incrementStageCount(phase);
				if(processMap.get(phase).getCover() || isPhaseCovered(r, phase)) {
					openNewPhase(phase);
					isPhaseCompleted(r,phase);
				}		
			}else if(isRepostulationRecord(r)) {
				performRepostulation();
			}else if(r.getEstatus() == -1) {
				handleReject(phase,r);
			}
		}
		if(this.secondInterviewPass) {
			processMap.get("SEGUNDAS_ENTREVISTAS").getStages().get("SE").setComplete(this.secondInterviewPass);
			processMap.get("SEGUNDAS_ENTREVISTAS").getStages().get("SE").setStatus(1);
		}
		
	}
	
	private void sanitizeRepostulation(RegistroProceso r) {
		if(this.performedRepostulation) {
			String tmp = r.getCodigo();
			
			if(tmp.contains("REP-")) {
					
					r.setCodigo(tmp.replace("REP-", ""));
			}
			
		}
		
	}


	private void handlePreReject(RegistroProceso r) {
		if(null != r.getIdPreRechazo()) {
			this.idPreReject = r.getIdPreRechazo();
			this.currentComment = r.getComentario();
		}
	}


	abstract protected void handleInterviewData(String phase, RegistroProceso r);


	private void sanitizeStatus(RegistroProceso r) {
		if(null == r.getEstatus()) {
			r.setEstatus(1);
		}
	}
	
	private void performRepostulation() {
		this.performedRepostulation = true;
		this.repostulationMap = conf.instanceProccesMap();
		this.processMap.put("FOLIO", this.repostulationMap.get("FOLIO"));
		this.processMap.put("FORM_DOCUMENTOS", this.repostulationMap.get("FORM_DOCUMENTOS"));
		this.processMap.put("CONTRATACION", this.repostulationMap.get("CONTRATACION"));
		this.processMap.get("FOLIO").setOpen(true);
	}

	private boolean isRepostulationRecord(RegistroProceso r) {
		// TODO Auto-generated method stub
		return (RecruitmentStages.CONT.equals(r.getCodigo()) && "REP".equals(r.getModo()));
	}

	
	protected void handleReject(String phase, RegistroProceso r) {
		
		if((!RecruitmentStages.S_ENT.equals(r.getCodigo()) ||
				RecruitmentStages.S_ENT.equals(r.getCodigo()) && 
				"MR".equals(r.getModo()))
				) {
			this.setRejectOnMap(phase, r);
		}
		
	}
	
	protected void setRejectOnMap(String phase, RegistroProceso r) {
		this.processMap.get(phase).getStages().get(r.getCodigo()).setStatus(-1);
		if(!("FORM_CONTRATACION".equals(phase) && "OK".equals(r.getModo()))) {
			this.processMap.get(phase).getStages().get(r.getCodigo()).setComplete(true);
			this.processMap.get(phase).setComplete(true);
		}
		
	}

	protected void handleStageRecord(String phase, RegistroProceso r) {
		if(null != r.getModo() && ! "AGSNR".equals(r.getModo())) {
			//TODO: AQUI 
			this.processMap.get(phase).getStages().get(r.getCodigo()).setCurrentMode(r.getModo());
			this.processMap.get(phase).getStages().get(r.getCodigo()).setCurrentModeDate(r.getFecha());
			if(r.getCodigo().equals("PE") && r.getModo().equals("AG")) {
				this.idCurrentInterview = r.getIdEntrevista();
			}
			this.doSEHack(r,phase);
		}
		
		this.processMap.get(phase).getStages().get(r.getCodigo()).setIdEtapa(r.getIdEtapa());
	}

	


	protected void openNewPhase(String phase) {
		String nuPhase = conf.getOpenPhaseByCovered(phase);
		if(null != nuPhase) {
			this.processMap.get(nuPhase).setOpen(true);
		}
	}         


	protected void incrementStageCount(String phase) {
		PhaseBean f = processMap.get(phase);
		f.setCompletedStages((f.getCompletedStages()+1));	
	}


	protected boolean isPhaseCompleted(RegistroProceso r, String phase) {
		PhaseBean f = processMap.get(phase);
		boolean b = (f.getCompletedStages() == f.getStages().size()); //TODO: aqui
		processMap.get(phase).setComplete(b);
		if(b) {
			this.completedPhases++;
		}
		return b;
	}


	protected boolean isPhaseCovered_old(RegistroProceso r, String phase) {
		PhaseBean f = processMap.get(phase);
		boolean b = false;
		if(f.getStages().size() > 1) {
			StageModePairBean stM = this.conf.getCoverByPhase(phase);
			if(null != stM.getMode()) {
				b = (r.getCodigo() == stM.getCode()); 
			}else {
				b = (r.getCodigo() == stM.getCode()) && (r.getModo() == stM.getMode());
			}
		}else {
			b = true;
		}
		
		processMap.get(phase).setCover(b);
		return b;
	}

	
	protected boolean isPhaseCovered(RegistroProceso r, String phase) {
		PhaseBean f = processMap.get(phase);
		boolean b = false;
		if(f.getStages().size() > 1) {
			StageModePairBean stM = this.conf.getCoverByPhase(phase);
			if(null == stM.getMode()) {
				b = (r.getCodigo().equals(stM.getCode())); 
			}else {
				b = (r.getCodigo().equals(stM.getCode())) && (stM.getMode().equals(r.getModo()));
			}
		}else {
			b = true;
		}
		
		processMap.get(phase).setCover(b);
		return b;
	}


	protected Boolean isStageCompleted(String phase, RegistroProceso r) {
		Boolean b = false;
		if(r.getEstatus() == 1) {
			if(null != r.getModo()) {
				b = checkStageCompleteByMode(r);
			}else {
				b = true;
			}
		}
		
		this.processMap.get(phase).getStages().get(r.getCodigo()).setStatus((b)?1:0);
		this.processMap.get(phase).getStages().get(r.getCodigo()).setComplete(b);
		
		return b;
	}


	protected Boolean checkStageCompleteByMode(RegistroProceso r) {
		Boolean complete = false;
		if(r.getCodigo().equals(RecruitmentStages.S_ENT)) {
			complete = this.validateSTComplete(r);
		}else {
			complete = this.conf.checkStageCompleteByMode(r.getCodigo(),r.getModo());
		}
		return complete;
	}


	protected Boolean validateSTComplete(RegistroProceso r) {
		Boolean complete = false;
		if(r.getModo().equals("RE") && r.getEstatus() ==1 || r.getModo().equals("EX")){
			complete = true;
		}
		
		return complete;
	}

	protected void setReject(AnalisisBean a, RegistroProceso r) {
		// TODO Auto-generated method stub
		
	}


	protected void setRepostulation(AnalisisBean a) {
		// TODO Auto-generated method stub
		
	}
	
	protected void validatePhase(AnalisisBean a, RegistroProceso r) {
		// TODO Auto-generated method stub
		
	}


	protected void setMajorPhase(AnalisisBean a, String p) {
	
		int i = this.getPhaseLevel(p);
		int j = this.getPhaseLevel(a.getFaseMayorRango());
		String np = (i > j)? p : a.getFaseMayorRango();
		a.setFaseMayorRango(np);
	}


	protected int getPhaseLevel(String p) {
		// TODO Auto-generated method stub
		return 0;
	}


	protected String setDataByPasheStage(AnalisisBean a, RegistroProceso r) {
	
		String stage = r.getCodigo();
		String phase = this.getPhaseByStage(stage);
		
		//DO VALIDATIONS OBJECTS
		a.getRegistrosPorFase().get(phase).add(r);
		
		return phase;
	}
	
	protected String getPhaseByStage(String stage) {
		return null;
	}


	public Integer getCompletedPhases() {
		return completedPhases;
	}


	public void setCompletedPhases(Integer completedPhases) {
		this.completedPhases = completedPhases;
	}


	protected abstract void doSEHack(RegistroProceso r, String phase);
	
	
}
