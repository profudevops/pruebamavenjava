package com.mx.profuturo.bolsa.service.common;

import com.mx.profuturo.bolsa.model.vo.common.UserInfoVO;
import com.mx.profuturo.bolsa.util.exception.custom.GenericStatusException;

/**
 * Created by alejandro.hernandez on 10/05/2017.
 */
public interface UserDataService {
    void getUser(Integer id, UserInfoVO userInfo) throws GenericStatusException, com.mx.profuturo.bolsa.util.exception.custom.GenericStatusException;
}
