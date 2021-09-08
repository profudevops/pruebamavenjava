package com.mx.profuturo.bolsa.service.reports;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import com.mx.profuturo.bolsa.model.reports.dto.QueryDTO;
import com.mx.profuturo.bolsa.model.reports.response.FinalistHiredResponse;
import com.mx.profuturo.bolsa.model.reports.response.HiringsResponse;
import com.mx.profuturo.bolsa.model.reports.response.InterviewTypeResponse;
import com.mx.profuturo.bolsa.model.reports.response.RecruitmentSourcesResponse;
import com.mx.profuturo.bolsa.model.reports.response.RejectReasonsResponse;
import com.mx.profuturo.bolsa.model.reports.vo.data.ContratacionesAnalistaVO;
import com.mx.profuturo.bolsa.model.reports.vo.data.ContratacionesMesVO;
import com.mx.profuturo.bolsa.model.reports.vo.data.ContratacionesNivelPuestoVO;
import com.mx.profuturo.bolsa.model.reports.vo.data.DatosFiltrosVO;
import com.mx.profuturo.bolsa.model.reports.vo.data.FinalistasContratadosVO;
import com.mx.profuturo.bolsa.model.reports.vo.data.FuentesReclutamientoVO;
import com.mx.profuturo.bolsa.model.reports.vo.data.MotivosRechazoVO;
import com.mx.profuturo.bolsa.model.reports.vo.data.TiposEntrevistaVO;
import com.mx.profuturo.bolsa.model.restclient.RequestBean;
import com.mx.profuturo.bolsa.util.exception.custom.GenericStatusException;

public abstract class ReportDataServiceBase implements ReportDataService {
	
	
	protected MonthAux monthAux = new MonthAux();
	

	private RequestBean<QueryDTO> generateRequest(QueryDTO dto){
		dto.setFechaInicio(dto.getStartDate());
		dto.setFechaFin(dto.getEndDate());
		
		RequestBean<QueryDTO> rq = new RequestBean<QueryDTO>();
		
		
		rq.setPayload(dto);
		return rq ;
	}
	
	@Override
	public abstract ArrayList<RejectReasonsResponse>  __getRejectReasonsServiceData(RequestBean<QueryDTO> dto) throws GenericStatusException;
	
	@Override
	public LinkedList<MotivosRechazoVO> getRejectReasonsData(QueryDTO dto) throws GenericStatusException {
		LinkedList<MotivosRechazoVO> vo = new LinkedList<MotivosRechazoVO>();
		ArrayList<RejectReasonsResponse> data = this.__getRejectReasonsServiceData(this.generateRequest(dto));		
		MotivosRechazoVO tmp = null;
		if(data!= null) {
			for(RejectReasonsResponse e:data) {
				tmp = new MotivosRechazoVO(e);
				vo.add(tmp);
			}
		}
		return vo ;
	}
	
	
	@Override
	public abstract ArrayList<RejectReasonsResponse>  __getRejectReasonsPerStageServiceData(RequestBean<QueryDTO> dto) throws GenericStatusException;
	
