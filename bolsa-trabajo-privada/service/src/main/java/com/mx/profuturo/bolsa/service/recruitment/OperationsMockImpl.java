package com.mx.profuturo.bolsa.service.recruitment;

import java.util.LinkedList;
import java.util.List;

import com.mx.profuturo.bolsa.model.restclient.RequestBean;
import com.mx.profuturo.bolsa.model.service.areasinteres.dto.GenericTextResponseBean;
import com.mx.profuturo.bolsa.model.service.auth.dto.BasicDTO;
import com.mx.profuturo.bolsa.model.service.candidates.dto.IniciarReclutamientoDTO;
import com.mx.profuturo.bolsa.model.service.common.GenericRequestBean;
import com.mx.profuturo.bolsa.model.vo.common.CatalogoVO;
import com.mx.profuturo.bolsa.util.exception.custom.GenericStatusException;
import com.mx.profuturo.bolsa.util.exception.custom.InterviewException;

import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.mx.profuturo.bolsa.model.recruitment.constants.RecruitmentStages;
import com.mx.profuturo.bolsa.model.recruitment.dto.CompletePhaseDTO;
import com.mx.profuturo.bolsa.model.recruitment.dto.GenericStageQueryDTO;
import com.mx.profuturo.bolsa.model.recruitment.dto.InterviewDTO;
import com.mx.profuturo.bolsa.model.recruitment.dto.form.InterviewResume;
import com.mx.profuturo.bolsa.model.recruitment.dto.form.PEAGStageForm;
import com.mx.profuturo.bolsa.model.recruitment.dto.form.SEAGStageForm;
import com.mx.profuturo.bolsa.model.recruitment.dto.form.SEESGStageForm;
import com.mx.profuturo.bolsa.model.recruitment.response.DatosBasicos;
import com.mx.profuturo.bolsa.model.recruitment.response.DatosEntrevistaResponseBean;
import com.mx.profuturo.bolsa.model.recruitment.response.Estrategia;
import com.mx.profuturo.bolsa.model.recruitment.response.ProcesoReclutamientoResponseBean;
import com.mx.profuturo.bolsa.model.recruitment.response.RegistroProceso;
import com.mx.profuturo.bolsa.model.recruitment.service.dto.FolioDTO;
import com.mx.profuturo.bolsa.model.recruitment.service.dto.InterviewAppointmentDTO;
import com.mx.profuturo.bolsa.model.recruitment.service.dto.InterviewResponseDTO;
import com.mx.profuturo.bolsa.model.recruitment.service.dto.PsychometricTestDTO;
import com.mx.profuturo.bolsa.model.recruitment.service.dto.TrustTestDTO;
import com.mx.profuturo.bolsa.model.recruitment.vo.FolioVO;

@Service
@Scope("request")
@Profile("mock")
public class OperationsMockImpl extends OperationsServiceBase {

	public LinkedList<DatosEntrevistaResponseBean>  __getNoHandledInterviewData(Integer idReclutamiento) {
		LinkedList<DatosEntrevistaResponseBean> list = new LinkedList<>();
		DatosEntrevistaResponseBean e1 = new DatosEntrevistaResponseBean();
		e1.setIdEntrevista(1);
		e1.setFecha("10/11/2019");
		e1.setHora("12");
		e1.setEntrevistador("Jorge Herrera");
		e1.setIdTipoEntrevistador(503);
		list.add(e1);
		DatosEntrevistaResponseBean e2 = new DatosEntrevistaResponseBean();
		e2.setIdEntrevista(2);
		e2.setFecha("11/11/2019");
		e2.setHora("14");
		e2.setEntrevistador("Rodolfo Milla");
		e2.setIdHora(2);

		list.add(e2);
		return list ;
	}


	protected DatosEntrevistaResponseBean __getInterviewData(RequestBean<Integer> genericRequestBean, String modo) {
	
		DatosEntrevistaResponseBean f = new DatosEntrevistaResponseBean();
		
		if("PE-AGG".equals(modo)) {
			f.setEntrevistador("Juani Kun");
			f.setFecha("10/10/2019");
			f.setHora("10");
			f.setSede("1");
			f.setIdEntrevista(1);
			f.setIdTipoEntrevista(1);
			f.setTipoEntrevista("Presencial");
			f.setIdTipoEntrevistador(1028);
			f.setIdDivision(1);
			f.setIdRegional(2);
			f.setIdGerencia(3);
			f.setIdEntrevistador("20");
			f.setIdHora(1);
			f.setCorreoEntrevistador("karen.padierna@profuturo.com");
		}
		
		if("SE-AGG".equals(modo)) {
			f.setEntrevistador("Ricky Llamas");
			f.setFecha("21/11/2019");
			f.setHora("10");
			f.setSede("1");
			f.setIdEntrevista(1);
			f.setIdTipoEntrevista(1);
			f.setTipoEntrevista("Presencial");
			f.setIdTipoEntrevistador(1031);
			f.setIdDivision(1);
			f.setIdRegional(2);
			f.setIdGerencia(3);
			f.setIdEntrevistador("20");
			f.setIdHora(1);
			f.setCorreoEntrevistador("karen.padierna@profuturo.com");
		}
		
		if("SE-AG".equals(modo)) {
			f.setEntrevistador("Brendy Ramirez");
			f.setFecha("31/10/2019");
			f.setHora("10");
			f.setSede("1");
			f.setIdEntrevista(1);
			f.setIdTipoEntrevista(1);
			f.setTipoEntrevista("Presencial");
			f.setIdTipoEntrevistador(1030);
			f.setIdDivision(1);
			f.setIdRegional(2);
			f.setIdGerencia(3);
			f.setIdEntrevistador("20");
			f.setIdHora(1);
			f.setCorreoEntrevistador("karen.padierna@profuturo.com");
		}
		
		
		return f;
	}
	
