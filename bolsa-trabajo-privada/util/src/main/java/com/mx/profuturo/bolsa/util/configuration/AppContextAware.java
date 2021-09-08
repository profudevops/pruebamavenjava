package com.mx.profuturo.bolsa.util.configuration;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class AppContextAware implements ApplicationContextAware {

    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        AppConfigContext.setApplicationContext(ctx);
    }
}
