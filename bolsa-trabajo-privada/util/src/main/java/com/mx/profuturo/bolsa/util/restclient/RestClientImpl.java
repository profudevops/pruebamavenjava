package com.mx.profuturo.bolsa.util.restclient;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.mx.profuturo.bolsa.model.restclient.RequestBean;
import com.mx.profuturo.bolsa.model.service.BaseResponseBean;
import com.mx.profuturo.bolsa.model.service.candidates.dto.CandidateInfoRequestBean;
import com.mx.profuturo.bolsa.util.converter.JaxbJacksonObjectMapper;
import com.mx.profuturo.bolsa.util.exception.custom.ServiceException;

/**
 * Created by luism on 17/05/2016.
 */

@Component
@Scope("request")
public class RestClientImpl implements RestClient {

    @Value("${ws.bolsatrabajo.user}")
    private String user;
    //private String user = "rwspraxisp";

    @Value("${ws.bolsatrabajo.password}")
    private String password;

    @Value("${ws.bolsatrabajo.usar-credenciales}")
    private Boolean useCredentials;

    //private String password = "Pr4x1s#u5R";


    @Autowired
    private RestTemplate restTemplate;


    private final JaxbJacksonObjectMapper mapper = new JaxbJacksonObjectMapper();
    private static final Logger LOGGER = LoggerFactory.getLogger(RestClientImpl.class);

    public RestClientImpl() {
        /*contructor Default*/
    }

    @Override
    public <T> T exetuteOperation(RequestBean<?> requestBean, Class<T> responseType, HttpMethod httpMethod) throws Exception{
        // this.isBrokerPetition = requestBean.isBrokerService();
        HttpEntity entity = this.getEntity(requestBean,httpMethod);
        ResponseEntity response =  null;
        T t = null;
        try {
        	if(requestBean.getPayload() instanceof CandidateInfoRequestBean){
        		setTimeoutAltered(restTemplate, 1200000); // con 5 minutos no funciono en prod (300000), se aumenta a 20 minutos
        	}
            response = restTemplate.exchange(requestBean.getConnectionConfigBean().getAsURL(), httpMethod, entity, String.class, requestBean.getUriVariables());
            t = this.mapper.readValue((String)response.getBody(), responseType);
            if (t instanceof BaseResponseBean 
            		&& null != ((BaseResponseBean)t).getErrorCode()) {
            	handleAsProfuturoException(response, BaseResponseBean.class);
            }
        }
        catch (RestClientException e) {
            LOGGER.error("ServiceException",  e);
           // throw new ServiceException("Lo sentimos, el sistema ha tenido un error y no se puede completar tu petición, por favor inténtalo más tarde.",e);
           handleAsProfuturoException(response, BaseResponseBean.class);
           
        }finally{
        	if(requestBean.getPayload() instanceof CandidateInfoRequestBean){
        		setTimeout(restTemplate);
        	}
        }
        return t;
    }
    
