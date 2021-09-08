package com.mx.profuturo.bolsa.service.reports;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.mx.profuturo.bolsa.model.reports.dto.QueryDTO;
import com.mx.profuturo.bolsa.model.reports.response.FinalistHiredResponse;
import com.mx.profuturo.bolsa.model.reports.response.HiringsResponse;
import com.mx.profuturo.bolsa.model.reports.response.InterviewTypeResponse;
import com.mx.profuturo.bolsa.model.reports.response.RecruitmentSourcesResponse;
import com.mx.profuturo.bolsa.model.reports.response.RejectReasonsResponse;
import com.mx.profuturo.bolsa.model.reports.vo.data.ContratacionesMesVO;
import com.mx.profuturo.bolsa.model.reports.vo.data.DatosFiltrosVO;
import com.mx.profuturo.bolsa.model.reports.vo.data.FinalistasContratadosVO;
import com.mx.profuturo.bolsa.model.restclient.RequestBean;
import com.mx.profuturo.bolsa.util.exception.custom.GenericStatusException;


@Service
@Scope("request")
@Profile("mock")
public class ReportDataMockServiceImpl extends ReportDataServiceBase{


	public ArrayList<RejectReasonsResponse> __getRejectReasonsServiceData(QueryDTO dto) {
		
		ArrayList<RejectReasonsResponse> vo = new ArrayList<RejectReasonsResponse>();
		
		RejectReasonsResponse r1 = this.buildRejectReasonRow();
		r1.setMotivo("Motivo A");
		r1.setEtapa("Etapa 1");
		
		vo.add(r1);
		RejectReasonsResponse r2 = this.buildRejectReasonRow();
		r2.setMotivo("Motivo B");
		r2.setEtapa("Etapa 2");
		vo.add(r2);
		RejectReasonsResponse r3 = this.buildRejectReasonRow();
		r3.setMotivo("Motivo C");
		r3.setEtapa("Etapa 1");
		vo.add(r3);
		RejectReasonsResponse r4 = this.buildRejectReasonRow();
		r4.setMotivo("Motivo B");
		r4.setEtapa("Etapa 2");
		vo.add(r4);
		RejectReasonsResponse r5 = this.buildRejectReasonRow();
		r5.setMotivo("Motivo E");
		r5.setEtapa("Etapa 1");
		vo.add(r5);
		RejectReasonsResponse r6 = this.buildRejectReasonRow();
		r6.setMotivo("Motivo A");
		r6.setEtapa("Etapa 2");
		vo.add(r6);
		RejectReasonsResponse r7 = this.buildRejectReasonRow();
		r7.setMotivo("Motivo B");
		r7.setEtapa("Etapa 3");
		vo.add(r7);
		RejectReasonsResponse r8 = this.buildRejectReasonRow();
		r8.setMotivo("Motivo C");
		r8.setEtapa("Etapa 2");
		vo.add(r8);
		RejectReasonsResponse r9 = this.buildRejectReasonRow();
		r9.setMotivo("Motivo D");
		r9.setEtapa("Etapa 1");
		vo.add(r9);
		return vo;
	}


	public ArrayList<RejectReasonsResponse> __getAbandonmentReasonServiceData(QueryDTO dto) {
		// TODO Auto-generated method stub
		return new ArrayList<RejectReasonsResponse>();
	}


	public ArrayList<FinalistHiredResponse> __getFinalistVsHiredServiceData(QueryDTO dto) {
		ArrayList<FinalistHiredResponse> vo = new ArrayList<FinalistHiredResponse>();
		for(int i = 0; i < 20; i++) {
			vo.add(this.buildFinalistVsHiredRow());
		}		
		
		return vo;
	}


	public ArrayList<HiringsResponse> __getPositionLevelServiceData(QueryDTO dto) {
		// TODO Auto-generated method stub
		
		ArrayList<HiringsResponse> list = new ArrayList<HiringsResponse>();
		
		HiringsResponse e = new HiringsResponse();
		
		e.setNivelPuesto("Gerente");
		list.add(e);
		
		
		e = new HiringsResponse();
		e.setNivelPuesto("Gerente");
		list.add(e);
		
		
		e = new HiringsResponse();
		e.setNivelPuesto("Gerente");
		list.add(e);
		
		
		e = new HiringsResponse();
		e.setNivelPuesto("Gerente");
		list.add(e);
		
		
		e = new HiringsResponse();
		e.setNivelPuesto("Gerente");
		list.add(e);
		
		
		
		e = new HiringsResponse();
		e.setNivelPuesto("Consultor");
		list.add(e);
		
		
		e = new HiringsResponse();
		e.setNivelPuesto("Consultor");
		list.add(e);
		
		
		e = new HiringsResponse();
		e.setNivelPuesto("Becario");
		list.add(e);
		
		
		e = new HiringsResponse();
		e.setNivelPuesto("Becario");
		list.add(e);
		
		e = new HiringsResponse();
		e.setNivelPuesto("Funcionario");
		list.add(e);
		
		return list;
	}


