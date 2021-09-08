package com.mx.profuturo.bolsa.model.service.candidates.dto;

import com.mx.profuturo.bolsa.model.reports.vo.data.DatosFiltrosVO;

public class CandidateCorpInfo extends DatosFiltrosVO {

    private String fechaPostulacion;
    private String postuladoOcartera;
    private String vacanteAplicada;
    private String areaInteres;
    private String subAreaInteres;
    private String nombreCompleto;
    private String fuente;
    private String residencia;
    private String municipio;
    private String celular;
    private String correo;
    private String curp;
    private String rfc;
    private String genero;
    private Integer edad;
    private String escolaridad;
    private String especialidad;
    private String estadoCivil;
    private String divisional;
    private String regional;
    private String gerencia;
    private String filtroTelefonico;
    private String descarteTelefonico;
    private String tipoPrimerEntrevista;
    private String fechaPrimerEntrevista;
    private String horaPrimerEntrevista;
    private String resultadoPrimerEntrevista;
    private String motivoDescartePrimerEntrevista;
    private String comentariosPrimerEntrevista;
    private String d;
    private String i;
    private String s;
    private String c;
    private String examenConfianza;
    private String resultadoExamenConfianza;
    private String examenPsicometrico;
    private String resultadoExamenPsicometrico;
    private String nombreEntrevistador;
    private String tipoSegundaEntrevista;
    private String fechaSegundaEntrevista;
    private String horaSegundaEntrevista;
    private String resultadoSegundaEntrevista;
    private String motivoDescarteSegundaEntrevista;
    private String comentariosSegundaEntrevista;
    private String responsableVacante;
    private String aceptaOferta;
    private String motivoNoAceptaOferta;
    private String comentariosOferta;
    private String solicitudEmpleo;
    private String socioEconomico;
    private String contratado;
    private String motivoNoContratado;
    private String comentarios;
    private String respostulado;
    private String empresaAnterior;
    private String puestoAnterior;

    public CandidateCorpInfo(){}

    public CandidateCorpInfo(String fechaPostulacion, String postuladoOcartera, String vacanteAplicada, String areaInteres, String subAreaInteres, String nombreCompleto, String fuente, String residencia, String municipio, String celular, String correo, String curp, String rfc, String genero, Integer edad, String escolaridad, String especialidad, String estadoCivil, String divisional, String regional, String gerencia, String filtroTelefonico, String descarteTelefonico, String tipoPrimerEntrevista, String fechaPrimerEntrevista, String horaPrimerEntrevista, String resultadoPrimerEntrevista, String motivoDescartePrimerEntrevista, String comentariosPrimerEntrevista, String d, String i, String s, String c, String examenConfianza, String resultadoExamenConfianza, String examenPsicometrico, String resultadoExamenPsicometrico, String nombreEntrevistador, String tipoSegundaEntrevista, String fechaSegundaEntrevista, String horaSegundaEntrevista, String resultadoSegundaEntrevista, String motivoDescarteSegundaEntrevista, String comentariosSegundaEntrevista, String responsableVacante, String aceptaOferta, String motivoNoAceptaOferta, String comentariosOferta, String solicitudEmpleo, String socioEconomico, String contratado, String motivoNoContratado, String comentarios, String respostulado, String empresaAnterior, String puestoAnterior) {
        this.fechaPostulacion = fechaPostulacion;
        this.postuladoOcartera = postuladoOcartera;
        this.vacanteAplicada = vacanteAplicada;
        this.areaInteres = areaInteres;
        this.subAreaInteres = subAreaInteres;
        this.nombreCompleto = nombreCompleto;
        this.fuente = fuente;
        this.residencia = residencia;
        this.municipio = municipio;
        this.celular = celular;
        this.correo = correo;
        this.curp = curp;
        this.rfc = rfc;
        this.genero = genero;
        this.edad = edad;
        this.escolaridad = escolaridad;
        this.especialidad = especialidad;
        this.estadoCivil = estadoCivil;
        this.divisional = divisional;
        this.regional = regional;
        this.gerencia = gerencia;
        this.filtroTelefonico = filtroTelefonico;
        this.descarteTelefonico = descarteTelefonico;
        this.tipoPrimerEntrevista = tipoPrimerEntrevista;
        this.fechaPrimerEntrevista = fechaPrimerEntrevista;
        this.horaPrimerEntrevista = horaPrimerEntrevista;
        this.resultadoPrimerEntrevista = resultadoPrimerEntrevista;
        this.motivoDescartePrimerEntrevista = motivoDescartePrimerEntrevista;
        this.comentariosPrimerEntrevista = comentariosPrimerEntrevista;
        this.d = d;
        this.i = i;
        this.s = s;
        this.c = c;
        this.examenConfianza = examenConfianza;
        this.resultadoExamenConfianza = resultadoExamenConfianza;
        this.examenPsicometrico = examenPsicometrico;
        this.resultadoExamenPsicometrico = resultadoExamenPsicometrico;
        this.nombreEntrevistador = nombreEntrevistador;
        this.tipoSegundaEntrevista = tipoSegundaEntrevista;
        this.fechaSegundaEntrevista = fechaSegundaEntrevista;
        this.horaSegundaEntrevista = horaSegundaEntrevista;
        this.resultadoSegundaEntrevista = resultadoSegundaEntrevista;
        this.motivoDescarteSegundaEntrevista = motivoDescarteSegundaEntrevista;
        this.comentariosSegundaEntrevista = comentariosSegundaEntrevista;
        this.responsableVacante = responsableVacante;
        this.aceptaOferta = aceptaOferta;
        this.motivoNoAceptaOferta = motivoNoAceptaOferta;
        this.comentariosOferta = comentariosOferta;
        this.solicitudEmpleo = solicitudEmpleo;
        this.socioEconomico = socioEconomico;
        this.contratado = contratado;
        this.motivoNoContratado = motivoNoContratado;
        this.comentarios = comentarios;
        this.respostulado = respostulado;
        this.empresaAnterior = empresaAnterior;
        this.puestoAnterior = puestoAnterior;
    }

