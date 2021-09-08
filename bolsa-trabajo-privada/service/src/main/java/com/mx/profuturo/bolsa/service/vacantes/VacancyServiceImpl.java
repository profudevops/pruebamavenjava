package com.mx.profuturo.bolsa.service.vacantes;

import com.mx.profuturo.bolsa.model.common.GenericParamRequestBean;
import com.mx.profuturo.bolsa.model.restclient.ConnectionConfigBean;
import com.mx.profuturo.bolsa.model.restclient.RequestBean;
import com.mx.profuturo.bolsa.model.service.BaseResponseBean;
import com.mx.profuturo.bolsa.model.service.areasinteres.dto.GenericTextResponseBean;
import com.mx.profuturo.bolsa.model.service.auth.dto.BasicDTO;
import com.mx.profuturo.bolsa.model.service.candidates.dto.AssignVacantResponseBean;
import com.mx.profuturo.bolsa.model.service.common.GenericRequestBean;
import com.mx.profuturo.bolsa.model.service.vacancies.dto.*;
import com.mx.profuturo.bolsa.model.service.vacancies.vo.*;
import com.mx.profuturo.bolsa.model.vo.Candidato.CandidatoDetallesVO;
import com.mx.profuturo.bolsa.model.vo.common.BasicCatalogVO;
import com.mx.profuturo.bolsa.model.vo.common.CatalogoVO;
import com.mx.profuturo.bolsa.model.vo.input.GenerarContenidoBolsaInput;
import com.mx.profuturo.bolsa.model.vo.vacancy.ResumenPublicacionVO;
import com.mx.profuturo.bolsa.model.vo.vacancy.ResumenReclutadorComercialVO;
import com.mx.profuturo.bolsa.model.vo.vacancy.VacanteGerenteComercialVO;
import com.mx.profuturo.bolsa.util.converter.JaxbJacksonObjectMapper;
import com.mx.profuturo.bolsa.util.exception.custom.BackEndExceptionBean;
import com.mx.profuturo.bolsa.util.exception.custom.GenericStatusException;
import com.mx.profuturo.bolsa.util.exception.custom.InterviewException;
import com.mx.profuturo.bolsa.util.restclient.RestClient;
import org.apache.http.annotation.Obsolete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
@Scope("request")
@Profile({"dev", "test", "prod"})
public class VacancyServiceImpl extends VacancyServiceBase {

    @Autowired
    RestClient restClient;

    @Value("${ws.vacantes.cambiarEstadoVacante}")
    private String cambiarEstadoVacanteEndpoint;

    @Value("${ws.vacantes.obtenerBolsasExternasPorRama}")
    private String obtenerBolsasExternasPorRamaEndpoint;

    @Value("${ws.vacantes.obtenerBolsas}")
    private String obtenerBolsasEndpoint;

    @Value("${ws.vacantes.buscarColaboradores}")
    private String buscarColaboradores;

    @Value("${ws.vacantes.buscarObservador}")
    private String buscarObservador;

    @Value("${ws.vacantes.detalleVacante}")
    private String detalleVacanteEndpoint;

    @Value("${ws.vacantes.guardarEstrategiaVacante}")
    private String guardarEstrategiaVacante;

    @Value("${ws.vacantes.guardarEstrategia}")
    private String guardarEstrategia;

    @Value("${ws.vacantes.maestraGuardarPublicacion}")
    private String maestraGuardarPublicacion;

    @Value("${ws.vacantes.contenidoPorBolsa}")
    private String contenidoPorBolsa;

    @Value("${ws.vacantes.vacanteMaestraGuardar}")
    private String vacanteMaestraGuardar;

    @Value("${ws.vacantes.vacanteIniciarProceso}")
    private String vacanteIniciarProceso;

    @Value("${ws.vacantes.obtenerCandidatos}")
    private String obtenerCandidatos;

    @Value("${ws.vacantes.descartar}")
    private String descartar;

    @Value("${ws.vacantes.obtenerContenidoPublicacionVacanteMaestra}")
    private String obtenerContenidoPublicacionVacanteMaestra;

    @Value("${ws.vacantes.obtenerEstrategiaVacanteMaestra}")
    private String obtenerEstrategiaVacanteMaestra;

    @Value("${ws.bolsatrabajo.port}")
    private String port;

    @Value("${ws.bolsatrabajo.server}")
    private String url;

    @Value("${ws.estadosMexicanos}")
    private String estadosMexicanosService;

