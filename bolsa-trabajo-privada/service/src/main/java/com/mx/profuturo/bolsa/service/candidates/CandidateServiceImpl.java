package com.mx.profuturo.bolsa.service.candidates;

import com.mx.profuturo.bolsa.model.service.areasinteres.dto.GenericTextResponseBean;
import com.mx.profuturo.bolsa.model.service.candidates.dto.*;
import com.mx.profuturo.bolsa.model.service.common.GenericRequestBean;
import com.mx.profuturo.bolsa.model.vo.Candidato.*;
import com.mx.profuturo.bolsa.model.vo.vacancy.VacanteListaVacanteVO;
import com.mx.profuturo.bolsa.util.converter.JaxbJacksonObjectMapper;
import com.mx.profuturo.bolsa.util.exception.custom.BackEndExceptionBean;
import com.mx.profuturo.bolsa.util.exception.custom.GenericStatusException;
import com.mx.profuturo.bolsa.util.exception.custom.InterviewException;
import com.mx.profuturo.bolsa.util.restclient.RestClient;
import com.mx.profuturo.bolsa.model.restclient.RequestBean;
import com.mx.profuturo.bolsa.model.restclient.ConnectionConfigBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Scope("request")
@Profile({"dev", "test", "prod"})
public class CandidateServiceImpl extends CandidateServiceBase {

    @Value("${ws.bolsatrabajo.server}")
    private String url;
    
//    @Value("${ws.bolsatrabajo.server.was}")
//	private String urlServerWas;

    @Value("${ws.candidatos.enviarCartera}")
    private String enviarCarteraEndpoint;

    @Value("${ws.candidatos.obtenerCandidatosEnReclutamiento}")
    private String obtenerCandidatosEnReclutamiento;

    @Value("${ws.candidatos.obtenerListaSimpleMisVacantes}")
    private String obtenerListaSimpleMisVacantes;

    @Value("${ws.candidatos.obtenerListaSimpleOtrasVacantes}")
    private String obtenerListaSimpleOtrasVacantes;

    @Value("${ws.candidatos.obtenerCalificacionesTags}")
    private String obtenerCalificacionesTags;

    @Value("${ws.candidatos.obtenerDetalleCandidato}")
    private String obtenerDetalleCandidato;

    @Value("${ws.candidatos.obtenerHistorialCandidato}")
    private String obtenerHistorialCandidato;

    @Value("${ws.candidatos.asignarVacante}")
    private String asignarVacante;

    @Value("${ws.candidatos.calificarCandidato}")
    private String calificarCandidato;

    @Value("${ws.candidatos.editarCorreo}")
    private String editarCorreo;

    @Value("${ws.candidatos.descargaInformacion}")
    private String obtenerInfoCandidato;

    @Value("${ws.candidatos.recomendarVacante}")
    private String recomendarVacante;

    @Value("${ws.bolsatrabajo.port}")
    private String port;
    
//    @Value("${ws.bolsatrabajo.port.was}")
//    private String portWas;

    @Autowired
    private RestClient restClient;


    private final JaxbJacksonObjectMapper mapper = new JaxbJacksonObjectMapper();

    @Override
    protected ResumenCandidatosListVO callGetInitCandidates(RequestBean<CandidatesRecruitmentDTO> genericRequestBean) throws GenericStatusException{
        genericRequestBean.setConnectionConfigBean(new ConnectionConfigBean(url,port,obtenerCandidatosEnReclutamiento));
        ResumenCandidatosListVO serviceResponse = new ResumenCandidatosListVO();
        try {
            serviceResponse = restClient.exetutePOST(genericRequestBean, ResumenCandidatosListVO.class);


        } catch (Exception e) {
            serviceResponse.setResumenCandidatos(new ArrayList<CandidatoResumenVO>());
            serviceResponse.setTotalPaginas(0);
            serviceResponse.setPaginaActual(0);
        }
        if(null==serviceResponse.getResumenCandidatos()){
            serviceResponse.setResumenCandidatos(new ArrayList<CandidatoResumenVO>());
            serviceResponse.setTotalPaginas(0);
            serviceResponse.setPaginaActual(0);
        }
        return serviceResponse;
    }

    @Override
    protected GetDetailsCandidateResponseBean callGetDetailsCandidate(RequestBean<GetDetailsCandidateRequestBean> genericRequestBean) throws GenericStatusException{
        genericRequestBean.setConnectionConfigBean(new ConnectionConfigBean(url,port,enviarCarteraEndpoint));
        GetDetailsCandidateResponseBean response = null;
        try {
            response = restClient.exetuteGET(genericRequestBean, GetDetailsCandidateResponseBean.class);

        } catch (Exception e) {
            throw new GenericStatusException(e);
        }
        return response;
    }

