package com.mx.profuturo.bolsa.configuration.security;

import com.mx.profuturo.bolsa.context.TestContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

/**
 * Created by alejandro.hernandez on 18/07/2016.
 */


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=TestContext.class, loader=AnnotationConfigContextLoader.class)
public class GrantingUserSettingsTest {

    @Test
    public void setUserTest(){

    }
}
