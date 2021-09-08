package com.mx.profuturo.bolsa.model.service.vacancies.dto;

public class PersonaProfuturoDTO {
    private Integer id;
    private String cusp;
    private  String email;
    private  String nombre;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCusp() {
        return cusp;
    }

    public void setCusp(String cusp) {
        this.cusp = cusp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}

