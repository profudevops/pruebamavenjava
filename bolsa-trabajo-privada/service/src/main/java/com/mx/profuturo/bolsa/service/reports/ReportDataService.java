package com.mx.profuturo.bolsa.service.reports;

import java.util.ArrayList;
import java.util.LinkedList;

import com.mx.profuturo.bolsa.model.reports.dto.QueryDTO;
import com.mx.profuturo.bolsa.model.reports.response.FinalistHiredResponse;
import com.mx.profuturo.bolsa.model.reports.response.HiringsResponse;
import com.mx.profuturo.bolsa.model.reports.response.InterviewTypeResponse;
import com.mx.profuturo.bolsa.model.reports.response.RecruitmentSourcesResponse;
import com.mx.profuturo.bolsa.model.reports.response.RejectReasonsResponse;
import com.mx.profuturo.bolsa.model.reports.vo.data.ContratacionesAnalistaVO;
import com.mx.profuturo.bolsa.model.reports.vo.data.ContratacionesMesVO;
import com.mx.profuturo.bolsa.model.reports.vo.data.ContratacionesNivelPuestoVO;
import com.mx.profuturo.bolsa.model.reports.vo.data.FinalistasContratadosVO;
import com.mx.profuturo.bolsa.model.reports.vo.data.FuentesReclutamientoVO;
import com.mx.profuturo.bolsa.model.reports.vo.data.MotivosRechazoVO;
import com.mx.profuturo.bolsa.model.reports.vo.data.TiposEntrevistaVO;
import com.mx.profuturo.bolsa.model.restclient.RequestBean;
import com.mx.profuturo.bolsa.util.exception.custom.GenericStatusException;

public interface ReportDataService {

	LinkedList<MotivosRechazoVO> getRejectReasonsData(QueryDTO dto) throws GenericStatusException;

	LinkedList<MotivosRechazoVO> getAbandonmentReasonData(QueryDTO query) throws GenericStatusException;

	LinkedList<FinalistasContratadosVO> getFinalistVsHiredData(QueryDTO query)throws GenericStatusException;

	LinkedList<ContratacionesNivelPuestoVO> getPositionLevelHiringsdData(QueryDTO query)throws GenericStatusException;

	LinkedList<ContratacionesMesVO> getHiringsPerMonthData(QueryDTO query)throws GenericStatusException;

	LinkedList<ContratacionesAnalistaVO> getHiringsPerAnalystData(QueryDTO query)throws GenericStatusException;

	LinkedList<FuentesReclutamientoVO> getRecruitmentSourcesData(QueryDTO query)throws GenericStatusException;

	LinkedList<TiposEntrevistaVO> getInterviewTypesData(QueryDTO query)throws GenericStatusException;
	

	ArrayList<RejectReasonsResponse> __getRejectReasonsServiceData(RequestBean<QueryDTO> dto) throws GenericStatusException;

	ArrayList<RejectReasonsResponse> __getAbandonmentReasonServiceData(RequestBean<QueryDTO> dto)throws GenericStatusException;

	ArrayList<FinalistHiredResponse> __getFinalistVsHiredServiceData(RequestBean<QueryDTO> dto)throws GenericStatusException;

	ArrayList<HiringsResponse> __getPositionLevelServiceData(RequestBean<QueryDTO> dto)throws GenericStatusException;

	ArrayList<HiringsResponse> __getHiringsPerMonthServiceData(RequestBean<QueryDTO> dto)throws GenericStatusException;

	ArrayList<HiringsResponse> __getHiringsPerAnalystServiceData(RequestBean<QueryDTO> dto)throws GenericStatusException;

	ArrayList<RecruitmentSourcesResponse> __getRecruitmentSourceServiceData(RequestBean<QueryDTO> dto)throws GenericStatusException;

	ArrayList<InterviewTypeResponse> __getInterviewsTypeServiceData(RequestBean<QueryDTO> dto)throws GenericStatusException;

	ArrayList<RejectReasonsResponse> __getRejectReasonsPerStageServiceData(RequestBean<QueryDTO> request)
			throws GenericStatusException;

	LinkedList<MotivosRechazoVO> getRejectReasonsPerStageData(QueryDTO dto) throws GenericStatusException;

}
