package com.mx.profuturo.bolsa.servlet;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockServletContext;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * Created by OmarHernandez on 19/05/2016.
 */
public class HomeTest {

    @Test
    public void doPostTest() throws ServletException, IOException {
        Home servletHome = new Home();
        MockServletContext mockServletContext = new MockServletContext();
        MockHttpServletRequest req = new MockHttpServletRequest(mockServletContext, "post", "/servlet/Home");
        MockHttpServletResponse rsp = new MockHttpServletResponse();
        servletHome.doPost(req,rsp);
        Assert.assertEquals("/login?acceso=validateProfile",rsp.getForwardedUrl());
    }
}
