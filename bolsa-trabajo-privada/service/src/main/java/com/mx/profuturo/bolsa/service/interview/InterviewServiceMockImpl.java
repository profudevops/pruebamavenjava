package com.mx.profuturo.bolsa.service.interview;

import com.mx.profuturo.bolsa.model.recruitment.vo.InterviewInfoVO;
import com.mx.profuturo.bolsa.model.recruitment.vo.InterviewsListVO;
import com.mx.profuturo.bolsa.model.restclient.RequestBean;
import com.mx.profuturo.bolsa.model.vo.common.CatalogoVO;
import com.mx.profuturo.bolsa.util.exception.custom.GenericStatusException;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;

@Service
@Scope("request")
@Profile("mock")
public class InterviewServiceMockImpl extends InterviewServiceBase {


    @Override
    public LinkedList<CatalogoVO> __callgetAvailableSchedule(RequestBean genericRequestBean) throws GenericStatusException {
        LinkedList<CatalogoVO> horas = new LinkedList<>();

        CatalogoVO hora1 = new CatalogoVO(1,"09:00 a.m.");
        CatalogoVO hora2 = new CatalogoVO(2,"10:00 a.m.");
        CatalogoVO hora3 = new CatalogoVO(4,"11:00 a.m.");


        horas.add(hora1);
        horas.add(hora2);
        horas.add(hora3);

        return horas;

    }

	@Override
	public InterviewsListVO __callGetInterviews(RequestBean genericRequestBean) throws GenericStatusException {

    	
    	
    	InterviewsListVO entrevistas =new InterviewsListVO();

    	InterviewInfoVO entrevista1 = new InterviewInfoVO();
    	InterviewInfoVO entrevista2 = new InterviewInfoVO();
    	InterviewInfoVO entrevista3 = new InterviewInfoVO();
    	InterviewInfoVO entrevista4 = new InterviewInfoVO();
    	InterviewInfoVO entrevista5 = new InterviewInfoVO();

    	ArrayList<InterviewInfoVO> list = new ArrayList<>();

    	entrevista1.setFecha("11/10/2019");
    	entrevista1.setHora("13:00");
    	entrevista1.setCandidato("Julio Llamas Moctezuma");
    	entrevista1.setVacante("Desarrollador fullstack");
    	entrevista1.setLugar("Wepow");
    	entrevista1.setIdReclutamiento(1);
    	entrevista1.setIdCandidato(1);

    	entrevista2.setFecha("11/10/2019");
    	entrevista2.setHora("13:00");
    	entrevista2.setCandidato("Julio Arturo Arredondo Serrano");
    	entrevista2.setVacante("Programadaor Java");
    	entrevista2.setLugar("Corporativo");
    	entrevista2.setIdReclutamiento(2);
    	entrevista2.setIdCandidato(2);

    	entrevista3.setFecha("11/10/2019");
    	entrevista3.setHora("13:30");
    	entrevista3.setCandidato("Arnulfo Daniel Loredo Jimenez");
    	entrevista3.setVacante("Programadaor Java");
    	entrevista3.setLugar("Corporativo");
    	entrevista3.setIdReclutamiento(3);
    	entrevista3.setIdCandidato(3);

    	entrevista4.setFecha("11/10/2019");
    	entrevista4.setHora("14:00");
    	entrevista4.setCandidato("Ricardo Llamas Galván");
    	entrevista4.setVacante("Programadaor Java");
    	entrevista4.setLugar("Corporativo");
    	entrevista4.setIdReclutamiento(4);
    	entrevista4.setIdCandidato(4);

    	entrevista5.setFecha("11/10/2019");
    	entrevista5.setHora("14:30");
    	entrevista5.setCandidato("Juan Erik Almaguer López");
    	entrevista5.setVacante("Programadaor Java");
    	entrevista5.setLugar("Corporativo");
    	entrevista5.setIdReclutamiento(5);
    	entrevista5.setIdCandidato(5);

    	list.add(entrevista1);
    	list.add(entrevista2);
    	list.add(entrevista3);
    	list.add(entrevista4);
    	list.add(entrevista5);


    	entrevistas.setEntrevistas(list);
    	entrevistas.setPaginaActual(1);
    	entrevistas.setTotalPaginas(2);
		return entrevistas;
	}
}
