package com.mx.profuturo.bolsa.util.mockclient;

import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.mx.profuturo.bolsa.model.restclient.RequestBean;
import com.mx.profuturo.bolsa.util.converter.JaxbJacksonObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.*;

/**
 * Created by Julio Arredondo on 30/06/2016.
 */
@Component
@Scope("session")

public class MockClientImpl implements  MockClient{

    private MockHelper errorHelper = null;
    private final JaxbJacksonObjectMapper mapper = new JaxbJacksonObjectMapper();
    private static final Logger LOGGER = LoggerFactory.getLogger(MockClientImpl.class);

    @Override
    public void setErrorHelper(MockHelper errorHelper){
        this.errorHelper = errorHelper;
    }

    @Override
    public void exceptionMethod()throws JsonParseException{
        throw new JsonParseException("no_exception", JsonLocation.NA);
    }

    @Override
    public <T> T getMock(Resource mockFile, Class<T> responseType) throws Exception{
        InputStream stream = null;
        String jsonMock = null;
        T t = null;
        try {
            if(errorHelper != null && errorHelper.shouldDoErrorStream()){
                stream = errorHelper.getMockErrorFile().getInputStream();
                errorHelper.reset();
            }else{
                stream = mockFile.getInputStream();
            }
            jsonMock = getStringFromInputStream(stream);
            t = this.mapper.readValue(jsonMock, responseType);

        } catch (IOException e) {
            LOGGER.info("IOException error" + e);
        }finally {
            errorHelper = null;
        }
        return t;
    }


    public String getJsonRequest(RequestBean<?> request) throws JsonProcessingException {
        return mapper.writeValueAsString(request);
    }

    private static String getStringFromInputStream(InputStream is) {

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            LOGGER.info("IOException err" + e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    LOGGER.info("IOException " + e);
                }
            }
        }
        return sb.toString();
    }

    @Override
    public void buildMockFile(Object response, String fileName)throws JsonProcessingException {
        String json = mapper.writeValueAsString(response);
        String filePath  = new StringBuilder("").append(fileName).append(".json").toString();
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(filePath, "UTF-8");
        } catch (FileNotFoundException e) {
            //e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
           // e.printStackTrace();
        }
        writer.println(json);
        writer.close();
    }

}