	protected ProcesoReclutamientoResponseBean __getRecruitmentData(Integer idReclutamiento, Integer step) {
		
		ProcesoReclutamientoResponseBean  response = null;
		if(idReclutamiento == 1) {
			response = recruitment1(idReclutamiento, step);
		}else if(idReclutamiento == 2) {
			response = recruitment2(idReclutamiento, step);
		}
		
		return response;
	}
	
	
	private ProcesoReclutamientoResponseBean recruitment1(Integer idReclutamiento, Integer step) {
		ProcesoReclutamientoResponseBean response = new ProcesoReclutamientoResponseBean();
		DatosBasicos db = new DatosBasicos();
		Estrategia e =  new Estrategia();
		LinkedList<RegistroProceso> r = new LinkedList<>();
		
		db.setAbierto(true);
		db.setTipoReclutamiento("AS");
		db.setEstatus("Primeras Entrevistas");
		db.setFechaCreacion("1/10/2019");
		db.setIdCandidato(1);
		db.setIdPublicacion(20);
		db.setIdReclutamiento(1);
		db.setIdObservador("20dedf");
		db.setIdUsuario("10");
		db.setIdConsultor("200");
		db.setVacanteMaestra(true);
		db.setNombreCandidato("Julio Llamas Moctezuma");
		db.setNombreVacante("Programador Full Stack");
		
		e.setExamenConfianza(true);
		e.setExamenPsicometrico(true);
		
		RegistroProceso r1 = new RegistroProceso();
		r1.setAprobado(true);
		r1.setCodigo("PI");
		r1.setComentario("");
		r1.setEstatus(1);
		r1.setIdEtapa(1);
		r1.setIdRechazo(0);
		r1.setIdUsuario("10");
		r1.setFecha("10/01/2019");
		r1.setNombreUsuario("Karla Fajardo");
		//r.add(r1);
		
		
		RegistroProceso r2 = new RegistroProceso();
		r2.setAprobado(true);
		r2.setCodigo("FT");
		r2.setComentario("");
		r2.setEstatus(1);
		r2.setIdEtapa(2);
		r2.setIdRechazo(0);
		r2.setIdUsuario("10");
		r2.setFecha("10/01/2019");
		r2.setNombreUsuario("Karla Fajardo");
		
		
		RegistroProceso r21 = new RegistroProceso();
		r21.setAprobado(false);
		r21.setCodigo("FT");
		r21.setComentario("");
		r21.setEstatus(-1);
		r21.setIdEtapa(2);
		r21.setIdRechazo(1);
		r21.setIdUsuario("10");
		r21.setFecha("10/01/2019");
		r21.setNombreUsuario("Karla Fajardo");
		//r.add(r2);
		
		
		RegistroProceso r3 = new RegistroProceso();
		r3.setAprobado(true);
		r3.setCodigo(RecruitmentStages.P_ENT);
		r3.setModo("AG");
		r3.setComentario("");
		r3.setEstatus(1);
		r3.setIdEtapa(3);
		r3.setIdRechazo(0);
		r3.setIdUsuario("10");
		r3.setFecha("10/01/2019");
		r3.setNombreUsuario("Karla Fajardo");
		r3.setIdEntrevista(1);
		//r.add(r3);
		
		
		RegistroProceso r4 = new RegistroProceso();
		r4.setAprobado(true);
		r4.setCodigo(RecruitmentStages.P_ENT);
		r4.setModo("RE");
		r4.setComentario("");
		r4.setEstatus(1);
		r4.setIdEtapa(3);
		r4.setIdRechazo(0);
		r4.setIdUsuario("10");
		r4.setFecha("10/01/2019");
		r4.setNombreUsuario("Karla Fajardo");
		//r.add(r4);
		
		
		RegistroProceso r5 = new RegistroProceso();
		r5.setAprobado(true);
		r5.setCodigo(RecruitmentStages.EX_PSICO);
		r5.setComentario("");
		r5.setEstatus(1);
		r5.setIdEtapa(3);
		r5.setIdRechazo(0);
		r5.setIdUsuario("10");
		r5.setFecha("10/01/2019");
		r5.setNombreUsuario("Karla Fajardo");
		//r.add(r5);
		
		
		RegistroProceso r51 = new RegistroProceso();
		r51.setAprobado(true);
		r51.setCodigo(RecruitmentStages.EX_PSICO);
		r51.setComentario("");
		r51.setEstatus(-1);
		r51.setIdEtapa(3);
		r51.setIdRechazo(0);
		r51.setIdUsuario("10");
		r51.setFecha("10/01/2019");
		r51.setNombreUsuario("Karla Fajardo");
		
		
		RegistroProceso r6 = new RegistroProceso();
		r6.setAprobado(true);
		r6.setCodigo(RecruitmentStages.EX_CONF);

		r6.setComentario("");
		r6.setEstatus(1);
		r6.setIdEtapa(3);
		r6.setIdRechazo(0);
		r6.setIdUsuario("10");
		r6.setFecha("10/01/2019");
		r6.setNombreUsuario("Karla Fajardo");
		//r.add(r6);
		
		
		RegistroProceso r7 = new RegistroProceso();
		r7.setAprobado(true);
		r7.setCodigo(RecruitmentStages.S_ENT);
		r7.setModo("AG");
		r7.setComentario("");
		r7.setEstatus(1);
		r7.setIdEtapa(3);
		r7.setIdRechazo(0);
		r7.setIdUsuario("10");
		r7.setFecha("10/01/2019");
		r7.setNombreUsuario("Karla Fajardo");
		//r.add(r7);
		
		
		RegistroProceso r8 = new RegistroProceso();
		r8.setAprobado(true);
		r8.setCodigo(RecruitmentStages.S_ENT);
		r8.setModo("RE");
		r8.setComentario("");
		r8.setEstatus(1);
		r8.setIdEtapa(3);
		r8.setIdRechazo(0);
		r8.setIdUsuario("10");
		r8.setFecha("10/01/2019");
		r8.setNombreUsuario("Karla Fajardo");
		//r.add(r8);
		
		RegistroProceso r81 = new RegistroProceso();
		r81.setAprobado(false);
		r81.setCodigo(RecruitmentStages.S_ENT);
		r81.setModo("RE");
		r81.setComentario("");
		r81.setEstatus(-1);
		r81.setIdEtapa(3);
		r81.setIdRechazo(0);
		r81.setIdUsuario("10");
		r81.setFecha("10/01/2019");
		r81.setNombreUsuario("Karla Fajardo");
		
		
		RegistroProceso r82 = new RegistroProceso();
		r82.setAprobado(false);
		r82.setCodigo(RecruitmentStages.S_ENT);
		r82.setModo("MR");
		r82.setComentario("");
		r82.setEstatus(-1);
		r82.setIdEtapa(3);
		r82.setIdRechazo(0);
		r82.setIdUsuario("10");
		r82.setFecha("10/01/2019");
		r82.setNombreUsuario("Karla Fajardo");
		
		
		//r.add(r81);
		
		RegistroProceso r9 = new RegistroProceso();
		r9.setAprobado(true);
		r9.setCodigo(RecruitmentStages.OFE);
		r9.setComentario("");
		r9.setEstatus(1);
		r9.setIdEtapa(3);
		r9.setIdRechazo(0);
		r9.setIdUsuario("10");
		r9.setFecha("10/01/2019");
		r9.setNombreUsuario("Karla Fajardo");
		//r.add(r9);
		
		RegistroProceso r10 = new RegistroProceso();
		r10.setAprobado(true);
		r10.setCodigo(RecruitmentStages.FORM_CONT);
		r10.setComentario("");
		r10.setEstatus(1);
		r10.setModo("GD");
		r10.setIdEtapa(3);
		r10.setIdRechazo(0);
		r10.setIdUsuario("10");
		r10.setFecha("10/01/2019");
		r10.setNombreUsuario("Karla Fajardo");
		//r.add(r10);

		
		RegistroProceso r11 = new RegistroProceso();
		r11.setAprobado(true);
		r11.setCodigo(RecruitmentStages.FORM_CONT);
		r11.setComentario("");
		r11.setEstatus(1);
		r11.setModo("OK");
		r11.setIdEtapa(3);
		r11.setIdRechazo(0);
		r11.setIdUsuario("10");
		r11.setFecha("10/01/2019");
		r11.setNombreUsuario("Karla Fajardo");
		//r.add(r11);
		
		RegistroProceso r12 = new RegistroProceso();
		r12.setAprobado(true);
		r12.setCodigo(RecruitmentStages.FOLIO);
		r12.setComentario("");
		r12.setEstatus(1);
		r12.setModo("GD");
		r12.setIdEtapa(3);
		r12.setIdRechazo(0);
		r12.setIdUsuario("10");
		r12.setFecha("10/01/2019");
		r12.setNombreUsuario("Karla Fajardo");
		//r.add(r12);
		
		
		RegistroProceso r13 = new RegistroProceso();
		r13.setAprobado(true);
		r13.setCodigo(RecruitmentStages.FORM_DOCTOS);
		r13.setComentario("");
		r13.setEstatus(1);
		r13.setModo("EN");
		r13.setIdEtapa(3);
		r13.setIdRechazo(0);
		r13.setIdUsuario("10");
		r13.setFecha("10/01/2019");
		r13.setNombreUsuario("Karla Fajardo");
		//r.add(r13);
		
		RegistroProceso r14 = new RegistroProceso();
		r14.setAprobado(true);
		r14.setCodigo(RecruitmentStages.FORM_DOCTOS);
		r14.setComentario("");
		r14.setEstatus(1);
		r14.setModo("GD");
		r14.setIdEtapa(3);
		r14.setIdRechazo(0);
		r14.setIdUsuario("10");
		r14.setFecha("10/01/2019");
		r14.setNombreUsuario("Karla Fajardo");
		//r.add(r14);
		
		RegistroProceso r15 = new RegistroProceso();
		r15.setAprobado(true);
		r15.setCodigo(RecruitmentStages.FORM_DOCTOS);
		r15.setComentario("");
		r15.setEstatus(1);
		r15.setModo("OK");
		r15.setIdEtapa(3);
		r15.setIdRechazo(0);
		r15.setIdUsuario("10");
		r15.setFecha("10/01/2019");
		r15.setNombreUsuario("Karla Fajardo");
		//r.add(r15);
		
		
		RegistroProceso r16 = new RegistroProceso();
		r16.setAprobado(true);
		r16.setCodigo(RecruitmentStages.EST_SOC);
		r16.setComentario("");
		r16.setEstatus(1);
		r16.setIdEtapa(3);
		r16.setIdRechazo(0);
		r16.setIdUsuario("10");
		r16.setFecha("10/01/2019");
		r16.setNombreUsuario("Karla Fajardo");
		
		RegistroProceso r17 = new RegistroProceso();
		r17.setAprobado(true);
		r17.setCodigo(RecruitmentStages.CONT);
		r17.setComentario("");
		r17.setEstatus(1);
		r17.setModo("OK");
		r17.setIdEtapa(3);
		r17.setIdRechazo(0);
		r17.setIdUsuario("10");
		r17.setFecha("10/01/2019");
		r17.setNombreUsuario("Karla Fajardo");
		
		
		RegistroProceso r171 = new RegistroProceso();
		r171.setAprobado(true);
		r171.setCodigo(RecruitmentStages.CONT);
		r171.setComentario("");
		r171.setEstatus(1);
		r171.setModo("REP");
		r171.setIdEtapa(3);
		r171.setIdRechazo(1);
		r171.setIdUsuario("10");
		r171.setFecha("10/01/2019");
		r171.setNombreUsuario("Karla Fajardo");
		//r.add(r16);
		
		
		if(step == 1) {
			r.add(r1);
		}else if(step == 2) {
			r.add(r1);
			r.add(r2);
		}else if(step == 21) {
			r.add(r1);
			r.add(r21);
			db.setAbierto(false);
			
		}else if(step == 3) {
			r.add(r1);
			r.add(r2);
			r.add(r3);
		}else if(step == 4) {
			r.add(r1);
			r.add(r2);
			r.add(r3);
			r.add(r4);
		}else if(step == 5) {
			r.add(r1);
			r.add(r2);
			r.add(r3);
			r.add(r4);
			r.add(r5);
		}else if(step == 51) {
			r.add(r1);
			r.add(r2);
			r.add(r3);
			r.add(r4);
			r.add(r51);
		}
		else if(step == 6) {
			r.add(r1);
			r.add(r2);
			r.add(r3);
			r.add(r4);
			r.add(r5);
			r.add(r6);
		}else if(step == 7) {
			r.add(r1);
			r.add(r2);
			r.add(r3);
			r.add(r4);
			r.add(r5);
			r.add(r7);
		}else if(step == 8) {
			r.add(r1);
			r.add(r2);
			r.add(r3);
			r.add(r4);
			r.add(r5);
			r.add(r7);
			r.add(r8);
			
		}else if(step == 81) {
			r.add(r1);
			r.add(r2);
			r.add(r3);
			r.add(r4);
			r.add(r5);
			r.add(r7);
			r.add(r81);
			
		}else if(step == 82) {
			r.add(r1);
			r.add(r2);
			r.add(r3);
			r.add(r4);
			r.add(r5);
			r.add(r7);
			r.add(r81);
			r.add(r82);
			db.setAbierto(false);
		}else if(step == 9) {
			
			r.add(r1);
			r.add(r2);
			r.add(r3);
			r.add(r4);
			r.add(r5);
			r.add(r7);
			r.add(r8);
			r.add(r9);
			
		}else if(step == 10) {
			
			r.add(r1);
			r.add(r2);
			r.add(r3);
			r.add(r4);
			r.add(r5);
			r.add(r7);
			r.add(r8);
			r.add(r9);
			r.add(r10);
			
		}else if(step == 11) {
			
			r.add(r1);
			r.add(r2);
			r.add(r3);
			r.add(r4);
			r.add(r5);
			r.add(r7);
			r.add(r8);
			r.add(r9);
			r.add(r10);
			r.add(r11);
			
		}else if(step == 12) {
			
			r.add(r1);
			r.add(r2);
			r.add(r3);
			r.add(r4);
			r.add(r5);
			r.add(r7);
			r.add(r8);
			r.add(r9);
			r.add(r10);
			r.add(r11);
			r.add(r12);
			
		}else if(step == 13) {
			
			r.add(r1);
			r.add(r2);
			r.add(r3);
			r.add(r4);
			r.add(r5);
			r.add(r7);
			r.add(r8);
			r.add(r9);
			r.add(r10);
			r.add(r11);
			r.add(r12);
			r.add(r13);
			
		}else if(step == 14) {
			
			r.add(r1);
			r.add(r2);
			r.add(r3);
			r.add(r4);
			r.add(r5);
			r.add(r7);
			r.add(r8);
			r.add(r9);
			r.add(r10);
			r.add(r11);
			r.add(r12);
			r.add(r13);
			r.add(r14);
			
		}else if(step == 15) {
			
			r.add(r1);
			r.add(r2);
			r.add(r3);
			r.add(r4);
			r.add(r5);
			r.add(r7);
			r.add(r8);
			r.add(r9);
			r.add(r10);
			r.add(r11);
			r.add(r12);
			r.add(r13);
			r.add(r14);
			r.add(r15);
			
		}else if(step == 16) {
			r.add(r1);
			r.add(r2);
			r.add(r3);
			r.add(r4);
			r.add(r5);
			r.add(r6);
			r.add(r7);
			r.add(r8);
			r.add(r9);
			r.add(r10);
			r.add(r11);
			r.add(r12);
			r.add(r13);
			r.add(r14);
			r.add(r15);
		}else if(step == 17) {
			r.add(r1);
			r.add(r2);
			r.add(r3);
			r.add(r4);
			r.add(r5);
			r.add(r7);
			r.add(r8);
			r.add(r6);
			r.add(r9);
			r.add(r10);
			r.add(r11);
			r.add(r12);
			r.add(r13);
			r.add(r14);
			r.add(r15);
			r.add(r16);
		}else if(step == 18) {
			r.add(r1);
			r.add(r2);
			r.add(r3);
			r.add(r4);
			r.add(r5);
			r.add(r7);
			r.add(r8);
			r.add(r6);
			r.add(r9);
			r.add(r10);
			r.add(r11);
			r.add(r12);
			r.add(r13);
			r.add(r14);
			r.add(r15);
			r.add(r16);
			r.add(r17);
		}else if(step == 19) {
			r.add(r1);
			r.add(r2);
			r.add(r3);
			r.add(r4);
			r.add(r5);
			r.add(r7);
			r.add(r8);
			r.add(r6);
			r.add(r9);
			r.add(r10);
			r.add(r11);
			r.add(r12);
			r.add(r13);
			r.add(r14);
			r.add(r15);
			r.add(r16);
			r.add(r171);
		}else if(step == 20) {
			r.add(r1);
			r.add(r2);
			r.add(r3);
			r.add(r4);
			r.add(r5);
			r.add(r7);
			r.add(r8);
			r.add(r6);
			r.add(r9);
			r.add(r10);
			r.add(r11);
			r.add(r12);
			r.add(r13);
			r.add(r14);
			r.add(r15);
			r.add(r16);
			r.add(r171);
			r.add(r12);
		}
		if(step >= 12) {
			db.setNumeroProvicional("55101");
		}
		
		if(step >= 14 && step <= 19) {
			db.setTieneExpediente(true);
		}
		
		response.setEstrategia(e);
		response.setDatosBasicos(db);
		response.setRegistros(r);
		
		return response ;

	}


