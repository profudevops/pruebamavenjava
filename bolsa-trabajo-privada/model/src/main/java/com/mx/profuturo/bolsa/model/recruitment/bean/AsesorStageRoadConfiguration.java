package com.mx.profuturo.bolsa.model.recruitment.bean;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;

import org.springframework.stereotype.Component;

import com.mx.profuturo.bolsa.model.recruitment.constants.RecruitmentStages;
import com.mx.profuturo.bolsa.model.recruitment.constants.RecruitmentStagesStatus;
import com.mx.profuturo.bolsa.model.recruitment.response.RegistroProceso;
import com.mx.profuturo.bolsa.model.recruitment.vo.RecruitmentStageVO;
import com.mx.profuturo.bolsa.model.role.CommercialRoles;

@Component
public class AsesorStageRoadConfiguration {
	
	

	//private static LinkedHashMap<String,LinkedList<RecruitmentStageVO>> phasesStages;
	private static LinkedHashMap<String,PhaseBean> processMap;
	private static LinkedHashMap<String,String> phaseReference;
	private static LinkedHashMap<String,String> newPhaseReference;
	private static LinkedHashMap<String,String> stageCompleteReference;
	private static LinkedHashMap<String, StageModePairBean> phaseCoverReference;
	private static LinkedHashMap<String, String> stageNameById;
	private static LinkedHashMap<String, String> iconByStageMode;
	
	private String mode;
	
	
	
	
	public AsesorStageRoadConfiguration() {
		//phasesStages = new LinkedHashMap<>();
		//this.buildPhaseStages(phasesStages);
		this.buildPhaseReference();
		this.setUpNewPhaseCoverReference();
		this.setUpPhaseBYStageCoverReference();
		this.setUpstageCompleteReference();
		this.setUPStageNameById();
		this.setUpIconByStageMode();
	}

	private void setUpIconByStageMode() {
		iconByStageMode = new LinkedHashMap<String, String>();
		iconByStageMode.put(RecruitmentStages.PI, "user");
		iconByStageMode.put(RecruitmentStages.CONT_OK, "crown");
		iconByStageMode.put("RECH", "times-circle");
	}
	
	private void setUpstageCompleteReference() {
		stageCompleteReference = new LinkedHashMap<String, String>();
		stageCompleteReference.put(RecruitmentStages.P_ENT, "RE");
		stageCompleteReference.put(RecruitmentStages.S_ENT, "RE");
		stageCompleteReference.put(RecruitmentStages.FORM_CONT, "OK");
		stageCompleteReference.put(RecruitmentStages.FOLIO, "FG");
		stageCompleteReference.put(RecruitmentStages.FORM_DOCTOS, "OK");
		stageCompleteReference.put(RecruitmentStages.FOLIO, "GD");
		stageCompleteReference.put(RecruitmentStages.CONT, "OK");
	}
	
	private void setUPStageNameById() {
		stageNameById = new LinkedHashMap<String, String>();
		stageNameById.put(RecruitmentStages.PI, "Proceso Iniciado");
		stageNameById.put(RecruitmentStages.FIL_TEL, "Filtro teléfonico");
		stageNameById.put(RecruitmentStages.P_ENT, "Primeras entrevistas");
		stageNameById.put(RecruitmentStages.EX_CONF, "Examenes de confianza");
		stageNameById.put(RecruitmentStages.EX_PSICO, "Exámen Psicometrico");
		stageNameById.put(RecruitmentStages.EX_PSICO_G, "Exámen Psicometrico");
		stageNameById.put(RecruitmentStages.S_ENT, "Segundas Entrevistas");
		stageNameById.put(RecruitmentStages.OFE, "Oferta");
		stageNameById.put(RecruitmentStages.FORM_CONT, "Solicitud de empleo");
		stageNameById.put(RecruitmentStages.FOLIO, "Folio de requisición");
		stageNameById.put(RecruitmentStages.FORM_DOCTOS, "Expediente");
		stageNameById.put(RecruitmentStages.EST_SOC, "Estudio socioeconómico");
		stageNameById.put(RecruitmentStages.CONT, "Contratación");
		stageNameById.put(RecruitmentStages.CONT_REP, "Repostulación");
		stageNameById.put(RecruitmentStages.PF, "Proceso Finalizado");
	}
	
