package com.mx.profuturo.bolsa.web.controller.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mx.profuturo.bolsa.model.role.CorporateRoles;
import com.mx.profuturo.bolsa.model.service.auth.dto.UserAuthDTO;
import com.mx.profuturo.bolsa.util.mockclient.MockClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("mockControl")
@Scope("request")
public class MockControlImpl {



    private Object buildObject(){

        return userAuthDTO();
    }

    @Autowired
    MockClient mockClient;

    @Secured(CorporateRoles.ADMN)
    @RequestMapping(value="doMock", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String  generateMock() {

        Object obj = this.buildObject();

        try {
            this.mockClient.buildMockFile(obj, "json_mock");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "{success:true}";

    }


    private UserAuthDTO userAuthDTO(){
        UserAuthDTO obj = new UserAuthDTO();
        obj.setPassword("2323344rfsdsdsd_&dfsdsdsdssasa");
        obj.setUsuario("RFJCSPQS");
        return obj;
    }



}
