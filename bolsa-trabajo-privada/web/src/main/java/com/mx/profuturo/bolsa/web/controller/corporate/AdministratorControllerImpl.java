package com.mx.profuturo.bolsa.web.controller.corporate;

import com.mx.profuturo.bolsa.model.service.BaseResponseBean;
import com.mx.profuturo.bolsa.web.controller.common.BaseController;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller("crpAdmnControl")
@Scope("request")
public class AdministratorControllerImpl extends BaseController
        implements AdministratorController{


    public @ResponseBody
    @RequestMapping(value="/corporate/administrator/assign_vacancy_to", method = RequestMethod.POST)
    BaseResponseBean assignVacancyTo(){
        return this.buildSuccessResponse();
    }

}

