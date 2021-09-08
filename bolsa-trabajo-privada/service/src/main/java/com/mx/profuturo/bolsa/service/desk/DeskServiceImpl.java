package com.mx.profuturo.bolsa.service.desk;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.mx.profuturo.bolsa.model.desk.vo.EscritorioVO;
import com.mx.profuturo.bolsa.model.recruitment.dto.GenericStageQueryDTO;
import com.mx.profuturo.bolsa.model.restclient.ConnectionConfigBean;
import com.mx.profuturo.bolsa.model.restclient.RequestBean;
import com.mx.profuturo.bolsa.model.vo.common.CatalogoVO;
import com.mx.profuturo.bolsa.util.exception.custom.GenericStatusException;
import com.mx.profuturo.bolsa.util.restclient.RestClient;

@Service
@Scope("request")
@Profile({"dev", "test", "prod"})
public class DeskServiceImpl extends DeskServiceBase {
	
	
	@Autowired
    RestClient restClient;


    @Value("${ws.bolsatrabajo.server}")
    private String url;

    @Value("${ws.bolsatrabajo.port}")
    private String port;
    @Value("${ws.escritorio.obtenerRecCorp}")
	private String getRecruiterCorpData;
	@Value("${ws.escritorio.obtenerAdmnCorp}")
	private String getAdministratorCorpData;
	@Value("${ws.escritorio.obtenerRecCtsCorp}")
	private String getCustomerRecruiterCorpData;
	@Value("${ws.escritorio.obtenerAdmnCom}")
	private String getAdministratorCommData;
	@Value("${ws.escritorio.obtenerConsCom}")
	private String getCommConsultorData;
	@Value("${ws.escritorio.obtenerAnalCom}")
	private String getCommAnalystData;
	@Value("${ws.escritorio.obtenerObsComm}")
	private String getCommObserver;
	@Value("${ws.escritorio.obtenerObsCorp}")
	private String getObserverCorpData;

	

	@Override
	public RecruiterCorpDeskResponse __getRecruiterCorpData(RequestBean<String> request) throws GenericStatusException {
		
		request.setConnectionConfigBean(new ConnectionConfigBean(url, port, getRecruiterCorpData));
		RecruiterCorpDeskResponse response = null;
        try{
            response = restClient.exetuteGET(request, RecruiterCorpDeskResponse.class);
            //serviceResponse =  response.getFiltros();

        }catch (Exception e){
            throw new GenericStatusException(e);
        }
        return response;
	}

	@Override
	public AdministratorCorpDeskResponse __getAdministratorCorpData(RequestBean<String> request) throws GenericStatusException {
		request.setConnectionConfigBean(new ConnectionConfigBean(url, port, getAdministratorCorpData));
		AdministratorCorpDeskResponse response = null;
        try{
            response = restClient.exetuteGET(request, AdministratorCorpDeskResponse.class);
            //serviceResponse =  response.getFiltros();

        }catch (Exception e){
            throw new GenericStatusException(e);
        }
        return response;
	}

	@Override
	public CustomerRecruiterCorpDeskResponse __getCustomerRecruiterCorpData(RequestBean<String> request) throws GenericStatusException {
		request.setConnectionConfigBean(new ConnectionConfigBean(url, port, getCustomerRecruiterCorpData));
		CustomerRecruiterCorpDeskResponse response = null;
        try{
            response = restClient.exetuteGET(request, CustomerRecruiterCorpDeskResponse.class);
            //serviceResponse =  response.getFiltros();

        }catch (Exception e){
            throw new GenericStatusException(e);
        }
        return response;
	}

	@Override
	public ObserverCorpDeskResponse __getObserverCorpData(RequestBean<String> request) throws GenericStatusException {
		request.setConnectionConfigBean(new ConnectionConfigBean(url, port, getObserverCorpData));
		ObserverCorpDeskResponse response = null;
        try{
            response = restClient.exetuteGET(request, ObserverCorpDeskResponse.class);
            //serviceResponse =  response.getFiltros();

        }catch (Exception e){
            throw new GenericStatusException(e);
        }
        return response;
	}

	@Override
	public AdministratorCommDeskResponse __getAdministratorCommData(RequestBean<String> request) throws GenericStatusException {
		request.setConnectionConfigBean(new ConnectionConfigBean(url, port, getAdministratorCommData));
		AdministratorCommDeskResponse response = null;
        try{
            response = restClient.exetuteGET(request, AdministratorCommDeskResponse.class);
            //serviceResponse =  response.getFiltros();

        }catch (Exception e){
            throw new GenericStatusException(e);
        }
        return response;
	}

	@Override
	public ConsultorCommDeskResponse __getCommConsultorData(RequestBean<String> request) throws GenericStatusException {
		request.setConnectionConfigBean(new ConnectionConfigBean(url, port, getCommConsultorData));
		ConsultorCommDeskResponse response = null;
        try{
            response = restClient.exetuteGET(request, ConsultorCommDeskResponse.class);
            //serviceResponse =  response.getFiltros();

        }catch (Exception e){
            throw new GenericStatusException(e);
        }
        return response;
	}

	@Override
	public AnalystCommDeskResponse __getCommAnalystData(RequestBean<String> request) throws GenericStatusException {
		request.setConnectionConfigBean(new ConnectionConfigBean(url, port, getCommAnalystData));
		AnalystCommDeskResponse response = null;
        try{
            response = restClient.exetuteGET(request, AnalystCommDeskResponse.class);
            //serviceResponse =  response.getFiltros();

        }catch (Exception e){
            throw new GenericStatusException(e);
        }
        return response;
	}

	@Override
	public ObserverCommDeskResponse __getCommObserver(RequestBean<String> request) throws GenericStatusException {
		request.setConnectionConfigBean(new ConnectionConfigBean(url, port, getCommObserver));
		ObserverCommDeskResponse response = null;
        try{
            response = restClient.exetuteGET(request, ObserverCommDeskResponse.class);
            //serviceResponse =  response.getFiltros();

        }catch (Exception e){
            throw new GenericStatusException(e);
        }
        return response;
	}


}
