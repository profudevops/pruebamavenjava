package com.mx.profuturo.bolsa.model.service.home;

public class SeccionVO {

    protected String titulo;
    protected String subtitulo;
    protected String texto;
    protected String mediaURL;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSubtitulo() {
        return subtitulo;
    }

    public void setSubtitulo(String subtitulo) {
        this.subtitulo = subtitulo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public SeccionVO(String titulo, String subtitulo, String texto, String mediaURL) {
        this.titulo = titulo;
        this.subtitulo = subtitulo;
        this.texto = texto;
        this.mediaURL = mediaURL;
    }

    public SeccionVO() {
    }
}
