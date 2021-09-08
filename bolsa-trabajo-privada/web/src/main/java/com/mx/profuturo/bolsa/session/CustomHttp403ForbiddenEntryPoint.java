package com.mx.profuturo.bolsa.session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by luism on 21/11/2016.
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class CustomHttp403ForbiddenEntryPoint implements AuthenticationEntryPoint {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomHttp403ForbiddenEntryPoint.class);

    @Value("${session.error.accessdenied}")
    private String accessDeniedUrl;

    @Value("${portal.environment}")
    private String environment;

    public CustomHttp403ForbiddenEntryPoint() {
        /*Default constructor*/
    }

    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException arg2) throws IOException, ServletException {
        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug("Pre-authenticated entry point called. Rejecting access");
            LOGGER.debug("cause" + arg2.getCause());
        }
        try {
            //response.sendRedirect(environment + accessDeniedUrl);
            response.sendRedirect(request.getSession().getServletContext().getContextPath() + "/loginError");
        }catch(Exception ex){
            LOGGER.info("Exception " + ex);
            //response.sendRedirect(environment + accessDeniedUrl);
            response.sendRedirect(request.getSession().getServletContext().getContextPath() + "/loginError");
        }
    }
}

