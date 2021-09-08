package com.mx.profuturo.bolsa.model.service.dto;

import org.springframework.beans.factory.annotation.Value;

public class ImageDataDTO {

    private String imageExtension;
    private String imageName;
    private byte[] imageString;


    public String getImageExtension() {
        return imageExtension;
    }

    public void setImageExtension(String imageExtension) {
        this.imageExtension = imageExtension;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public byte[] getImageString() {
        return imageString;
    }

    public void setImageString(byte[] imageString) {
        this.imageString = imageString;
    }
}