	public ArrayList<HiringsResponse> __getHiringsPerMonthServiceData(QueryDTO dto) {
		// TODO Auto-generated method stub
		
		ArrayList<HiringsResponse> list = new ArrayList<HiringsResponse>();
		
		
		for(int i = 0; i < 36; i++) {
			list.add(this.buildHiringsPerMonthRow());
		}
		
		return list;
	}


	public ArrayList<HiringsResponse> __getHiringsPerAnalystServiceData(QueryDTO dto) {
		
		ArrayList<HiringsResponse> list = new ArrayList<HiringsResponse>();
		
		HiringsResponse e1 = new HiringsResponse();
		e1.setAnalista("a1");
		HiringsResponse e2 = new HiringsResponse();
		e2.setAnalista("a1");
		HiringsResponse e3 = new HiringsResponse();
		e3.setAnalista("a1");
		HiringsResponse e4 = new HiringsResponse();
		e4.setAnalista("a3");
		HiringsResponse e5 = new HiringsResponse();
		e5.setAnalista("a3");
		HiringsResponse e6 = new HiringsResponse();
		e6.setAnalista("a2");
		HiringsResponse e7 = new HiringsResponse();
		e7.setAnalista("a2");
		HiringsResponse e8 = new HiringsResponse();
		e8.setAnalista("a2");
		HiringsResponse e9 = new HiringsResponse();
		e9.setAnalista("a4");
		HiringsResponse e10 = new HiringsResponse();
		e10.setAnalista("a5");
		HiringsResponse e11 = new HiringsResponse();
		e11.setAnalista("a5");
		
		list.add(e1);
		list.add(e2);
		list.add(e3);
		list.add(e4);
		list.add(e5);
		list.add(e6);
		list.add(e7);
		list.add(e8);
		list.add(e9);
		list.add(e10);
		list.add(e11);
		return list;
	}


	public ArrayList<RecruitmentSourcesResponse> __getRecruitmentSourceServiceData(QueryDTO dto) {
		ArrayList<RecruitmentSourcesResponse> list = new ArrayList<>();
		
		RecruitmentSourcesResponse r1 = new RecruitmentSourcesResponse();
		r1.setFuente("F1");
		r1.setEstatus("Postulado");
		list.add(r1);
		
		RecruitmentSourcesResponse r2 = new RecruitmentSourcesResponse();
		r2.setFuente("F2");
		r2.setEstatus("Postulado");
		list.add(r2);
		
		
		RecruitmentSourcesResponse r3 = new RecruitmentSourcesResponse();
		r3.setFuente("F3");
		r3.setEstatus("Postulado");
		list.add(r3);
		
		
		
		RecruitmentSourcesResponse r4 = new RecruitmentSourcesResponse();
		r4.setFuente("F4");
		r4.setEstatus("Postulado");
		list.add(r4);
		
		
		RecruitmentSourcesResponse r5 = new RecruitmentSourcesResponse();
		r5.setFuente("F1");
		r5.setEstatus("Contratado");
		list.add(r5);
		
		
		RecruitmentSourcesResponse r6 = new RecruitmentSourcesResponse();
		r6.setFuente("F1");
		r6.setEstatus("Contratado");
		list.add(r6);
		
		RecruitmentSourcesResponse r7 = new RecruitmentSourcesResponse();
		r7.setFuente("F2");
		r7.setEstatus("Postulado");
		list.add(r7);
		
		RecruitmentSourcesResponse r8 = new RecruitmentSourcesResponse();
		r8.setFuente("F2");
		r8.setEstatus("Contratado");
		list.add(r8);
		
		RecruitmentSourcesResponse r9 = new RecruitmentSourcesResponse();
		r9.setFuente("F3");
		r9.setEstatus("Postulado");
		list.add(r9);
		return list;
	}


