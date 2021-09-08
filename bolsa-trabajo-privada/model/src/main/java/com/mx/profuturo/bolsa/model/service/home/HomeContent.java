package com.mx.profuturo.bolsa.model.service.home;

public class HomeContent {

    private SeccionVO cabecera;
    private BuscadorVacanteVO buscador;
    private SeccionVO buscadorCodigo;
    private SeccionVO pausaMedia;
    private SeccionVO presentacionVideo;
    private SeccionVO galeria;

    public SeccionVO getCabecera() {
        return cabecera;
    }

    public void setCabecera(SeccionVO cabecera) {
        this.cabecera = cabecera;
    }

    public BuscadorVacanteVO getBuscador() {
        return buscador;
    }

    public void setBuscador(BuscadorVacanteVO buscador) {
        this.buscador = buscador;
    }

    public SeccionVO getBuscadorCodigo() {
        return buscadorCodigo;
    }

    public void setBuscadorCodigo(SeccionVO buscadorCodigo) {
        this.buscadorCodigo = buscadorCodigo;
    }

    public SeccionVO getPausaMedia() {
        return pausaMedia;
    }

    public void setPausaMedia(SeccionVO pausaMedia) {
        this.pausaMedia = pausaMedia;
    }

    public SeccionVO getPresentacionVideo() {
        return presentacionVideo;
    }

    public void setPresentacionVideo(SeccionVO presentacionVideo) {
        this.presentacionVideo = presentacionVideo;
    }

    public SeccionVO getGaleria() {
        return galeria;
    }

    public void setGaleria(SeccionVO galeria) {
        this.galeria = galeria;
    }

    public HomeContent(SeccionVO cabecera, BuscadorVacanteVO buscador, SeccionVO buscadorCodigo, SeccionVO pausaMedia, SeccionVO predentacionVideo, SeccionVO galeria) {
        this.cabecera = cabecera;
        this.buscador = buscador;
        this.buscadorCodigo = buscadorCodigo;
        this.pausaMedia = pausaMedia;
        this.presentacionVideo = predentacionVideo;
        this.galeria = galeria;
    }

    public HomeContent() {

    }
}
