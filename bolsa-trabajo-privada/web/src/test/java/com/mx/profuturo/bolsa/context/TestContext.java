package com.mx.profuturo.bolsa.context;


import com.mx.profuturo.bolsa.security.AntiSamyFilter;
import com.mx.profuturo.bolsa.security.GrantingUserSettings;
import com.mx.profuturo.bolsa.session.SessionListener;
import com.mx.profuturo.bolsa.util.mockclient.MockClient;
import com.mx.profuturo.bolsa.util.mockclient.MockClientImpl;
import com.mx.profuturo.bolsa.util.mockclient.MockHelper;
import com.mx.profuturo.bolsa.util.restclient.RestClient;
import com.mx.profuturo.bolsa.util.restclient.RestClientImpl;
import com.mx.profuturo.bolsa.util.restclient.interceptor.LoggingRestClient;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lUiSm on 4/25/16.
 */
@Configuration
public class TestContext extends WebMvcConfigurerAdapter {

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
        messageSource.setBasenames("classpath:/locale/global/messages","classpath:/locale/global/messages_uri","classpath:/locale/demo/messages","classpath:/locale/payments/messages","classpath:/locale/exceptions/messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public RestClient restClient(){
        return new RestClientImpl();
    }

    @Bean
    public MockClient mockClient() {
        return new MockClientImpl();
    }

    @Bean
    public MockHelper mockErrorHelper() {
        return new MockHelper();
    }

    @Bean
    public AntiSamyFilter antiSamyFilter() {
        return new AntiSamyFilter();
    }


    @Bean
    public GrantingUserSettings grantingUserSettings(){
        return new GrantingUserSettings();
    }

    @Bean
    public SessionListener sessionListener(){return new SessionListener();}


}