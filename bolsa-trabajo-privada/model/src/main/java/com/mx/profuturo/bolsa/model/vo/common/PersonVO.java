package com.mx.profuturo.bolsa.model.vo.common;

public class PersonVO {

    protected String firstName;
    protected String lastName;
    protected String secondLastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSecondLastName() {
        return secondLastName;
    }

    public void setSecondLastName(String secondLastName) {
        this.secondLastName = secondLastName;
    }

    public PersonVO(){

    }

    public PersonVO(PersonVO vo){
        this.firstName = vo.getFirstName();
        this.lastName = vo.getLastName();
        this.secondLastName = vo.getSecondLastName();
    }
}