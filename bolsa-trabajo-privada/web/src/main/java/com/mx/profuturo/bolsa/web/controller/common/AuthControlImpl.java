package com.mx.profuturo.bolsa.web.controller.common;

import com.mx.profuturo.bolsa.configuration.TokenService;
import com.mx.profuturo.bolsa.model.desk.vo.EscritorioVO;
import com.mx.profuturo.bolsa.model.graphics.vo.SingleKPIWidgetVO;
import com.mx.profuturo.bolsa.model.service.BaseResponseBean;
import com.mx.profuturo.bolsa.model.service.EntityResponseBean;
import com.mx.profuturo.bolsa.model.service.auth.dto.UserAuthDTO;
import com.mx.profuturo.bolsa.model.service.auth.response.UserCredentialsVO;
import com.mx.profuturo.bolsa.model.service.auth.response.UserDataVO;
import com.mx.profuturo.bolsa.model.service.sample.UserDataBean;

import com.mx.profuturo.bolsa.service.auth.AuthService;
import com.mx.profuturo.bolsa.util.exception.custom.GenericStatusException;
import com.mx.profuturo.bolsa.util.exception.custom.LoginErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletResponse;

@CrossOrigin
@Controller("authControl")
@Scope("request")
public class AuthControlImpl extends BaseController {

    @Autowired
    AuthService authService;

    @Autowired
    TokenService tokenService;


    @CrossOrigin
    public @ResponseBody
    @RequestMapping(value="login", method = RequestMethod.POST)
    EntityResponseBean<UserDataVO> doLogin (@RequestBody UserAuthDTO userAuth,HttpServletResponse response)throws LoginErrorException {
        String decryptedPwd = authService.decryptPassword(userAuth.getPassword());
        if(null == decryptedPwd){
            throw new LoginErrorException();
        }
        else{
            userAuth.setPassword(decryptedPwd);
        }
        //user ldap autentication
        if(authService.validateUserPatterns(userAuth)){
            UserCredentialsVO userCredentialsVO = authService.startUserSession(userAuth);
            UserDataVO userDataVO = authService.getUserBasicData(userCredentialsVO);
            String result = tokenService.createToken(userCredentialsVO);
            response.addHeader("token", result);
            userDataVO.setToken(result);
            return this.buildEntitySuccessResponse(userDataVO);
        }

        throw new LoginErrorException();
    }


    @CrossOrigin
    public @ResponseBody
    @RequestMapping(value="tiempo-restante", method = RequestMethod.GET)
    EntityResponseBean<UserDataVO> getRemainingTime (@RequestHeader("token") String token)throws GenericStatusException {
        Long time = tokenService.getTokenRemainTime(token);
        return this.buildEntitySuccessResponse(time);
    }

    @CrossOrigin
    public @ResponseBody
    @RequestMapping(value="obtener-escritorio", method = RequestMethod.GET)
    EntityResponseBean<EscritorioVO> escritorio ()throws GenericStatusException {
        EscritorioVO vo = new EscritorioVO();
        vo.setCodigo("Admin");
        SingleKPIWidgetVO kp1 = new SingleKPIWidgetVO();
    	/*
    	 * type: 'widget',
		    title: 'Candidatos Postulados',
		    value: 6,
		    status: 'complete',
    	 */
        kp1.setTitle("Candidatos Postulados");
        kp1.setValue(6);
        kp1.setStatus("complete");
        vo.getWidgets().add(kp1);
        return this.buildEntitySuccessResponse(vo);
    }

    @CrossOrigin
    public @ResponseBody
    @RequestMapping(value="renovar-token", method = RequestMethod.GET)
    EntityResponseBean<Boolean> refreshToken (@RequestHeader("token") String token, HttpServletResponse response)throws GenericStatusException {
        String newToken = tokenService.refreshToken(token);
        response.addHeader("token", newToken);
        return this.buildEntitySuccessResponse(true);
    }


}

