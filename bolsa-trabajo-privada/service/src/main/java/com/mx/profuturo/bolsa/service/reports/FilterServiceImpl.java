package com.mx.profuturo.bolsa.service.reports;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.mx.profuturo.bolsa.model.reports.vo.FilterVO;
import com.mx.profuturo.bolsa.model.restclient.ConnectionConfigBean;
import com.mx.profuturo.bolsa.model.restclient.RequestBean;
import com.mx.profuturo.bolsa.model.vo.common.CatalogoVO;
import com.mx.profuturo.bolsa.model.vo.common.SCatalogoVO;
import com.mx.profuturo.bolsa.util.exception.custom.GenericStatusException;
import com.mx.profuturo.bolsa.util.restclient.RestClient;

@Service
@Scope("request")
@Profile({"dev", "test", "prod"})
public class FilterServiceImpl extends FilterServiceBase{

	@Autowired
    RestClient restClient;


    @Value("${ws.bolsatrabajo.server}")
    private String url;

    @Value("${ws.bolsatrabajo.port}")
    private String port;
    
    @Value("${ws.metricos.filtros.analystByGerencia}")
    private String analystByGerencia;

    @Value("${ws.metricos.filtros.getObservadores}")
    private String getObservadores;
    
    @Value("${ws.metricos.filtros.getCentroCostos}")
    private String getCentroCostos;

    @Value("${ws.metricos.filtros.getNivelPuesto}")
    private String getNivelPuesto;

    @Value("${ws.metricos.filtros.getTipoVacante}")
    private String getTipoVacante;


    @Value("${ws.bolsatrabajo.getPeriodos}")
    private String getPeriodos;

    @Value("${ws.metricos.filtros.getAnalistasCorporativo}")
	private String getAnalistasCorporativo;
    
	@Override
	public ArrayList<CatalogoVO> __callgetAnalistasCorporativo() throws GenericStatusException {
		RequestBean genericRequestBean = new RequestBean<>();

		genericRequestBean.setConnectionConfigBean(new ConnectionConfigBean(url, port, getAnalistasCorporativo));
		ArrayList<CatalogoVO> response = null;
        try{
            response = (ArrayList<CatalogoVO>)restClient.exetuteGETList(genericRequestBean, CatalogoVO.class);
            //serviceResponse =  response.getFiltros();

        }catch (Exception e){
            throw new GenericStatusException(e);
        }
        return response;
	}

	@Override
	public ArrayList<CatalogoVO> __callGetPeriodos() throws GenericStatusException {
		ArrayList<CatalogoVO> l = new ArrayList<>();
		CatalogoVO e1 = new CatalogoVO();
		e1.setId(1);
		e1.setDescripcion("Anual");
		CatalogoVO e2 = new CatalogoVO();
		e2.setId(2);
		e2.setDescripcion("Semestral");
		CatalogoVO e3 = new CatalogoVO();
		e3.setId(3);
		e3.setDescripcion("Trimestral");
		l.add(e1);
		l.add(e2);
		l.add(e3);
		return l;
	}

	@Override
	public ArrayList<CatalogoVO> __callGetTipoVacante() throws GenericStatusException {
		ArrayList<CatalogoVO> l = new ArrayList<>();
		CatalogoVO e1 = new CatalogoVO();
		e1.setCodigo("V_CORP");
		e1.setDescripcion("Corporativo");
		CatalogoVO e2 = new CatalogoVO();
		e2.setCodigo("V_CTS");
		e2.setDescripcion("Atención a Cliente");
		l.add(e1);
		l.add(e2);
		return l;
	}

	@Override
	public ArrayList<CatalogoVO> __callGetNivelPuesto() throws GenericStatusException {
		RequestBean genericRequestBean = new RequestBean<>();
		genericRequestBean.setConnectionConfigBean(new ConnectionConfigBean(url, port, getNivelPuesto));
		ArrayList<CatalogoVO> response = null;
        try{
            response = (ArrayList<CatalogoVO>)restClient.exetuteGETList(genericRequestBean, CatalogoVO.class);
            //serviceResponse =  response.getFiltros();

        }catch (Exception e){
            throw new GenericStatusException(e);
        }
        return response;
	}

	@Override
	public ArrayList<CatalogoVO> __callGetCentroCostos() throws GenericStatusException {
		RequestBean genericRequestBean = new RequestBean<>();
		genericRequestBean.setConnectionConfigBean(new ConnectionConfigBean(url, port, getCentroCostos));
		ArrayList<CatalogoVO> response = null;
        try{
            response = (ArrayList<CatalogoVO>)restClient.exetuteGETList(genericRequestBean, CatalogoVO.class);
            //serviceResponse =  response.getFiltros();

        }catch (Exception e){
            throw new GenericStatusException(e);
        }
        return response;
	}

	@Override
	public ArrayList<SCatalogoVO> __callGetObservadores() throws GenericStatusException {
		RequestBean genericRequestBean = new RequestBean<>();
		genericRequestBean.setConnectionConfigBean(new ConnectionConfigBean(url, port, getObservadores));
		ArrayList<SCatalogoVO> response = null;
        try{
            response = (ArrayList<SCatalogoVO>)restClient.exetuteGETList(genericRequestBean, SCatalogoVO.class);
            //serviceResponse =  response.getFiltros();

        }catch (JsonMappingException e){
            response = new ArrayList<>();
        }catch (Exception e){
            throw new GenericStatusException(e);
        }
        return response;
        

	}

	@Override
	public ArrayList<CatalogoVO> __callAnalystByGerencia(RequestBean<Integer> id) throws GenericStatusException {
		RequestBean genericRequestBean = new RequestBean<>();
		id.setConnectionConfigBean(new ConnectionConfigBean(url, port, analystByGerencia));
		ArrayList<CatalogoVO> response = null;
        try{
            response = (ArrayList<CatalogoVO>)restClient.exetuteGETList(genericRequestBean, CatalogoVO.class);
            //serviceResponse =  response.getFiltros();

        }catch (Exception e){
            throw new GenericStatusException(e);
        }
        return response;
	}


}
