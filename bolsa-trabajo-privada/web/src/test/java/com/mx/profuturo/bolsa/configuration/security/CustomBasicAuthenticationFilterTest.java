package com.mx.profuturo.bolsa.configuration.security;

import com.mx.profuturo.bolsa.context.TestContextSecurity;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by OmarHernandez on 19/05/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=TestContextSecurity.class, loader=AnnotationConfigContextLoader.class)
public class CustomBasicAuthenticationFilterTest {

    @Test
    public void doFilterNonAuthorizedTest() throws IOException, ServletException {
        MockServletContext mockServletContext = new MockServletContext();
        MockFilterChain mockChain = new MockFilterChain();
        MockHttpServletRequest req = new MockHttpServletRequest(mockServletContext, "post", "/login?param='javascript:alert('hola');'");
        MockHttpServletResponse rsp = new MockHttpServletResponse();
        //customBasicAuthenticationFilter.doFilter(req,rsp,mockChain);
        Assert.assertEquals(rsp.getStatus(), HttpServletResponse.SC_OK);
    }

    @Test
    public void doFilterAuthorizedTest() throws IOException, ServletException {
        MockServletContext mockServletContext = new MockServletContext();
        MockFilterChain mockChain = new MockFilterChain();
        MockHttpServletRequest req = new MockHttpServletRequest(mockServletContext, "post", "/indexPortalPrivado");
        MockHttpServletResponse rsp = new MockHttpServletResponse();
        //customBasicAuthenticationFilter.doFilter(req,rsp,mockChain);
        Assert.assertEquals(rsp.getStatus(), HttpServletResponse.SC_OK);
    }
}
