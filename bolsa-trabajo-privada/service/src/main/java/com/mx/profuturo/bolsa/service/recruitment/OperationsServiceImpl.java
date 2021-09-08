package com.mx.profuturo.bolsa.service.recruitment;

import com.mx.profuturo.bolsa.model.recruitment.dto.GenericStageQueryDTO;
import com.mx.profuturo.bolsa.model.recruitment.response.DatosEntrevistaResponseBean;
import com.mx.profuturo.bolsa.model.recruitment.response.ProcesoReclutamientoResponseBean;
import com.mx.profuturo.bolsa.model.recruitment.response.SaveFolioResponseBean;
import com.mx.profuturo.bolsa.model.recruitment.service.dto.FolioDTO;
import com.mx.profuturo.bolsa.model.recruitment.service.dto.InterviewAppointmentDTO;
import com.mx.profuturo.bolsa.model.recruitment.service.dto.InterviewResponseDTO;
import com.mx.profuturo.bolsa.model.recruitment.service.dto.PsychometricTestDTO;
import com.mx.profuturo.bolsa.model.recruitment.service.dto.TrustTestDTO;
import com.mx.profuturo.bolsa.model.recruitment.vo.FolioVO;
import com.mx.profuturo.bolsa.model.restclient.ConnectionConfigBean;
import com.mx.profuturo.bolsa.model.restclient.RequestBean;
import com.mx.profuturo.bolsa.model.service.areasinteres.dto.GenericTextResponseBean;
import com.mx.profuturo.bolsa.model.service.auth.dto.BasicDTO;
import com.mx.profuturo.bolsa.model.service.candidates.dto.IniciarReclutamientoDTO;
import com.mx.profuturo.bolsa.model.service.common.GenericRequestBean;
import com.mx.profuturo.bolsa.model.vo.common.CatalogoVO;
import com.mx.profuturo.bolsa.model.wallet.vo.WalletFiltersVO;
import com.mx.profuturo.bolsa.util.converter.JaxbJacksonObjectMapper;
import com.mx.profuturo.bolsa.util.exception.custom.BackEndExceptionBean;
import com.mx.profuturo.bolsa.util.exception.custom.GenericStatusException;
import com.mx.profuturo.bolsa.util.exception.custom.InterviewException;
import com.mx.profuturo.bolsa.util.restclient.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@Service
@Scope("request")
@Profile({"dev", "test", "prod"})
public class OperationsServiceImpl extends OperationsServiceBase{

    @Autowired
    RestClient restClient;


    @Value("${ws.bolsatrabajo.server}")
    private String url;

    @Value("${ws.bolsatrabajo.port}")
    private String port;


    
    @Value("${ws.reclutamiento.getRecruitmentData}")
    private String getRecruitmentData;
    
    @Value("${ws.reclutamiento.getNoHandledInterviewData}")
    private String getNoHandledInterviewData;
    
    @Value("${ws.reclutamiento.getInterviewData}")
    private String getInterviewData;

    @Value("${ws.reclutamiento.hasPublicationSpaces}")
    private String hasPublicationSpaces;

    @Value("${ws.reclutamiento.saveGenericStage}")
	private String saveGenericStage;

    @Value("${ws.reclutamiento.savePsychometricTest}")
	private String savePsychometricTest;

    @Value("${ws.reclutamiento.saveTrustTest}")
	private String saveTrustTest;

    @Value("${ws.reclutamiento.guardarFolio}")
	private String saveSaveFolio;



    @Value("${ws.reclutamiento.saveInterviewAppointmentDTO}")
	private String saveInterviewAppointmentDTO;

    
    @Value("${ws.reclutamiento.saveInterviewResponse}")
 	private String saveInterviewResponse;
    
    private final JaxbJacksonObjectMapper mapper = new JaxbJacksonObjectMapper();
    

    public ProcesoReclutamientoResponseBean __getRecruitmentData(RequestBean<Integer> genericRequestBean, Integer step) throws GenericStatusException{
        ProcesoReclutamientoResponseBean response;
        genericRequestBean.setConnectionConfigBean(new ConnectionConfigBean(url, port, getRecruitmentData));
        try{
            response = restClient.exetuteGET(genericRequestBean, ProcesoReclutamientoResponseBean.class);

        }catch (Exception e){
            throw new GenericStatusException(e);
        }
		return response;
    }

    public LinkedList<DatosEntrevistaResponseBean> __getNoHandledInterviewData(RequestBean<Integer> genericRequestBean) throws GenericStatusException{
        LinkedList<DatosEntrevistaResponseBean> response;
        genericRequestBean.setConnectionConfigBean(new ConnectionConfigBean(url, port, getNoHandledInterviewData));
        try{
            response = (LinkedList<DatosEntrevistaResponseBean>) restClient.exetuteGETList(genericRequestBean, DatosEntrevistaResponseBean.class);

        }catch (Exception e){
            throw new GenericStatusException(e);
        }
        return response;
    }

    public DatosEntrevistaResponseBean __getInterviewData(RequestBean<Integer> genericRequestBean,String modo) throws GenericStatusException{
        DatosEntrevistaResponseBean response;
        genericRequestBean.setConnectionConfigBean(new ConnectionConfigBean(url, port, getInterviewData));
        try{
            response = restClient.exetuteGET(genericRequestBean, DatosEntrevistaResponseBean.class);

        }catch (Exception e){
            throw new GenericStatusException(e);
        }
        return response;
    }

