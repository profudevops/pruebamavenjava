package com.mx.profuturo.bolsa.model.restclient;

import java.util.Map;

/**
 * Created by luism on 18/05/2016.
 */
public class RequestBean<T> {
    protected String url;
    protected Map<String, ?> uriVariables;
    protected T payload;
    protected T entityPayload;
    protected Boolean isBrokerService = false;
    protected ConnectionConfigBean connectionConfigBean;

    protected class ProfuturoRequest<T> {
        protected T rqt;
        public T getRqt() {
            return rqt;
        }

        public void setRqt(T rqt) {
            this.rqt = rqt;
        }

    }

    public T getEntityPayload(){
        return entityPayload;
    }

    public Boolean isBrokerService(){
        return this.isBrokerService;
    }


    public void setBrokerService(Boolean bs){
        this.isBrokerService = bs;
    }


    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUriVariables(Map<String, ?> uriVariables) {
        this.uriVariables = uriVariables;
    }

    public Map<String, ?> getUriVariables() {
        return uriVariables;
    }

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload= payload;
    }

    public void setEntityPayload(T entityPayload) {
        this.entityPayload = entityPayload;
    }

    public Boolean getBrokerService() {
        return isBrokerService;
    }

    public ConnectionConfigBean getConnectionConfigBean() {
        return connectionConfigBean;
    }

    public void setConnectionConfigBean(ConnectionConfigBean connectionConfigBean) {
        this.connectionConfigBean = connectionConfigBean;
    }
}
