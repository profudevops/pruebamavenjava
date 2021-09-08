package com.mx.profuturo.bolsa.model.vo.Candidato;

public class CandidatoResumenVO {
    private Integer idCandidato;
    private String nombres;
    private String apellidoMaterno;
    private String apellidoPaterno;
    private String nivelDeEstudio;
    private String telefono;
    private String tipo;
    private String vacante;
    private String estatus;
    private Boolean enReclutamiento;

    public String getVacante() {
		return vacante;
	}

	public void setVacante(String vacante) {
		this.vacante = vacante;
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public Integer getIdCandidato() {
        return idCandidato;
    }

    public void setIdCandidato(Integer idCandidato) {
        this.idCandidato = idCandidato;
    }

    public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getNivelDeEstudio() {
        return nivelDeEstudio;
    }

    public void setNivelDeEstudio(String nivelDeEstudio) {
        this.nivelDeEstudio = nivelDeEstudio;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
