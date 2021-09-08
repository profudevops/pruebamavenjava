package com.mx.profuturo.bolsa.service.portofolio;

import com.mx.profuturo.bolsa.model.wallet.dto.WalletSearchDTO;
import com.mx.profuturo.bolsa.model.wallet.vo.WalletReultListVO;
import com.mx.profuturo.bolsa.util.exception.custom.GenericStatusException;

public abstract class PortofolioServiceBase implements PortofolioService {

    @Override
    public WalletReultListVO findCandidates(WalletSearchDTO filtros) throws GenericStatusException {
        return this.__callFindCandidates(filtros);
    }


    public abstract WalletReultListVO __callFindCandidates(WalletSearchDTO filtros)throws GenericStatusException;
}
