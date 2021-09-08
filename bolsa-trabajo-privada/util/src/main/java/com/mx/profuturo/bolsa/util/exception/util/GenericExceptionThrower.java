package com.mx.profuturo.bolsa.util.exception.util;

import com.mx.profuturo.bolsa.model.service.BaseResponseBean;
import com.mx.profuturo.bolsa.util.exception.custom.GenericStatusException;
import org.springframework.http.HttpStatus;

/**
 * Created by Julio Arredondo on 20/04/2017.
 */
public class GenericExceptionThrower {

    public static void validateServiceResponse(BaseResponseBean response) throws GenericStatusException {
        if(null != response){
            if (response.getStatus() != HttpStatus.OK.value()) {
                throw new GenericStatusException(String.valueOf(response.getStatus()),response.getStatusText());
            }
        }else{
            throw  new GenericStatusException("Error","Ocurrio un error en los servicios");
        }
    }

}
