package com.mx.profuturo.bolsa.configuration;

import com.mx.profuturo.bolsa.util.configuration.AppContextAware;
import org.springframework.boot.context.embedded.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.web.context.request.RequestContextListener;

/**
 * Created by luism on 09/06/2016.
 */
@Configuration
@Order(1000)
public class ApplicationContextConfig {


    /**
     * --------------------------------
     * CONTROLLERS
     * ---------------------------------
     */

    @Bean
    public ServletListenerRegistrationBean<HttpSessionEventPublisher> httpSessionEventPublisher() {
        return new ServletListenerRegistrationBean<>(new HttpSessionEventPublisher());
    }

    @Bean
    public ServletListenerRegistrationBean<RequestContextListener> requestContextListener() {
        return new ServletListenerRegistrationBean<>(new RequestContextListener());
    }

    @Bean
    public AppContextAware appContextAware(){
        return new AppContextAware();
    }

}