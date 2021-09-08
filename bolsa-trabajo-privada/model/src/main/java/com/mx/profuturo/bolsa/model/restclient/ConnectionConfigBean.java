package com.mx.profuturo.bolsa.model.restclient;

/**
 * Created by Julio Arredondo on 27/06/2017.
 */
public class ConnectionConfigBean {
    private String endPoint;
    private String port;
    private String uri;

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }


    public ConnectionConfigBean(){}

    public ConnectionConfigBean(String endPoint, String port, String uri){
        this.endPoint = endPoint;
        this.port = port;
        this.uri = uri;
    }

    public String getAsURL(){
        return new StringBuilder().append(this.endPoint).append(":")
                .append(this.port).append(this.uri).toString();
    }
}
