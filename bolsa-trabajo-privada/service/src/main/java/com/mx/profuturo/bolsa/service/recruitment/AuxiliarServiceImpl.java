package com.mx.profuturo.bolsa.service.recruitment;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import com.mx.profuturo.bolsa.model.recruitment.constants.RecruitmentStages;
import com.mx.profuturo.bolsa.model.recruitment.dto.FolioDTO;
import com.mx.profuturo.bolsa.model.recruitment.dto.InterviewInServiceDTO;
import com.mx.profuturo.bolsa.model.recruitment.response.FolioResponseBean;
import com.mx.profuturo.bolsa.model.recruitment.vo.FolioVO;
import com.mx.profuturo.bolsa.model.restclient.ConnectionConfigBean;
import com.mx.profuturo.bolsa.model.restclient.RequestBean;
import com.mx.profuturo.bolsa.model.service.areasinteres.dto.GenericTextResponseBean;
import com.mx.profuturo.bolsa.model.service.common.GenericRequestBean;
import com.mx.profuturo.bolsa.model.vo.common.CatalogoVO;
import com.mx.profuturo.bolsa.util.exception.custom.GenericStatusException;
import com.mx.profuturo.bolsa.util.restclient.RestClient;

@Service
@Scope("request")
@Profile({"dev", "test", "prod"})
public class AuxiliarServiceImpl extends AuxiliarBaseService {

	@Autowired
    RestClient restClient;


    @Value("${ws.bolsatrabajo.server}")
    private String url;

    @Value("${ws.bolsatrabajo.port}")
    private String port;

    @Value("${ws.reclutamiento.getRejectReassons}")
	private String getRejectReassons;
    
    @Value("${ws.reclutamiento.tiposEntrevista}")
    private String getInterviewType;
    

    @Value("${ws.reclutamiento.horasDisponibles}")
    private String getInterviewHours;
    
    @Value("${ws.reclutamiento.buscarFolio}")
    private String getFolio;
    
    
	@Override
	protected List<CatalogoVO> __callGetRejectReassons() throws GenericStatusException {

		RequestBean genericRequestBean = new RequestBean<>();
		genericRequestBean.setConnectionConfigBean(new ConnectionConfigBean(url, port, getRejectReassons));
        List<CatalogoVO> response = null;
        try{
            response = restClient.exetuteGETList(genericRequestBean, CatalogoVO.class);
            //serviceResponse =  response.getFiltros();

        }catch (Exception e){
            throw new GenericStatusException(e);
        }
        return response;
	}

	 public List<CatalogoVO> __callgetInterviewType(RequestBean<GenericRequestBean> genericRequestBean)throws GenericStatusException{
	        genericRequestBean.setConnectionConfigBean(new ConnectionConfigBean(url, port, getInterviewType));
	        List<CatalogoVO> response = null;
	        try{
	            response = restClient.exetuteGETList(genericRequestBean, CatalogoVO.class);
	            //serviceResponse =  response.getFiltros();

	        }catch (Exception e){
	            throw new GenericStatusException(e);
	        }
	        return response;

	    }

	@Override
	protected List<CatalogoVO> __callGetInterviewHours(InterviewInServiceDTO dto) throws GenericStatusException {
		RequestBean<InterviewInServiceDTO> genericRequestBean = new RequestBean<>();
		genericRequestBean.setPayload(dto);
		genericRequestBean.setConnectionConfigBean(new ConnectionConfigBean(url, port, getInterviewHours));
        List<CatalogoVO> response = null;
        try{
            response = restClient.exetutePOSTList(genericRequestBean, CatalogoVO.class);
            //serviceResponse =  response.getFiltros();

        }catch (Exception e){
            throw new GenericStatusException(e);
        }
        return response;
	}

	@Override
	protected LinkedList<FolioVO> __callSearchFolio(String folio) throws GenericStatusException {
		RequestBean<FolioDTO> genericRequestBean = new RequestBean<>();
		FolioDTO dto = new FolioDTO();
		BigDecimal bdFolio = BigDecimal.valueOf(Long.valueOf(folio));
		Map<String,BigDecimal>  uriVariables  = new HashMap<>();
		uriVariables.put("folio", bdFolio);
		genericRequestBean.setUriVariables(uriVariables  );
		genericRequestBean.setConnectionConfigBean(new ConnectionConfigBean(url, port, getFolio));
        FolioResponseBean response = null;
        FolioVO r = new FolioVO();
        LinkedList<FolioVO> out = new LinkedList<>();
        try{
            response =  restClient.exetuteGET(genericRequestBean, FolioResponseBean.class);
            if(null != response.getFolio()) {
            	r.setCodigo(String.valueOf(response.getFolio()));
                r.setDescripcion(this.buildFolioDes(response));
                r.setId(response.getFolio());
                out.add(r);
            }
            
            
        }catch (Exception e){
            throw new GenericStatusException(e);
        }
        
        
        return out;
	}

	private String buildFolioDes(FolioResponseBean r) {
		// TODO Auto-generated method stub
		

		
		StringBuilder sb = new StringBuilder().
				append(r.getNomPuesto()).
				append(" (").
				append(r.getNomCentroCosto()).
				append(" / ").
				append(r.getRegional()).
				append(" )");
		
		
		return sb.toString();
	}
		
	
}
