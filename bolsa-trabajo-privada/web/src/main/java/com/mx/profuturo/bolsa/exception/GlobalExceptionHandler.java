package com.mx.profuturo.bolsa.exception;


import com.mx.profuturo.bolsa.model.service.BaseResponseBean;
import com.mx.profuturo.bolsa.model.service.areasinteres.dto.GenericTextResponseBean;
import com.mx.profuturo.bolsa.util.exception.custom.GenericStatusException;
import com.mx.profuturo.bolsa.util.exception.custom.InterviewException;
import com.mx.profuturo.bolsa.util.exception.custom.LoginErrorException;
import com.mx.profuturo.bolsa.util.exception.custom.NoResultsException;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.annotation.Resource;
import java.io.FileNotFoundException;
import java.io.IOException;

@ControllerAdvice
@PropertySource("classpath:application.properties")
public class GlobalExceptionHandler {

    @Resource
    Environment environment;

    
    @ResponseBody
    @ExceptionHandler
    public ResponseEntity interviewException(InterviewException e) {
        BaseResponseBean response = new BaseResponseBean();
        response.setStatusText(e.getMessage());
        response.setStatus("500");
        response.setSuccess(false);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    
    
    @ResponseBody
    @ExceptionHandler
    public ResponseEntity serviceException(GenericStatusException e) {
        BaseResponseBean response = new BaseResponseBean();
        String m = (e.getMessage()== null)
        		?"Error al consumir el servicio. Inténtalo más tarde":
        			e.getMessage();
        response.setStatusText(m);
        response.setStatus("500");
        response.setSuccess(false);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseBody
    @ExceptionHandler
    public ResponseEntity uploadImageExcetion(IOException e) {
        BaseResponseBean response = new BaseResponseBean();
        response.setStatusText("Ocurrió un error durante la carga de imágenes");
        response.setStatus("500");
        response.setSuccess(false);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseBody
    @ExceptionHandler
    public ResponseEntity loginError(LoginErrorException e) {
        BaseResponseBean response = new BaseResponseBean();
        response.setStatusText("Datos de autenticación incorrectos");
        response.setStatus("401");
        response.setSuccess(false);
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ResponseBody
    @ExceptionHandler
    public ResponseEntity noRestults(NoResultsException e){
        BaseResponseBean response = new BaseResponseBean();
        response.setStatusText("No se encontró información");
        response.setStatus("404");
        response.setSuccess(false);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }




}
