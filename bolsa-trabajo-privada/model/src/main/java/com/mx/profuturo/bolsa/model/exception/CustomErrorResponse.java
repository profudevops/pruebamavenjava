package com.mx.profuturo.bolsa.model.exception;

/**
 * Created by fmaldonadov on 21/06/2016.
 */
public class CustomErrorResponse {

    private String title;
    private String description;

    public void setTitle(String value){
        title = value;
    }
    public void setDescription(String value){
        description = value;
    }

    public String getTitle(){
        return title;
    }
    public String getDescription() {
        return description;
    }

}