	private ProcesoReclutamientoResponseBean recruitment2(Integer idReclutamiento, Integer step) {
		ProcesoReclutamientoResponseBean response = new ProcesoReclutamientoResponseBean();
		DatosBasicos db = new DatosBasicos();
		Estrategia e =  new Estrategia();
		LinkedList<RegistroProceso> r = new LinkedList<>();
		
		db.setAbierto(true);
		db.setTipoReclutamiento("GE");
		db.setEstatus("Primeras Entrevistas");
		db.setFechaCreacion("1/10/2019");
		db.setIdCandidato(1);
		db.setIdPublicacion(20);
		db.setIdReclutamiento(1);
		db.setIdUsuario("10");
		db.setNombreCandidato("Julio Llamas Moctezuma");
		db.setNombreVacante("Programador Full Stack");
		
		e.setExamenConfianza(true);
		e.setExamenPsicometrico(true);
		
		RegistroProceso r1 = new RegistroProceso();
		r1.setAprobado(true);
		r1.setCodigo("PI");
		r1.setComentario("");
		r1.setEstatus(1);
		r1.setIdEtapa(1);
		r1.setIdRechazo(0);
		r1.setIdUsuario("10");
		r1.setFecha("10/01/2019");
		r1.setNombreUsuario("Karla Fajardo");
		//r.add(r1);
		
		
		RegistroProceso r2 = new RegistroProceso();
		r2.setAprobado(true);
		r2.setCodigo("FT");
		r2.setComentario("");
		r2.setEstatus(1);
		r2.setIdEtapa(2);
		r2.setIdRechazo(0);
		r2.setIdUsuario("10");
		r2.setFecha("10/01/2019");
		r2.setNombreUsuario("Karla Fajardo");
		
		
		RegistroProceso r21 = new RegistroProceso();
		r21.setAprobado(false);
		r21.setCodigo("FT");
		r21.setComentario("");
		r21.setEstatus(-1);
		r21.setIdEtapa(2);
		r21.setIdRechazo(1);
		r21.setIdUsuario("10");
		r21.setFecha("10/01/2019");
		r21.setNombreUsuario("Karla Fajardo");
		//r.add(r2);
		
		
		RegistroProceso r3 = new RegistroProceso();
		r3.setAprobado(true);
		r3.setCodigo(RecruitmentStages.P_ENT);
		r3.setModo("AG");
		r3.setComentario("");
		r3.setEstatus(1);
		r3.setIdEtapa(3);
		r3.setIdEntrevista(1);
		r3.setIdRechazo(0);
		r3.setIdUsuario("10");
		r3.setFecha("10/01/2019");
		r3.setNombreUsuario("Karla Fajardo");
		//r.add(r3);
		
		
		RegistroProceso r4 = new RegistroProceso();
		r4.setAprobado(true);
		r4.setCodigo(RecruitmentStages.P_ENT);
		r4.setModo("RE");
		r4.setComentario("");
		r4.setEstatus(1);
		r4.setIdEtapa(3);
		r4.setIdRechazo(0);
		r4.setIdUsuario("10");
		r4.setFecha("10/01/2019");
		r4.setNombreUsuario("Karla Fajardo");
		//r.add(r4);
		
		
		RegistroProceso r5 = new RegistroProceso();
		r5.setAprobado(true);
		r5.setCodigo(RecruitmentStages.EX_PSICO);
		r5.setComentario("");
		r5.setEstatus(1);
		r5.setIdEtapa(3);
		r5.setIdRechazo(0);
		r5.setIdUsuario("10");
		r5.setFecha("10/01/2019");
		r5.setNombreUsuario("Karla Fajardo");
		//r.add(r5);
		
		
		RegistroProceso r51 = new RegistroProceso();
		r51.setAprobado(true);
		r51.setCodigo(RecruitmentStages.EX_PSICO);
		r51.setComentario("");
		r51.setEstatus(-1);
		r51.setIdEtapa(3);
		r51.setIdRechazo(0);
		r51.setIdUsuario("10");
		r51.setFecha("10/01/2019");
		r51.setNombreUsuario("Karla Fajardo");
		
		
		RegistroProceso r6 = new RegistroProceso();
		r6.setAprobado(true);
		r6.setCodigo(RecruitmentStages.EX_CONF);

		r6.setComentario("");
		r6.setEstatus(1);
		r6.setIdEtapa(3);
		r6.setIdRechazo(0);
		r6.setIdUsuario("10");
		r6.setFecha("10/01/2019");
		r6.setNombreUsuario("Karla Fajardo");
		//r.add(r6);
		
		
		RegistroProceso r7 = new RegistroProceso();
		r7.setAprobado(true);
		r7.setCodigo(RecruitmentStages.S_ENT);
		r7.setModo("AG");
		r7.setComentario("");
		r7.setEstatus(1);
		r7.setIdEtapa(3);
		r7.setIdRechazo(0);
		r7.setIdUsuario("10");
		r7.setFecha("10/01/2019");
		r7.setNombreUsuario("Karla Fajardo");
		//r.add(r7);
		
		
		RegistroProceso r8 = new RegistroProceso();
		r8.setAprobado(true);
		r8.setCodigo(RecruitmentStages.S_ENT);
		r8.setModo("RE");
		r8.setComentario("");
		r8.setEstatus(1);
		r8.setIdEtapa(3);
		r8.setIdRechazo(0);
		r8.setIdUsuario("10");
		r8.setFecha("10/01/2019");
		r8.setNombreUsuario("Karla Fajardo");
		//r.add(r8);
		
		RegistroProceso r81 = new RegistroProceso();
		r81.setAprobado(false);
		r81.setCodigo(RecruitmentStages.S_ENT);
		r81.setModo("RE");
		r81.setComentario("");
		r81.setEstatus(-1);
		r81.setIdEtapa(3);
		r81.setIdRechazo(0);
		r81.setIdUsuario("10");
		r81.setFecha("10/01/2019");
		r81.setNombreUsuario("Karla Fajardo");
		
		
		RegistroProceso r82 = new RegistroProceso();
		r82.setAprobado(false);
		r82.setCodigo(RecruitmentStages.S_ENT);
		r82.setModo("MR");
		r82.setComentario("");
		r82.setEstatus(-1);
		r82.setIdEtapa(3);
		r82.setIdRechazo(0);
		r82.setIdUsuario("10");
		r82.setFecha("10/01/2019");
		r82.setNombreUsuario("Karla Fajardo");
		
		
		//r.add(r81);
		
		RegistroProceso r9 = new RegistroProceso();
		r9.setAprobado(true);
		r9.setCodigo(RecruitmentStages.OFE);
		r9.setComentario("");
		r9.setEstatus(1);
		r9.setIdEtapa(3);
		r9.setIdRechazo(0);
		r9.setIdUsuario("10");
		r9.setFecha("10/01/2019");
		r9.setNombreUsuario("Karla Fajardo");
		//r.add(r9);
		
		RegistroProceso r10 = new RegistroProceso();
		r10.setAprobado(true);
		r10.setCodigo(RecruitmentStages.FORM_CONT);
		r10.setComentario("");
		r10.setEstatus(1);
		r10.setModo("GD");
		r10.setIdEtapa(3);
		r10.setIdRechazo(0);
		r10.setIdUsuario("10");
		r10.setFecha("10/01/2019");
		r10.setNombreUsuario("Karla Fajardo");
		//r.add(r10);

		
		RegistroProceso r11 = new RegistroProceso();
		r11.setAprobado(true);
		r11.setCodigo(RecruitmentStages.FORM_CONT);
		r11.setComentario("");
		r11.setEstatus(1);
		r11.setModo("OK");
		r11.setIdEtapa(3);
		r11.setIdRechazo(0);
		r11.setIdUsuario("10");
		r11.setFecha("10/01/2019");
		r11.setNombreUsuario("Karla Fajardo");
		//r.add(r11);
		
		RegistroProceso r12 = new RegistroProceso();
		r12.setAprobado(true);
		r12.setCodigo(RecruitmentStages.FOLIO);
		r12.setComentario("");
		r12.setEstatus(1);
		r12.setModo("GD");
		r12.setIdEtapa(3);
		r12.setIdRechazo(0);
		r12.setIdUsuario("10");
		r12.setFecha("10/01/2019");
		r12.setNombreUsuario("Karla Fajardo");
		//r.add(r12);
		
		
		RegistroProceso r13 = new RegistroProceso();
		r13.setAprobado(true);
		r13.setCodigo(RecruitmentStages.FORM_DOCTOS);
		r13.setComentario("");
		r13.setEstatus(1);
		r13.setModo("EN");
		r13.setIdEtapa(3);
		r13.setIdRechazo(0);
		r13.setIdUsuario("10");
		r13.setFecha("10/01/2019");
		r13.setNombreUsuario("Karla Fajardo");
		//r.add(r13);
		
		RegistroProceso r14 = new RegistroProceso();
		r14.setAprobado(true);
		r14.setCodigo(RecruitmentStages.FORM_DOCTOS);
		r14.setComentario("");
		r14.setEstatus(1);
		r14.setModo("GD");
		r14.setIdEtapa(3);
		r14.setIdRechazo(0);
		r14.setIdUsuario("10");
		r14.setFecha("10/01/2019");
		r14.setNombreUsuario("Karla Fajardo");
		//r.add(r14);
		
		RegistroProceso r15 = new RegistroProceso();
		r15.setAprobado(true);
		r15.setCodigo(RecruitmentStages.FORM_DOCTOS);
		r15.setComentario("");
		r15.setEstatus(1);
		r15.setModo("OK");
		r15.setIdEtapa(3);
		r15.setIdRechazo(0);
		r15.setIdUsuario("10");
		r15.setFecha("10/01/2019");
		r15.setNombreUsuario("Karla Fajardo");
		//r.add(r15);
		
		
		RegistroProceso r16 = new RegistroProceso();
		r16.setAprobado(true);
		r16.setCodigo(RecruitmentStages.EST_SOC);
		r16.setComentario("");
		r16.setEstatus(1);
		r16.setIdEtapa(3);
		r16.setIdRechazo(0);
		r16.setIdUsuario("10");
		r16.setFecha("10/01/2019");
		r16.setNombreUsuario("Karla Fajardo");
		
		RegistroProceso r17 = new RegistroProceso();
		r17.setAprobado(true);
		r17.setCodigo(RecruitmentStages.CONT);
		r17.setComentario("");
		r17.setEstatus(1);
		r17.setModo("OK");
		r17.setIdEtapa(3);
		r17.setIdRechazo(0);
		r17.setIdUsuario("10");
		r17.setFecha("10/01/2019");
		r17.setNombreUsuario("Karla Fajardo");
		
		
		RegistroProceso r171 = new RegistroProceso();
		r171.setAprobado(true);
		r171.setCodigo(RecruitmentStages.CONT);
		r171.setComentario("");
		r171.setEstatus(1);
		r171.setModo("REP");
		r171.setIdEtapa(3);
		r171.setIdRechazo(1);
		r171.setIdUsuario("10");
		r171.setFecha("10/01/2019");
		r171.setNombreUsuario("Karla Fajardo");
		//r.add(r16);
		
		
		if(step == 1) {
			r.add(r1);
		}else if(step == 2) {
			r.add(r1);
			r.add(r2);
		}else if(step == 21) {
			r.add(r1);
			r.add(r21);
			db.setAbierto(false);
			
		}else if(step == 3) {
			r.add(r1);
			r.add(r2);
			r.add(r3);
		}else if(step == 4) {
			r.add(r1);
			r.add(r2);
			r.add(r3);
			r.add(r4);
		}else if(step == 5) {
			r.add(r1);
			r.add(r2);
			r.add(r3);
			r.add(r4);
			r.add(r5);
		}else if(step == 51) {
			r.add(r1);
			r.add(r2);
			r.add(r3);
			r.add(r4);
			r.add(r51);
		}
		else if(step == 6) {
			r.add(r1);
			r.add(r2);
			r.add(r3);
			r.add(r4);
			r.add(r5);
			r.add(r6);
		}else if(step == 7) {
			r.add(r1);
			r.add(r2);
			r.add(r3);
			r.add(r4);
			r.add(r5);
			r.add(r7);
		}else if(step == 8) {
			r.add(r1);
			r.add(r2);
			r.add(r3);
			r.add(r4);
			r.add(r5);
			r.add(r7);
			r.add(r8);
			
		}else if(step == 81) {
			r.add(r1);
			r.add(r2);
			r.add(r3);
			r.add(r4);
			r.add(r5);
			r.add(r7);
			r.add(r81);
			
		}else if(step == 82) {
			r.add(r1);
			r.add(r2);
			r.add(r3);
			r.add(r4);
			r.add(r5);
			r.add(r7);
			r.add(r81);
			r.add(r82);
			db.setAbierto(false);
		}else if(step == 9) {
			
			r.add(r1);
			r.add(r2);
			r.add(r3);
			r.add(r4);
			r.add(r5);
			r.add(r7);
			r.add(r8);
			r.add(r9);
			
		}else if(step == 10) {
			
			r.add(r1);
			r.add(r2);
			r.add(r3);
			r.add(r4);
			r.add(r5);
			r.add(r7);
			r.add(r8);
			r.add(r9);
			r.add(r10);
			
		}else if(step == 11) {
			
			r.add(r1);
			r.add(r2);
			r.add(r3);
			r.add(r4);
			r.add(r5);
			r.add(r7);
			r.add(r8);
			r.add(r9);
			r.add(r10);
			r.add(r11);
			
		}else if(step == 12) {
			
			r.add(r1);
			r.add(r2);
			r.add(r3);
			r.add(r4);
			r.add(r5);
			r.add(r7);
			r.add(r8);
			r.add(r9);
			r.add(r10);
			r.add(r11);
			r.add(r12);
			
		}else if(step == 13) {
			
			r.add(r1);
			r.add(r2);
			r.add(r3);
			r.add(r4);
			r.add(r5);
			r.add(r7);
			r.add(r8);
			r.add(r9);
			r.add(r10);
			r.add(r11);
			r.add(r12);
			r.add(r13);
			
		}else if(step == 14) {
			
			r.add(r1);
			r.add(r2);
			r.add(r3);
			r.add(r4);
			r.add(r5);
			r.add(r7);
			r.add(r8);
			r.add(r9);
			r.add(r10);
			r.add(r11);
			r.add(r12);
			r.add(r13);
			r.add(r14);
			
		}else if(step == 15) {
			
			r.add(r1);
			r.add(r2);
			r.add(r3);
			r.add(r4);
			r.add(r5);
			r.add(r7);
			r.add(r8);
			r.add(r9);
			r.add(r10);
			r.add(r11);
			r.add(r12);
			r.add(r13);
			r.add(r14);
			r.add(r15);
			
		}else if(step == 16) {
			r.add(r1);
			r.add(r2);
			r.add(r3);
			r.add(r4);
			r.add(r5);
			r.add(r6);
			r.add(r7);
			r.add(r8);
			r.add(r9);
			r.add(r10);
			r.add(r11);
			r.add(r12);
			r.add(r13);
			r.add(r14);
			r.add(r15);
		}else if(step == 17) {
			r.add(r1);
			r.add(r2);
			r.add(r3);
			r.add(r4);
			r.add(r5);
			r.add(r7);
			r.add(r8);
			r.add(r6);
			r.add(r9);
			r.add(r10);
			r.add(r11);
			r.add(r12);
			r.add(r13);
			r.add(r14);
			r.add(r15);
			r.add(r16);
		}else if(step == 18) {
			r.add(r1);
			r.add(r2);
			r.add(r3);
			r.add(r4);
			r.add(r5);
			r.add(r7);
			r.add(r8);
			r.add(r6);
			r.add(r9);
			r.add(r10);
			r.add(r11);
			r.add(r12);
			r.add(r13);
			r.add(r14);
			r.add(r15);
			r.add(r16);
			r.add(r17);
		}else if(step == 19) {
			r.add(r1);
			r.add(r2);
			r.add(r3);
			r.add(r4);
			r.add(r5);
			r.add(r7);
			r.add(r8);
			r.add(r6);
			r.add(r9);
			r.add(r10);
			r.add(r11);
			r.add(r12);
			r.add(r13);
			r.add(r14);
			r.add(r15);
			r.add(r16);
			r.add(r171);
		}else if(step == 20) {
			r.add(r1);
			r.add(r2);
			r.add(r3);
			r.add(r4);
			r.add(r5);
			r.add(r7);
			r.add(r8);
			r.add(r6);
			r.add(r9);
			r.add(r10);
			r.add(r11);
			r.add(r12);
			r.add(r13);
			r.add(r14);
			r.add(r15);
			r.add(r16);
			r.add(r171);
			r.add(r12);
		}
		
		
		if(step >= 12) {
			db.setNumeroProvicional("55101");
		}
		
		response.setEstrategia(e);
		response.setDatosBasicos(db);
		response.setRegistros(r);
		
		return response ;

	}


	

	
	@Override
	public void handleSaveStep(CompletePhaseDTO dto) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public ProcesoReclutamientoResponseBean getDataById(Integer idReclutamiento) throws GenericStatusException {
		// TODO Auto-generated method stub
		return this.getDataById(idReclutamiento,0);
	}




