package com.mx.profuturo.bolsa.util.restclient.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by lUiSm on 5/20/16.
 */
public class LoggingRestClient implements ClientHttpRequestInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingRestClient.class);

    public LoggingRestClient() {
        /*default con constructor*/
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] body, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
        logRequest(httpRequest, body);
        ClientHttpResponse response = clientHttpRequestExecution.execute(httpRequest, body);
        logResponse(response);
        return response;
    }

    private void logRequest(HttpRequest request, byte[] body) throws IOException {
        LOGGER.info("===========================JSONrequest===============================================");
        LOGGER.info("URI : " + request.getURI());
        LOGGER.info("Method : " + request.getMethod());
        LOGGER.info("Request Body : " + new String(body, "UTF-8"));
    }

    private void logResponse(ClientHttpResponse response) throws IOException {
        StringBuilder inputStringBuilder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getBody(), "UTF-8"));
        String line = bufferedReader.readLine();
        while (line != null) {
            inputStringBuilder.append(line);
            inputStringBuilder.append('\n');
            line = bufferedReader.readLine();
        }
        LOGGER.info("============================JSONresponse============================================");
        LOGGER.info("status code: " + response.getStatusCode());
        LOGGER.info("status text: " + response.getStatusText());
        LOGGER.info("Response Body : " + inputStringBuilder.toString());
        bufferedReader.close();
    }
}