	@Override
	public LinkedList<MotivosRechazoVO> getRejectReasonsPerStageData(QueryDTO dto) throws GenericStatusException {
		LinkedList<MotivosRechazoVO> vo = new LinkedList<MotivosRechazoVO>();
		ArrayList<RejectReasonsResponse> data = this.__getRejectReasonsServiceData(this.generateRequest(dto));		
		MotivosRechazoVO tmp = null;
		if(data!= null) {
			for(RejectReasonsResponse e:data) {
				tmp = new MotivosRechazoVO(e);
				vo.add(tmp);
			}
		}
		return vo ;
	}
	@Override
	public abstract ArrayList<RejectReasonsResponse> __getAbandonmentReasonServiceData(RequestBean<QueryDTO> dto) throws GenericStatusException;
	@Override
	public LinkedList<MotivosRechazoVO> getAbandonmentReasonData(QueryDTO dto) throws GenericStatusException {
		return this.getRejectReasonsData(dto);
	}
	@Override
	public abstract ArrayList<FinalistHiredResponse> __getFinalistVsHiredServiceData(RequestBean<QueryDTO> dto) throws GenericStatusException;
	@Override
	public LinkedList<FinalistasContratadosVO> getFinalistVsHiredData(QueryDTO query) throws GenericStatusException{
		LinkedList<FinalistasContratadosVO> list = new LinkedList<>();
		
		
		
		ArrayList<FinalistHiredResponse> data = this.__getFinalistVsHiredServiceData(this.generateRequest(query));		
		FinalistasContratadosVO tmp = null;
		if(data!= null) {
			for(FinalistHiredResponse e:data) {
				tmp = new FinalistasContratadosVO(e);
				list.add(tmp);
			}
		}
		
		return list;
		
	}
	@Override
	public abstract ArrayList<HiringsResponse> __getPositionLevelServiceData(RequestBean<QueryDTO> query) throws GenericStatusException;
	public LinkedList<ContratacionesNivelPuestoVO> getPositionLevelHiringsdData(QueryDTO query) throws GenericStatusException{
		LinkedList<ContratacionesNivelPuestoVO> list = new LinkedList<ContratacionesNivelPuestoVO>();
			
		ArrayList<HiringsResponse> data = this.__getPositionLevelServiceData(this.generateRequest(query));
		ContratacionesNivelPuestoVO tmp = null;
		if(data!= null) {
			for(HiringsResponse e:data) {
				tmp = new ContratacionesNivelPuestoVO(e);
				list.add(tmp);
			}
		}
		
		
		return list ;
	}
	
	
	@Override
	public abstract ArrayList<HiringsResponse> __getHiringsPerMonthServiceData(RequestBean<QueryDTO> dto) throws GenericStatusException;
	public LinkedList<ContratacionesMesVO> getHiringsPerMonthData(QueryDTO query) throws GenericStatusException{
		LinkedList<ContratacionesMesVO> list = new LinkedList<ContratacionesMesVO>();
	
		ArrayList<HiringsResponse> data = __getHiringsPerMonthServiceData(this.generateRequest(query));
		ContratacionesMesVO tmp = null;
		if(data!= null) {
			for(HiringsResponse e:data) {
				tmp = new ContratacionesMesVO(e);
				list.add(tmp);
			}
		}

		return list ;
	}
	@Override
	public abstract ArrayList<HiringsResponse> __getHiringsPerAnalystServiceData(RequestBean<QueryDTO> dto) throws GenericStatusException;
	public LinkedList<ContratacionesAnalistaVO> getHiringsPerAnalystData(QueryDTO query) throws GenericStatusException{
		LinkedList<ContratacionesAnalistaVO> list = new LinkedList<ContratacionesAnalistaVO>();
	
		ArrayList<HiringsResponse> data = __getHiringsPerMonthServiceData(this.generateRequest(query));
		ContratacionesAnalistaVO tmp = null;
		if(data!= null) {
			for(HiringsResponse e:data) {
				tmp = new ContratacionesAnalistaVO(e);
				list.add(tmp);
			}
		}

		return list ;
	}
	
	
	@Override
	public abstract ArrayList<RecruitmentSourcesResponse> __getRecruitmentSourceServiceData(RequestBean<QueryDTO> dto) throws GenericStatusException;
	
	public LinkedList<FuentesReclutamientoVO> getRecruitmentSourcesData(QueryDTO query) throws GenericStatusException{
		
		
		LinkedList<FuentesReclutamientoVO> list = new LinkedList<FuentesReclutamientoVO>();
		ArrayList<RecruitmentSourcesResponse> data = __getRecruitmentSourceServiceData(this.generateRequest(query));
		FuentesReclutamientoVO tmp = null;
		if(data!= null) {
			for(RecruitmentSourcesResponse e:data) {
				tmp = new FuentesReclutamientoVO(e);
				list.add(tmp);
			}
		}

		return list ;
	}
	@Override
	public abstract ArrayList<InterviewTypeResponse> __getInterviewsTypeServiceData(RequestBean<QueryDTO> dto) throws GenericStatusException;
	public LinkedList<TiposEntrevistaVO> getInterviewTypesData(QueryDTO query) throws GenericStatusException{
		LinkedList<TiposEntrevistaVO> list = new LinkedList<TiposEntrevistaVO>();
		
		ArrayList<InterviewTypeResponse> data = __getInterviewsTypeServiceData(this.generateRequest(query));
		TiposEntrevistaVO tmp = null;
		if(data!= null) {
			for(InterviewTypeResponse e:data) {
				tmp = new TiposEntrevistaVO(e);
				list.add(tmp);
			}
		}
		
		return list;
	}
}