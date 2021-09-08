package com.mx.profuturo.bolsa.model.service.sample;

public class UserInfoResponseBean {
    private UserDataBean data;
    private boolean success;

    public UserDataBean getData() {
        return data;
    }

    public void setData(UserDataBean data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
