package com.mx.profuturo.bolsa.configuration;

import com.mx.profuturo.bolsa.servlet.Home;
import com.mx.profuturo.bolsa.session.SessionListener;
import com.mx.profuturo.bolsa.util.restclient.interceptor.LoggingRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration;
import org.springframework.boot.context.embedded.MultipartConfigFactory;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.annotation.Order;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.thymeleaf.dialect.IDialect;
import org.thymeleaf.extras.conditionalcomments.dialect.ConditionalCommentsDialect;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

import javax.servlet.MultipartConfigElement;
import java.util.*;

/**
 * Created by lUiSm on 4/25/16.
 */
@Configuration
@EnableWebMvc
@ComponentScan("com.mx.profuturo")
@PropertySource("classpath:application.properties")
@EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class, SecurityAutoConfiguration.class})
@Order(800)
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    private static final String UTF_8 = "UTF-8";

    @Value("${bolsaTrabajo.images.path}")
    private String imagePath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("resources/**").addResourceLocations("/resources/**");
        registry.addResourceHandler("/css/**").addResourceLocations("classpath:/css/**");
        registry.addResourceHandler("/img/**").addResourceLocations("/img/**");
        registry.addResourceHandler("/js/**").addResourceLocations("/js/**");
        registry.addResourceHandler("/statics/**").addResourceLocations("classpath:/statics/");
        registry.addResourceHandler("/public/**").addResourceLocations("classpath:/public/");
        registry.addResourceHandler("/bolsa-de-trabajo-admin/**").addResourceLocations("classpath:/public/");
        registry.addResourceHandler("/documents/**").addResourceLocations("file:/home/ricky/Documents/ImagenesBolsa/");
        registry.addResourceHandler("/img/**").addResourceLocations(imagePath);
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("/index.html");
        registry.addViewController("{path:[^.]*}/../{path:[^.]*}").setViewName("/index.html");
        registry.addViewController("/**/{path:[^.]*}").setViewName("/index.html");

    }


    @Bean
    public SpringTemplateEngine templateEngine(){

        Set<IDialect> dialects = new LinkedHashSet<>();
        dialects.add(new SpringSecurityDialect());
        dialects.add(new ConditionalCommentsDialect());

        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(springThymeleafTemplateResolver());
        return templateEngine;
    }

    @Bean
    public TemplateResolver springThymeleafTemplateResolver() {
        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
        resolver.setCharacterEncoding(UTF_8);
        resolver.setPrefix("classpath:/public/");
        resolver.setTemplateMode("LEGACYHTML5");
        resolver.setOrder(1);
        return resolver;
    }


    @Bean
    public RequestMappingHandlerAdapter requestMappingHandlerAdapter() {
        RequestMappingHandlerAdapter handlerAdapter = new RequestMappingHandlerAdapter();
        handlerAdapter.setOrder(1);
        handlerAdapter.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        handlerAdapter.getMessageConverters().add(new ByteArrayHttpMessageConverter());
        handlerAdapter.getMessageConverters().add(new SourceHttpMessageConverter());
        handlerAdapter.getMessageConverters().add(new FormHttpMessageConverter());
        handlerAdapter.getMessageConverters().add(new StringHttpMessageConverter());
        return handlerAdapter;
    }


    /**
     * --------------------------------
     * SERVLETS
     * ---------------------------------
     */

    @Bean
    public ServletRegistrationBean servletHome(){
        return new ServletRegistrationBean(new Home(),"/servlet/Home");
    }

    @Bean
    public MultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(Integer.MAX_VALUE); //1.5 MB
        return factory.createMultipartConfig();
    }

    /**
     * --------------------------------
     * LISTENERS
     * ---------------------------------
     */
    @Bean
    public SessionListener executorListener() {
        return new SessionListener();
    }

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate= new RestTemplate(new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));
        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add(new LoggingRestClient());
        restTemplate.setInterceptors(interceptors);
        return restTemplate;
    }

    @Bean(name ="messageSource")
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames(
                "classpath:/locale/global/messages",
                "classpath:/locale/global/messages_uri",
                "classpath:/locale/errors/messages",
                "classpath:/locale/errors/messages_uri"
        );
        messageSource.setDefaultEncoding(UTF_8);
        return messageSource;
    }

    @Bean
    public LocaleResolver localeResolver() {
        final Locale localeMx = new Locale("es", "MX");
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(localeMx);
        return slr;
    }

    //Interceptors
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }

}
