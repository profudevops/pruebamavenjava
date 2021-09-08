package com.mx.profuturo.bolsa.model.service.sample;

public class SamplePojo {
    private  String message;
    private  int  i;
    private double y;
    private  boolean b;
    public SamplePojo(String message, int i, double v, boolean b) {
        this.message = message;
        this.i = i;
        this.y = v;
        this.b = b;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public boolean isB() {
        return b;
    }

    public void setB(boolean b) {
        this.b = b;
    }
}
