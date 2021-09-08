package com.mx.profuturo.bolsa.configuration.security;

import com.mx.profuturo.bolsa.context.TestContext;
import com.mx.profuturo.bolsa.security.ValidatingHttpRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.io.File;
import java.io.IOException;

/**
 * Created by OmarHernandez on 19/05/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=TestContext.class, loader=AnnotationConfigContextLoader.class)
public class ValidatingHttpRequestTest {

    File antisamyXml;

    /***
     * Important! Please add the file in the location src/test/resources/xml/antisamy-afore.xml
     * @throws IOException
     */

    @Before
    public void setup(){
        antisamyXml = new File("src/test/resources/xml/antisamy.xml");
    }

    @Test
    public void getParameterTest() throws IOException {
        MockServletContext mockServletContext = new MockServletContext();

        MockHttpServletRequest req = new MockHttpServletRequest(mockServletContext, "post", "/login?'");
        req.addParameter("parameterName", "'<div><script>alert(1);</script></div>");

        ValidatingHttpRequest validatingHttpRequest = new ValidatingHttpRequest(req);
        validatingHttpRequest.setFile(antisamyXml);
        String sanitizedParam = validatingHttpRequest.getParameter("parameterName");
        Assert.assertEquals("<div />", sanitizedParam);
    }
}
