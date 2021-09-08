package com.mx.profuturo.bolsa.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet Filter implementation class AntiSamyFilter
 */
@WebFilter("/**")
public class AntiSamyFilter extends OncePerRequestFilter {

    @Value("classpath:/xml/antisamy.xml")
    private Resource xmlFile;

    /**
     * Default cocnstructor.
     */
    public AntiSamyFilter() {
        /**Default constructor*/
    }

    /**
     * @see Filter#destroy()
     */
    @Override
    public void destroy() {
        /* Do nothing */
    }

    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        final ValidatingHttpRequest myRequestWrapper = new ValidatingHttpRequest(request);
        myRequestWrapper.setFile(this.xmlFile.getFile());
        filterChain.doFilter(myRequestWrapper, response);
    }

    public void setXmlFile(Resource xmlFile) {
        this.xmlFile = xmlFile;
    }

    public Resource getXmlFile() {
        return xmlFile;
    }
}
