package com.mx.profuturo.bolsa.session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Created by luism on 01/06/2016.
 */
@WebListener
@Order(100000)
public class SessionListener implements HttpSessionListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(SessionListener.class);

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        LOGGER.info("Sesion creada " + se.getSession().getId());
        LOGGER.info("Servidor de creacion  " + se.getSession().getServletContext().getServerInfo());
        //se.getSession().setMaxInactiveInterval(15 * 60);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        LOGGER.info("Sesion destruida");
    }
}
