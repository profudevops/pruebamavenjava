package com.mx.profuturo.bolsa.model.recruitment.bean;

import java.util.LinkedHashMap;

import org.springframework.stereotype.Component;

import com.mx.profuturo.bolsa.model.recruitment.constants.RecruitmentStages;
import com.mx.profuturo.bolsa.model.role.CommercialRoles;
import com.mx.profuturo.bolsa.model.role.CorporateRoles;

@Component
public class GeneralFlowUserOperations {

	public LinkedHashMap<String, LinkedHashMap<String, StagesModesBean>> getOperationsByRole(String role) {
		LinkedHashMap<String, LinkedHashMap<String, StagesModesBean>> operations = null;
		if(CommercialRoles.ADMN.equals(role) || CommercialRoles.CONSULTOR.equals(role) 
				|| CommercialRoles.ANALYST.equals(role) ) {
			operations = this.getFullFlowOperations();
		}else if(CommercialRoles.OBSERVER.equals(role) || CorporateRoles.OBSERVER.equals(role)) {
			operations = this.getObserverOperations();
		}else {
			operations = this.getFullFlowOperations();
		}
		return operations ;
	}
	
	
	
	private LinkedHashMap<String, LinkedHashMap<String, StagesModesBean>> getObserverOperations() {
		// TODO Auto-generated method stub
		
		LinkedHashMap<String, LinkedHashMap<String, StagesModesBean>> ops = 
				new LinkedHashMap<String, LinkedHashMap<String,StagesModesBean>>();
		
		LinkedHashMap<String, StagesModesBean> sEntrevistas = new LinkedHashMap<String, StagesModesBean>();
		
		StagesModesBean ses = new StagesModesBean();
		ses.setCode(RecruitmentStages.S_ENT);
		/*LinkedHashMap<String, String> se_sts = new LinkedHashMap<>();
		se_sts.put("AG", "AG");
		se_sts.put("ES", "ES");
		se_sts.put("NT", "NT");
		se_sts.put("RE", "RE");
		ses.setStages(pe_sts);*/
		
		sEntrevistas.put(RecruitmentStages.S_ENT, null);
		ops.put("SEGUNDAS_ENTREVISTAS", sEntrevistas);
		
		return ops;
	}

	
	private LinkedHashMap<String, LinkedHashMap<String, StagesModesBean>> getFullFlowOperations() {
		
		LinkedHashMap<String, LinkedHashMap<String, StagesModesBean>> ops = 
				new LinkedHashMap<String, LinkedHashMap<String,StagesModesBean>>();
		
		LinkedHashMap<String, StagesModesBean> contacto = new LinkedHashMap<String, StagesModesBean>();
		
		contacto.put(RecruitmentStages.FIL_TEL, null);
		
		ops.put("CONTACTO", contacto);
		
		
		
		LinkedHashMap<String, StagesModesBean> pEntrevistas = new LinkedHashMap<String, StagesModesBean>();
		
		StagesModesBean pes = new StagesModesBean();
		pes.setCode(RecruitmentStages.P_ENT);
		
		/*LinkedHashMap<String, String> pe_sts = new LinkedHashMap<>();
		pe_sts.put("AG", "AG");
		pe_sts.put("RE", "RE");
		pes.setStages(pe_sts);*/
		
		pEntrevistas.put(RecruitmentStages.P_ENT, null);
		pEntrevistas.put(RecruitmentStages.EX_PSICO_G, null);
		pEntrevistas.put(RecruitmentStages.EX_CONF, null);
		
		ops.put("PRIMERAS_ENTREVISTAS", pEntrevistas);
	
		LinkedHashMap<String, StagesModesBean> sEntrevistas = new LinkedHashMap<String, StagesModesBean>();
		
		StagesModesBean ses = new StagesModesBean();
		ses.setCode(RecruitmentStages.S_ENT);
		/*LinkedHashMap<String, String> se_sts = new LinkedHashMap<>();
		se_sts.put("AG", "AG");
		se_sts.put("ES", "ES");
		se_sts.put("NT", "NT");
		se_sts.put("RE", "RE");
		ses.setStages(pe_sts);*/
		
		sEntrevistas.put(RecruitmentStages.S_ENT, null);
		ops.put("SEGUNDAS_ENTREVISTAS", sEntrevistas);
		
		
		LinkedHashMap<String, StagesModesBean> oferta = new LinkedHashMap<String, StagesModesBean>();
		oferta.put(RecruitmentStages.OFE, null);
		ops.put("OFERTA", oferta);
		
		LinkedHashMap<String, StagesModesBean> form_con = new LinkedHashMap<String, StagesModesBean>();
		form_con.put(RecruitmentStages.FORM_CONT, null);
		ops.put("FORM_CONTRATACION", form_con);
		
		LinkedHashMap<String, StagesModesBean> folio = new LinkedHashMap<String, StagesModesBean>();
		folio.put(RecruitmentStages.FOLIO, null);
		ops.put("FOLIO", folio);
		
		LinkedHashMap<String, StagesModesBean> form_docs = new LinkedHashMap<String, StagesModesBean>();
		form_docs.put(RecruitmentStages.FORM_DOCTOS, null);
		form_docs.put(RecruitmentStages.EST_SOC, null);
		ops.put("FORM_DOCUMENTOS", form_docs);
		
		LinkedHashMap<String, StagesModesBean> contratacion = new LinkedHashMap<String, StagesModesBean>();
		contratacion.put(RecruitmentStages.CONT, null);
		ops.put("CONTRATACION",contratacion);
		
		return ops;
	}

}
