package com.mx.profuturo.bolsa.configuration.security;

import com.mx.profuturo.bolsa.context.TestContext;
import com.mx.profuturo.bolsa.security.AntiSamyFilter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.util.Assert;

import javax.servlet.ServletException;
import java.io.IOException;


/**
 * Created by OmarHernandez on 10/05/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=TestContext.class, loader=AnnotationConfigContextLoader.class)
public class AntiSamyFilterTest {

    @Autowired
    AntiSamyFilter antiSamyFilter;

    @Value("classpath:/xml/antisamy.xml")
    private Resource xmlFile;

    @Test
    public void doFilterInternalTest() throws ServletException, IOException {

        MockServletContext mockServletContext = new MockServletContext();
        MockFilterChain mockChain = new MockFilterChain();
        MockHttpServletRequest req = new MockHttpServletRequest(mockServletContext, "post", "/login?param='javascript:alert('hola');'");
        req.addParameter("parameterName", "'<div><script>alert(1);</script></div>");
        MockHttpServletResponse rsp = new MockHttpServletResponse();
        antiSamyFilter.doFilter(req,rsp,mockChain);
        Assert.notNull(antiSamyFilter);
    }

    @Test
    public void setXmlFileTest(){
        Assert.notNull(antiSamyFilter,"EL objeto antiSamyFilter es nulo");
        antiSamyFilter.setXmlFile(xmlFile);
        Assert.notNull(antiSamyFilter.getXmlFile(),"EL archivo es nulo");
    }

}