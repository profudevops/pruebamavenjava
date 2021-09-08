package com.mx.profuturo.bolsa.model.service;

public class EntityResponseBean<T> extends BaseResponseBean{
    private T entity;

    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }
}