	@Override
	protected GenericTextResponseBean __callHasPublicationSpaces(RequestBean<Integer> genericRequestBean)
			throws GenericStatusException {
		
		GenericTextResponseBean response = new GenericTextResponseBean();
		response.setRespuesta("Exito");
		return response ;
	}


	@Override
	protected ProcesoReclutamientoResponseBean __getRecruitmentData(RequestBean<Integer> genericRequestBean,
			Integer step) throws GenericStatusException {

		Integer idReclutamiento = (Integer) genericRequestBean.getUriVariables().get("idReclutamiento");
		return this.__getRecruitmentData(idReclutamiento, step);
	}


	@Override
	LinkedList<DatosEntrevistaResponseBean> __getNoHandledInterviewData(RequestBean<Integer> genericRequestBean){
		Integer idReclutamiento = genericRequestBean.getEntityPayload();
		return this.__getNoHandledInterviewData(idReclutamiento);
	}


	@Override
	public void create(IniciarReclutamientoDTO dto) {
		// TODO Auto-generated method stub
		
	}

	private GenericTextResponseBean generateBasicDTO() {
		return new GenericTextResponseBean();
	}

	@Override
	protected GenericTextResponseBean __callSaveGenericStage(RequestBean<GenericStageQueryDTO> genericRequestBean)
			throws GenericStatusException {
		// TODO Auto-generated method stub
		if(true) {
			throw new  GenericStatusException();
		}
		
		return this.generateBasicDTO();
	}


