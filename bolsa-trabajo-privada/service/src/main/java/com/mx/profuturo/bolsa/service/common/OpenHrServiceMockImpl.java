package com.mx.profuturo.bolsa.service.common;

import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("request")
@Profile("mock")
public class OpenHrServiceMockImpl {
}
