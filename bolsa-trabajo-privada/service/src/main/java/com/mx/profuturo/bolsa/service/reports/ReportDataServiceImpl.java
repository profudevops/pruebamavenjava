package com.mx.profuturo.bolsa.service.reports;

import java.util.ArrayList;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.mx.profuturo.bolsa.model.reports.dto.QueryDTO;
import com.mx.profuturo.bolsa.model.reports.response.FinalistHiredResponse;
import com.mx.profuturo.bolsa.model.reports.response.HiringsResponse;
import com.mx.profuturo.bolsa.model.reports.response.InterviewTypeResponse;
import com.mx.profuturo.bolsa.model.reports.response.RecruitmentSourcesResponse;
import com.mx.profuturo.bolsa.model.reports.response.RejectReasonsResponse;
import com.mx.profuturo.bolsa.model.restclient.ConnectionConfigBean;
import com.mx.profuturo.bolsa.model.restclient.RequestBean;
import com.mx.profuturo.bolsa.service.desk.CustomerRecruiterCorpDeskResponse;
import com.mx.profuturo.bolsa.util.exception.custom.GenericStatusException;
import com.mx.profuturo.bolsa.util.restclient.RestClient;

@Service
@Scope("request")
@Profile({"dev", "test", "prod"})
public class ReportDataServiceImpl extends ReportDataServiceBase {

	@Autowired
	private RestClient restClient;


	@Value("${ws.bolsatrabajo.server}")
	private String url;

	@Value("${ws.bolsatrabajo.port}")
	private String port;

	@Value("${ws.metricas.motivosRechazo}")	
	private String getRejectReasonsServiceData;

	@Value("${ws.metricas.etapas}")
	private String getRejectReasonsPerStageServiceData;
	
	
	@Value("${ws.metricas.motivosAbandono}")
	private String getAbandonmentReasonServiceData;

	@Value("${ws.metricas.finalistasContratados}")
	private String getFinalistVsHiredServiceData;

	@Value("${ws.metricas.candaditosNivel}")
	private String getPositionLevelServiceData;

	@Value("${ws.metricas.contrataciones}")
	private String getHiringsPerAnalystServiceData;

	@Value("${ws.metricas.fuentesReclutamiento}")
	private String getRecruitmentSourceServiceData;

	@Value("${ws.metricas.tiposEntrevista}")
	private String getInterviewsTypeServiceData;

	@Value("${ws.metricas.contratadosMes}")
	private String getHiringsPerMonthServiceData;
	
	    
	@Override
	public ArrayList<RejectReasonsResponse> __getRejectReasonsServiceData(RequestBean<QueryDTO> request) throws GenericStatusException {
		request.setConnectionConfigBean(new ConnectionConfigBean(url, port, getAbandonmentReasonServiceData));
		ArrayList<RejectReasonsResponse> response = null;
        try{
            response =  (ArrayList<RejectReasonsResponse>) restClient.exetutePOSTList(request, RejectReasonsResponse.class);
            //serviceResponse =  response.getFiltros();

        }catch (Exception e){
            throw new GenericStatusException(e);
        }
        return response;
	}
	
	
	
	@Override
	public ArrayList<RejectReasonsResponse> __getRejectReasonsPerStageServiceData(RequestBean<QueryDTO> request) throws GenericStatusException {
		request.setConnectionConfigBean(new ConnectionConfigBean(url, port, getRejectReasonsPerStageServiceData));
		ArrayList<RejectReasonsResponse> response = null;
        try{
            response =  (ArrayList<RejectReasonsResponse>) restClient.exetutePOSTList(request, RejectReasonsResponse.class);
            //serviceResponse =  response.getFiltros();

        }catch (Exception e){
            throw new GenericStatusException(e);
        }
        return response;
	}