	public String getStageName(String stage) {
		return stageNameById.get(stage);
	}

	private void buildPhaseReference() {
		phaseReference = new LinkedHashMap<String, String>();
		phaseReference.put(RecruitmentStages.PI, "INICIO");
		phaseReference.put(RecruitmentStages.FIL_TEL, "CONTACTO");
		
		phaseReference.put(RecruitmentStages.P_ENT, "PRIMERAS_ENTREVISTAS");
		phaseReference.put(RecruitmentStages.P_ENT_AG, "PRIMERAS_ENTREVISTAS");
		phaseReference.put(RecruitmentStages.P_ENT_RE, "PRIMERAS_ENTREVISTAS");
		
		phaseReference.put(RecruitmentStages.EX_PSICO, "PRIMERAS_ENTREVISTAS");
		phaseReference.put(RecruitmentStages.EX_PSICO_G, "PRIMERAS_ENTREVISTAS");
		phaseReference.put(RecruitmentStages.EX_CONF, "PRIMERAS_ENTREVISTAS");
		
		phaseReference.put(RecruitmentStages.S_ENT, "SEGUNDAS_ENTREVISTAS");
		phaseReference.put(RecruitmentStages.S_ENT_AG, "SEGUNDAS_ENTREVISTAS");
		phaseReference.put(RecruitmentStages.S_ENT_AGSNR, "SEGUNDAS_ENTREVISTAS");
		phaseReference.put(RecruitmentStages.S_ENT_RE, "SEGUNDAS_ENTREVISTAS");
		
		
		phaseReference.put(RecruitmentStages.OFE, "OFERTA");
		
		phaseReference.put(RecruitmentStages.FORM_CONT, "FORM_CONTRATACION");
		
		phaseReference.put(RecruitmentStages.FOLIO, "FOLIO");
		phaseReference.put(RecruitmentStages.FOLIO_GD, "FOLIO");
		
		
		phaseReference.put(RecruitmentStages.FORM_DOCTOS, "FORM_DOCUMENTOS");
		phaseReference.put(RecruitmentStages.FORM_DOCTOS_EN, "FORM_DOCUMENTOS");
		phaseReference.put(RecruitmentStages.FORM_DOCTOS_GD, "FORM_DOCUMENTOS");	
		phaseReference.put(RecruitmentStages.EST_SOC, "FORM_DOCUMENTOS");
		
		phaseReference.put(RecruitmentStages.CONT, "CONTRATACION");	
		phaseReference.put(RecruitmentStages.CONT_OK, "CONTRATACION");	
		phaseReference.put(RecruitmentStages.CONT_REP, "CONTRATACION");	
	}

