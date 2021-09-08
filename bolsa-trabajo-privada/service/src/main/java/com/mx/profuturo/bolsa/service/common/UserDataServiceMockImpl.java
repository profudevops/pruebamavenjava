package com.mx.profuturo.bolsa.service.common;

import com.mx.profuturo.bolsa.model.restclient.RequestBean;
import com.mx.profuturo.bolsa.model.service.sample.UserInfoRequestBean;
import com.mx.profuturo.bolsa.model.service.sample.UserInfoResponseBean;
import com.mx.profuturo.bolsa.util.exception.custom.GenericStatusException;
import com.mx.profuturo.bolsa.util.mockclient.MockClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

/**
 * Created by alejandro.hernandez on 10/05/2017.
 */
@Service
@Scope("request")
@Profile("mock")
public class UserDataServiceMockImpl extends  UserDataServiceBase {

    @Autowired
    MockClient mockClient;

    @Value("classpath:/mock/common/user_info_response_bean.json")
    private Resource userInfoResource;

    @Override
    protected UserInfoResponseBean callUserInfoService(RequestBean<UserInfoRequestBean> genericRequestBean) throws GenericStatusException {
        UserInfoResponseBean response = null;
        try {
            response = mockClient.getMock(userInfoResource, UserInfoResponseBean.class);
        }catch (Exception e){
            throw new GenericStatusException(e);
        }
        return response;
    }
}
