package com.mx.profuturo.bolsa.configuration;

import com.mx.profuturo.bolsa.model.role.CorporateRoles;
import com.mx.profuturo.bolsa.security.AntiSamyFilter;
import com.mx.profuturo.bolsa.security.CustomCharacterEncodingFilter;
import com.mx.profuturo.bolsa.security.CustomHiddenHttpMethodFilter;

import springfox.documentation.swagger.web.SecurityConfiguration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@Configuration
@EnableWebSecurity
@ComponentScan("com.mx.profuturo")
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@Order(900)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String VIEW_ERROR = "/index.html";

    @Bean
    public CustomHiddenHttpMethodFilter customHiddenHttpMethodFilter(){
        return new CustomHiddenHttpMethodFilter();
    }
    
//    @Bean
//    public SecurityConfiguration security() {
//        return SecurityConfigurationBuilder.builder()
//            .clientId("")
//            .clientSecret("")
//            .scopeSeparator(" ")
//            .useBasicAuthenticationWithAccessCodeGrant(true)
//            .build();
//    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

       /* httpSecurity.authorizeRequests()
        		.antMatchers("/h2-console/**" ).permitAll()
        		.antMatchers ("/v2/api-docs", "/configuration/ui","/guardar-avance-formulario", "/swagger-resources", "/configuration/security","/swagger-ui.html", "/webjars/**", "/swagger-resources/configuration/ui", "/swagger-ui.html","/swagger-resources/configuration/security").permitAll()
                .antMatchers("/loginError","/login","/version","/candidatos/**","/vacantes/**","/authUser/**","/SampleModelAndView").permitAll()
                .antMatchers("/statics/**", "/templates/**", "/public/**").permitAll()
                .antMatchers("/corporate/**").hasAnyAuthority(
                CorporateRoles.ADMN,
                CorporateRoles.RECRUITER,
                CorporateRoles.COSTUMER_RECRUITER)
                .antMatchers("/**","/public/static/**").access("isAuthenticated()").and()
                .httpBasic().authenticationEntryPoint(customHttp403ForbiddenEntryPoint()).and()
                .headers().frameOptions().sameOrigin().httpStrictTransportSecurity().disable().and()
                .csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .sessionFixation().newSession().maximumSessions(1); */
       
        httpSecurity.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers ("/v2/api-docs", "/configuration/ui", "/swagger-resources", "/configuration/security","/swagger-ui.html", "/webjars/**", "/swagger-resources/configuration/ui", "/swagger-ui.html","/swagger-resources/configuration/security").permitAll()
                .and().csrf().disable();

        
    }
    
  
   
    /**
     * --------------------------------
     * FILTERS
     * ---------------------------------
     */

    @Bean
    public CustomCharacterEncodingFilter customCharacterEncodingFilter(){
        CustomCharacterEncodingFilter filter =  new CustomCharacterEncodingFilter();
        filter.setEncoding("UTF8");
        filter.setForceEncoding(true);
        return filter;
    }

    @Bean
    public AntiSamyFilter antiSamyFilter(){
        return new AntiSamyFilter();
    }



    @Bean
    public SecurityContextLogoutHandler securityContextLogoutHandler(){
        return new SecurityContextLogoutHandler();
    }

    @Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter(){
        return new HiddenHttpMethodFilter();
    }


}