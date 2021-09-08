package com.mx.profuturo.bolsa.model.vo.home;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mx.profuturo.bolsa.model.service.dto.NuevaImagenDTO;

import java.util.ArrayList;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Galeria extends SeccionVO {
    private Map<Integer,String> imagenesOut;
    private String textoPrimeraImagen;
    private  ArrayList<NuevaImagenDTO> imagenes;


    public ArrayList<NuevaImagenDTO> getImagenes() {
        return imagenes;
    }

    public void setImagenes(ArrayList<NuevaImagenDTO> imagenes) {
        this.imagenes = imagenes;
    }

    public Map<Integer, String> getImagenesOut() {
        return imagenesOut;
    }

    public void setImagenesOut(Map<Integer, String> imagenes) {
        this.imagenesOut = imagenes;
    }

    public String getTextoPrimeraImagen() {
        return textoPrimeraImagen;
    }

    public void setTextoPrimeraImagen(String textoPrimeraImagen) {
        this.textoPrimeraImagen = textoPrimeraImagen;
    }
}
