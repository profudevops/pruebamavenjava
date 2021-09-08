package com.mx.profuturo.bolsa.service.recruitment;

import java.util.LinkedHashMap;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.mx.profuturo.bolsa.model.recruitment.bean.AsesorStageRoadConfiguration;
import com.mx.profuturo.bolsa.model.recruitment.bean.PhaseBean;
import com.mx.profuturo.bolsa.model.recruitment.constants.RecruitmentStages;
import com.mx.profuturo.bolsa.model.recruitment.dto.GenericStageQueryDTO;
import com.mx.profuturo.bolsa.model.recruitment.response.ProcesoReclutamientoResponseBean;
import com.mx.profuturo.bolsa.model.recruitment.response.RegistroProceso;
import com.mx.profuturo.bolsa.model.recruitment.response.ResumenEntrevista;
import com.mx.profuturo.bolsa.model.recruitment.vo.EventVO;
import com.mx.profuturo.bolsa.model.recruitment.vo.RecruitmentStageVO;
import com.mx.profuturo.bolsa.model.recruitment.vo.RecruitmentType;
import com.mx.profuturo.bolsa.model.role.CommercialRoles;
import com.mx.profuturo.bolsa.model.role.CorporateRoles;
import com.mx.profuturo.bolsa.util.exception.custom.GenericStatusException;

@Service
@Scope("request")
public class HistoryServiceImpl implements HistoryService{

	@Autowired
	OperationsService operationsService;
	
	@Autowired
	private AsesorStageRoadConfiguration conf;
	
	private Boolean readOnly = false;
	
	private String role;

	private LinkedHashMap<String, PhaseBean> processMap;

	private Integer idReclutamiento;
	private String tipoReclutamiento;
	
	@Override
	public LinkedList<EventVO> getHistory(Integer idReclutamiento, Integer step, LinkedList<RecruitmentStageVO> linkedList) throws GenericStatusException {
		ProcesoReclutamientoResponseBean data = operationsService.getDataById(idReclutamiento, step);
		//this.idReclutamiento = idReclutamiento;
		this.readOnly = ! data.getDatosBasicos().getAbierto();
		LinkedList<EventVO> list = this.createHistoryList(data);
		return list;
	}
	
	@Override
	public LinkedList<EventVO> getHistory(ProcesoReclutamientoResponseBean data, LinkedHashMap<String, PhaseBean> processMap,
			String estado, String role) {
		this.role = role;
		this.idReclutamiento = data.getDatosBasicos().getIdReclutamiento();
		this.tipoReclutamiento = data.getDatosBasicos().getTipoReclutamiento();
		this.processMap = processMap;
		this.readOnly = ("READONLY".equals(estado)?true:false);
		LinkedList<EventVO> list = this.createHistoryList(data);
		return list;
	}
	
	
	private LinkedList<EventVO> createHistoryList(ProcesoReclutamientoResponseBean data) {
		
		LinkedList<EventVO> list = new LinkedList<EventVO>();
		EventVO e = null;
		for(RegistroProceso r: data.getRegistros()) {
			e = this.buildEventByRecord(r);
			list.offerFirst(e);
		}
	
		return list;
		
	}

	private EventVO buildEventByRecord(RegistroProceso r) {	
		String modo = this.validateModo(r.getModo());
		String sm = conf.mixStageMode(r.getCodigo(), modo);
		r.setNombreUsuario(this.validateUserName(r.getNombreUsuario(), sm));
		
		EventVO e = new EventVO();
		e.setAutor(r.getNombreUsuario());
		e.setCodigoFase(sm);
		e.setContenido(this.createEventContentByCode(sm, r, r.getResumenEntrevista()));
		e.setEditable(this.isEventEditable(sm));
		e.setFecha(r.getFecha());
		e.setIcono(this.getIconByCode(sm, r.getEstatus()));
		e.setIdFase(r.getIdEtapa());
		e.setTitulo(this.getStageNameByCode(r.getCodigo()));
		e.setIdEntrevista(r.getIdEntrevista());
		e.setIdReclutamiento(this.idReclutamiento);
		//e.setResumenEntrevista(r.getResumenEntrevista());
		return e ;
	}
	
	private String validateUserName(String nombreUsuario, String sm) {
		String name = nombreUsuario;
		if(RecruitmentStages.FORM_DOCTOS_EN.equals(sm) || 
				RecruitmentStages.FORM_DOCTOS_GD.equals(sm) || 
				RecruitmentStages.FORM_DOCTOS_OK.equals(sm)
				) {
			name = "SISTEMA";
		}
		
		return name;
	}

	private String validateModo(String modo) {
		String r = modo;
		if(RecruitmentType.GENERAL.equals(this.tipoReclutamiento) && "AG".equals(modo)) {
			r = "AGG";
		}
		return r;
	}
	
	
	private Boolean isRoleObserver() {
		return (CorporateRoles.OBSERVER.equals(this.role) 
				|| CommercialRoles.OBSERVER.equals(this.role));
	}

	private String getStageNameByCode(String stage) {
		return conf.getStageName(stage);
	}

	private String getIconByCode(String sm, Integer status) {
		String par = null;
		if(status == 1) {
			par =conf.getIconByStageMode(sm);
		}else {
			par = conf.getIconByStageMode("RECH");
		}
		return par;
	}

