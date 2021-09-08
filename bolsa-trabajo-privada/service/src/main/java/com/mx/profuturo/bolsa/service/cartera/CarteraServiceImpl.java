package com.mx.profuturo.bolsa.service.cartera;

import com.mx.profuturo.bolsa.model.restclient.ConnectionConfigBean;
import com.mx.profuturo.bolsa.model.restclient.RequestBean;
import com.mx.profuturo.bolsa.model.service.areasinteres.dto.AreaInteresDTO;
import com.mx.profuturo.bolsa.model.service.cartera.GetSubInterestAreaRequest;
import com.mx.profuturo.bolsa.model.service.cartera.GetSubInterestAreaResponse;
import com.mx.profuturo.bolsa.model.service.cartera.LoadFiltersRequest;
import com.mx.profuturo.bolsa.model.service.cartera.LoadFiltersResponse;
import com.mx.profuturo.bolsa.model.wallet.dto.WalletSearchDTO;
import com.mx.profuturo.bolsa.model.wallet.vo.PortofolioFilteresResponseBean;
import com.mx.profuturo.bolsa.model.wallet.vo.WalletFiltersVO;
import com.mx.profuturo.bolsa.model.wallet.vo.WalletReultListVO;
import com.mx.profuturo.bolsa.util.exception.custom.GenericStatusException;
import com.mx.profuturo.bolsa.util.exception.custom.NoResultsException;
import com.mx.profuturo.bolsa.util.restclient.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Scope("request")
@Profile({"dev", "test", "prod"})
public class CarteraServiceImpl extends CarteraServiceBase {

    @Autowired
    RestClient restClient;

    @Value("${ws.bolsatrabajo.server}")
    private String url;

    @Value("${ws.bolsatrabajo.port}")
    private String port;

    @Value("${ws.cartera.cargarFiltros}")
    private String loadPortofolioEndpoint;

    @Value("${ws.cartera.obtenerSubareaInteres}")
    private String obtenerSubareaInteres;

    @Value("${ws.cartera.buscar}")
	private String findCandidatesEndpoint;

    protected WalletFiltersVO callLoadFilters(RequestBean<LoadFiltersRequest> genericRequestBean) throws GenericStatusException{
        genericRequestBean.setConnectionConfigBean(new ConnectionConfigBean(url, port, loadPortofolioEndpoint));
        WalletFiltersVO response = null;
        WalletFiltersVO responseString = null;
        try{
            response = restClient.exetuteGET(genericRequestBean, WalletFiltersVO.class);
            //serviceResponse =  response.getFiltros();

        }catch (Exception e){
            response = new WalletFiltersVO();
        }
        return response;
    }

    @Override
    protected ArrayList<AreaInteresDTO> callGetSubInterestArea(RequestBean<GetSubInterestAreaRequest> genericRequestBean)throws GenericStatusException{
        genericRequestBean.setConnectionConfigBean(new ConnectionConfigBean(url, port, obtenerSubareaInteres));
        ArrayList<AreaInteresDTO> response = new ArrayList<>();
        List<AreaInteresDTO> serviceResponse;
        try{
            serviceResponse = restClient.exetutePOSTList(genericRequestBean, AreaInteresDTO.class);
            response.addAll(serviceResponse);

        }catch (Exception e){
            throw new GenericStatusException(e);
        }
        return response;
    }

	@Override
	protected WalletReultListVO callFindCandidates(RequestBean<WalletSearchDTO> genericRequestBean) throws GenericStatusException {

		genericRequestBean.setConnectionConfigBean(new ConnectionConfigBean(url, port, findCandidatesEndpoint));

		WalletReultListVO response = null;
        try{
            response = restClient.exetutePOST(genericRequestBean, WalletReultListVO.class);
        }catch (Exception e){
            throw new GenericStatusException(e);
        }
        if( response.getResultados()==null || response.getResultados().size()<1){
            throw new NoResultsException();
        }
        return response;
	}

}
