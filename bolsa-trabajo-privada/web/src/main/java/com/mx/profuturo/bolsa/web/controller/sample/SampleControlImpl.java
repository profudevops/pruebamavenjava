package com.mx.profuturo.bolsa.web.controller.sample;
import com.mx.profuturo.bolsa.configuration.TokenService;
import com.mx.profuturo.bolsa.model.service.sample.SamplePojo;
import com.mx.profuturo.bolsa.model.vo.common.UserInfoVO;
import com.mx.profuturo.bolsa.service.common.UserDataService;
import com.mx.profuturo.bolsa.util.exception.custom.GenericStatusException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@Scope("request")
public class SampleControlImpl implements SampleControl{


    UserDataService userDataService;


    private TokenService tokenService;

    @Autowired
    SampleControlImpl(TokenService tokenService,UserDataService userDataService){
        this.tokenService =tokenService;
        this.userDataService = userDataService;
    }

    @Override
    @RequestMapping(value="SpringBootController", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String  getMessage() {
        return "Ejemplo";
    }

    @Override
    @RequestMapping(value="SampleModelAndView")
    public ModelAndView getSampleModelAndVIew() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("SampleKey","Sample value");
        modelAndView.setViewName("sample-view");
        return modelAndView;
    }

    @Override
    @RequestMapping(value="getSamplePojo", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody SamplePojo  getSamplePojo() {
        SamplePojo samplePojo = new SamplePojo("description",12,3.14f,true);
        return samplePojo;
    }

    @Override
    @RequestMapping(value = "getUserInfo", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody UserInfoVO getUserInfo() throws GenericStatusException {
        UserInfoVO userInfoVO = new UserInfoVO();
        userDataService.getUser(1,userInfoVO);
        return userInfoVO;
    }


    @Override
    @RequestMapping(value = "getUserToken", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String saveUser() throws GenericStatusException {
        UserInfoVO userInfoVO = new UserInfoVO();
        userDataService.getUser(1,userInfoVO);
        return null; //tokenService.createToken(userInfoVO.getFirstName(),userInfoVO.getLastName(),userInfoVO.getSecondLastName());
    }

}
