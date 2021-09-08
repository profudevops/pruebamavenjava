package com.mx.profuturo.bolsa.util.messages;


import com.mx.profuturo.bolsa.util.configuration.AppConfigContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.util.HtmlUtils;

import java.util.Locale;

public class UtilI18n {


    ReloadableResourceBundleMessageSource messageSource;


    public String internacionalizaString(String mensaje) {
        Locale locMEX = new Locale("es", "MX");
        String mensajeInternacionalizado;
        ApplicationContext contextApp = AppConfigContext.getApplicationContext();
        messageSource = (ReloadableResourceBundleMessageSource) contextApp.getBean("messageSource");

        mensajeInternacionalizado = messageSource.getMessage(mensaje, null, locMEX);
        return HtmlUtils.htmlUnescape(mensajeInternacionalizado);
    }


}
