package com.mx.profuturo.bolsa.service.hiring;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.mx.profuturo.bolsa.model.restclient.ConnectionConfigBean;
import com.mx.profuturo.bolsa.model.service.areasinteres.dto.GenericTextResponseBean;
import com.mx.profuturo.bolsa.model.service.hiringform.dto.AccesoFormularioContratacionDTO;
import com.mx.profuturo.bolsa.model.service.hiringform.dto.GuardarEtapaFormularioDTO;
import com.mx.profuturo.bolsa.model.service.hiringform.dto.RecuperarFormularioDTO;
import com.mx.profuturo.bolsa.model.service.hiringform.dto.TerminarFormularioDTO;
import com.mx.profuturo.bolsa.model.service.hiringform.vo.CandidatoContratacionVO;
import com.mx.profuturo.bolsa.model.service.hiringform.vo.DatosFormularioVO;
import com.mx.profuturo.bolsa.util.exception.custom.GenericStatusException;
import com.mx.profuturo.bolsa.util.restclient.RestClient;
import com.mx.profuturo.bolsa.model.restclient.RequestBean;

@Service
@Scope("request")
@Profile({"dev", "test", "prod"})
public class HiringFormServiceImpl extends HiringFormServiceBase {


    @Autowired
    RestClient restClient;


    @Value("${ws.bolsatrabajo.server}")
    private String url;

    @Value("${ws.bolsatrabajo.port}")
    private String port;


	@Value(("${ws.reclutamiento.avanceFormulario}"))
	private String dataHiring;
	
	
	@Override
	public CandidatoContratacionVO validateHiringForm(AccesoFormularioContratacionDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CandidatoContratacionVO __callHiValidateCredentials(AccesoFormularioContratacionDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean __callSaveFormData(GuardarEtapaFormularioDTO dto) throws GenericStatusException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	@Override
	public DatosFormularioVO __callDataHiring(RecuperarFormularioDTO recudto) throws GenericStatusException {
	    RequestBean<Integer> genericRequestBean = new RequestBean<Integer>();
	    Map<String, Integer> uriVariables = new HashMap<String, Integer>();
	    uriVariables.put("proceso", recudto.getIdProceso());
		genericRequestBean.setUriVariables(uriVariables);
	    
		genericRequestBean.setConnectionConfigBean(new ConnectionConfigBean(url, port, dataHiring));
		DatosFormularioVO response = null;
        try{
            response = restClient.exetuteGET(genericRequestBean, DatosFormularioVO.class);

        }catch (Exception e){
            throw new GenericStatusException(e);
        }
        return response;
	}*/
	
	
	@Override
    public DatosFormularioVO __callDataHiring(RecuperarFormularioDTO recudto) throws GenericStatusException {
		
		RequestBean<RecuperarFormularioDTO> genericRequestBean = new RequestBean<>();
		genericRequestBean.setPayload(recudto);
		
		genericRequestBean.setConnectionConfigBean(new ConnectionConfigBean(url,port,dataHiring));
        DatosFormularioVO response = null;
        try {
            response = restClient.exetutePOST(genericRequestBean, DatosFormularioVO.class);

            return response;
        } catch (Exception e) {
            throw new GenericStatusException();
        }
    }

	@Override
	public Boolean __callEndHiringForm(TerminarFormularioDTO endFormdto) throws GenericStatusException {
		// TODO Auto-generated method stub
		return null;
	}

}
