package com.mx.profuturo.bolsa.service.recruitment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.mx.profuturo.bolsa.model.recruitment.bean.AsesorFlowUserOperations;
import com.mx.profuturo.bolsa.model.recruitment.bean.StageBean;
import com.mx.profuturo.bolsa.model.recruitment.constants.RecruitmentStages;
import com.mx.profuturo.bolsa.model.recruitment.response.RegistroProceso;
import com.mx.profuturo.bolsa.model.role.CommercialRoles;
import com.mx.profuturo.bolsa.model.role.CorporateRoles;


@Scope("request")
@Service("Asesor")
public class AsesorProcessServiceImpl extends BaseProcessServiceImpl{
	

	{
		this.mode = "ASESOR";
	}	
	
	protected String chooseFOLIOMode(StageBean rst, String role) {
		String st = null;
			if(CommercialRoles.ANALYST.equals(role)) {
				st = RecruitmentStages.FOLIO_ES;
			}else {
				st = RecruitmentStages.FOLIO_BU;
			}
		
		return st;
	}

	
	protected String choosePEMode(StageBean rst) {
		String st = null;
		if(rst.getCurrentMode() == null) {
			st = RecruitmentStages.P_ENT_AG;
		}else {
			st = RecruitmentStages.P_ENT_RE;
		}
		return st;
	}
	
	protected void handleInterviewData(String phase, RegistroProceso r) {
		if(r.getResumenEntrevista()!= null) {
			this.idLastInterviewer = r.getResumenEntrevista().getIdEntrevistador();
		}
		
	}
	
	protected String chooseCNMode(StageBean rst, String role) {
		String st = null;
		String mode = null;
		if(rst.getCurrentMode() == null) {
			mode = handleCanHireSignals(rst, role);
			if(mode == null) {				
				st = (this.performedRepostulation)? RecruitmentStages.CONT_DE_G: RecruitmentStages.CONT_DE;
			}else {
				st = RecruitmentStages.CONT_PC;
				this.processMap.get(this.conf.getPhaseByStage(RecruitmentStages.CONT)).getStages().get(RecruitmentStages.CONT).setSignal(mode);
			}
		}
		return st;
	}

	 
	protected String chooseSEMode(StageBean rst, String role) {
		String st = null;
		
		if(role.equals(CommercialRoles.OBSERVER) || role.equals(CorporateRoles.OBSERVER) ) {
			if( null != rst.getCurrentMode()  && "AG".equals(rst.getCurrentMode())){
				st = RecruitmentStages.S_ENT_RE;
			}/*else if(rst.getCurrentMode() != null){
				st = RecruitmentStages.S_ENT_IN; //?
			}*/
		}else {
			if(null==rst.getCurrentMode()) {
				st = RecruitmentStages.S_ENT_AG;
			}else if("AG".equals(rst.getCurrentMode())) {
				if((this.idUsuario.equals(this.idLastInterviewer))) {
					st = RecruitmentStages.S_ENT_RE; 
				}else {
					st = RecruitmentStages.S_ENT_ES; 
				}	
			}else if("RE".equals(rst.getCurrentMode())) {
				st = RecruitmentStages.S_ENT_MR;
			}else if("MR".equals(rst.getCurrentMode())) {
				st = RecruitmentStages.S_ENT_AG;
			}
		}
		
		return st;
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
		
		return RecruitmentStages.EX_PSICO;
	}


	@Override
	protected String alterStage(String stage) {
		return stage;
	}

	
	@Override
	protected void doSEHack(RegistroProceso r, String phase) {
		if(true) {
			this.idCurrentInterview = r.getIdEntrevista();
		}
		
	}


	
}
