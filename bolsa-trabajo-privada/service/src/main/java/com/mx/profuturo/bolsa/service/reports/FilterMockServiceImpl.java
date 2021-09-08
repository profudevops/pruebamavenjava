package com.mx.profuturo.bolsa.service.reports;

import java.util.ArrayList;
import java.util.LinkedList;

import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.mx.profuturo.bolsa.model.reports.vo.FilterVO;
import com.mx.profuturo.bolsa.model.restclient.RequestBean;
import com.mx.profuturo.bolsa.model.vo.common.CatalogoVO;
import com.mx.profuturo.bolsa.model.vo.common.SCatalogoVO;
import com.mx.profuturo.bolsa.util.exception.custom.GenericStatusException;

@Service
@Scope("request")
@Profile("mock")
public class FilterMockServiceImpl extends FilterServiceBase{


	@Override
	public ArrayList<CatalogoVO> __callgetAnalistasCorporativo() {
		ArrayList<CatalogoVO> l = new ArrayList<>();
		CatalogoVO e1 = new CatalogoVO();
		e1.setId(1);
		e1.setDescripcion("Carla");
		CatalogoVO e2 = new CatalogoVO();
		e2.setId(2);
		e2.setDescripcion("Karen");
		CatalogoVO e3 = new CatalogoVO();
		e3.setId(3);
		e3.setDescripcion("Dany");
		CatalogoVO e4 = new CatalogoVO();
		e4.setId(4);
		e4.setDescripcion("Ana");
		
		l.add(e1);
		l.add(e2);
		l.add(e3);
		l.add(e4);
	
		
		return l;
	}

	@Override
	public ArrayList<CatalogoVO> __callGetPeriodos() {
		ArrayList<CatalogoVO> l = new ArrayList<>();
		CatalogoVO e1 = new CatalogoVO();
		e1.setId(1);
		e1.setDescripcion("Anual");
		CatalogoVO e2 = new CatalogoVO();
		e2.setId(2);
		e2.setDescripcion("Semestral");
		CatalogoVO e3 = new CatalogoVO();
		e3.setId(3);
		e3.setDescripcion("Trimestral");
		l.add(e1);
		l.add(e2);
		l.add(e3);
		return l;
	}

	@Override
	public ArrayList<CatalogoVO> __callGetTipoVacante() {
		ArrayList<CatalogoVO> l = new ArrayList<>();
		CatalogoVO e1 = new CatalogoVO();
		e1.setCodigo("V_CORP");
		e1.setDescripcion("Corporativo");
		CatalogoVO e2 = new CatalogoVO();
		e2.setCodigo("V_CTS");
		e2.setDescripcion("Atención a Cliente");
		l.add(e1);
		l.add(e2);
		return l;
	}

	@Override
	public ArrayList<CatalogoVO> __callGetNivelPuesto() {
		ArrayList<CatalogoVO> l = new ArrayList<>();
		CatalogoVO e1 = new CatalogoVO();
		e1.setId(1);
		e1.setDescripcion("Becario");
		CatalogoVO e2 = new CatalogoVO();
		e2.setId(2);
		e2.setDescripcion("Consultor");
		l.add(e1);
		l.add(e2);
		return l;
	}

	@Override
	public ArrayList<CatalogoVO> __callGetCentroCostos() {
		ArrayList<CatalogoVO> l = new ArrayList<>();
		CatalogoVO e1 = new CatalogoVO();
		e1.setId(1);
		e1.setDescripcion("Centro Costos I");
		CatalogoVO e2 = new CatalogoVO();
		e2.setId(2);
		e2.setDescripcion("Centro Costos II");
		l.add(e1);
		l.add(e2);
		return l;
	}

	@Override
	public ArrayList<SCatalogoVO> __callGetObservadores() {
		ArrayList<SCatalogoVO> l = new ArrayList<>();
		SCatalogoVO e1 = new SCatalogoVO();
		e1.setId("1");
		e1.setDescripcion("Jorge Herrera");
		SCatalogoVO e2 = new SCatalogoVO();
		e2.setId("2");
		e2.setDescripcion("Rodolfo Milla");
		l.add(e1);
		l.add(e2);
		return l;
	}

	@Override
	public ArrayList<CatalogoVO> __callAnalystByGerencia(RequestBean<Integer> rq) {
		Integer id = (Integer) rq.getUriVariables().get("idGerencia");
		// TODO Auto-generated method stub
		ArrayList<CatalogoVO> l = new ArrayList<>();
		CatalogoVO e1 = new CatalogoVO();
		e1.setId(1);
		e1.setDescripcion("Gerente 1");
		CatalogoVO e2 = new CatalogoVO();
		e2.setId(2);
		e2.setDescripcion("Gerente 2");
		CatalogoVO e3 = new CatalogoVO();
		e3.setId(3);
		e3.setDescripcion("Gerente 3");
		CatalogoVO e4 = new CatalogoVO();
		e4.setId(4);
		e4.setDescripcion("Gerente 4");
		
		if(id == 1) {
			l.add(e1);
			l.add(e2);
		}else {
			l.add(e3);
			l.add(e4);
		}
		
		return l;
	}

}