    @Value("${ws.vacantes.obtenerGerencias}")
    private String obtenerGerencias;

    @Value("${ws.vacantes.obtenerRegionales}")
    private String obtenerRegionales;

    @Value("${ws.vacantes.obtenerInmuebles}")
    private String obtenerInmuebles;

    @Value("${ws.vacantes.obtenerVacanteMaestra}")
    private String obtenerVacanteMaestra;

    @Value("${ws.vacantes.cancelarVacante}")
    private String cancelarVacante;

    @Value(("${ws.vacantes.obtenerDivisiones}"))
    private String obtenerDivisiones;

    @Value("${ws.vacantes.asignarResponsabilidadPublicacion}")
    private String asignarResponsabilidadPublicacion;

    @Value("${ws.vacantes.obtenerNivelPuesto}")
    private String obtenerNivelPuesto;

    @Value("${ws.vacantes.obtenerSugerenciasCandidatos}")
    private String obtenerSugerenciasCandidatos;

    @Value("${ws.vacantes.obtenerEstrategia}")
    private String obtenerEstrategia;

    @Value("${ws.vacantes.obtenerResumenVacanteMaestra}")
    private String obtenerResumenVacanteMaestra;

    @Value("${ws.vacantes.obtenerTodo}")
    private String obtenerTodo;

    @Value("${ws.vacantes.obtenerVacante}")
    private String obtenerVacante;

    @Value("${ws.vacantes.detalleVacanteCerrada}")
    private String detalleVacanteCerrada;

    @Value("${ws.vacantes.obtenerPublicaciones}")
    private String obtenerPublicaciones;

    @Value("${ws.vacantes.editarNumeroPlazas}")
    private String editarNumeroPlazas;

    private final JaxbJacksonObjectMapper mapper = new JaxbJacksonObjectMapper();
    
    @Override
    protected BasicDTO _callUpdateVacant(RequestBean request) throws GenericStatusException {
        return null;
    }

    @Override
    protected BasicDTO _callSaveStrategy(RequestBean<GuardarEstrategiaCorpDTO> genericRequestBean) throws GenericStatusException {
        genericRequestBean.setConnectionConfigBean(new ConnectionConfigBean(url,port,guardarEstrategia));
        BasicDTO response = null;
        try {
            response = restClient.exetutePOST(genericRequestBean, BasicDTO.class);
        } catch (Exception e) {
            throw new GenericStatusException();
        }
        return response;
    }

    @Override
    protected BasicDTO _callSaveMasterVacancyStrategy(RequestBean<InformacionEstrategiaVacanteMaestraDTO> genericRequestBean)throws GenericStatusException{
        genericRequestBean.setConnectionConfigBean(new ConnectionConfigBean(url,port,guardarEstrategiaVacante));
        BasicDTO response = null;
        try {
            response = restClient.exetutePOST(genericRequestBean, BasicDTO.class);
        } catch (Exception e) {
            throw new GenericStatusException();
        }
        return response;

    }

    @Override
    public ArrayList<CatalogoVO> __callGetMexicanStates(RequestBean<GenericParamRequestBean> genericRequestBean)throws GenericStatusException{
        ArrayList<CatalogoVO> mexicanStates = new ArrayList<>();

        genericRequestBean.setConnectionConfigBean(new ConnectionConfigBean(url,port,estadosMexicanosService));
        List<CatalogoVO> response = null;
        try {
            response = restClient.exetuteGETList(genericRequestBean, CatalogoVO.class);

            mexicanStates.addAll(response);
            return mexicanStates;

        } catch (Exception e) {
            throw new GenericStatusException(e);
        }


    }


    @Override
    protected BasicDTO __callSaveMasterVacancyPublication(RequestBean<GuardarPublicacionDTO> genericRequestBean) throws GenericStatusException{
        genericRequestBean.setConnectionConfigBean(new ConnectionConfigBean(url,port,maestraGuardarPublicacion));
        BasicDTO response = null;
        try {
            response = restClient.exetutePOST(genericRequestBean, BasicDTO.class);
        } catch (Exception e) {
            throw new GenericStatusException();
        }
        return response;
    }

