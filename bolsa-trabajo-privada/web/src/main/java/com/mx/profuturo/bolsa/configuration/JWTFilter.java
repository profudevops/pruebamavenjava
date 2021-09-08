package com.mx.profuturo.bolsa.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JWTFilter extends GenericFilterBean {


    private TokenService tokenService;
    private boolean skipJWT = false;

    @Autowired
    JWTFilter(TokenService tokenService) {
        this.tokenService = tokenService;
    }


    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws IOException, ServletException {
    if(!this.skipJWT){
            filter(req,res,filterChain);
        }else{
            HttpServletResponse response = (HttpServletResponse) res;
            response.setStatus(HttpServletResponse.SC_OK);
            filterChain.doFilter(req, res);
        }
    }

    private void filter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        String token = request.getHeader("Authorization");
        if(null!=token){
            token = this.getCleanToken(token);
        }


        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.sendError(HttpServletResponse.SC_OK, "success");
            return;
        }

        if (allowRequestWithoutToken(request)) {
            response.setStatus(HttpServletResponse.SC_OK);
            filterChain.doFilter(req, res);
        } else {

            if (token == null || !tokenService.isTokenValid(token)) {
                //response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"acceso no autorizado");
                response.sendRedirect("/bolsa-de-trabajo-admin/");

            } else {
                Integer userId = new Integer(tokenService.getUserIdFromToken(token));
                request.setAttribute("userId", userId);

                if(allowRequestWithToken(request)) {
                    filterChain.doFilter(req, res);
                }else{
                    //response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"acceso no autorizado");
                    response.sendRedirect("/bolsa-de-trabajo-admin/");
                }

            }
        }

    }

    public boolean allowRequestWithoutToken(HttpServletRequest request) {
        if(request.getRequestURI().contains("..")){
            return false;
        }
        if (request.getRequestURI().contains("login") ||
        		request.getRequestURI().contains("renovar-token") ||
                request.getRequestURI().contains("public") ||
                request.getRequestURI().equals("/bolsa-de-trabajo-admin/") /*||
                request.getRequestURI().contains("bolsatrabajoprivado") /*||
                */)
                {
            return true;
        }
        return false;
    }


    public boolean allowRequestWithToken(HttpServletRequest request) {
        if(request.getRequestURI().contains("..")){
            return false;
        }
        if (request.getRequestURI().contains("login") ||
                request.getRequestURI().contains("public") ||
                request.getRequestURI().contains("/bolsa-de-trabajo-admin/") /*||
                request.getRequestURI().contains("bolsatrabajoprivado") /*||
                */)
        {
            return true;
        }
        return false;
    }

    public String getCleanToken(String token){
        return token.replace("Bearer ","");
    }

}
