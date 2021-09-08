package com.mx.profuturo.bolsa.service.common;

import com.mx.profuturo.bolsa.model.restclient.RequestBean;
import com.mx.profuturo.bolsa.model.service.sample.UserDataBean;
import com.mx.profuturo.bolsa.model.service.sample.UserInfoRequestBean;
import com.mx.profuturo.bolsa.model.service.sample.UserInfoResponseBean;
import com.mx.profuturo.bolsa.model.vo.common.UserInfoVO;
import com.mx.profuturo.bolsa.util.exception.custom.GenericStatusException;

/**
 * Created by alejandro.hernandez on 10/05/2017.
 */
public abstract class UserDataServiceBase implements UserDataService {



    @Override
    public void getUser(Integer id, UserInfoVO userInfo) throws GenericStatusException {
        UserInfoResponseBean userInfoResponse = getUserData(id);
        if(!userInfoResponse.isSuccess()){
            throw new GenericStatusException("404", "ID BUC NOT FOUND OR ERROR.");
        }
        this.buildBasicUserData(userInfo, userInfoResponse );

    }

    private void buildBasicUserData(UserInfoVO userInfo, UserInfoResponseBean userInfoResponse) {
        UserDataBean userData = userInfoResponse.getData();
        userInfo.setSecondLastName(userData.getApellidoMaterno());
        userInfo.setFirstName(userData.getNombre());
        userInfo.setLastName(userData.getApellidoPaterno());
    }

    private UserInfoResponseBean getUserData(Integer id) throws GenericStatusException {
        UserInfoRequestBean userInfoRequestBean = new UserInfoRequestBean();
        userInfoRequestBean.setIdClienteUnico(id);
        RequestBean<UserInfoRequestBean> genericRequestBean = new RequestBean();
        genericRequestBean.setPayload(userInfoRequestBean);
        return callUserInfoService(genericRequestBean);
    }

    protected abstract UserInfoResponseBean callUserInfoService(RequestBean<UserInfoRequestBean> genericRequestBean) throws GenericStatusException;
}
