package com.mx.profuturo.bolsa.model.wallet.vo;

import com.mx.profuturo.bolsa.model.service.cartera.LoadFiltersResponse;

public class PortofolioFilteresResponseBean {

    private WalletFiltersVO filtros;

    public WalletFiltersVO getFiltros() {
        return filtros;
    }

    public void setFiltros(WalletFiltersVO filtros) {
        this.filtros = filtros;
    }

}
