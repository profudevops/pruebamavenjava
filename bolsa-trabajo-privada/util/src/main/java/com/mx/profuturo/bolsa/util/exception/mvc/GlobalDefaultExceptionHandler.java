package com.mx.profuturo.bolsa.util.exception.mvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mx.profuturo.bolsa.model.exception.CustomErrorResponse;
import com.mx.profuturo.bolsa.model.exception.FileEmptyException;
import com.mx.profuturo.bolsa.model.exception.FileTooBigException;
import com.mx.profuturo.bolsa.util.exception.custom.ConnectionException;
import com.mx.profuturo.bolsa.util.exception.custom.GenericStatusException;
import com.mx.profuturo.bolsa.util.exception.custom.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lUiSm on 5/4/16.
 */
@ControllerAdvice
public class  GlobalDefaultExceptionHandler extends SimpleMappingExceptionResolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalDefaultExceptionHandler.class);
    public static final String GLOBAL_ERROR = "global-error";
    public static final String DEFAULT_ERROR = "default-error";

    @Autowired
    private ApplicationContext context;

    public static final String DEFAULT_ERROR_VIEW = "modal-errors";

    @ResponseStatus(HttpStatus.GATEWAY_TIMEOUT)
    @ExceptionHandler(value = {ConnectionException.class})
    @RequestMapping(value = "/json", method = RequestMethod.GET, produces = "text/html")
    public ModelAndView getConnectionException(HttpServletRequest req, Exception e) throws JsonProcessingException {
        ModelAndView m = new ModelAndView();
        try{
            m.addObject("data",getDataResponseService(e));
        }catch(Exception ex){
            m.setViewName("session-logout");
            LOGGER.info("Error " + ex);
        }
        if( isAjax(req) ) {
            m.setViewName("session-logout   ");
        }
        return m;
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(value = {ServiceException.class})
    @RequestMapping(value = "/json", method = RequestMethod.GET, produces = "text/html")
    public ModelAndView getServiceException(HttpServletRequest req, Exception e) throws JsonProcessingException {
        ModelAndView m = new ModelAndView();
        try{
            m.addObject("data",getDataResponseService(e));
        }catch(Exception ex){
            m.setViewName(GLOBAL_ERROR);
            LOGGER.info("Error " + ex);
        }
        if( isAjax(req) ) {
            m.setViewName(DEFAULT_ERROR);
        }
        return m;
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(value = {GenericStatusException.class})
    @RequestMapping(value = "/json", method = RequestMethod.GET, produces = "text/html")
    public ModelAndView getGenericStatusException(HttpServletRequest req, Exception e) throws JsonProcessingException {
        ModelAndView m = new ModelAndView();
        try{
            m.addObject("data",getDataResponse(e));
        }catch(Exception ex){
            m.setViewName(GLOBAL_ERROR);
            LOGGER.info("Error " + ex);
        }
        if( isAjax(req) ) {
            m.setViewName(DEFAULT_ERROR);
        }
        return m;
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @ExceptionHandler(value = FileEmptyException.class)
    @RequestMapping(value = "/json", method = RequestMethod.GET, produces = "text/html")
    public ModelAndView getFileNotContectException(FileEmptyException ex, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(DEFAULT_ERROR);
        modelAndView.addObject("data", ex.getMessage());
        return modelAndView;
    }

    @ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(value = FileTooBigException.class)
    public ModelAndView getFileTooBig(FileTooBigException ex) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(DEFAULT_ERROR);
        modelAndView.addObject("data", ex.getMessage());
        return modelAndView;
    }

    private String getDataResponse(Exception e) throws Exception{
        CustomErrorResponse errorResponse = createCustomError(e);
        return encryptResponse(objectToJson(errorResponse));
    }

    private CustomErrorResponse createCustomError(Exception e){
        GenericStatusException ge = (GenericStatusException)e;

        CustomErrorResponse errorResponse = new CustomErrorResponse();
        errorResponse.setTitle(getEncodedData(ge.getCode()));
        errorResponse.setDescription(getEncodedData(ge.getMessage()));
        return errorResponse;
    }

    private String getDataResponseService(Exception e) throws Exception{
        ServiceException ge = (ServiceException)e;

        CustomErrorResponse errorResponse = new CustomErrorResponse();
        errorResponse.setTitle(getEncodedData("Error de Servicio"));
        errorResponse.setDescription(getEncodedData(ge.getMessage()));

        return encryptResponse(objectToJson(errorResponse));

    }

    private String encryptResponse(String json) {
        byte[] encodedBytes = Base64.encode(json.getBytes());
        return new String(encodedBytes);
    }

    private String objectToJson(CustomErrorResponse errorResponse) throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(errorResponse);
    }

    private static String getEncodedData(String str){

        List<Character> charArray = new ArrayList<Character>();
        List<Character> charArrayCoded = new ArrayList<Character>();
        char coded = 'A';
        int nChar = 0;

        for (char c : str.toCharArray()) {
            charArray.add(c);
        }

        for(int i = 0;i < charArray.size(); i++){
            nChar = (int)charArray.get(i);
            nChar = nChar + 3;
            coded = (char)nChar;
            charArrayCoded.add(coded);
        }

        StringBuilder nvCode = new StringBuilder(charArrayCoded.size());
        for(Character ch : charArrayCoded){
            nvCode.append(ch);
        }

        String nvoCoded = nvCode.toString();

        return nvoCoded;
    }

    //@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    //@ExceptionHandler(value = {Exception.class})
    //@RequestMapping(value = "/json", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> getException(HttpServletRequest req, Exception e) throws JsonProcessingException {

        CustomErrorResponse errorResponse = new CustomErrorResponse();
        errorResponse.setTitle("Response Entity Exception");
        errorResponse.setDescription(e.getMessage());

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(errorResponse);

        final HttpHeaders httpHeaders= new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity<String> responseJson = new ResponseEntity<String>(jsonString, httpHeaders, HttpStatus.OK);

        return responseJson;
    }

    private boolean isAjax(HttpServletRequest request) {
        return "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
    }
}