	private void buildPhaseStages(LinkedHashMap<String,LinkedList<RecruitmentStageVO>> phasesStages) {
		
/*
		LinkedList<RecruitmentStageVO> contacto = new LinkedList<>();
		LinkedList<RecruitmentStageVO> p_entrevistas = new LinkedList<>();
		LinkedList<RecruitmentStageVO> s_entrevistas = new LinkedList<>();
		LinkedList<RecruitmentStageVO> expendiente = new LinkedList<>();
		LinkedList<RecruitmentStageVO> contratacion = new LinkedList<>();
		
		RecruitmentStageVO f1 = new RecruitmentStageVO();
		f1.setCodigo(RecruitmentStages.FIL_TEL);
		f1.setDescripcion("Filtro Télefonico");
		contacto.add(f1);
		
		RecruitmentStageVO f2 = new RecruitmentStageVO();
		f2.setCodigo(RecruitmentStages.P_ENT);
		f2.setDescripcion("Primera Entrevista");
		p_entrevistas.add(f2);
		
		RecruitmentStageVO f3 = new RecruitmentStageVO();
		f3.setCodigo(RecruitmentStages.EX_PSICO);
		f3.setDescripcion("Examenes Psicometricos");
		p_entrevistas.add(f3);
		
		
		RecruitmentStageVO f4 = new RecruitmentStageVO();
		f4.setCodigo(RecruitmentStages.EX_CONF);
		f4.setDescripcion("Examen Confianza");
		f4.setStatus(RecruitmentStagesStatus.PEND);
		p_entrevistas.add(f4);
		
		
		RecruitmentStageVO f5 = new RecruitmentStageVO();
		f5.setCodigo(RecruitmentStages.S_ENT);
		f5.setDescripcion("Segundas Entrevistas");
		s_entrevistas.add(f5);
		
		RecruitmentStageVO e2 = new RecruitmentStageVO();
		e2.setCodigo(RecruitmentStages.OFE);
		e2.setDescripcion("Oferta");
		e2.setStatus(RecruitmentStagesStatus.PEND);
		expendiente.add(e2);
		
		RecruitmentStageVO e3 = new RecruitmentStageVO();
		e3.setCodigo(RecruitmentStages.FORM_CONT);
		e3.setDescripcion("Formulario Contratacion");
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
		
		RecruitmentStageVO e7 = new RecruitmentStageVO();
		e7.setCodigo(RecruitmentStages.EST_SOC);
		e7.setDescripcion("Estudio Socioeconómico");
		e7.setStatus(RecruitmentStagesStatus.PEND);
		expendiente.add(e7);
		
		RecruitmentStageVO e6 = new RecruitmentStageVO();
		e6.setCodigo(RecruitmentStages.CONT);
		e6.setDescripcion("Contratacion");
		e6.setStatus(RecruitmentStagesStatus.PEND);
		contratacion.add(e6);
		
		phasesStages.put("CONTACTO", contacto);
		phasesStages.put("PRIMERAS_ENTREVISTAS", p_entrevistas);
		phasesStages.put("SEGUNDAS_ENTREVISTAS", s_entrevistas);
		phasesStages.put("SEGUNDAS_ENTREVISTAS", s_entrevistas);
		phasesStages.put("EXPEDIENTE", expendiente);
		
		phasesStages.put("CONTRATACION", contratacion);
		*/
	
	}

	/*public static LinkedHashMap<String, LinkedList<RecruitmentStageVO>> getPhasesStages() {
		return phasesStages;
	}*/

	public LinkedHashMap<String, PhaseBean> instanceProccesMap() {
		LinkedHashMap<String, PhaseBean> pm = new LinkedHashMap<String, PhaseBean>();
		this.setUpPM(pm);
		return pm;
	}

