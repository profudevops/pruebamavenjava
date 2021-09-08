package com.mx.profuturo.bolsa.service.vacantes;

import com.mx.profuturo.bolsa.util.mockclient.MockHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("request")
@Profile("mock")
public class PublicationMockServiceImpl extends PublicationServiceBase {

    @Autowired
    MockHelper mockErrorHelper;
}
