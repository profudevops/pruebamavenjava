package com.mx.profuturo.bolsa.model.service.vacancies.dto;

public class BuscarDatosVacanteDTO {

    private Integer idPublicacion;
    private boolean isAdmin;
    private String idAnalista;
    private int paginaActual;
    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getIdPublicacion() { return idPublicacion; }

    public void setIdPublicacion(Integer idPublicacion) { this.idPublicacion = idPublicacion; }

    public boolean isAdmin() {
        return this.isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getIdAnalista() {
        return idAnalista;
    }

    public void setIdAnalista(String idAnalista) {
        this.idAnalista = idAnalista;
    }

    public int getPaginaActual() {
        return paginaActual;
    }

    public void setPaginaActual(int paginaActual) {
        this.paginaActual = paginaActual;
    }
}
