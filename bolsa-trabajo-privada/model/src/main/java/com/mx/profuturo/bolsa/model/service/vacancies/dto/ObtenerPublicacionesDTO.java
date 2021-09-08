package com.mx.profuturo.bolsa.model.service.vacancies.dto;

public class ObtenerPublicacionesDTO {

    private int idSede;
    private int idVacante;
    private String idUsuario;
    private String role;

    public String getIdUsuario() { return idUsuario; }

    public void setIdUsuario(String idUsuario) { this.idUsuario = idUsuario; }

    public String getRole() { return role; }

    public void setRole(String role) { this.role = role; }

    public int getIdSede() {
        return idSede;
    }

    public void setIdSede(int idSede) {
        this.idSede = idSede;
    }

    public int getIdVacante() {
        return idVacante;
    }

    public void setIdVacante(int idVacante) {
        this.idVacante = idVacante;
    }
}
