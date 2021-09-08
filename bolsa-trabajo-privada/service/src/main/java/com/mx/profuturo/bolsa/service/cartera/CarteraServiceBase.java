package com.mx.profuturo.bolsa.service.cartera;

import com.mx.profuturo.bolsa.model.restclient.RequestBean;
import com.mx.profuturo.bolsa.model.service.areasinteres.dto.AreaInteresDTO;
import com.mx.profuturo.bolsa.model.service.cartera.*;
import com.mx.profuturo.bolsa.model.wallet.dto.WalletSearchDTO;
import com.mx.profuturo.bolsa.model.wallet.vo.WalletFiltersVO;
import com.mx.profuturo.bolsa.model.wallet.vo.WalletResultVO;
import com.mx.profuturo.bolsa.model.wallet.vo.WalletReultListVO;
import com.mx.profuturo.bolsa.util.exception.custom.GenericStatusException;
import org.apache.http.annotation.Obsolete;

import java.util.ArrayList;
import java.util.LinkedList;

public abstract class CarteraServiceBase implements CarteraService{

    @Override
    public WalletFiltersVO loadFilters(String modo)throws GenericStatusException{
        LoadFiltersRequest request = new LoadFiltersRequest();
        request.setModo(modo);
        RequestBean<LoadFiltersRequest> genericRequestBean = new RequestBean();
        genericRequestBean.setUriVariables(request.getAsMap());
        return callLoadFilters(genericRequestBean);
    }

    @Override
    public ArrayList<AreaInteresDTO> getSubInterestArea(ArrayList<Integer> areaFilter)throws GenericStatusException{
        GetSubInterestAreaRequest request = new GetSubInterestAreaRequest();
        request.setIds(areaFilter);
        RequestBean<GetSubInterestAreaRequest> genericRequestBean = new RequestBean<>();
        genericRequestBean.setPayload(request);
        return callGetSubInterestArea(genericRequestBean);

    }

    @Override
    public WalletReultListVO findCandidates(WalletSearchDTO filtros)throws GenericStatusException{
    	
        RequestBean<WalletSearchDTO> genericRequestBean = new RequestBean<>();
        genericRequestBean.setPayload(filtros);
        return callFindCandidates(genericRequestBean);
    } 

    protected abstract WalletReultListVO callFindCandidates(RequestBean<WalletSearchDTO> filtros)throws GenericStatusException;
    protected abstract WalletFiltersVO callLoadFilters(RequestBean<LoadFiltersRequest> genericRequestBean)throws GenericStatusException;
    protected abstract ArrayList<AreaInteresDTO> callGetSubInterestArea(RequestBean<GetSubInterestAreaRequest> genericRequestBean)throws GenericStatusException;

}