    @Override
    protected  BasicDTO __callSaveVacancy(RequestBean<CrearVacanteDTO> genericRequestBean) throws GenericStatusException{
        genericRequestBean.setConnectionConfigBean(new ConnectionConfigBean(url,port,vacanteMaestraGuardar));
        BasicDTO response = new BasicDTO();
        SaveVacancyResponseDTO serviceResponse = null;
        try {
            serviceResponse = restClient.exetutePOST(genericRequestBean, SaveVacancyResponseDTO.class);

            if(serviceResponse.getIdPublicacion()!=null){
            response.setId(serviceResponse.getIdPublicacion());
            }else{
                response.setId(serviceResponse.getId());
            }

        } catch (Exception e) {
            throw new GenericStatusException();
        }
        return response;

    }

    @Override
    protected ResumenVacantesVO __callGetallVacancies(RequestBean<ObtenerEscritorioVacanteDTO> genericRequestBean) throws GenericStatusException{
        genericRequestBean.setConnectionConfigBean(new ConnectionConfigBean(url,port,obtenerTodo));
        ResumenVacantesVO response = null;
        try {
            response = restClient.exetutePOST(genericRequestBean, ResumenVacantesVO.class);
        } catch (Exception e) {
            throw new GenericStatusException();
        }
        return response;
    }

