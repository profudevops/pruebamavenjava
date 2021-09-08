package com.mx.profuturo.bolsa.service.interview;

import com.mx.profuturo.bolsa.model.recruitment.vo.InterviewInfoVO;
import com.mx.profuturo.bolsa.model.recruitment.vo.InterviewsListVO;
import com.mx.profuturo.bolsa.model.restclient.ConnectionConfigBean;
import com.mx.profuturo.bolsa.model.restclient.RequestBean;
import com.mx.profuturo.bolsa.model.service.auth.dto.BasicDTO;
import com.mx.profuturo.bolsa.model.vo.common.CatalogoVO;
import com.mx.profuturo.bolsa.util.exception.custom.GenericStatusException;
import com.mx.profuturo.bolsa.util.restclient.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


@Service
@Scope("request")
@Profile({"dev", "test", "prod"})
public class InterviewServiceImpl extends InterviewServiceBase {

    @Value("${ws.bolsatrabajo.port}")
    private String port;

    @Value("${ws.bolsatrabajo.server}")
    private String url;


    @Value("${ws.bolsatrabajo.horasDisponibles}")
    private String horasDisponibles;

    @Value("${ws.entrevistas.obtenerEntrevistas}")
    private String obtenerEntrevistas;
    
    @Autowired
    RestClient restClient;

    @Override
    public LinkedList<CatalogoVO> __callgetAvailableSchedule(RequestBean genericRequestBean) throws GenericStatusException{
        LinkedList<CatalogoVO> response = new LinkedList<>();

        genericRequestBean.setConnectionConfigBean(new ConnectionConfigBean(url,port,horasDisponibles));

        try {
            List<CatalogoVO> serviceResponse = restClient.exetutePOSTList(genericRequestBean, CatalogoVO.class);
            if(serviceResponse != null){
                response.addAll(serviceResponse);
            }

        } catch (Exception e) {
            throw new GenericStatusException(e);
        }
        return response;

    }

	@Override
	public InterviewsListVO __callGetInterviews(RequestBean genericRequestBean) throws GenericStatusException {
		InterviewsListVO response = new InterviewsListVO();
        genericRequestBean.setConnectionConfigBean(new ConnectionConfigBean(url,port,obtenerEntrevistas));

        try {
        	 response = restClient.exetutePOST
            		(genericRequestBean, InterviewsListVO.class);

        	 if(null == response.getEntrevistas()){
                 response.setEntrevistas(new ArrayList<InterviewInfoVO>());
                 response.setTotalPaginas(0);
                 response.setPaginaActual(0);
             }

        } catch (Exception e) {
            response.setEntrevistas(new ArrayList<InterviewInfoVO>());
            response.setTotalPaginas(0);
            response.setPaginaActual(0);
        }

		return response;
	}
    
}
