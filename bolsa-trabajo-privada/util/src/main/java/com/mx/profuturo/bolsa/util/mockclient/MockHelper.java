package com.mx.profuturo.bolsa.util.mockclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

/**
 * Created by Julio Arredondo on 28/07/2016.
 */
@Component
@Scope("request")
public class MockHelper {

    @Value("classpath:/mock/generic/404.json")
    protected Resource mockGenericErrorFile;

    @Value("classpath:/mock/generic/500.json")
    protected Resource errorFile500;

    @Value("classpath:/mock/generic/404.json")
    protected Resource errorFile404;

    protected String errorCode;

    protected Boolean forceAnyError;
    {
        forceAnyError = false;
    }

    public void forceError(String errorCode){
        this.errorCode = errorCode;
        this.forceAnyError = true;
    }

    public Boolean shouldDoErrorStream(){
        return this.forceAnyError;
    }

    public Resource getMockErrorFile(){
        Resource errorFile = null;
        if("404".equals(this.errorCode)){
            errorFile = errorFile404;
        }else if("500".equals(this.errorCode)){
            errorFile = errorFile500;
        }
        return errorFile;
    }

    public void reset(){
        this.forceAnyError = false;
        this.errorCode = null;
    }



}