    /**
     * TimeOut alterado
     * @param restTemplate
     */
    private void setTimeoutAltered(RestTemplate restTemplate, int timeout) {
    	LOGGER.info("Se procede a alterar el timeout y readtimeout a " + timeout +" milisegundos");
        //Explicitly setting ClientHttpRequestFactory instance to     
        //SimpleClientHttpRequestFactory instance to leverage 
        //set*Timeout methods
    	SimpleClientHttpRequestFactory rf = new SimpleClientHttpRequestFactory();
    	rf.setReadTimeout(timeout);
        rf.setConnectTimeout(timeout);
        restTemplate.setRequestFactory(new BufferingClientHttpRequestFactory(rf));
        LOGGER.info("Se altero el timeout y readtimeout a " + timeout +" milisegundos");
        
    }
    /**
     * TimeOut restablecido
     * @param restTemplate
     */
    private void setTimeout(RestTemplate restTemplate) {
    	LOGGER.info("Se procede a alterar el timeout y readtimeout a default");
        restTemplate.setRequestFactory(new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));
        LOGGER.info("Se altero el timeout y readtimeout a default");
    }
    
    
    private <T> void handleAsProfuturoException(ResponseEntity response,Class<T> responseType) throws ServiceException, JsonParseException, JsonMappingException, IOException {
		// TODO Auto-generated method stub
    	
    	BaseResponseBean t = (BaseResponseBean) this.mapper.readValue((String)response.getBody(),responseType);
    	if(t.getErrorCode() == 404) {
    		LOGGER.error("Inicio de manejo de excepción Profuturo");
    		Exception e = new Exception(t.getRootErrorMessage());
    		throw new ServiceException(t.getRootErrorMessage(),e);
    	}else {
    		LOGGER.error("imposible de manejo de excepción Profuturo");
    		Exception e = new Exception("Error procesando la solicitud");
    		throw new ServiceException("Error procesando la solicitud",e);
    	}
    	
    	
	}

	public String exetuteRawOperation(RequestBean<?> requestBean, HttpMethod httpMethod) throws Exception{
        // this.isBrokerPetition = requestBean.isBrokerService();
        HttpEntity entity = this.getEntity(requestBean,httpMethod);
        String response;
		//ResponseEntity response =  null;
        try {
        	
        	
        	RestTemplate restTemplate2 = new RestTemplate();
            restTemplate2.setErrorHandler(new RestTemplateResponseErrorHandler());
   /*
		response = restTemplate.exchange(
            		requestBean.getConnectionConfigBean().getAsURL()
          
            		, HttpMethod.POST, entity, String.class, requestBean.getUriVariables());
*/         
            
            response = restTemplate2.postForObject(
            		requestBean.getConnectionConfigBean().getAsURL()
            		//"https://julian.free.beeceptor.com/julian"
            		,entity, String.class);
      
         
        }
        catch (RestClientException e) {
        	
        	
            LOGGER.error("ServiceException",  e);
            String m = (!"Internal Server Error".equals(e.getCause().getMessage()))?e.getCause().getMessage():e.getMessage();
            throw new ServiceException(m,e);
        }
        return response;
        //return response.getBody();
    }
    /*

    private String buildServiceUrl(String uri){
        StringBuilder sb = new StringBuilder();
        if(!isBrokerPetition){
            sb.append(endPoint).append(":").append(port).append(uri);
        }else{
            sb.append(brokerEndPoint).append(":").append(brokerPort).append(uri);
        }
        return sb.toString();
    }

    */

    @Override
    public <T> T exetuteGET(RequestBean<?> requestBean, Class<T> responseType) throws Exception{
         return this.exetuteOperation(requestBean,responseType,HttpMethod.GET);
    }

    @Override
    public <T> T exetutePOST(RequestBean<?> requestBean, Class<T> responseType) throws Exception {
        return this.exetuteOperation(requestBean,responseType,HttpMethod.POST);
    }

    @Override
    public <T> T exetutePUT(RequestBean<?> requestBean, Class<T> responseType) throws Exception {
        return this.exetuteOperation(requestBean,responseType,HttpMethod.PUT);
    }

    @Override
    public <T> T exetuteDELETE(RequestBean<?> requestBean, Class<T> responseType) throws Exception {
        return this.exetuteOperation(requestBean,responseType,HttpMethod.DELETE);
    }

    private HttpHeaders getHeaders(){
        HttpHeaders headers = new HttpHeaders();
        if(useCredentials){
            String auth = user + ":" + password;
            byte[] encodedAuth = Base64.encode(
                    auth.getBytes(Charset.forName("US-ASCII")) );
            String authHeader = "Basic " + new String( encodedAuth );
            headers.set( "Authorization", authHeader );
        }
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        return headers;
    }

    private HttpEntity getEntity(RequestBean<?> requestBean, HttpMethod httpMethod) throws ServiceException{
        validateUriAttributes(requestBean);
        HttpEntity<?> entity = new HttpEntity(this.getHeaders());
        if(!httpMethod.equals(httpMethod.GET)){
            if(null != requestBean.getPayload()){
                entity = new HttpEntity(requestBean.getPayload(),this.getHeaders());
            }else{
                throw new ServiceException("Error: payload can not be null");
            }
        }
        return entity;
    }

    private RequestBean<?> validateUriAttributes(RequestBean<?> requestBean){
        if(null == requestBean.getUriVariables()){
            Map<String,?> attributes = new HashMap<String, String>();
            requestBean.setUriVariables(attributes);
        }
        return requestBean;
    }

    @Override
    public <T> List<T> exetuteOperationList(RequestBean<?> requestBean, Class<T> responseType, HttpMethod httpMethod) throws Exception{
        // this.isBrokerPetition = requestBean.isBrokerService();
        HttpEntity entity = this.getEntity(requestBean,httpMethod);
        ResponseEntity response =  null;
        List<T> t = null;
        try {
            response = restTemplate.exchange(requestBean.getConnectionConfigBean().getAsURL(), httpMethod, entity, String.class, requestBean.getUriVariables());
            t = this.mapper.readValue((String)response.getBody(), mapper.getTypeFactory().constructCollectionType(List.class, responseType));
        }
        catch (RestClientException e) {
            LOGGER.error("ServiceException",  e);
            throw new ServiceException("Lo sentimos, el sistema ha tenido un error y no se puede completar tu petición, por favor inténtalo más tarde.",e);

        }
        return t;
    }


    @Override
    public <T> List<T> exetuteGETList(RequestBean<?> requestBean, Class<T> responseType) throws Exception{
        return this.exetuteOperationList(requestBean,responseType,HttpMethod.GET);
    }

    @Override
    public <T> List<T> exetutePOSTList(RequestBean<?> requestBean, Class<T> responseType) throws Exception {
        return this.exetuteOperationList(requestBean,responseType,HttpMethod.POST);
    }

	@Override
	public String exetuteRawPOST(RequestBean<?> genericRequestBean) throws Exception {
		return this.exetuteRawOperation(genericRequestBean, HttpMethod.POST);
	}

}
