package com.mx.profuturo.bolsa.service.portofolio;

import com.mx.profuturo.bolsa.model.service.areasinteres.dto.AreaInteresDTO;
import com.mx.profuturo.bolsa.model.wallet.dto.WalletSearchDTO;
import com.mx.profuturo.bolsa.model.wallet.vo.WalletReultListVO;
import com.mx.profuturo.bolsa.util.exception.custom.GenericStatusException;

import java.util.ArrayList;

public interface PortofolioService {

    WalletReultListVO findCandidates(WalletSearchDTO filtros)throws GenericStatusException;
    //ArrayList<AreaInteresDTO> getInterestsubarea()throws GenericStatusException;
}