    @Override
    protected GenericTextResponseBean callSetScoreCandidate(RequestBean<SetScoreCandidateInDTO> genericRequestBean) throws GenericStatusException{
        genericRequestBean.setConnectionConfigBean(new ConnectionConfigBean(url,port,calificarCandidato));
        GenericTextResponseBean response = null;
        try {
            response = restClient.exetutePOST(genericRequestBean, GenericTextResponseBean.class);

        } catch (Exception e) {
            throw new GenericStatusException(e);
        }
        return response;
    }

    @Override
    protected GenericTextResponseBean callSetVacantCandidate(RequestBean<GenericIdCandidateInDTO> genericRequestBean) throws GenericStatusException{
        genericRequestBean.setConnectionConfigBean(new ConnectionConfigBean(url,port,asignarVacante));
        GenericTextResponseBean response = null;
        try {
            response = restClient.exetutePOST(genericRequestBean, GenericTextResponseBean.class);

        } catch (Exception e) {
            throw new GenericStatusException(e);
        }
        return response;
    }




    @Override
    protected CandidatoDetallesVO __callgetCanidateDetails(RequestBean<Integer> genericRequestBean) throws GenericStatusException{

        CandidatoDetallesVO response = new CandidatoDetallesVO();

        genericRequestBean.setConnectionConfigBean(new ConnectionConfigBean(url,port,obtenerDetalleCandidato));
        try {
            response = restClient.exetuteGET(genericRequestBean, CandidatoDetallesVO.class);
        } catch (Exception e) {
            throw new GenericStatusException();
        }
        return response;

    }


    @Override
    protected RecruitmentHistoryVO __callGetCandidateHistory(RequestBean<Integer> genericRequestBean) throws GenericStatusException{

        RecruitmentHistoryVO response = null;

        genericRequestBean.setConnectionConfigBean(new ConnectionConfigBean(url,port,obtenerHistorialCandidato));
        try {
            response = restClient.exetuteGET(genericRequestBean, RecruitmentHistoryVO.class);
        } catch (Exception e) {
        	
        	response = new RecruitmentHistoryVO();
        }
        return response;
    }