	private void setUpPM(LinkedHashMap<String, PhaseBean> pm) {
		
		PhaseBean f1 = new PhaseBean();
		f1.setCode("INICIO");
		f1.setDescription("INICIO");
		
		LinkedHashMap<String, StageBean> f1_st = new LinkedHashMap<String, StageBean>();
		LinkedHashMap<String, StageBean> f2_st = new LinkedHashMap<String, StageBean>();
		LinkedHashMap<String, StageBean> f3_st = new LinkedHashMap<String, StageBean>();
		LinkedHashMap<String, StageBean> f4_st = new LinkedHashMap<String, StageBean>();
		LinkedHashMap<String, StageBean> f5_st = new LinkedHashMap<String, StageBean>();
		LinkedHashMap<String, StageBean> f6_st = new LinkedHashMap<String, StageBean>();
		LinkedHashMap<String, StageBean> f7_st = new LinkedHashMap<String, StageBean>();
		LinkedHashMap<String, StageBean> f8_st = new LinkedHashMap<String, StageBean>();
		LinkedHashMap<String, StageBean> f9_st = new LinkedHashMap<String, StageBean>();
		LinkedHashMap<String, StageBean> f10_st = new LinkedHashMap<String, StageBean>();
		
		
		StageBean f1_st1 = new StageBean();
		f1_st1.setCode(RecruitmentStages.PI);
		f1_st1.setDescripcion("Proceso Iniciado");
		f1_st1.setStatus(0);
		f1_st.put(f1_st1.getCode(), f1_st1);
		f1.setStages(f1_st);
		pm.put("INICIO", f1);
		
		
		PhaseBean f2 = new PhaseBean();
		StageBean f2_st1 = new StageBean();
		f2_st1.setCode(RecruitmentStages.FIL_TEL);
		f2_st1.setDescripcion("Filtro Telefónico");
		f2_st1.setStatus(0);
		f2_st.put(f2_st1.getCode(), f2_st1);
		f2.setStages(f2_st);
		pm.put("CONTACTO", f2);
		
		
		PhaseBean f3 = new PhaseBean();
		
		StageBean f3_st1 = new StageBean();
		
		f3_st1.setCode(RecruitmentStages.P_ENT);
		f3_st1.setHasModes(true);
		f3_st1.setDescripcion("Primeras Entrevistas");
		f3_st1.setStatus(0);
		
		
		
		
		StageBean f3_st2 = new StageBean();
		f3_st2.setCode(RecruitmentStages.EX_CONF);
		f3_st2.setDescripcion("Examen Confianza");
		f3_st2.setStatus(0);
		
		
		
		StageBean f3_st3 = new StageBean();
		f3_st3.setCode(("GENERAL").equals(this.mode)?RecruitmentStages.EX_PSICO_G:RecruitmentStages.EX_PSICO);
		f3_st3.setDescripcion("Examen Psicométrico");
		f3_st3.setStatus(0);
		
		f3_st.put(f3_st1.getCode(), f3_st1);
		f3_st.put(f3_st2.getCode(), f3_st2);
		f3_st.put(f3_st3.getCode(), f3_st3);
		f3.setStages(f3_st);
		
		pm.put("PRIMERAS_ENTREVISTAS", f3);
		
		
		
		PhaseBean f4 = new PhaseBean();
		StageBean f4_st1 = new StageBean();
		f4_st1.setCode(RecruitmentStages.S_ENT);
		f4_st1.setDescripcion("Segundas Entrevistas");
		f4_st1.setStatus(0);
		f4_st1.setHasModes(true);
		f4_st.put(f4_st1.getCode(), f4_st1);
		f4.setStages(f4_st);
		pm.put("SEGUNDAS_ENTREVISTAS", f4);
		
		PhaseBean f5 = new PhaseBean();
		StageBean f5_st1 = new StageBean();
		f5_st1.setCode(RecruitmentStages.OFE);
		f5_st1.setDescripcion("Oferta");
		f5_st1.setStatus(0);
		f5_st.put(f5_st1.getCode(), f5_st1);
		f5.setStages(f5_st);
		pm.put("OFERTA", f5);
		
		PhaseBean f6 = new PhaseBean();
		StageBean f6_st1 = new StageBean();
		f6_st1.setCode(RecruitmentStages.FORM_CONT);
		f6_st1.setDescripcion("Solicitud de empleo");
		f6_st1.setStatus(0);
		f6_st1.setHasModes(true);
		f6_st.put(f6_st1.getCode(), f6_st1);
		f6.setStages(f6_st);
		pm.put("FORM_CONTRATACION", f6);
		
		PhaseBean f9 = new PhaseBean();
		StageBean f9_st1 = new StageBean();
		f9_st1.setCode(RecruitmentStages.FOLIO);
		f9_st1.setDescripcion("Folio");
		f9_st1.setStatus(0);
		f9_st1.setHasModes(true);
		f9_st.put(f9_st1.getCode(), f9_st1);
		f9.setStages(f9_st);
		pm.put("FOLIO", f9);
		
		PhaseBean f7 = new PhaseBean();
		StageBean f7_st1 = new StageBean();
		f7_st1.setCode(RecruitmentStages.FORM_DOCTOS);
		f7_st1.setDescripcion("Formulario documentación");
		f7_st1.setStatus(0);
		f7_st1.setHasModes(true);
		f7_st.put(f7_st1.getCode(), f7_st1);
		
		StageBean f7_st2 = new StageBean();
		f7_st2.setCode(RecruitmentStages.EST_SOC);
		f7_st2.setDescripcion("Estudio socioeconómico");
		f7_st2.setStatus(0);
		f7_st.put(f7_st2.getCode(), f7_st2);
		
		f7.setStages(f7_st);
		pm.put("FORM_DOCUMENTOS", f7);
		
		PhaseBean f8 = new PhaseBean();
		StageBean f8_st1 = new StageBean();
		f8_st1.setCode(RecruitmentStages.CONT);
		f8_st1.setDescripcion("Contratación");
		f8_st1.setStatus(0);
		f8_st1.setHasModes(true);
		f8_st.put(f8_st1.getCode(), f8_st1);
		f8.setStages(f8_st);
		pm.put("CONTRATACION", f8);
		
		
		PhaseBean f10 = new PhaseBean();
		StageBean f10_st1 = new StageBean();
		f10_st1.setCode(RecruitmentStages.PF);
		f10_st1.setDescripcion("Proceso Finalizado");
		f10_st1.setStatus(0);
		f10_st1.setHasModes(true);
		f10_st.put(f10_st1.getCode(), f10_st1);
		f10.setStages(f10_st);
		pm.put("FIN", f10);
		
	}
	
	
	private void setUpNewPhaseCoverReference() {
		
		newPhaseReference = new LinkedHashMap<String, String>();
		newPhaseReference.put("INICIO", "CONTACTO");
		newPhaseReference.put("CONTACTO", "PRIMERAS_ENTREVISTAS");
		newPhaseReference.put("PRIMERAS_ENTREVISTAS", "SEGUNDAS_ENTREVISTAS");
		newPhaseReference.put("SEGUNDAS_ENTREVISTAS", "OFERTA");
		newPhaseReference.put("OFERTA", "FORM_CONTRATACION");
		newPhaseReference.put("FORM_CONTRATACION", "FOLIO");
		newPhaseReference.put("FOLIO", "FORM_DOCUMENTOS");
		newPhaseReference.put("FORM_DOCUMENTOS", "CONTRATACION");
		newPhaseReference.put("CONTRATACION","FIN");
	}
	
