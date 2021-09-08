package com.mx.profuturo.bolsa.service.notifications;

import com.mx.profuturo.bolsa.model.restclient.RequestBean;
import com.mx.profuturo.bolsa.model.service.areasinteres.dto.GenericTextResponseBean;
import com.mx.profuturo.bolsa.model.service.notifications.base.NotificationBase;
import com.mx.profuturo.bolsa.util.exception.custom.GenericStatusException;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("request")
@Profile("mock")
public class NotificationServiceMockImpl extends NotificationServiceBase {

    @Override
    protected GenericTextResponseBean callResendNotification(RequestBean<NotificationBase> genericRequestBean) throws GenericStatusException {
        GenericTextResponseBean response = new GenericTextResponseBean();
        response.setRespuesta("Exito");
        return response;
    }

}