	public ArrayList<InterviewTypeResponse> __getInterviewsTypeServiceData(QueryDTO dto) {
		// TODO Auto-generated method stub
		ArrayList<InterviewTypeResponse> list = new ArrayList<InterviewTypeResponse>();
		
		InterviewTypeResponse r1 = new InterviewTypeResponse();
		r1.setTipoEntrevista("F1");
		r1.setEstatusReclutamiento("Postulado");
		
		list.add(r1);
		
		InterviewTypeResponse r2 = new InterviewTypeResponse();
		r2.setTipoEntrevista("F2");
		r2.setEstatusReclutamiento("Postulado");
		list.add(r2);
		
		
		InterviewTypeResponse r3 = new InterviewTypeResponse();
		r3.setTipoEntrevista("F3");
		r3.setEstatusReclutamiento("Postulado");
		list.add(r3);
		
		
		
		InterviewTypeResponse r4 = new InterviewTypeResponse();
		r4.setTipoEntrevista("F4");
		r4.setEstatusReclutamiento("Postulado");
		list.add(r4);
		
		
		InterviewTypeResponse r5 = new InterviewTypeResponse();
		r5.setTipoEntrevista("F1");
		r5.setEstatusReclutamiento("Contratado");
		list.add(r5);
		
		
		InterviewTypeResponse r6 = new InterviewTypeResponse();
		r6.setTipoEntrevista("F1");
		r6.setEstatusReclutamiento("Contratado");
		list.add(r6);
		
		InterviewTypeResponse r7 = new InterviewTypeResponse();
		r7.setTipoEntrevista("F2");
		r7.setEstatusReclutamiento("Postulado");
		list.add(r7);
		
		InterviewTypeResponse r8 = new InterviewTypeResponse();
		r8.setTipoEntrevista("F2");
		r8.setEstatusReclutamiento("Contratado");
		list.add(r8);
		
		InterviewTypeResponse r9 = new InterviewTypeResponse();
		r9.setTipoEntrevista("F3");
		r9.setEstatusReclutamiento("Postulado");
		list.add(r9);
		
		
		
		return list;
	}
	
	
	private HiringsResponse buildHiringsPerMonthRow() {
		HiringsResponse row = new HiringsResponse();
	
		/*Random rand = new Random();
		int n = rand.nextInt(12);*/
		row.setMes(this.monthAux.getMonths().get(12));
		return row;
	}
	
	private FinalistHiredResponse buildFinalistVsHiredRow() {
		
		FinalistHiredResponse row = new FinalistHiredResponse();
		/*Random rand = new Random();
		int n = rand.nextInt(2);*/
		int n = 0;
		row.setEstatus((n == 0) ? "contratado" : "finalista");
		return row;
	}
	private RejectReasonsResponse buildRejectReasonRow() {
		
		RejectReasonsResponse row = new RejectReasonsResponse();
		row.setAnalista("analista");
		row.setCandidato("candidato");
		row.setFecha("fecha");
		row.setEtapa("etapa");
		row.setMotivo("motivo");
		return row ;
	}
	
	
	private DatosFiltrosVO buildFilterDataRow() {
		DatosFiltrosVO vo = new DatosFiltrosVO();
		vo.setAreaInteres("Sistema");
		vo.setCentroCostos("centrocostos");
		vo.setDivision("division");
		vo.setGerencia("gerencia");
		vo.setRegional("regional");
		vo.setSubAreaInteres("subAreaInteres");
		return vo;
	}

	@Override
	public ArrayList<RejectReasonsResponse> __getRejectReasonsServiceData(RequestBean<QueryDTO> dto) {
		// TODO Auto-generated method stub
		return this.__getRejectReasonsServiceData(this.getQueryFromRequest(dto));
	}

	@Override
	public ArrayList<RejectReasonsResponse> __getAbandonmentReasonServiceData(RequestBean<QueryDTO> dto) {
		// TODO Auto-generated method stub
		return this.__getAbandonmentReasonServiceData(this.getQueryFromRequest(dto));
	}

	@Override
	public ArrayList<FinalistHiredResponse> __getFinalistVsHiredServiceData(RequestBean<QueryDTO> dto) {
		// TODO Auto-generated method stub
		return this.__getFinalistVsHiredServiceData(this.getQueryFromRequest(dto));
	}

	@Override
	public ArrayList<HiringsResponse> __getPositionLevelServiceData(RequestBean<QueryDTO> dto) {
		// TODO Auto-generated method stub
		return this.__getPositionLevelServiceData(this.getQueryFromRequest(dto));
	}

	@Override
	public ArrayList<HiringsResponse> __getHiringsPerMonthServiceData(RequestBean<QueryDTO> dto) {
		// TODO Auto-generated method stub
		return this.__getHiringsPerAnalystServiceData(this.getQueryFromRequest(dto));
	}

	@Override
	public ArrayList<HiringsResponse> __getHiringsPerAnalystServiceData(RequestBean<QueryDTO> dto) {
		// TODO Auto-generated method stub
		return this.__getHiringsPerAnalystServiceData(this.getQueryFromRequest(dto));
	}

	@Override
	public ArrayList<RecruitmentSourcesResponse> __getRecruitmentSourceServiceData(RequestBean<QueryDTO> dto) {
		// TODO Auto-generated method stub
		return this.__getRecruitmentSourceServiceData(this.getQueryFromRequest(dto));
	}

	@Override
	public ArrayList<InterviewTypeResponse> __getInterviewsTypeServiceData(RequestBean<QueryDTO> dto) {
		// TODO Auto-generated method stub
		return this.__getInterviewsTypeServiceData(this.getQueryFromRequest(dto));
	}
	
	private QueryDTO getQueryFromRequest(RequestBean<QueryDTO> rq) {
		QueryDTO dto = rq.getEntityPayload();
		return dto;
	}


	@Override
	public ArrayList<RejectReasonsResponse> __getRejectReasonsPerStageServiceData(RequestBean<QueryDTO> dto)
			throws GenericStatusException {
		// TODO Auto-generated method stub
		return this.__getRejectReasonsServiceData(dto);
	}
	
}
