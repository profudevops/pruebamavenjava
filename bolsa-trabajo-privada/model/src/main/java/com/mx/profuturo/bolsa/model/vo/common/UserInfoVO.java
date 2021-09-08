package com.mx.profuturo.bolsa.model.vo.common;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by alejandro.hernandez on 10/05/2017.
 */
public class UserInfoVO implements Serializable {
    private static final long serialVersionUID = -5142915174468388076L;
    private String firstName;
    private String lastName;
    private String secondLastName;

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

}