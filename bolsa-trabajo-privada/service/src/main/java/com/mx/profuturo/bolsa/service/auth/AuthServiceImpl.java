package com.mx.profuturo.bolsa.service.auth;

import com.mx.profuturo.bolsa.model.restclient.ConnectionConfigBean;
import com.mx.profuturo.bolsa.model.restclient.RequestBean;
import com.mx.profuturo.bolsa.model.service.auth.dto.AuthRequestDTO;
import com.mx.profuturo.bolsa.model.service.auth.dto.BasicDTO;
import com.mx.profuturo.bolsa.model.service.auth.dto.SessionRegisterDTO;
import com.mx.profuturo.bolsa.model.service.auth.dto.UserAuthDTO;
import com.mx.profuturo.bolsa.model.service.auth.response.UserAuthResponse;
import com.mx.profuturo.bolsa.model.service.auth.response.UserCredentialsVO;
import com.mx.profuturo.bolsa.model.service.sample.UserInfoRequestBean;
import com.mx.profuturo.bolsa.model.service.sample.UserInfoResponseBean;
import com.mx.profuturo.bolsa.util.exception.custom.GenericStatusException;
import com.mx.profuturo.bolsa.util.exception.custom.LoginErrorException;
import com.mx.profuturo.bolsa.util.restclient.RestClient;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;

@Service
@Scope("request")
@Profile({"dev", "test", "prod"})
public class AuthServiceImpl extends AuthServiceBase{

    private static final Pattern contrasenaRegex = Pattern.compile("^(?=(.*[a-zA-Z\\u00f1\\u00d1]){4})" +  "(?=(.*[@#_\\-/*$¡¿=+]){2})(?=(.*\\d){2})" +"[a-zA-Z\\u00f1\\u00d1@#_\\-/*$¡¿=+\\d]{12,16}$");

    private static final Pattern usuarioRegex = Pattern.compile("^[a-zA-Z\\d]{6,8}$");

    @Value("${ws.login}")
    private String login;

    @Value("${ws.bolsatrabajo.port}")
    private String port;

    @Value("${ws.bolsatrabajo.server}")
    private String url;

    @Autowired
    RestClient restClient;

    @Override
    protected String __callRegisterSession(SessionRegisterDTO dto) {
        return null;
    }

    @Override
    protected UserCredentialsVO __callAuthenticateUser(RequestBean<UserAuthDTO> genericRequestBean) throws LoginErrorException {
        genericRequestBean.setConnectionConfigBean(new ConnectionConfigBean(url,port,login));
        UserAuthResponse serviceResponse = null;
        UserCredentialsVO response = new UserCredentialsVO();


        try {
            serviceResponse = restClient.exetutePOST(genericRequestBean, UserAuthResponse.class);
        }
             catch (Exception e) {
        throw new LoginErrorException();
        }
        if(serviceResponse.getNumeroEmpleado()==null || serviceResponse.getRol()==null){
            throw new LoginErrorException();
        }
            response.setId(serviceResponse.getNumeroEmpleado());
            response.setRole(serviceResponse.getRol());
            response.setFirstName(serviceResponse.getNombreCompleto());

            return response;




    }

    @Override
    protected UserInfoResponseBean callAuthService(RequestBean<UserInfoRequestBean> genericRequestBean) throws GenericStatusException {
        return null;
    }

    @Override
    public boolean validateUserPatterns(UserAuthDTO dto) throws LoginErrorException{
        if ( !contrasenaRegex.matcher(dto.getPassword()).matches()) {
            throw new LoginErrorException();
        }

        if ( !usuarioRegex.matcher(dto.getUsuario()).matches()) {
            throw new LoginErrorException();
        }

        return true;

    }
}
