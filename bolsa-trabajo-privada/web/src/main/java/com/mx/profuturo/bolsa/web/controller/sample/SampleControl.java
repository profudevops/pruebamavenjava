package com.mx.profuturo.bolsa.web.controller.sample;

import com.mx.profuturo.bolsa.model.service.sample.SamplePojo;
import com.mx.profuturo.bolsa.model.vo.common.UserInfoVO;
import com.mx.profuturo.bolsa.util.exception.custom.GenericStatusException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

public interface SampleControl {

    String getMessage();

    ModelAndView getSampleModelAndVIew();

    @RequestMapping(value="app/getSamplePojo", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    SamplePojo getSamplePojo();

    @RequestMapping(value = "getUserInfo", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    UserInfoVO getUserInfo() throws GenericStatusException;

    @RequestMapping(value = "getUserToken", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    String saveUser() throws GenericStatusException;
}
