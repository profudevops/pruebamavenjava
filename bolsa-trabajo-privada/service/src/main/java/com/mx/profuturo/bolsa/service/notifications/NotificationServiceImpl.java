package com.mx.profuturo.bolsa.service.notifications;

import com.mx.profuturo.bolsa.model.restclient.ConnectionConfigBean;
import com.mx.profuturo.bolsa.model.restclient.RequestBean;
import com.mx.profuturo.bolsa.model.service.areasinteres.dto.GenericTextResponseBean;
import com.mx.profuturo.bolsa.model.service.notifications.base.NotificationBase;
import com.mx.profuturo.bolsa.util.exception.custom.GenericStatusException;
import com.mx.profuturo.bolsa.util.restclient.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("request")
@Profile({"dev", "test", "prod"})
public class NotificationServiceImpl extends NotificationServiceBase {

	private int i; 
	
    @Value("${ws.bolsatrabajo.server}")
    private String url;

    @Value("${ws.bolsatrabajo.port}")
    private String port;

    @Autowired
    private RestClient restClient;

    @Value("${ws.notificacion.reenvio}")
    private String resendNotification;

    @Override
    protected GenericTextResponseBean callResendNotification(RequestBean<NotificationBase> genericRequestBean) throws GenericStatusException {
        GenericTextResponseBean response;
        genericRequestBean.setConnectionConfigBean(new ConnectionConfigBean(url,port,resendNotification));
        try {
            response = restClient.exetutePOST(genericRequestBean, GenericTextResponseBean.class);
        } catch (Exception e) {
            throw new GenericStatusException(e);
        }
        return response;
    }
}
