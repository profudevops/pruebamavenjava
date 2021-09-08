package com.mx.profuturo.bolsa.service.recruitment;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.mx.profuturo.bolsa.model.recruitment.constants.RecruitmentStages;
import com.mx.profuturo.bolsa.model.recruitment.dto.InterviewInServiceDTO;
import com.mx.profuturo.bolsa.model.recruitment.vo.FolioVO;
import com.mx.profuturo.bolsa.model.restclient.ConnectionConfigBean;
import com.mx.profuturo.bolsa.model.restclient.RequestBean;
import com.mx.profuturo.bolsa.model.service.common.GenericRequestBean;
import com.mx.profuturo.bolsa.model.vo.common.CatalogoVO;
import com.mx.profuturo.bolsa.util.exception.custom.GenericStatusException;

public abstract class AuxiliarBaseService implements AuxiliarService {

	@Autowired
	OperationsService operationsService;
	
	@Override
	public LinkedHashMap<String,LinkedList<CatalogoVO>>  getCatalogsByStage(String idUsuario, String codigo) throws GenericStatusException {
		LinkedHashMap<String,LinkedList<CatalogoVO>> catalogos = new LinkedHashMap<>();
		
		if(RecruitmentStages.P_ENT_AG.equals(codigo) || RecruitmentStages.P_ENT_AG_G.equals(codigo)) {
			//LinkedList<CatalogoVO> horas = (LinkedList<CatalogoVO>) this.__callGetInterviewHours(new InterviewInServiceDTO());
			//catalogos.put("horas", horas);
			LinkedList<CatalogoVO> tipoEntrevista= this.getInterviewType();
			catalogos.put("tipoEntrevista", tipoEntrevista);
			LinkedList<CatalogoVO> inmuebles = this.getStateProperties();
			catalogos.put("inmuebles", inmuebles);
		}else if(RecruitmentStages.S_ENT_AG.equals(codigo) || RecruitmentStages.S_ENT_AG_G.equals(codigo)) {
			//LinkedList<CatalogoVO> horas 
			//= (LinkedList<CatalogoVO>) this.__callGetInterviewHours(new InterviewInServiceDTO());
			//catalogos.put("horas", horas);
			LinkedList<CatalogoVO> tipoEntrevista= this.getInterviewType();
			catalogos.put("tipoEntrevista", tipoEntrevista);
			//LinkedList<CatalogoVO> inmuebles = this.getStateProperties();
			//catalogos.put("inmuebles", inmuebles);
		}
		
		return catalogos;
	}

	@Override
	public LinkedList<FolioVO> searchFolio(String folio) throws GenericStatusException {
		/**/
		
		return this.__callSearchFolio(folio);
	}
	
	protected abstract LinkedList<FolioVO> __callSearchFolio(String folio) throws GenericStatusException;

	@Override
	public List<CatalogoVO> getInterviewHours(InterviewInServiceDTO dto) throws GenericStatusException {
		
		return this.__callGetInterviewHours(dto);
	}
	
	
	@Override
	public LinkedList<CatalogoVO> getInterviewType() throws GenericStatusException{
		LinkedList<CatalogoVO> interviewList = new LinkedList<>();
		GenericRequestBean request = new GenericRequestBean();
		RequestBean<GenericRequestBean> genericRequestBean = new RequestBean();
		List<CatalogoVO> serviceList = this.__callgetInterviewType(genericRequestBean);
		interviewList.addAll(serviceList);

		return interviewList;

	}
	

   

    
    /*
	private LinkedList<CatalogoVO> getInterviewType() {
		
		LinkedList<CatalogoVO> list = new LinkedList<CatalogoVO>();
		CatalogoVO h1 = new CatalogoVO();
		h1.setId(1);
		h1.setDescripcion("Presencial");
		list.add(h1);
		
		CatalogoVO h2 = new CatalogoVO();
		h2.setId(2);
		h2.setDescripcion("WePow");
		list.add(h2);
		
		CatalogoVO h3 = new CatalogoVO();
		h3.setId(3);
		h3.setDescripcion("VideoConferencia / VIVO");
		list.add(h3);
		
		return list;
	}*/
	
	private LinkedList<CatalogoVO> getStateProperties() {
		
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
	
	
	public List<CatalogoVO> getRejectReassons() throws GenericStatusException {
		
		return this.__callGetRejectReassons();
		
	}
	
	protected abstract List<CatalogoVO> __callGetInterviewHours(InterviewInServiceDTO dto) throws GenericStatusException;
	protected abstract List<CatalogoVO> __callGetRejectReassons() throws GenericStatusException;
	protected abstract List<CatalogoVO> __callgetInterviewType(RequestBean<GenericRequestBean> genericRequestBean)throws GenericStatusException;
}
