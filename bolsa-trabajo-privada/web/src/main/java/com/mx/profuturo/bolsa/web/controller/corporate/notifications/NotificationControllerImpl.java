package com.mx.profuturo.bolsa.web.controller.corporate.notifications;

import com.mx.profuturo.bolsa.model.service.BaseResponseBean;
import com.mx.profuturo.bolsa.model.service.notifications.base.NotificationBase;
import com.mx.profuturo.bolsa.service.notifications.NotificationService;
import com.mx.profuturo.bolsa.util.exception.custom.GenericStatusException;
import com.mx.profuturo.bolsa.web.controller.common.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "notificaciones")
@Controller("controllerNotifications")
@Scope("request")
@CrossOrigin
public class NotificationControllerImpl extends BaseController {

    @Autowired
    private NotificationService notificationService;

    public @ResponseBody
    @RequestMapping(value = "reenviar-notificacion", method = RequestMethod.POST)
    BaseResponseBean reSendNotification(@RequestBody NotificationBase request) throws GenericStatusException {
        notificationService.resendNotification(request);
        return this.buildSuccessResponse();
    }

}
