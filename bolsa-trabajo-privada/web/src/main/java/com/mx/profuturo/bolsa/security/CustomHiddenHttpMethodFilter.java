package com.mx.profuturo.bolsa.security;

import org.springframework.web.filter.HiddenHttpMethodFilter;

import javax.servlet.annotation.WebFilter;

/**
 * Created by luism on 08/06/2016.
 */
@WebFilter("/**")
public class CustomHiddenHttpMethodFilter extends HiddenHttpMethodFilter {
}