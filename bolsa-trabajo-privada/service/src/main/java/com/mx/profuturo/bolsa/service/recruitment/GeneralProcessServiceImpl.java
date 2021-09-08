package com.mx.profuturo.bolsa.service.recruitment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.mx.profuturo.bolsa.model.recruitment.bean.GeneralFlowUserOperations;
import com.mx.profuturo.bolsa.model.recruitment.bean.StageBean;
import com.mx.profuturo.bolsa.model.recruitment.constants.RecruitmentStages;
import com.mx.profuturo.bolsa.model.recruitment.response.RegistroProceso;
import com.mx.profuturo.bolsa.model.role.CommercialRoles;
import com.mx.profuturo.bolsa.model.role.CorporateRoles;


@Scope("request")
@Service("General")
public class GeneralProcessServiceImpl extends BaseProcessServiceImpl{
	
	{
		this.mode = "ASESOR";
	}
	
	
	protected String chooseFOLIOMode(StageBean rst, String role) {
		String st = null;
		st = RecruitmentStages.FOLIO_BU;
		return st;
	}

	
	protected String choosePEMode(StageBean rst) {
		String st = null;
		if(rst.getCurrentMode() == null) {
			st = RecruitmentStages.P_ENT_AG_G;
		}else {
			st = RecruitmentStages.P_ENT_RE;
		}
		return st;
	}
	
	
	protected String chooseCNMode(StageBean rst, String role) {
		String st = null;
		String mode = null;
		if(rst.getCurrentMode() == null) {
			mode = handleCanHireSignals(rst, role);
			if(mode == null) {
				st = RecruitmentStages.CONT_DE_G; 
			}else {
				st = RecruitmentStages.CONT_PC;
				this.processMap.get(this.conf.getPhaseByStage(RecruitmentStages.CONT)).getStages().get(RecruitmentStages.CONT).setSignal(mode);
			}
		}
		return st;
	}
	
	
	 
	protected String chooseSEMode(StageBean rst, String role) {
		String st = null;
		
		if(hasSEAutorization(role)){
			if(this.idUsuario.equals(this.idLastInterviewer)&&"AG".equals(rst.getCurrentMode())) {
				st = RecruitmentStages.S_ENT_RE;
			}else if(null!=rst.getCurrentMode()&&!"AGSNR".equals(rst.getCurrentMode())) {
				if(role.equals(CommercialRoles.OBSERVER) || role.equals(CorporateRoles.OBSERVER) 
						|| role.equals(CorporateRoles.ADMN)) {
					if(rst.getCurrentMode().equals("AG")){
						st = RecruitmentStages.S_ENT_RE;
					}else if("RE".equals(rst.getCurrentMode()) && rst.getStatus() != 1 && role.equals(CorporateRoles.ADMN)){		
						st = RecruitmentStages.S_ENT_MR_G;
					}
				}else if("RE".equals(rst.getCurrentMode()) && rst.getStatus() != 1){		
					st = RecruitmentStages.S_ENT_MR_G;
				}else if("AG".equals(rst.getCurrentMode())) {
					st = RecruitmentStages.S_ENT_ES_G;
				}
			}else if(!(role.equals(CommercialRoles.OBSERVER) || role.equals(CorporateRoles.OBSERVER))) {
				if(rst.getCurrentMode() == null) {
					st = RecruitmentStages.S_ENT_AG_G;
				}else if("AG".equals(rst.getCurrentMode())) {
					
					st = RecruitmentStages.S_ENT_ES_G;
					
					
				}
			}
			}
		return st;
	}
	
	


	private boolean hasSEAutorization(String role) {
		boolean auth = true;
		if((role.equals(CommercialRoles.OBSERVER) || role.equals(CorporateRoles.OBSERVER))) {
			auth = (this.observer.equals(this.idUsuario));
		}
		return auth;
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


	@Override
	protected String chooseEXPSICOMode(StageBean rst) {
		
		return RecruitmentStages.EX_PSICO_G;
	}


	@Override
	protected String alterStage(String stage) {
		String nuStage = stage;
		if(RecruitmentStages.EX_PSICO.equals(stage)) 
		{
			nuStage  = RecruitmentStages.EX_PSICO_G;
		}
		return nuStage;
	}
	
	
	protected void doSEHack(RegistroProceso r, String phase) {
		
		if(r.getCodigo().equals("SE") && r.getModo().equals("AG")) {
			this.idCurrentInterview = r.getIdEntrevista();
		}
		
		if(r.getCodigo().equals("SE") && r.getModo().equals("RE")  && r.getAprobado()) {
			this.secondInterviewPass = true;
			processMap.get(phase).setCover(true);
			processMap.get(phase).setComplete(true);
			processMap.get("OFERTA").setOpen(true);
			processMap.get(phase).getStages().get("SE").setComplete(true);
			processMap.get(phase).getStages().get("SE").setStatus(1);
			this.completedPhases++;
		}
		
	}
	
	protected void handleInterviewData(String phase, RegistroProceso r) {
		if(r.getResumenEntrevista()!= null && ("AG".equals(r.getModo()))) {
			this.idLastInterviewer = r.getResumenEntrevista().getIdEntrevistador();
		}
		
	}

}