    @Override
    protected GenericTextResponseBean callEditEmail(RequestBean<CandidateEditEmailRequestBean> genericRequestBean) throws GenericStatusException{
        GenericTextResponseBean response;
        genericRequestBean.setConnectionConfigBean(new ConnectionConfigBean(url,port,editarCorreo));
       
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
     				throw new InterviewException(new Exception("Ocurrio un error editando el correo"));
     			}
     	    	
     		}
     	    
     	    return response;
    }

    @Override
    protected GenericTextResponseBean callGetCandidateInformation(RequestBean<CandidateInfoRequestBean> genericRequestBean) throws GenericStatusException {
    	GenericTextResponseBean response;
        genericRequestBean.setConnectionConfigBean(new ConnectionConfigBean(url,port,obtenerInfoCandidato));
        try {
            response = restClient.exetutePOST(genericRequestBean, GenericTextResponseBean.class); //ListCandidateInformation.class);
        } catch (Exception e) {
            throw new GenericStatusException(e);
        }

        return response;

    }


    @Override
    protected GenericTextResponseBean callRecommendCandidate(RequestBean<GenericIdCandidateInDTO> genericRequestBean) throws GenericStatusException{
        genericRequestBean.setConnectionConfigBean(new ConnectionConfigBean(url,port,recomendarVacante));
        GenericTextResponseBean response = null;
        try {
            response = restClient.exetutePOST(genericRequestBean, GenericTextResponseBean.class);

        } catch (Exception e) {
            throw new GenericStatusException(e);
        }
        return response;
    }

    @Override
    protected GetVacantResponseBean callGetVacant(RequestBean<GenericIdCandidateRequestBean> genericRequestBean) throws GenericStatusException{
        genericRequestBean.setConnectionConfigBean(new ConnectionConfigBean(url,port,enviarCarteraEndpoint));
        GetVacantResponseBean response = null;
        try {
            response = restClient.exetuteGET(genericRequestBean, GetVacantResponseBean.class);

        } catch (Exception e) {
            throw new GenericStatusException(e);
        }
        return response;
    }

    @Override
    protected AssignVacantResponseBean callAssignVacant(RequestBean<AssignVacantRequestBean> genericRequestBean) throws GenericStatusException
    
    {
        
        GenericTextResponseBean response = null;     
        genericRequestBean.setConnectionConfigBean(new ConnectionConfigBean(url,port,asignarVacante));
	   
	    String rawResponse = null;
	    try {
	       rawResponse = restClient.exetuteRawPOST(genericRequestBean);
	       response = this.mapper.readValue(rawResponse, GenericTextResponseBean.class);
	    } catch (Exception e) {
	        throw new InterviewException(e);
	    }
	    
	    AssignVacantResponseBean finalResponse = new AssignVacantResponseBean();
	    finalResponse.setRespuesta(response.getRespuesta());
	    
	    if(!"Exito".equals(response.getRespuesta())){
	    	try {
				BackEndExceptionBean eb = this.mapper.readValue(rawResponse, BackEndExceptionBean.class);
				throw new InterviewException(eb);
			} catch (IOException e) {
				throw new InterviewException(new Exception("Ocurrio un error iniciando el proceso"));
			}
	    	
		}
	    
	    
		return finalResponse;
    }
    

    @Override
    protected QualifyCandidateResponseBean callQualifyCandidate(RequestBean<QualifyCandidateRequestBean> genericRequestBean) throws GenericStatusException{
        genericRequestBean.setConnectionConfigBean(new ConnectionConfigBean(url,port,enviarCarteraEndpoint));
        QualifyCandidateResponseBean response = null;
        try {
            response = restClient.exetutePOST(genericRequestBean, QualifyCandidateResponseBean.class);

        } catch (Exception e) {
            throw new GenericStatusException(e);
        }
        return response;
    }


    @Override
    protected GenericTextResponseBean callSendToPortofolio(RequestBean<SendWalletRequestBean> genericRequestBean) throws GenericStatusException{
        genericRequestBean.setConnectionConfigBean(new ConnectionConfigBean(url,port,enviarCarteraEndpoint));
        GenericTextResponseBean response = null;
        try {
            response = restClient.exetutePOST(genericRequestBean, GenericTextResponseBean.class);

        } catch (Exception e) {
            throw new GenericStatusException(e);
        }
        return response;
    }

    @Override
    protected List<CandidatesBean> callRecruitmentCandidates(RequestBean<RecruitmentCandidatesRequestBean> genericRequestBean)  throws GenericStatusException{
        genericRequestBean.setConnectionConfigBean(new ConnectionConfigBean(url,port,enviarCarteraEndpoint));
        List<CandidatesBean> response = null;
        try {
            response = restClient.exetuteGETList(genericRequestBean, CandidatesBean.class);

        } catch (Exception e) {
            throw new GenericStatusException(e);
        }
        return response;
    }

    @Override
    protected CandidateHistoryResponseBean callCandidateHistory(RequestBean<CandidateHistoryRequestBean> genericRequestBean) throws GenericStatusException{
        genericRequestBean.setConnectionConfigBean(new ConnectionConfigBean(url,port,enviarCarteraEndpoint));
        CandidateHistoryResponseBean response = null;
        try {
            response = restClient.exetuteGET(genericRequestBean, CandidateHistoryResponseBean.class);

        } catch (Exception e) {
            throw new GenericStatusException(e);
        }
        return response;
    }

    @Override
    protected List<VacanteListaVacanteVO> callSimpleListMyVacancy(RequestBean<SimpleListMyVacancyRequestBean> genericRequestBean) throws GenericStatusException{
        genericRequestBean.setConnectionConfigBean(new ConnectionConfigBean(url,port,obtenerListaSimpleMisVacantes));
        List<VacanteListaVacanteVO> response = null;
        try {
            response = restClient.exetuteGETList(genericRequestBean, VacanteListaVacanteVO.class);

        } catch (Exception e) {
            throw new GenericStatusException(e);
        }
        return response;
    }

    @Override
    protected List<VacanteListaVacanteVO> callSimpleListOtherVacancy(RequestBean<SimpleListMyVacancyRequestBean> genericRequestBean) throws GenericStatusException{
        genericRequestBean.setConnectionConfigBean(new ConnectionConfigBean(url,port,obtenerListaSimpleOtrasVacantes));
        List<VacanteListaVacanteVO> response = null;
        try {
            response = restClient.exetuteGETList(genericRequestBean, VacanteListaVacanteVO.class);

        } catch (Exception e) {
            throw new GenericStatusException(e);
        }
        return response;
    }

    @Override
    protected RecommendVacancyResponseBean callRecommendVacancy(RequestBean<RecommendVacancyRequestBean> genericRequestBean) throws GenericStatusException{
        genericRequestBean.setConnectionConfigBean(new ConnectionConfigBean(url,port,enviarCarteraEndpoint));
        RecommendVacancyResponseBean response = null;
        try {
            response = restClient.exetutePOST(genericRequestBean, RecommendVacancyResponseBean.class);

        } catch (Exception e) {
            throw new GenericStatusException(e);
        }
        return response;
    }

    @Override
    protected List<ScoreVO> __callGetScoreTags(RequestBean<GenericRequestBean> genericRequestBean) throws GenericStatusException{
        genericRequestBean.setConnectionConfigBean(new ConnectionConfigBean(url,port,obtenerCalificacionesTags));
        List<ScoreVO> response = null;
        try {
            response = restClient.exetuteGETList(genericRequestBean, ScoreVO.class);

        } catch (Exception e) {
            throw new GenericStatusException(e);
        }
        return response;

    }


}
