package com.mx.profuturo.bolsa.configuration;

import com.mx.profuturo.bolsa.model.service.BaseResponseBean;
import com.mx.profuturo.bolsa.util.exception.custom.GenericStatusException;
import com.mx.profuturo.bolsa.util.exception.custom.LoginErrorException;
import com.mx.profuturo.bolsa.web.controller.common.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.annotation.Resource;

@ControllerAdvice
@PropertySource("classpath:application.properties")
public class GlobalException {

    @Resource
    Environment environment;


    @ResponseBody
    @ExceptionHandler
    public ResponseEntity loginError(LoginErrorException e) {

        BaseResponseBean response = new BaseResponseBean();
        response.setSuccess(false);
        response.setStatus("401");
        response.setStatusText("Error en login");

        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }


}