    public GenericTextResponseBean __callHasPublicationSpaces(RequestBean<Integer> genericRequestBean) throws GenericStatusException{
    	genericRequestBean.setConnectionConfigBean(new ConnectionConfigBean(url, port, hasPublicationSpaces));
    	GenericTextResponseBean response = null;
        try{
            response = restClient.exetuteGET(genericRequestBean, GenericTextResponseBean.class);

        }catch (Exception e){
            throw new GenericStatusException(e);
        }
        return response;

    }


	@Override
	public void create(IniciarReclutamientoDTO dto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected GenericTextResponseBean __callSaveGenericStage(RequestBean<GenericStageQueryDTO> genericRequestBean) throws GenericStatusException {
		genericRequestBean.setConnectionConfigBean(new ConnectionConfigBean(url,port,saveGenericStage));
	    GenericTextResponseBean response = null;
	    try {
	        response = restClient.exetutePOST(genericRequestBean, GenericTextResponseBean.class);
	        
	    } catch (Exception e) {
	        throw new GenericStatusException();
	    }
	    
	    if(!"Exito".equals(response.getRespuesta())){
			throw new GenericStatusException();
		}
	    
	    return response;
	}

	@Override
	protected GenericTextResponseBean  __callSavePsychometricTest(RequestBean<PsychometricTestDTO> genericRequestBean)
			throws GenericStatusException {
		genericRequestBean.setConnectionConfigBean(new ConnectionConfigBean(url,port,savePsychometricTest));
	    GenericTextResponseBean response = null;
	    try {
	        response = restClient.exetutePOST(genericRequestBean, GenericTextResponseBean.class);
	        
	    } catch (Exception e) {
	        throw new GenericStatusException();
	    }
	    
	    if(!"Exito".equals(response.getRespuesta())){
			throw new GenericStatusException();
		}
	    
	    return response;
	}

	@Override
	protected GenericTextResponseBean __callSaveTrustTest(RequestBean<TrustTestDTO> genericRequestBean) throws GenericStatusException {
		// TODO Auto-generated method stub
		genericRequestBean.setConnectionConfigBean(new ConnectionConfigBean(url,port,saveTrustTest));
	    GenericTextResponseBean response = null;
	    try {
	        response = restClient.exetutePOST(genericRequestBean, GenericTextResponseBean.class);
	        
	    } catch (Exception e) {
	        throw new GenericStatusException();
	    }
	    
	    if(!"Exito".equals(response.getRespuesta())){
			throw new GenericStatusException();
		}
	    
	    return response;
	}

	@Override
	protected GenericTextResponseBean __callSaveFolio(RequestBean<FolioDTO> genericRequestBean) throws GenericStatusException {
		// TODO Auto-generated method stub
		genericRequestBean.setConnectionConfigBean(new ConnectionConfigBean(url,port,saveSaveFolio));
	    GenericTextResponseBean response = null;
	 	    String rawResponse = null;
	 	    try {
	 	       rawResponse = restClient.exetuteRawPOST(genericRequestBean);
	 	       response = this.mapper.readValue(rawResponse, GenericTextResponseBean.class);
	 	    } catch (Exception e) {
	 	        throw new InterviewException(e);
	 	    }
	 	    
	 	    if(!"Exito".equals(response.getRespuesta())){
	 	    	try {
	 				BackEndExceptionBean eb = this.mapper.readValue(rawResponse, BackEndExceptionBean.class);
	 				throw new InterviewException(eb);
	 			} catch (IOException e) {
	 				throw new InterviewException(new Exception("Ocurrio un error guardando el folio"));
	 			}
	 	    	
	 		}
	 	    
	 	    return response;
	}

	@Override
	protected GenericTextResponseBean __callSaveInterviewAppointmentDTO(RequestBean<InterviewAppointmentDTO> genericRequestBean)
			throws InterviewException {
		// TODO Auto-generated method stub
		genericRequestBean.setConnectionConfigBean(new ConnectionConfigBean(url,port,saveInterviewAppointmentDTO));
	    GenericTextResponseBean response = null;
	    String rawResponse = null;
	    try {
	       rawResponse = restClient.exetuteRawPOST(genericRequestBean);
	       response = this.mapper.readValue(rawResponse, GenericTextResponseBean.class);
	    } catch (Exception e) {
	        throw new InterviewException(e);
	    }
	    
	    if(!"Exito".equals(response.getRespuesta())){
	    	try {
				BackEndExceptionBean eb = this.mapper.readValue(rawResponse, BackEndExceptionBean.class);
				throw new InterviewException(eb);
			} catch (IOException e) {
				throw new InterviewException(new Exception("Ocurrio un error agendando la cita"));
			}
	    	
		}
	    
	    return response;
	}

	@Override
	protected GenericTextResponseBean __callSaveInterviewResponse(RequestBean<InterviewResponseDTO> genericRequestBean)
			throws GenericStatusException {
		genericRequestBean.setConnectionConfigBean(new ConnectionConfigBean(url,port,saveInterviewResponse));
	    GenericTextResponseBean response = null;
	    try {
	        response = restClient.exetutePOST(genericRequestBean, GenericTextResponseBean.class);
	        
	    } catch (Exception e) {
	        throw new GenericStatusException();
	    }
	    
	    if(!"Exito".equals(response.getRespuesta())){
			throw new GenericStatusException();
		}
	    
	    return response;
	}
	

	@Override
	protected LinkedList<FolioVO> __callSearchFolio(RequestBean<String> genericRequestBean)
			throws GenericStatusException {
		// TODO Auto-generated method stub
		return null;
	}

}
