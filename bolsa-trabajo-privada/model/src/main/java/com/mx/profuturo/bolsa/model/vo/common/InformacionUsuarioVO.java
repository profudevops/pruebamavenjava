package com.mx.profuturo.bolsa.model.vo.common;
import java.io.Serializable;

/**
 * Created by alejandro.hernandez on 10/05/2017.
 */
public class InformacionUsuarioVO implements Serializable {
    private static final long serialVersionUID = -5142915174468388076L;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String mensaje;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