    public String getFechaPostulacion() {
        return fechaPostulacion;
    }

    public void setFechaPostulacion(String fechaPostulacion) {
        this.fechaPostulacion = fechaPostulacion;
    }

    public String getPostuladoOcartera() {
        return postuladoOcartera;
    }

    public void setPostuladoOcartera(String postuladoOcartera) {
        this.postuladoOcartera = postuladoOcartera;
    }

    public String getVacanteAplicada() {
        return vacanteAplicada;
    }

    public void setVacanteAplicada(String vacanteAplicada) {
        this.vacanteAplicada = vacanteAplicada;
    }

    public String getAreaInteres() {
        return areaInteres;
    }

    public void setAreaInteres(String areaInteres) {
        this.areaInteres = areaInteres;
    }

    public String getSubAreaInteres() {
        return subAreaInteres;
    }

    public void setSubAreaInteres(String subAreaInteres) {
        this.subAreaInteres = subAreaInteres;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getFuente() {
        return fuente;
    }

    public void setFuente(String fuente) {
        this.fuente = fuente;
    }

    public String getResidencia() {
        return residencia;
    }

    public void setResidencia(String residencia) {
        this.residencia = residencia;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getEscolaridad() {
        return escolaridad;
    }

    public void setEscolaridad(String escolaridad) {
        this.escolaridad = escolaridad;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getDivisional() {
        return divisional;
    }

    public void setDivisional(String divisional) {
        this.divisional = divisional;
    }

    public String getRegional() {
        return regional;
    }

    public void setRegional(String regional) {
        this.regional = regional;
    }

    public String getGerencia() {
        return gerencia;
    }

    public void setGerencia(String gerencia) {
        this.gerencia = gerencia;
    }

    public String getFiltroTelefonico() {
        return filtroTelefonico;
    }

    public void setFiltroTelefonico(String filtroTelefonico) {
        this.filtroTelefonico = filtroTelefonico;
    }

    public String getDescarteTelefonico() {
        return descarteTelefonico;
    }

    public void setDescarteTelefonico(String descarteTelefonico) {
        this.descarteTelefonico = descarteTelefonico;
    }

    public String getTipoPrimerEntrevista() {
        return tipoPrimerEntrevista;
    }

    public void setTipoPrimerEntrevista(String tipoPrimerEntrevista) {
        this.tipoPrimerEntrevista = tipoPrimerEntrevista;
    }

    public String getFechaPrimerEntrevista() {
        return fechaPrimerEntrevista;
    }

    public void setFechaPrimerEntrevista(String fechaPrimerEntrevista) {
        this.fechaPrimerEntrevista = fechaPrimerEntrevista;
    }

    public String getHoraPrimerEntrevista() {
        return horaPrimerEntrevista;
    }

    public void setHoraPrimerEntrevista(String horaPrimerEntrevista) {
        this.horaPrimerEntrevista = horaPrimerEntrevista;
    }

    public String getResultadoPrimerEntrevista() {
        return resultadoPrimerEntrevista;
    }

    public void setResultadoPrimerEntrevista(String resultadoPrimerEntrevista) {
        this.resultadoPrimerEntrevista = resultadoPrimerEntrevista;
    }

    public String getMotivoDescartePrimerEntrevista() {
        return motivoDescartePrimerEntrevista;
    }

    public void setMotivoDescartePrimerEntrevista(String motivoDescartePrimerEntrevista) {
        this.motivoDescartePrimerEntrevista = motivoDescartePrimerEntrevista;
    }

    public String getComentariosPrimerEntrevista() {
        return comentariosPrimerEntrevista;
    }

    public void setComentariosPrimerEntrevista(String comentariosPrimerEntrevista) {
        this.comentariosPrimerEntrevista = comentariosPrimerEntrevista;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public String getI() {
        return i;
    }

    public void setI(String i) {
        this.i = i;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getExamenConfianza() {
        return examenConfianza;
    }

    public void setExamenConfianza(String examenConfianza) {
        this.examenConfianza = examenConfianza;
    }

    public String getResultadoExamenConfianza() {
        return resultadoExamenConfianza;
    }

    public void setResultadoExamenConfianza(String resultadoExamenConfianza) {
        this.resultadoExamenConfianza = resultadoExamenConfianza;
    }

    public String getExamenPsicometrico() {
        return examenPsicometrico;
    }

    public void setExamenPsicometrico(String examenPsicometrico) {
        this.examenPsicometrico = examenPsicometrico;
    }

    public String getResultadoExamenPsicometrico() {
        return resultadoExamenPsicometrico;
    }

    public void setResultadoExamenPsicometrico(String resultadoExamenPsicometrico) {
        this.resultadoExamenPsicometrico = resultadoExamenPsicometrico;
    }

    public String getNombreEntrevistador() {
        return nombreEntrevistador;
    }

    public void setNombreEntrevistador(String nombreEntrevistador) {
        this.nombreEntrevistador = nombreEntrevistador;
    }

    public String getTipoSegundaEntrevista() {
        return tipoSegundaEntrevista;
    }

    public void setTipoSegundaEntrevista(String tipoSegundaEntrevista) {
        this.tipoSegundaEntrevista = tipoSegundaEntrevista;
    }

    public String getFechaSegundaEntrevista() {
        return fechaSegundaEntrevista;
    }

    public void setFechaSegundaEntrevista(String fechaSegundaEntrevista) {
        this.fechaSegundaEntrevista = fechaSegundaEntrevista;
    }

    public String getHoraSegundaEntrevista() {
        return horaSegundaEntrevista;
    }

    public void setHoraSegundaEntrevista(String horaSegundaEntrevista) {
        this.horaSegundaEntrevista = horaSegundaEntrevista;
    }

    public String getResultadoSegundaEntrevista() {
        return resultadoSegundaEntrevista;
    }

    public void setResultadoSegundaEntrevista(String resultadoSegundaEntrevista) {
        this.resultadoSegundaEntrevista = resultadoSegundaEntrevista;
    }

    public String getMotivoDescarteSegundaEntrevista() {
        return motivoDescarteSegundaEntrevista;
    }

    public void setMotivoDescarteSegundaEntrevista(String motivoDescarteSegundaEntrevista) {
        this.motivoDescarteSegundaEntrevista = motivoDescarteSegundaEntrevista;
    }

    public String getComentariosSegundaEntrevista() {
        return comentariosSegundaEntrevista;
    }

    public void setComentariosSegundaEntrevista(String comentariosSegundaEntrevista) {
        this.comentariosSegundaEntrevista = comentariosSegundaEntrevista;
    }

    public String getResponsableVacante() {
        return responsableVacante;
    }

    public void setResponsableVacante(String responsableVacante) {
        this.responsableVacante = responsableVacante;
    }

    public String getAceptaOferta() {
        return aceptaOferta;
    }

    public void setAceptaOferta(String aceptaOferta) {
        this.aceptaOferta = aceptaOferta;
    }

    public String getMotivoNoAceptaOferta() {
        return motivoNoAceptaOferta;
    }

    public void setMotivoNoAceptaOferta(String motivoNoAceptaOferta) {
        this.motivoNoAceptaOferta = motivoNoAceptaOferta;
    }

    public String getComentariosOferta() {
        return comentariosOferta;
    }

    public void setComentariosOferta(String comentariosOferta) {
        this.comentariosOferta = comentariosOferta;
    }

    public String getSolicitudEmpleo() {
        return solicitudEmpleo;
    }

    public void setSolicitudEmpleo(String solicitudEmpleo) {
        this.solicitudEmpleo = solicitudEmpleo;
    }

    public String getSocioEconomico() {
        return socioEconomico;
    }

    public void setSocioEconomico(String socioEconomico) {
        this.socioEconomico = socioEconomico;
    }

    public String getContratado() {
        return contratado;
    }

    public void setContratado(String contratado) {
        this.contratado = contratado;
    }

    public String getMotivoNoContratado() {
        return motivoNoContratado;
    }

    public void setMotivoNoContratado(String motivoNoContratado) {
        this.motivoNoContratado = motivoNoContratado;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public String getRespostulado() {
        return respostulado;
    }

    public void setRespostulado(String respostulado) {
        this.respostulado = respostulado;
    }

    public String getEmpresaAnterior() {
        return empresaAnterior;
    }

    public void setEmpresaAnterior(String empresaAnterior) {
        this.empresaAnterior = empresaAnterior;
    }

    public String getPuestoAnterior() {
        return puestoAnterior;
    }

    public void setPuestoAnterior(String puestoAnterior) {
        this.puestoAnterior = puestoAnterior;
    }
}
