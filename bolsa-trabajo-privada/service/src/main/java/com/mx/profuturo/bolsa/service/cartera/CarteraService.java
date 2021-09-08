package com.mx.profuturo.bolsa.service.cartera;

import com.mx.profuturo.bolsa.model.service.areasinteres.dto.AreaInteresDTO;
import com.mx.profuturo.bolsa.model.service.cartera.*;
import com.mx.profuturo.bolsa.model.wallet.dto.WalletSearchDTO;
import com.mx.profuturo.bolsa.model.wallet.vo.WalletFiltersVO;
import com.mx.profuturo.bolsa.model.wallet.vo.WalletReultListVO;
import com.mx.profuturo.bolsa.util.exception.custom.GenericStatusException;

import java.util.ArrayList;

public interface CarteraService {

    WalletFiltersVO loadFilters(String modo)throws GenericStatusException;
    ArrayList<AreaInteresDTO> getSubInterestArea(ArrayList<Integer> areaFilter)throws GenericStatusException;
    WalletReultListVO findCandidates(WalletSearchDTO filtros)throws GenericStatusException;

}
