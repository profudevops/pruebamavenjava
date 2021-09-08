package com.mx.profuturo.bolsa.util.configuration;

import org.springframework.context.ApplicationContext;

/**
 * The Class AppConfigContext.
 */
public class AppConfigContext {

    private static ApplicationContext context;

    private AppConfigContext(){
        /*Default constructor*/
    }

    /**
     * @return
     */
    public static ApplicationContext getApplicationContext() {
        return context;
    }

    /**
     * @param applicationContext
     */
    public static void setApplicationContext(ApplicationContext applicationContext) {
        context = applicationContext;
    }

}
