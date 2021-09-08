package com.mx.profuturo.bolsa.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import java.util.Properties;

@ComponentScan("com.mx.profuturo")
public class ExceptionConfig {

    public static final String DEFAULT_ERROR_VIEW = "hola";
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionConfig.class);

    public ExceptionConfig() {
        LOGGER.info("Creating ExceptionConfiguration");
    }

    @Bean(name = "simpleMappingExceptionResolver")
    public SimpleMappingExceptionResolver createSimpleMappingExceptionResolver() {
        LOGGER.info("Creating SimpleMappingExceptionResolver");
        SimpleMappingExceptionResolver r = new SimpleMappingExceptionResolver();

        Properties mappings = new Properties();
        mappings.setProperty("Exception", DEFAULT_ERROR_VIEW);
        mappings.setProperty("NoHandlerFoundException", DEFAULT_ERROR_VIEW);

        r.setExceptionMappings(mappings); // None by default
        r.setExceptionAttribute("ex"); // Default is "exception"
        r.setDefaultErrorView(DEFAULT_ERROR_VIEW);
        return r;
    }
}

