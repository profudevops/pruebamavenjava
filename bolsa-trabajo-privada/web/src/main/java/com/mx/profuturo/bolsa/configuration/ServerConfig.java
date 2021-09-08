package com.mx.profuturo.bolsa.configuration;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by lUiSm on 5/4/16.
 */
@Configuration
@ComponentScan("com.mx.profuturo")
public class ServerConfig implements EmbeddedServletContainerCustomizer {

    @Override
    public void customize(ConfigurableEmbeddedServletContainer configurableEmbeddedServletContainer) {
        /*
         * This method is empty
         * Use with local configuration if is needed
         * @param configurableEmbeddedServletContainer
         */
    }
}