	@Override
	protected GenericTextResponseBean __callSavePsychometricTest(RequestBean<PsychometricTestDTO> genericRequestBean)
			throws GenericStatusException {
		// TODO Auto-generated method stub
		return this.generateBasicDTO();
	}


	@Override
	protected GenericTextResponseBean __callSaveTrustTest(RequestBean<TrustTestDTO> genericRequestBean) throws GenericStatusException {
		// TODO Auto-generated method stub
		return this.generateBasicDTO();
	}


	@Override
	protected GenericTextResponseBean __callSaveFolio(RequestBean<FolioDTO> genericRequestBean) throws GenericStatusException {
		// TODO Auto-generated method stub
		return this.generateBasicDTO();
	}


	@Override
	protected GenericTextResponseBean __callSaveInterviewAppointmentDTO(RequestBean<InterviewAppointmentDTO> genericRequestBean)
			throws InterviewException {
		// TODO Auto-generated method stub
		return this.generateBasicDTO();
	}


	@Override
	protected GenericTextResponseBean __callSaveInterviewResponse(RequestBean<InterviewResponseDTO> genericRequestBean)
			throws GenericStatusException {
		// TODO Auto-generated method stub
		return this.generateBasicDTO();
	}


	@Override
	protected LinkedList<FolioVO> __callSearchFolio(RequestBean<String> genericRequestBean)
			throws GenericStatusException {
		
		String folio = genericRequestBean.getEntityPayload();
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
