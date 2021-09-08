package com.mx.profuturo.bolsa.service.auth;

import com.mx.profuturo.bolsa.model.restclient.RequestBean;
import com.mx.profuturo.bolsa.model.role.CommercialRoles;
import com.mx.profuturo.bolsa.model.role.CorporateRoles;
import com.mx.profuturo.bolsa.model.service.auth.dto.SessionRegisterDTO;
import com.mx.profuturo.bolsa.model.service.auth.dto.UserAuthDTO;
import com.mx.profuturo.bolsa.model.service.auth.response.UserCredentialsVO;
import com.mx.profuturo.bolsa.model.service.sample.UserInfoRequestBean;
import com.mx.profuturo.bolsa.model.service.sample.UserInfoResponseBean;
import com.mx.profuturo.bolsa.util.exception.custom.GenericStatusException;
import com.mx.profuturo.bolsa.util.exception.custom.LoginErrorException;
import com.mx.profuturo.bolsa.util.mockclient.MockClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
@Scope("request")
@Profile("mock")
public class AuthServiceMockImpl extends AuthServiceBase{


    @Autowired
    MockClient mockClient;

    @Value("classpath:/mock/common/user_info_response_bean.json")
    private Resource userInfoResource;

    @Override
    protected String __callRegisterSession(SessionRegisterDTO dto) {

        return dto.getCreationTime();
    }

    @Override
    protected UserCredentialsVO __callAuthenticateUser(RequestBean<UserAuthDTO> genericRequestBean) throws LoginErrorException{

        UserCredentialsVO vo = new UserCredentialsVO();
        UserAuthDTO dto  = genericRequestBean.getPayload();

        if("LIVI".equals(dto.getUsuario())){
            vo.setId(dto.getUsuario());
            vo.setRole(CorporateRoles.ADMN);
            vo.setFirstName("Livi");
            vo.setLastName("a");
            vo.setSecondLastName("b");
            vo.setBranch("CORP");
        }else if("CARLA".equals(dto.getUsuario())){

            vo.setId(dto.getUsuario());
            vo.setRole(CorporateRoles.RECRUITER);
            vo.setFirstName("Carla");
            vo.setLastName("B");
            vo.setSecondLastName("C");
            vo.setBranch("CORP");

        }else if("JESSY".equals(dto.getUsuario())){

            vo.setId(dto.getUsuario());
            vo.setRole(CorporateRoles.COSTUMER_RECRUITER);
            vo.setFirstName("JESSY");
            vo.setLastName("D");
            vo.setSecondLastName("E");
            vo.setBranch("CORP");

        }else if("CHANTAL".equals(dto.getUsuario())){

            vo.setId(dto.getUsuario());
            vo.setRole(CommercialRoles.ADMN);
            vo.setFirstName("CHANTAL");
            vo.setLastName("Arredondo");
            vo.setSecondLastName("Serrano");
            vo.setBranch("COM");

        }else if("STEPH".equals(dto.getUsuario())){

            vo.setId(dto.getUsuario());
            vo.setRole(CommercialRoles.CONSULTOR);
            vo.setFirstName("STEPH");
            vo.setLastName("Arredondo");
            vo.setSecondLastName("Serrano");
            vo.setBranch("COM");

        }else if("RICKY".equals(dto.getUsuario())) {
        	vo.setId(dto.getUsuario());
            vo.setRole(CommercialRoles.ANALYST);
            vo.setFirstName("RICKY");
            vo.setLastName("Arredondo");
            vo.setSecondLastName("Serrano");
            vo.setBranch("COM");
        }else if("JUAN".equals(dto.getUsuario())) {
        	vo.setId(dto.getUsuario());
            vo.setRole(CorporateRoles.OBSERVER);
            vo.setFirstName("Juan");
            vo.setLastName("Arredondo");
            vo.setSecondLastName("Serrano");
            vo.setBranch("CO");
        }else if("CARLOS".equals(dto.getUsuario())) {
        	vo.setId(dto.getUsuario());
            vo.setRole(CommercialRoles.OBSERVER);
            vo.setFirstName("Carlos");
            vo.setLastName("Arredondo");
            vo.setSecondLastName("Serrano");
            vo.setBranch("COM");
        }else if("ERIK".equals(dto.getUsuario())){
            vo.setId(dto.getUsuario());
            vo.setRole(CommercialRoles.RECRUITER);
            vo.setFirstName("Erik");
            vo.setLastName("Almaguer");
            vo.setSecondLastName("Fuentes");
            vo.setBranch("COM");

        }else if("ISRAEL".equals(dto.getUsuario())){
            vo.setId(dto.getUsuario());
            vo.setRole(CorporateRoles.MARKETING);
            vo.setFirstName("Israel");
            vo.setLastName("Almaguer");
            vo.setSecondLastName("Fuentes");
            vo.setBranch("CORP");
        }else if("ANA".equals(dto.getUsuario())){
            vo.setId(dto.getUsuario());
            vo.setRole(CommercialRoles.RECRUITER);
            vo.setFirstName("Ana");
            vo.setLastName("Almaguer");
            vo.setSecondLastName("Fuentes");
            vo.setBranch("COM");
        }
        else if("SANDRA".equals(dto.getUsuario())){
            vo.setId(dto.getUsuario());
            vo.setRole(CommercialRoles.METRIC_VIEWER);
            vo.setFirstName("Sandra");
            vo.setLastName("Almaguer");
            vo.setSecondLastName("Fuentes");
            vo.setBranch("COM");
        }
        else {
            throw new LoginErrorException();
        }


        return vo;
    }

    @Override
    protected UserInfoResponseBean callAuthService(RequestBean<UserInfoRequestBean> genericRequestBean) throws GenericStatusException {
        UserInfoResponseBean response = null;
        try {
            response = mockClient.getMock(userInfoResource, UserInfoResponseBean.class);
        }catch (Exception e){
            throw new GenericStatusException(e);
        }
        return response;
    }

    @Override
    public boolean validateUserPatterns(UserAuthDTO dto) throws LoginErrorException{

        return true;

    }
}

