package com.mx.profuturo.bolsa.util.restclient;

import com.mx.profuturo.bolsa.model.recruitment.service.dto.InterviewAppointmentDTO;
import com.mx.profuturo.bolsa.model.restclient.RequestBean;
import com.mx.profuturo.bolsa.model.service.areasinteres.dto.GenericTextResponseBean;
import org.springframework.http.HttpMethod;

import java.util.List;

/**
 * Created by luism on 17/05/2016.
 */
public interface RestClient {

    <T> T exetuteOperation(RequestBean<?> requestBean, Class<T> responseType, HttpMethod httpMethod) throws Exception;
    <T> T exetuteGET(RequestBean<?> requestBean, Class<T> responseType) throws Exception;
    <T> T exetutePOST(RequestBean<?> requestBean, Class<T> responseType) throws Exception;
    <T> T exetutePUT(RequestBean<?> requestBean, Class<T> responseType) throws Exception;
    <T> T exetuteDELETE(RequestBean<?> requestBean, Class<T> responseType) throws Exception;
    <T> List<T> exetuteOperationList(RequestBean<?> requestBean, Class<T> responseType, HttpMethod httpMethod) throws Exception;
    <T> List<T> exetuteGETList(RequestBean<?> requestBean, Class<T> responseType) throws Exception;
    <T> List<T> exetutePOSTList(RequestBean<?> requestBean, Class<T> responseType) throws Exception;
    /*
    <T> T exetuteTRACE(RequestBean<?> requestBean, Class<T> responseType) throws ServiceException, Exception;
    <T> T exetuteHEAD(RequestBean<?> requestBean, Class<T> responseType) throws ServiceException, Exception;*/
    
	String exetuteRawPOST(RequestBean<?> genericRequestBean) throws Exception;


}
