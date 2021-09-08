package com.mx.profuturo.bolsa.service.notifications;

import com.mx.profuturo.bolsa.model.service.notifications.base.NotificationBase;
import com.mx.profuturo.bolsa.util.exception.custom.GenericStatusException;

public interface NotificationService {
    boolean resendNotification(NotificationBase requestBean) throws GenericStatusException;
}
