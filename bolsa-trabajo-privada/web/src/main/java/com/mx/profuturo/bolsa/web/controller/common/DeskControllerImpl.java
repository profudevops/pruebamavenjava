package com.mx.profuturo.bolsa.web.controller.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mx.profuturo.bolsa.configuration.TokenService;
import com.mx.profuturo.bolsa.model.desk.dto.DeskDTO;
import com.mx.profuturo.bolsa.model.desk.vo.EscritorioVO;
import com.mx.profuturo.bolsa.model.service.EntityResponseBean;
import com.mx.profuturo.bolsa.model.service.auth.response.UserCredentialsVO;
import com.mx.profuturo.bolsa.service.desk.DeskService;
import com.mx.profuturo.bolsa.util.exception.custom.GenericStatusException;

@Controller("deskControl")
@Scope("request")
public class DeskControllerImpl extends BaseController {

    @Autowired
    DeskService deskService;


	@Autowired
	private TokenService tokenService;
	
    @CrossOrigin
    public @ResponseBody
    @RequestMapping(value="/escritorio/obtener", method = RequestMethod.GET)
    EntityResponseBean<EscritorioVO> getDesk(@RequestHeader("Authorization") String token)throws GenericStatusException {
        DeskDTO dto = new DeskDTO();
        UserCredentialsVO user = tokenService.getUserFromToken(this.getCleanToken(token));
        dto.setRole(user.getRole());
        dto.setUserId(user.getId());
        EscritorioVO vo = deskService.getDeskData(dto);
        return this.buildEntitySuccessResponse(vo);
    }

}
