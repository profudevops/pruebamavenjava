package com.mx.profuturo.bolsa.web.controller.common;

import com.mx.profuturo.bolsa.model.role.CorporateRoles;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

public interface MockControl {

    @RequestMapping(value="doMock", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @Secured(CorporateRoles.ADMN)
    String  generateMock();
}
