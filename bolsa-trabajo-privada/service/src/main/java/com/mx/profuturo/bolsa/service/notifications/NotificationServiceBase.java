package com.mx.profuturo.bolsa.service.notifications;

import com.mx.profuturo.bolsa.model.restclient.RequestBean;
import com.mx.profuturo.bolsa.model.service.areasinteres.dto.GenericTextResponseBean;
import com.mx.profuturo.bolsa.model.service.notifications.base.NotificationBase;
import com.mx.profuturo.bolsa.util.exception.custom.GenericStatusException;

public abstract class NotificationServiceBase implements NotificationService {


    @Override
    public boolean resendNotification(NotificationBase requestBean) throws GenericStatusException {

        RequestBean<NotificationBase> genericRequestBean = new RequestBean<>();
        genericRequestBean.setPayload(requestBean);

        GenericTextResponseBean respuesta = callResendNotification(genericRequestBean);
        String responseText = respuesta.getRespuesta();
        if("Exito".equals(responseText)){
            return true;
        }
        throw new GenericStatusException();
    }

    protected abstract GenericTextResponseBean callResendNotification(RequestBean<NotificationBase> genericRequestBean) throws GenericStatusException;

}