	private void setUpPhaseBYStageCoverReference() {
		
		phaseCoverReference = new LinkedHashMap<String, StageModePairBean>();
		StageModePairBean st1 = new StageModePairBean(RecruitmentStages.PI);
		phaseCoverReference.put("INICIO", st1);
		StageModePairBean st2 = new StageModePairBean(RecruitmentStages.FIL_TEL);
		phaseCoverReference.put("CONTACTO", st2);
		StageModePairBean st3 = new StageModePairBean(RecruitmentStages.P_ENT, "RE");
		phaseCoverReference.put("PRIMERAS_ENTREVISTAS", st3);
		StageModePairBean st4 = new StageModePairBean(RecruitmentStages.S_ENT, "RE");
		phaseCoverReference.put("SEGUNDAS_ENTREVISTAS", st4);
		StageModePairBean st5 = new StageModePairBean(RecruitmentStages.OFE);
		phaseCoverReference.put("OFERTA", st5);
		StageModePairBean st6 = new StageModePairBean(RecruitmentStages.FORM_CONT, "OK");
		phaseCoverReference.put("FORM_CONTRATACION", st6);
		StageModePairBean st7 = new StageModePairBean(RecruitmentStages.FORM_DOCTOS, "OK");
		phaseCoverReference.put("FORM_DOCUMENTOS", st7);
		
		StageModePairBean st8 = new StageModePairBean(RecruitmentStages.CONT, "OK");
		phaseCoverReference.put("CONTRATACION", st8);
		
		StageModePairBean st9 = new StageModePairBean(RecruitmentStages.PF);
		phaseCoverReference.put("FIN", st9);
		
	}
	

	public String getPhaseByStage(String codigo) {
		return phaseReference.get(codigo);
	}

	
	


	public String getOpenPhaseByCovered(String phase) {
		return newPhaseReference.get(phase);
	}

	public StageModePairBean getCoverByPhase(String phase) {
		return phaseCoverReference.get(phase);
	}

	public Boolean checkStageCompleteByMode(String codigo, String modo) {
		return modo.equals(stageCompleteReference.get(codigo));
	}

	public String getIconByStageMode(String st) {
		return iconByStageMode.get(st);
	}
	
	public String mixStageMode(String codigo, String modo) {
		String sm = null;
		if(modo != null) {
			sm = new StringBuilder(codigo).append("-").append(modo).toString();
		}else {
			sm = codigo;
		}
		return sm;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}
	
	
	
	
	
	
}
