package com.mx.profuturo.bolsa.util.mockclient;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.mx.profuturo.bolsa.model.restclient.RequestBean;
import org.springframework.core.io.Resource;

/**
 * Created by Julio Arredondo on 04/07/2016.
 */
public interface MockClient {
    void setErrorHelper(MockHelper errorHelper);

    void exceptionMethod()throws JsonParseException;

    <T> T getMock(Resource mockFile, Class<T> responseType) throws Exception;

    public String getJsonRequest(RequestBean<?> request) throws JsonProcessingException;

    void buildMockFile(Object response, String fileName)throws JsonProcessingException;
}
