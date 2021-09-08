package com.mx.profuturo.bolsa.model.wallet.vo;

import java.util.LinkedList;

public class WalletReultListVO {

    private int paginaActual;
    private int totalPaginas;
    LinkedList<WalletResultVO> resultados;

    public int getPaginaActual() {
        return paginaActual;
    }

    public void setPaginaActual(int paginaActual) {
        this.paginaActual = paginaActual;
    }

    public int getTotalPaginas() {
        return totalPaginas;
    }

    public void setTotalPaginas(int totalPaginas) {
        this.totalPaginas = totalPaginas;
    }

    public LinkedList<WalletResultVO> getResultados() {
        return resultados;
    }

    public void setResultados(LinkedList<WalletResultVO> reultados) {
        this.resultados = reultados;
    }
}