	@Override
	public ArrayList<RejectReasonsResponse> __getAbandonmentReasonServiceData(RequestBean<QueryDTO> request) throws GenericStatusException {
		request.setConnectionConfigBean(new ConnectionConfigBean(url, port, getAbandonmentReasonServiceData));
		ArrayList<RejectReasonsResponse> response = null;
        try{
            response = (ArrayList<RejectReasonsResponse>) restClient.exetutePOSTList(request, RejectReasonsResponse.class);
            //serviceResponse =  response.getFiltros();

        }catch (Exception e){
            throw new GenericStatusException(e);
        }
        return response;
	}

	@Override
	public ArrayList<FinalistHiredResponse> __getFinalistVsHiredServiceData(RequestBean<QueryDTO> request) throws GenericStatusException {
		
		request.setConnectionConfigBean(new ConnectionConfigBean(url, port, getFinalistVsHiredServiceData));
		
		ArrayList<FinalistHiredResponse> response = null;
        try{
            response = (ArrayList<FinalistHiredResponse>) restClient.exetutePOSTList(request, FinalistHiredResponse.class);
            //serviceResponse =  response.getFiltros();

        }catch (Exception e){
            throw new GenericStatusException(e);
        }
        return response;
	}

	@Override
	public ArrayList<HiringsResponse> __getPositionLevelServiceData(RequestBean<QueryDTO> request) throws GenericStatusException {
		request.setConnectionConfigBean(new ConnectionConfigBean(url, port, getPositionLevelServiceData));
		
		ArrayList<HiringsResponse> response = null;
        try{
            response = (ArrayList<HiringsResponse>) restClient.exetutePOSTList(request, HiringsResponse.class);
            //serviceResponse =  response.getFiltros();

        }catch (Exception e){
            throw new GenericStatusException(e);
        }
        return response;
	}

	@Override
	public ArrayList<HiringsResponse> __getHiringsPerMonthServiceData(RequestBean<QueryDTO> request) throws GenericStatusException {
		
		request.setConnectionConfigBean(new ConnectionConfigBean(url, port, getHiringsPerMonthServiceData));
		
		
		ArrayList<HiringsResponse> response = null;
        try{
            response = (ArrayList<HiringsResponse>) restClient.exetutePOSTList(request, HiringsResponse.class);
            //serviceResponse =  response.getFiltros();

        }catch (Exception e){
            throw new GenericStatusException(e);
        }
        return response;
	}

	@Override
	public ArrayList<HiringsResponse> __getHiringsPerAnalystServiceData(RequestBean<QueryDTO> request) throws GenericStatusException {
		
		request.setConnectionConfigBean(new ConnectionConfigBean(url, port,getHiringsPerAnalystServiceData));
		
		ArrayList<HiringsResponse> response = null;
        try{
            response = (ArrayList<HiringsResponse>) restClient.exetutePOSTList(request, HiringsResponse.class);
            //serviceResponse =  response.getFiltros();

        }catch (Exception e){
            throw new GenericStatusException(e);
        }
        return response;
	}

	@Override
	public ArrayList<RecruitmentSourcesResponse> __getRecruitmentSourceServiceData(RequestBean<QueryDTO> request) throws GenericStatusException {
		request.setConnectionConfigBean(new ConnectionConfigBean(url, port,getRecruitmentSourceServiceData));
		
		ArrayList<RecruitmentSourcesResponse> response = null;
        try{
            response = (ArrayList<RecruitmentSourcesResponse>) restClient.exetutePOSTList(request, RecruitmentSourcesResponse.class);
            //serviceResponse =  response.getFiltros();

        }catch (Exception e){
            throw new GenericStatusException(e);
        }
        return response;
	}

	@Override
	public ArrayList<InterviewTypeResponse> __getInterviewsTypeServiceData(RequestBean<QueryDTO> request) throws GenericStatusException {

		request.setConnectionConfigBean(new ConnectionConfigBean(url, port,getInterviewsTypeServiceData));

		
		ArrayList<InterviewTypeResponse> response = null;
        try{
            response = (ArrayList<InterviewTypeResponse>) restClient.exetutePOSTList(request, InterviewTypeResponse.class);
            //serviceResponse =  response.getFiltros();

        }catch (Exception e){
            throw new GenericStatusException(e);
        }
        return response;
	}

}
