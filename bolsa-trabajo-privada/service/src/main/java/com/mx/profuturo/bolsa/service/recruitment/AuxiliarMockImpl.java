package com.mx.profuturo.bolsa.service.recruitment;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import com.mx.profuturo.bolsa.model.recruitment.constants.RecruitmentStages;
import com.mx.profuturo.bolsa.model.recruitment.dto.InterviewInServiceDTO;
import com.mx.profuturo.bolsa.model.recruitment.vo.FolioVO;
import com.mx.profuturo.bolsa.model.restclient.RequestBean;
import com.mx.profuturo.bolsa.model.service.common.GenericRequestBean;
import com.mx.profuturo.bolsa.model.vo.common.CatalogoVO;
import com.mx.profuturo.bolsa.util.exception.custom.GenericStatusException;

@Service
@Scope("request")
@Profile("mock")
public class AuxiliarMockImpl extends AuxiliarBaseService {

	@Override
	protected LinkedList<CatalogoVO> __callGetRejectReassons() {
		LinkedList<CatalogoVO> list = new LinkedList<>();
		CatalogoVO f1 = new CatalogoVO();
		f1.setId(1);
		f1.setDescripcion("Pretenciones elevadas");
		list.add(f1);
		
		CatalogoVO f2 = new CatalogoVO();
		f2.setId(2);
		f2.setDescripcion("Candidato Abandono");
		list.add(f2);
		return list;
	}

	@Override
	public LinkedList<CatalogoVO> __callgetInterviewType(RequestBean<GenericRequestBean> genericRequestBean)throws GenericStatusException {
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
		
	}

	@Override
	protected List<CatalogoVO> __callGetInterviewHours(InterviewInServiceDTO id) throws GenericStatusException {
		LinkedList<CatalogoVO> list = new LinkedList<CatalogoVO>();
		CatalogoVO h1 = new CatalogoVO();
		h1.setId(10);
		h1.setCodigo("10:00");
		h1.setDescripcion("10:00 am");
		list.add(h1);
		
		CatalogoVO h2 = new CatalogoVO();
		h2.setCodigo("12:30");
		h2.setDescripcion("12:30 pm");
		h2.setId(11);
		list.add(h2);
		
		CatalogoVO h3 = new CatalogoVO();
		h3.setCodigo("13:00");
		h3.setId(13);
		h3.setDescripcion("01:00 pm");
		list.add(h3);
		
		return list;
	}

	@Override
	protected LinkedList<FolioVO> __callSearchFolio(String folio) {
		LinkedList<FolioVO> list = new LinkedList<>();
		
		if("F001".equals(folio)) {
			FolioVO h1 = new FolioVO();
			h1.setCodigo("F001");
			h1.setId(1l);
			h1.setDescripcion("CONSULTOR SISTEMAS PROGRAMADOR");
			list.add(h1);
		}else if("F002".equals(folio)) {
			FolioVO h1 = new FolioVO();
			h1.setCodigo("F002");
			h1.setId(2l);
			h1.setDescripcion("GERENTE SISTEMAS ");
			list.add(h1);
		}else if("F003".equals(folio)) {
			FolioVO h1 = new FolioVO();
			h1.setCodigo("F003");
			h1.setId(3l);
			h1.setDescripcion("BECARIO SISTEMAS PROGRAMADOR");
			list.add(h1);
		}
		
		return list;
	}
		
}
