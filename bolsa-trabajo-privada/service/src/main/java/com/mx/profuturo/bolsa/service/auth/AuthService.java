package com.mx.profuturo.bolsa.service.auth;

import com.mx.profuturo.bolsa.model.service.auth.dto.SessionRegisterDTO;
import com.mx.profuturo.bolsa.model.service.auth.dto.UserAuthDTO;
import com.mx.profuturo.bolsa.model.service.auth.response.UserCredentialsVO;
import com.mx.profuturo.bolsa.model.service.auth.response.UserDataVO;
import com.mx.profuturo.bolsa.model.service.sample.UserDataBean;
import com.mx.profuturo.bolsa.util.exception.custom.GenericStatusException;
import com.mx.profuturo.bolsa.util.exception.custom.LoginErrorException;

public interface AuthService {

    UserCredentialsVO startUserSession(UserAuthDTO dto)throws LoginErrorException;

    UserDataVO getUserBasicData(UserCredentialsVO userCredentialsVO);

    boolean validateUserPatterns(UserAuthDTO dto) throws LoginErrorException;

    String registerSession(String Id);

    UserCredentialsVO authenticateUser(UserAuthDTO dto)throws GenericStatusException;

    UserDataBean authUser(String userDate) throws GenericStatusException, com.mx.profuturo.bolsa.util.exception.custom.GenericStatusException;

    String decryptPassword(String encryptedPwd);
}
