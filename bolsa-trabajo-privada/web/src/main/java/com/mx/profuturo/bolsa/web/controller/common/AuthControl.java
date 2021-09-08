package com.mx.profuturo.bolsa.web.controller.common;

import com.mx.profuturo.bolsa.model.service.sample.UserDataBean;
import com.mx.profuturo.bolsa.util.exception.custom.GenericStatusException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public interface AuthControl {
    @RequestMapping("authUser")
    public ModelAndView authUser(@RequestParam String userDate, HttpServletResponse response) throws GenericStatusException;
}
