package com.mx.profuturo.bolsa.model.service.auth.dto;

import com.mx.profuturo.bolsa.model.service.auth.response.UserCredentialsVO;

public class SessionRegisterDTO {
    private String userId;
    private String creationTime;

    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }
}