	private Boolean isEventEditable(String sm) {
		Boolean b = false;
		if(!this.isRoleObserver()){
			if(!this.readOnly && (RecruitmentStages.P_ENT_AG.equals(sm) || RecruitmentStages.P_ENT_AG_G.equals(sm))) {
				b = this.validatePENTEdition();
			}else if(!this.readOnly && (RecruitmentStages.S_ENT_AG.equals(sm)
					|| RecruitmentStages.S_ENT_AG_G.equals(sm))
					|| RecruitmentStages.S_ENT_AGSNR.equals(sm)) {
				b = this.validateSENTEdition();
			}
		}
		return b;
	}

	private Boolean validateSENTEdition() {
		return !(this.processMap.get("OFERTA").getOpen());
	}


	private Boolean validatePENTEdition() {
		
		return !(this.processMap.get("SEGUNDAS_ENTREVISTAS").getOpen());
	}

	private String createEventContentByCode(String sm, RegistroProceso r, ResumenEntrevista re) {
		String stage = (r.getModo() != null &&  r.getModo().equals("REP"))
				?this.getStageNameByCode(sm):this.getStageNameByCode(r.getCodigo());
		String content = null;
		StringBuffer sb = new StringBuffer();
		
		
		
		
		if("SISTEMA".equals(r.getNombreUsuario())) {
			content = "Etapa pendiente de implementación";
		}else{
			if(RecruitmentStages.PI.equals(sm)) {
				content = "El candidato ha iniciado su proceso de reclutamiento";
			}else if(RecruitmentStages.P_ENT_AG.equals(sm) || RecruitmentStages.S_ENT_AG.equals(sm)||
					RecruitmentStages.P_ENT_AG_G.equals(sm) || RecruitmentStages.S_ENT_AG_G.equals(sm)
					|| RecruitmentStages.S_ENT_AGSNR.equals(sm)) {
				if(null != re) {
					sb.append("Entrevista ").append(re.getTipoEntrevista()).
					append(" agendada a ").append(re.getEntrevistador()).
					append(" el ").append(re.getFecha()).
					append(" a las ").
					append(re.getHora()).append(" horas");
					if(null != re.getSede()) {
						sb.append(" en ").append(re.getSede());
					}
					
					content = sb.toString();
					
				}else {
					content  = "Entrevista agendada para la etapa " + stage; 
				}
				
			}else if(RecruitmentStages.P_ENT_RE.equals(sm) || RecruitmentStages.S_ENT_RE.equals(sm)){
				content = (r.getEstatus() == 1)?
						"El candidato fue aprobado por el entrevistador":
							"El candidato fue descartado por el entrevistador"; 
			}else if(RecruitmentStages.S_ENT_MR.equals(sm)){
				content = (r.getEstatus() == 1)?
						"El candidato fue elegido para intentar otra entrevista con un nuevo entrevistador  ":
							"El candidato fue rechazado definitivamente"; 
			}else if(RecruitmentStages.FORM_CONT_GD.equals(sm)){
				content = "El candidato completo la solicitud de empleo"; 
			}else if(RecruitmentStages.FORM_CONT_OK.equals(sm)){
				content = (r.getEstatus() == 1)?
						"Solicitud de empleo aprobada ":
							"Solicitud de empleo rechazada"; 
			}else if(RecruitmentStages.S_ENT_MR.equals(sm)){
				content = (r.getEstatus() == 1)?
						"El candidato fue elegido para intentar otra entrevista con un nuevo entrevistador  ":
							"El candidato fue rechazado definitivamente"; 
			}else if(RecruitmentStages.EX_CONF.equals(sm)){
				content = (r.getEstatus() == 1)?
						"El candidato aprobó el examen de confianza":
							"El candidato no aprobó el examen de confianza"; 
			}else if(RecruitmentStages.CONT_OK.equals(sm)){
				content = (r.getEstatus() == 1)?
						"El candidato fue contratado":
							"El candidato no fue contratado"; 
			}else if(RecruitmentStages.CONT_REP.equals(sm)){
				content = 
							"El candidato fue repostulado"; 
			}else if(r.getModo() != null){
				content = "Ha ocurrido un evento para en esta etapa";
			}else if(r.getModo() != null && !conf.checkStageCompleteByMode(r.getCodigo(), r.getModo())){
				content = "Ha ocurrido un evento para en esta etapa";
			}else if(r.getModo() != null && !conf.checkStageCompleteByMode(r.getCodigo(), r.getModo())){
				content = "Ha ocurrido un evento para en esta etapa";
			}else {
				
				content = (r.getEstatus() == 1)?
						"La etapa  ha sido completada con exito":
							"El candidato ha sido rechazado";
			}
		}
	
		if(r.getComentario()!=null) {
			StringBuilder sbh = new StringBuilder(content);
			 sbh.append("<br/><b>Comentario:</b> ").append(r.getComentario());
			 content = sbh.toString();
		}
		
		
		if(r.getRechazo()!=null) {
			StringBuilder sbh = new StringBuilder(content);
			 sbh.append("<br/><b>Motivo de rechazo:</b> ").append(r.getRechazo());
			 content = sbh.toString();
		}
		
		return content;
	}


	
}
