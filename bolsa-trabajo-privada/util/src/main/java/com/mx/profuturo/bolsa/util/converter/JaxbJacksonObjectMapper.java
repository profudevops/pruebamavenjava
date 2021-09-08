package com.mx.profuturo.bolsa.util.converter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;

/**
 * Created by luism on 18/05/2016.
 */
public class JaxbJacksonObjectMapper extends ObjectMapper {
    private static final long serialVersionUID = 1L;

    public JaxbJacksonObjectMapper() {
        JaxbAnnotationIntrospector introspector = new JaxbAnnotationIntrospector(this.getTypeFactory());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd\'T\'HH:mm:ss.SSSZ");
        this.setDateFormat(dateFormat);
        this.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        this.setAnnotationIntrospector(introspector);
        this.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        this.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        this.configure(MapperFeature.USE_ANNOTATIONS, true);

    }



    /**
     * Parseo from InputStream json to Object typed
     * @param source Source from Input Stream
     * @param response Object type
     * @param <T> Object to response
     * @return Object parsed
     * @throws IOException If there is a problem
     */
    public  <T> T parseObject(InputStream source, Class<T> response) throws IOException {
        return super.readValue(source, response);
    }
}