    @Override
    protected GenericTextResponseBean __callStartProcess(RequestBean<IniciarProcesoDTO> genericRequestBean) throws InterviewException,GenericStatusException{
     
    
    
	    GenericTextResponseBean response = null;     
	    genericRequestBean.setConnectionConfigBean(new ConnectionConfigBean(url,port,vacanteIniciarProceso));
	   
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
				throw new InterviewException(new Exception("Ocurrio un error iniciando el proceso"));
			}
	    	
		}
	    
	    
		return response;
    }
    
    
    @Override
    protected GenericTextResponseBean _callAssignPublication(RequestBean<AsignarPublicacionDTO> genericRequestBean) throws GenericStatusException{
        genericRequestBean.setConnectionConfigBean(new ConnectionConfigBean(url,port,asignarResponsabilidadPublicacion));
        GenericTextResponseBean response = null;
        try {
            response = restClient.exetutePOST(genericRequestBean, GenericTextResponseBean.class);
        } catch (Exception e) {
            throw new GenericStatusException();
        }
        return response;

    }


    @Override
    protected GenericTextResponseBean __callDiscardFromProcess(RequestBean<IniciarProcesoDTO> genericRequestBean) throws GenericStatusException{
        genericRequestBean.setConnectionConfigBean(new ConnectionConfigBean(url,port,descartar));
        GenericTextResponseBean response = null;
        try {
            response = restClient.exetutePOST(genericRequestBean, GenericTextResponseBean.class);
        } catch (Exception e) {
            throw new GenericStatusException();
        }
        return response;

    }




    @Override
    protected InformacionPublicacionMaestra __callMasterVacancyInfo(RequestBean<Integer> genericRequestBean) throws GenericStatusException{
        InformacionPublicacionMaestra response = new InformacionPublicacionMaestra();
        genericRequestBean.setConnectionConfigBean(new ConnectionConfigBean(url,port,obtenerContenidoPublicacionVacanteMaestra));
        try {
            response = restClient.exetuteGET(genericRequestBean, InformacionPublicacionMaestra.class);
        } catch (Exception e) {
            throw new GenericStatusException();
        }
        return response;

    }


    @Override
    protected EstrategiaVacanteMaestraVO __callGetMasterVacanctStrategy(RequestBean<Integer> genericRequestBean)throws GenericStatusException{
        EstrategiaVacanteMaestraVO response = new EstrategiaVacanteMaestraVO();
        genericRequestBean.setConnectionConfigBean(new ConnectionConfigBean(url,port,obtenerEstrategiaVacanteMaestra));
        try {

            response = restClient.exetuteGET(genericRequestBean, EstrategiaVacanteMaestraVO.class);

        } catch (Exception e) {
            response.setCorreoObservador("");
            response.setVisibilidad(1);
            response.setEmailObservador("");
            response.setNumeroEmpleadoObservador("0");
            response.setBolsasExternas(new ArrayList<Integer>());
        }
        return response;

    }


    @Override
    protected  ArrayList<CatalogoVO>  __callGetPositionLevels(RequestBean<GenericRequestBean> genericRequestBean)throws GenericStatusException{

        ArrayList<CatalogoVO> response = new ArrayList<>();
        genericRequestBean.setConnectionConfigBean(new ConnectionConfigBean(url,port,obtenerNivelPuesto));
        try {

            List<CatalogoVO> serviceResponse = restClient.exetuteGETList(genericRequestBean, CatalogoVO.class);
            response.addAll(serviceResponse);
        } catch (Exception e) {
            throw new GenericStatusException();
        }
        return response;
    }


    @Override
    protected TablaPostuladosVO __callGetCandidates(RequestBean<BuscarDatosVacanteDTO> genericRequestBean) throws GenericStatusException{

        genericRequestBean.setConnectionConfigBean(new ConnectionConfigBean(url,port,obtenerCandidatos));
        TablaPostuladosVO response = new TablaPostuladosVO();
        try {
            response = restClient.exetutePOST(genericRequestBean, TablaPostuladosVO.class);
        } catch (Exception e) {
            response.setTotalPaginas(0);
            response.setPaginaActual(0);
            response.setPostulados(new ArrayList<ResumenAplicacionVO>());
        }
        return response;
    }

    @Override
    protected BasicDTO _callCreateStrategy(RequestBean request) throws GenericStatusException {
        return null;
    }

    @Override
    protected LinkedList<ResumenVacanteCorp> __callGetCorpObserverVacancies(RequestBean request) throws GenericStatusException {
        return null;
    }

    @Override
    protected LinkedList<ResumenVacanteMaestraAnalistaVO> __callGetAnalystVacancies(RequestBean request) throws GenericStatusException {
        return null;
    }

    @Override
    protected ResumenPublicacionesVO __callGetRecruiterVacancies(RequestBean requestBean) throws GenericStatusException {
        return null;
    }

    @Override
    protected LinkedList<InformacionGeneralVacanteMaestraVO> _callGetMasterVacanciesByUser(RequestBean request) throws GenericStatusException {
        return null;
    }

    @Override
    protected BasicDTO _callcreateSeat(RequestBean request) throws GenericStatusException {
        return null;
    }

    @Override
    protected ResumenVacantesAdminCorpVO __callGetAdministratorVacancies(RequestBean genericRequestBean){
        return null;
    }

    @Override
    public BasicDTO updateVacancy(VacanteDTO vacanteDTO) {
        return null;
    }

    @Override
    protected VacanteGerenteComercialVO _callGetCommercialRecruiterVacancies(RequestBean request) throws GenericStatusException {
        return null;
    }


    @Override
    protected ArrayList<BasicCatalogVO> callGetJobBoard(RequestBean<VacanteDTO> genericRequestBean)throws GenericStatusException{
        genericRequestBean.setConnectionConfigBean(new ConnectionConfigBean(url,port,obtenerBolsasEndpoint));
        ArrayList<BasicCatalogVO> response = null;

        try {
            List<BasicCatalogVO> serviceResponse = restClient.exetuteGETList(genericRequestBean, BasicCatalogVO.class);
            response.addAll(serviceResponse);

        } catch (Exception e) {
            throw new GenericStatusException();
        }
        return response;
    }

    @Override
    protected ArrayList<BasicCatalogVO> callGetExternalJobBoardBranch(RequestBean<GetExternalBranchRequest> genericRequestBean){
        genericRequestBean.setConnectionConfigBean(new ConnectionConfigBean(url,port,obtenerBolsasExternasPorRamaEndpoint));
        List<BasicCatalogVO> serviceResponse = new ArrayList<>();
        ArrayList response = new ArrayList();
        try {
            serviceResponse = restClient.exetuteGETList(genericRequestBean, BasicCatalogVO.class);
            response.addAll(serviceResponse);

        } catch (Exception e) {
        }
        return response;
    }

    @Override
    protected ArrayList<SugerenciaCandidatoVO>__callGetCandidateSuggestions(RequestBean<Integer> genericRequestBean)throws GenericStatusException{
        genericRequestBean.setConnectionConfigBean(new ConnectionConfigBean(url,port,obtenerSugerenciasCandidatos));
        List<SugerenciaCandidatoVO> serviceResponse = new ArrayList<>();
        ArrayList response = new ArrayList();
        try {
            serviceResponse = restClient.exetuteGETList(genericRequestBean, SugerenciaCandidatoVO.class);
            response.addAll(serviceResponse);

        } catch (Exception e) {
        }
        return response;
    }

    @Override
    protected ListaPublicacionesVO __callGetPublications(RequestBean<ObtenerPublicacionesDTO> genericRequestBean) throws GenericStatusException{
        genericRequestBean.setConnectionConfigBean(new ConnectionConfigBean(url,port,obtenerPublicaciones));
        ListaPublicacionesVO response = null;
        try {
            response = restClient.exetutePOST(genericRequestBean, ListaPublicacionesVO.class);
        } catch (Exception e) {
            throw new GenericStatusException();
        }
        return response;
    }

    @Override
    protected GenericTextResponseBean __callEditMasterVacancySpots(RequestBean<EditarPlazasVacanteMaestraDTO> genericRequestBean)throws GenericStatusException{
        GenericTextResponseBean response = new GenericTextResponseBean();
        genericRequestBean.setConnectionConfigBean(new ConnectionConfigBean(url,port,editarNumeroPlazas));
        try {
            response = restClient.exetutePOST(genericRequestBean, GenericTextResponseBean.class);

        } catch (Exception e) {
            throw new GenericStatusException();
        }
        return response;
    }

    @Override
    protected GetVacancyResponse callGetVacancy(RequestBean<VacanteDTO> genericRequestBean){
        genericRequestBean.setConnectionConfigBean(new ConnectionConfigBean(url,port,cambiarEstadoVacanteEndpoint));
        GetVacancyResponse response = null;
        try {
            response = restClient.exetuteGET(genericRequestBean, GetVacancyResponse.class);

        } catch (Exception e) {
        }
        return response;
    }

    @Override
    protected DetalleVacanteVO callVacancyDetail(RequestBean<BuscarDatosVacanteDTO> genericRequestBean) throws GenericStatusException{
        genericRequestBean.setConnectionConfigBean(new ConnectionConfigBean(url,port,detalleVacanteEndpoint));
        DetalleVacanteVO response = null;
        try {
            response = restClient.exetutePOST(genericRequestBean, DetalleVacanteVO.class);
        } catch (Exception e) {
            throw new GenericStatusException();
        }
        return response;
    }

    @Override
    protected BaseResponseBean callChangeStateVacancyResponse(RequestBean<EstadoVacanteDTO> genericRequestBean) throws GenericStatusException{
        genericRequestBean.setConnectionConfigBean(new ConnectionConfigBean(url,port,cambiarEstadoVacanteEndpoint));
        BaseResponseBean response = null;
        try {
            response = restClient.exetutePOST(genericRequestBean, BaseResponseBean.class);
        } catch (Exception e) {
            throw new GenericStatusException();
        }
        return response;
    }

    @Override
    protected List<PersonaProfuturoVO> __callSearchCollaborator(RequestBean<Object> genericRequestBean)throws GenericStatusException{
        genericRequestBean.setConnectionConfigBean(new ConnectionConfigBean(url,port,buscarColaboradores));
        List<PersonaProfuturoVO> response = null;
        try {
            response = restClient.exetuteGETList(genericRequestBean, PersonaProfuturoVO.class);

        } catch (Exception e) {
            throw new GenericStatusException();
        }
        return response;
    }

    @Override
    protected List<PersonaProfuturoVO> __callSearchObserver(RequestBean<String> genericRequestBean){
        genericRequestBean.setConnectionConfigBean(new ConnectionConfigBean(url,port,buscarObservador));
        List<PersonaProfuturoVO> response = null;
        try {
            response = restClient.exetuteGETList(genericRequestBean, PersonaProfuturoVO.class);

        } catch (Exception e) {
        }
        return response;
    }

    @Override
    protected DetalleVacanteCerradaVO __callGetClosedVacancyDetail(RequestBean<Integer> genericRequestBean)throws GenericStatusException{
        genericRequestBean.setConnectionConfigBean(new ConnectionConfigBean(url,port,detalleVacanteCerrada));
        DetalleVacanteCerradaVO response = null;
        try {
            response = restClient.exetuteGET(genericRequestBean, DetalleVacanteCerradaVO.class);
        } catch (Exception e) {
            throw new GenericStatusException();
        }
        return response;
    }

    @Override
    protected List<ContenidoCodigoBolsaVO> __callGenerateContent(RequestBean<GenerarContenidoBolsaInput> genericRequestBean)throws GenericStatusException{
        genericRequestBean.setConnectionConfigBean(new ConnectionConfigBean(url,port,contenidoPorBolsa));
        List<ContenidoCodigoBolsaVO> response = null;
        try {
            response = restClient.exetutePOSTList(genericRequestBean, ContenidoCodigoBolsaVO.class);

        } catch (Exception e) {
            response = Collections.<ContenidoCodigoBolsaVO>emptyList();
        }
        return response;

    }

    @Override
    protected InformacionVacanteVO __callGetVacancyInfo(RequestBean<Integer> genericRequestBean) throws GenericStatusException{
        genericRequestBean.setConnectionConfigBean(new ConnectionConfigBean(url,port,obtenerVacante));
        InformacionVacanteVO response = new InformacionVacanteVO();
        try {
            response = restClient.exetuteGET(genericRequestBean,InformacionVacanteVO.class);

        } catch (Exception e) {
            throw new GenericStatusException();
        }
        return response;
    }

    @Override
    protected EstrategiaVO __callGetStrategyById(RequestBean<Integer> genericRequestBean)throws GenericStatusException{
        EstrategiaVO response = new EstrategiaVO();
        genericRequestBean.setConnectionConfigBean(new ConnectionConfigBean(url,port,obtenerEstrategia));
        try {
            response = restClient.exetuteGET(genericRequestBean, EstrategiaVO.class);

        } catch (Exception e) {
            response.setBolsasExternas(new ArrayList<Integer>());
            response.setObservador("");
        }if((response.getBolsasExternas()==null || response.getBolsasExternas().size()==0)
                && response.getObservador()==null){
            response.setBolsasExternas(new ArrayList<Integer>());
            response.setObservador("");
        }
        return response;
    }

    @Override
    protected List<CatalogoVO> __callGetDepartments(RequestBean<Integer> genericRequestBean){
        genericRequestBean.setConnectionConfigBean(new ConnectionConfigBean(url,port,obtenerGerencias));
        List<CatalogoVO> response = new LinkedList<>();
        try {
            response = restClient.exetuteGETList(genericRequestBean,CatalogoVO.class);

        } catch (Exception e) {
        }
        return response;

    }

    @Override
    protected List<CatalogoVO> __callGetRegions(RequestBean<Integer> genericRequestBean){
        genericRequestBean.setConnectionConfigBean(new ConnectionConfigBean(url,port,obtenerRegionales));
        List<CatalogoVO> response = new LinkedList<>();
        try {
            response = restClient.exetuteGETList(genericRequestBean,CatalogoVO.class);

        } catch (Exception e) {
        }
        return response;

    }

    @Override
    protected List<CatalogoVO> __callFindOffice(RequestBean<Integer> genericRequestBean)throws GenericStatusException{
        genericRequestBean.setConnectionConfigBean(new ConnectionConfigBean(url,port,obtenerInmuebles));
        List<CatalogoVO> response = new LinkedList<>();
        try {
            response = restClient.exetuteGETList(genericRequestBean,CatalogoVO.class);

        } catch (Exception e) {
            throw new GenericStatusException();
        }
        return response;

    }

    @Override
    protected List<CatalogoVO> __callGetDivisions(RequestBean<Integer> genericRequestBean){
        genericRequestBean.setConnectionConfigBean(new ConnectionConfigBean(url,port,obtenerDivisiones));
        List<CatalogoVO> response = new LinkedList<>();
        try {
            response = restClient.exetuteGETList(genericRequestBean,CatalogoVO.class);

        } catch (Exception e) {
        }
        return response;

    }



    @Override
    protected VacanteMaestraVO __callGetMasterVacancy(RequestBean<Integer> genericRequestBean)throws GenericStatusException{
        genericRequestBean.setConnectionConfigBean(new ConnectionConfigBean(url,port,obtenerVacanteMaestra));
        VacanteMaestraVO response = new VacanteMaestraVO();
        try {
            response = restClient.exetuteGET(genericRequestBean, VacanteMaestraVO.class);

        } catch (Exception e) {
        }
        return response;

    }

    @Override
    protected GenericTextResponseBean __callCancelVacancy(RequestBean<Object> genericRequestBean)throws GenericStatusException{
        genericRequestBean.setConnectionConfigBean(new ConnectionConfigBean(url,port,cancelarVacante));
        GenericTextResponseBean response = new GenericTextResponseBean();
        try {
            response = restClient.exetuteGET(genericRequestBean, GenericTextResponseBean.class);

        } catch (Exception e) {
            throw new GenericStatusException();
        }
        return response;

    }

    @Override
    protected ResumenVacanteMaestraVO __callGetMasterVacancySummary(RequestBean<Integer> genericRequestBean)throws GenericStatusException{

        genericRequestBean.setConnectionConfigBean(new ConnectionConfigBean(url,port,obtenerResumenVacanteMaestra));
        ResumenVacanteMaestraVO response = new ResumenVacanteMaestraVO();
        try {
            response = restClient.exetuteGET(genericRequestBean, ResumenVacanteMaestraVO.class);

        } catch (Exception e) {
            throw new GenericStatusException();
        }
        return response;
    }



}
