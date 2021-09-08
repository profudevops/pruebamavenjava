package com.mx.profuturo.bolsa.web.controller.common;

import com.mx.profuturo.bolsa.model.service.BaseResponseBean;
import com.mx.profuturo.bolsa.model.service.EntityResponseBean;
import com.mx.profuturo.bolsa.model.service.auth.dto.BasicDTO;

public abstract class BaseController {


    //@Autowired
   // protected GrantingUserSettings userSettings;

    public BaseResponseBean buildSuccessResponse(){
        BaseResponseBean responseBean = new BaseResponseBean();
        responseBean.setSuccess(true);
        responseBean.setStatus("200");
        return responseBean;
    }


    public EntityResponseBean buildEntitySuccessResponse(Object entity){
        EntityResponseBean responseBean = new EntityResponseBean();
        responseBean.setSuccess(true);
        responseBean.setStatus("200");
        responseBean.setEntity(entity);
        return responseBean;
    }
    
    
    public EntityResponseBean buildEntityErrorResponse(Object entity){
        EntityResponseBean responseBean = new EntityResponseBean();
        responseBean.setSuccess(false);
        responseBean.setStatus("400");
        responseBean.setEntity(entity);
        return responseBean;
    }

    public String getCleanToken(String token){
        return token.replace("Bearer ","");
    }
    
    

    public BasicDTO buildBasicDTO(){
        BasicDTO dto = new BasicDTO();
        dto.setId(1);
        return dto;
    }
}
