package com.mx.profuturo.bolsa.service.vacantes;

import com.mx.profuturo.bolsa.model.common.GenericParamRequestBean;
import com.mx.profuturo.bolsa.model.recruitment.vo.RecruitmentType;
import com.mx.profuturo.bolsa.model.restclient.RequestBean;
import com.mx.profuturo.bolsa.model.role.*;
import com.mx.profuturo.bolsa.model.service.BaseResponseBean;
import com.mx.profuturo.bolsa.model.service.areasinteres.dto.GenericTextResponseBean;
import com.mx.profuturo.bolsa.model.service.areasinteres.dto.GetAreaInteres;
import com.mx.profuturo.bolsa.model.service.auth.dto.BasicDTO;
import com.mx.profuturo.bolsa.model.service.candidates.dto.SuccessCreateResponseBean;
import com.mx.profuturo.bolsa.model.service.candidates.dto.SuccessGetSeatsResponseBean;
import com.mx.profuturo.bolsa.model.service.common.GenericRequestBean;
import com.mx.profuturo.bolsa.model.service.vacancies.base.VacanteDatosBasicos;
import com.mx.profuturo.bolsa.model.service.vacancies.dto.AbrirPlazasDTO;
import com.mx.profuturo.bolsa.model.service.vacancies.dto.ObtenerPlazasAbiertasPorSedeDTO;
import com.mx.profuturo.bolsa.model.service.vacancies.dto.PublicacionDTO;
import com.mx.profuturo.bolsa.model.service.vacancies.vo.*;
import com.mx.profuturo.bolsa.model.vo.common.BasicCatalogVO;
import com.mx.profuturo.bolsa.model.vo.input.GenerarContenidoBolsaInput;
import com.mx.profuturo.bolsa.model.vo.vacancy.*;
import com.mx.profuturo.bolsa.util.exception.custom.GenericStatusException;
import com.mx.profuturo.bolsa.util.exception.custom.InterviewException;

import java.util.*;


import com.mx.profuturo.bolsa.model.restclient.RequestBean;
import com.mx.profuturo.bolsa.model.role.CorporateRoles;
import com.mx.profuturo.bolsa.model.service.auth.dto.BasicDTO;
import com.mx.profuturo.bolsa.model.service.candidates.dto.SuccessCreateResponseBean;
import com.mx.profuturo.bolsa.model.service.candidates.dto.SuccessGetSeatsResponseBean;
import com.mx.profuturo.bolsa.model.service.vacancies.dto.*;
import com.mx.profuturo.bolsa.model.vo.common.CatalogoVO;
import com.mx.profuturo.bolsa.model.vo.vacancy.PublicacionVO;
import com.mx.profuturo.bolsa.util.exception.custom.GenericStatusException;
import org.apache.http.annotation.Obsolete;
import sun.awt.image.ImageWatched;

import java.util.LinkedList;
import java.util.List;


public abstract class VacancyServiceBase implements VacancyService {

	private final Integer CORP_OFFICE = 581;


	@Override
	public boolean assignPublication(AsignarPublicacionDTO data) throws GenericStatusException{
		RequestBean<AsignarPublicacionDTO> genericRequestBean = new RequestBean<>();
		genericRequestBean.setPayload(data);
		GenericTextResponseBean respuesta = this._callAssignPublication(genericRequestBean);
		String responseText = respuesta.getRespuesta();
		if(responseText.equals("Exito")){
			return true;
		}
		throw new GenericStatusException();
	}

	@Override
	public InformacionVacanteVO getVacancyInfo(int idVacante)throws GenericStatusException{
		RequestBean<Integer> genericRequestBean = new RequestBean<>();
		Map<String,Integer> m = new HashMap<>();
		m.put("idPublicacion",idVacante);
		genericRequestBean.setUriVariables(m);
		InformacionVacanteVO response = __callGetVacancyInfo(genericRequestBean);
		if(response.getExpectativas()==null){
			response.setExpectativas(new ArrayList<String>());
		}
		if(response.getFunciones()==null){
			response.setFunciones(new ArrayList<String>());
		}

		if(null != response.getExpectativas()){
			response.setIndispensable(response.getExpectativas());
		}

		if(response.getIdAreaInteres()!=null && response.getSubArea()!=null){
			response.setIdSubAreaInteres(response.getSubArea());}
		return response;

	}

	@Override
	public DetalleVacanteCerradaVO getClosedVacancyDetail(Integer idVacante)throws GenericStatusException{
		RequestBean<Integer> genericRequestBean = new RequestBean<>();
		Map<String,Integer> m = new HashMap<>();
		m.put("idVacante",idVacante);
		genericRequestBean.setUriVariables(m);
		return __callGetClosedVacancyDetail(genericRequestBean);

	}



	@Override
	public LinkedList<PersonaProfuturoVO> searchCollaborator(String idUsuario,String email, Integer idRama) throws GenericStatusException{
		Map<String,Object> m = new HashMap<>();
		m.put("numeroEmpleado",idUsuario);
		m.put("email",email);
		m.put("idRama", idRama);
		RequestBean<Object> genericRequestBean = new RequestBean<>();
		genericRequestBean.setUriVariables(m);
		List response = __callSearchCollaborator(genericRequestBean);
		LinkedList<PersonaProfuturoVO> serviceResponse = new LinkedList<>();
		serviceResponse.addAll(response);
		return serviceResponse;
	}

	@Override
	public LinkedList<CatalogoVO> getDepartmentsByRegional(int idRegional) throws GenericStatusException{
		Map<String,Integer> m = new HashMap<>();
		m.put("idRegional",idRegional);
		RequestBean<Integer> genericRequestBean = new RequestBean<>();
		genericRequestBean.setUriVariables(m);
		List<CatalogoVO> response = __callGetDepartments(genericRequestBean);
		LinkedList<CatalogoVO> serviceResponse = new LinkedList<>();
		serviceResponse.addAll(response);
		return serviceResponse;

	}

	@Override
	public LinkedList<CatalogoVO> getRegionsByBranch(int idDivisional) throws GenericStatusException{
		Map<String,Integer> m = new HashMap<>();
		m.put("idDivisional",idDivisional);
		RequestBean<Integer> genericRequestBean = new RequestBean<>();
		genericRequestBean.setUriVariables(m);
		List<CatalogoVO> response = __callGetRegions(genericRequestBean);
		LinkedList<CatalogoVO> serviceResponse = new LinkedList<>();
		serviceResponse.addAll(response);
		return serviceResponse;
	}

	@Override
	public LinkedList<PersonaProfuturoVO> searchObserver(String idUsuario) throws GenericStatusException{
		Map<String,String> m = new HashMap<>();
		m.put("emailObservador",idUsuario);
		RequestBean<String> genericRequestBean = new RequestBean<>();
		genericRequestBean.setUriVariables(m);
		List<PersonaProfuturoVO> response = __callSearchObserver(genericRequestBean);
		LinkedList<PersonaProfuturoVO> serviceResponse = new LinkedList<>();
		if(response!=null) {
			serviceResponse.addAll(response);
		}
		return serviceResponse;
	}

	@Override
	public BasicDTO updateVacancy(CrearVacanteDTO data)throws GenericStatusException {
		RequestBean<CrearVacanteDTO> genericRequestBean = new RequestBean();
		genericRequestBean.setPayload(data);
		return this._callUpdateVacant(genericRequestBean);
	}

	@Override
	public BasicDTO saveStrategy(GuardarEstrategiaCorpDTO data) throws GenericStatusException{
		AsignarPublicacionDTO autoAssingdto = new AsignarPublicacionDTO();
		boolean assignSuccess = false;
		RequestBean<GuardarEstrategiaCorpDTO> genericRequestBean = new RequestBean();
		genericRequestBean.setPayload(data);
		BasicDTO response = _callSaveStrategy(genericRequestBean);
		if(response.getId()>0){
			autoAssingdto.setIdEmpleado(data.getReclutador());
			autoAssingdto.setIdPublicacion(response.getId());
			assignSuccess = this.assignPublication(autoAssingdto);
		}
		if (assignSuccess){
			return response;
		}

		throw new GenericStatusException();
	}

	public ArrayList<CatalogoVO> getMexicanStates()throws GenericStatusException{
		RequestBean<GenericParamRequestBean> genericRequestBean = new RequestBean<>();
		return this.__callGetMexicanStates(genericRequestBean);

	}

	@Override
	public BasicDTO saveMasterVacancyStrategy(InformacionEstrategiaVacanteMaestraDTO estrategiaVacanteMaestra)throws GenericStatusException{
		RequestBean<InformacionEstrategiaVacanteMaestraDTO> genericRequestBean = new RequestBean();

		if(null != estrategiaVacanteMaestra.getObservador()){
			estrategiaVacanteMaestra.setIdObservador(estrategiaVacanteMaestra.getObservador());
		}

		genericRequestBean.setPayload(estrategiaVacanteMaestra);
		BasicDTO response = _callSaveMasterVacancyStrategy(genericRequestBean);
		if(response.getId()>0){
			return response;
		}
		throw new GenericStatusException();
	}

	@Override
	public BasicDTO saveVacancy(CrearVacanteDTO data) throws GenericStatusException{
		RequestBean<CrearVacanteDTO> genericRequestBean = new RequestBean();
		if(null!= data.getIndispensable()){
			data.setExpectativas(data.getIndispensable());
		}
		genericRequestBean.setPayload(data);
		BasicDTO response = __callSaveVacancy(genericRequestBean);
		if(response.getId()>0){
			return response;
		}
		throw new GenericStatusException();
	}

	@Override
	public BasicDTO createStrategy(EstrategiaVO data) throws GenericStatusException{
		RequestBean<EstrategiaVO> genericRequestBean = new RequestBean();
		genericRequestBean.setPayload(data);
		BasicDTO response = _callCreateStrategy(genericRequestBean);
		if(response.getId()>0){
			return response;
		}
		throw new GenericStatusException();
	}

	@Override
	public ResumenVacantesVO getMastaerVacanciesByUser(RequestBean genericRequestBean) throws GenericStatusException{
		ResumenVacantesVO resumenVacantesVO = new ResumenVacantesVO();
		resumenVacantesVO.setVacantesMaestras(_callGetMasterVacanciesByUser(genericRequestBean));
		return resumenVacantesVO;

	}

	@Override
	public ArrayList<ContenidoCodigoBolsaVO> generateContent(Integer idPublicacion) throws GenericStatusException{
		GenerarContenidoBolsaInput payload = new GenerarContenidoBolsaInput();
		payload.setIdPublicacion(idPublicacion);
		RequestBean<GenerarContenidoBolsaInput> genericRequestBean = new RequestBean<>();
		genericRequestBean.setPayload(payload);
		List response = __callGenerateContent(genericRequestBean);
		ArrayList<ContenidoCodigoBolsaVO> serviceResponse = new ArrayList<>();
		serviceResponse.addAll(response);
		return serviceResponse;
	}

	@Override
	public BasicDTO saveMasterVacancyPublication(GuardarPublicacionDTO publicacion, String idRole)throws GenericStatusException{

		RequestBean<GuardarPublicacionDTO> genericRequestBean = new RequestBean();

		genericRequestBean.setPayload(publicacion);
		BasicDTO response = __callSaveMasterVacancyPublication(genericRequestBean);
		if(response.getId()>0){
			return response;
		}
		throw new GenericStatusException();

	}

	@Override
	public boolean startProcess(IniciarProcesoDTO iniciarProcesoDTO)throws InterviewException, GenericStatusException{
		RequestBean<IniciarProcesoDTO> genericRequestBean = new RequestBean<>();
		genericRequestBean.setPayload(iniciarProcesoDTO);
		GenericTextResponseBean respuesta = this.__callStartProcess(genericRequestBean);
		return true;

	}

	@Override
	public boolean discardFromProcess(IniciarProcesoDTO descartarDTO)throws GenericStatusException{
		RequestBean<IniciarProcesoDTO> genericRequestBean = new RequestBean<>();
		genericRequestBean.setPayload(descartarDTO);
		GenericTextResponseBean respuesta = this.__callDiscardFromProcess(genericRequestBean);
		String responseText = respuesta.getRespuesta();
		if("Exito".equals(responseText)){
			return true;
		}
		throw new GenericStatusException();

	}

	@Override
	public InformacionPublicacionMaestra getMasterVacancyInfo(Integer idPublicacion)throws GenericStatusException{
		Map<String,Integer> m = new HashMap<>();
		m.put("idPublicacion",idPublicacion);
		RequestBean<Integer> genericRequestBean = new RequestBean<>();
		genericRequestBean.setUriVariables(m);
		InformacionPublicacionMaestra response = __callMasterVacancyInfo(genericRequestBean);
		return response;

	}


	@Override
	public TablaPostuladosVO getCandidates(BuscarDatosVacanteDTO request,String role, Integer paginaActual)throws GenericStatusException{
		request.setIsAdmin(false);

		if(role.equals(CommercialRoles.ADMN)|| role.equals(CorporateRoles.ADMN)){
			request.setIsAdmin(true);}

		RequestBean<BuscarDatosVacanteDTO> genericRequestBean = new RequestBean<>();
		genericRequestBean.setPayload(request);
		TablaPostuladosVO response = __callGetCandidates(genericRequestBean);
		for(ResumenAplicacionVO resumen : response.getPostulados()){
			if(resumen.getTelefono()==null){
				resumen.setTelefono("");
			}
		}
		return response;
	}

	@Override
	public LinkedList<CatalogoVO> getOffice(int idRama, int idGerencia) throws GenericStatusException{
		Map<String,Integer> m = new HashMap<>();
		m.put("idGerencia",idGerencia);
		RequestBean<Integer> genericRequestBean = new RequestBean<>();
		genericRequestBean.setUriVariables(m);
		List<CatalogoVO> response = __callFindOffice(genericRequestBean);
		LinkedList<CatalogoVO> serviceResponse = new LinkedList<>();
		serviceResponse.addAll(response);
		return serviceResponse;
	}

	@Override
	public LinkedList<CatalogoVO> getDivisions(int idRama) throws GenericStatusException{
		return this.getDivisions(idRama, true);
	}
	@Override
	public LinkedList<CatalogoVO> getDivisions(int idRama, Boolean validation) throws GenericStatusException{
		Map<String,Integer> m = new HashMap<>();

		if(validation && idRama== Branches.CORPORATEID){
			m.put("idRama",Branches.CLIENTSID);
		}else {
			m.put("idRama", idRama);
		}

		RequestBean<Integer> genericRequestBean = new RequestBean<>();
		genericRequestBean.setUriVariables(m);
		List<CatalogoVO> response = __callGetDivisions(genericRequestBean);
		LinkedList<CatalogoVO> serviceResponse = new LinkedList<>();

		/*if(idRama== Branches.CORPORATEID){
			Map<String,Integer> m1 = new HashMap<>();
			m1.put("idRama",Branches.CLIENTSID);
			genericRequestBean.setUriVariables(m1);
			List<CatalogoVO> clientResponse = __callGetDivisions(genericRequestBean);
			serviceResponse.addAll(clientResponse);

		}*/
		serviceResponse.addAll(response);
		return serviceResponse;
    }

	@Override
    public VacanteMaestraVO getMasterVacancy(Integer idVacante) throws GenericStatusException{
        Map<String,Integer> m = new HashMap<>();
        m.put("idVacante",idVacante);
        RequestBean<Integer> genericRequestBean = new RequestBean<>();
        genericRequestBean.setUriVariables(m);
        VacanteMaestraVO response = __callGetMasterVacancy(genericRequestBean);
        if(response.getSubArea()!=null){
        	response.setIdSubAreaInteres(response.getSubArea());

		}

		if(null != response.getExpectativas()){
			response.setIndispensable(response.getExpectativas());
		}
		return response;

	}

	//protected abstract SuccessGetSeatsResponseBean _callGetSeats() throws GenericStatusException;
	protected abstract InformacionVacanteVO __callGetVacancyInfo(RequestBean<Integer> genericRequestBean)throws GenericStatusException;
	protected abstract DetalleVacanteCerradaVO __callGetClosedVacancyDetail(RequestBean<Integer> genericRequestBean)throws GenericStatusException;
	protected abstract EstrategiaVO __callGetStrategyById(RequestBean<Integer> genericRequestBean)throws GenericStatusException;
	protected abstract List<CatalogoVO> __callGetDivisions(RequestBean<Integer> genericRequestBean)throws GenericStatusException;
	protected abstract VacanteMaestraVO __callGetMasterVacancy(RequestBean<Integer> genericRequestBean)throws GenericStatusException;
	protected abstract List<CatalogoVO> __callFindOffice(RequestBean<Integer> genericRequestBean)throws GenericStatusException;
	protected abstract List<CatalogoVO> __callGetDepartments(RequestBean<Integer> genericRequestBean)throws GenericStatusException;
	protected abstract List<CatalogoVO> __callGetRegions(RequestBean<Integer> genericRequestBean)throws GenericStatusException;
	protected abstract ArrayList<CatalogoVO> __callGetMexicanStates(RequestBean<GenericParamRequestBean> genericRequestBean)throws GenericStatusException;
	protected abstract TablaPostuladosVO __callGetCandidates(RequestBean<BuscarDatosVacanteDTO> genericRequestBean) throws GenericStatusException;
	protected abstract GenericTextResponseBean __callStartProcess(RequestBean<IniciarProcesoDTO> genericRequestBean) throws GenericStatusException;
	protected abstract InformacionPublicacionMaestra __callMasterVacancyInfo(RequestBean<Integer> genericRequestBean) throws GenericStatusException;
	protected abstract GenericTextResponseBean __callDiscardFromProcess(RequestBean<IniciarProcesoDTO> genericRequestBean) throws GenericStatusException;
	protected abstract  BasicDTO __callSaveVacancy(RequestBean<CrearVacanteDTO> genericRequestBean) throws GenericStatusException;
	protected abstract  BasicDTO _callUpdateVacant(RequestBean genericRequestBean) throws GenericStatusException;
	protected abstract  BasicDTO _callSaveStrategy(RequestBean<GuardarEstrategiaCorpDTO> genericRequestBean) throws GenericStatusException;
	protected abstract  BasicDTO _callCreateStrategy(RequestBean<EstrategiaVO> genericRequestBean) throws GenericStatusException;
	protected abstract BasicDTO __callSaveMasterVacancyPublication(RequestBean<GuardarPublicacionDTO> genericRequestBean) throws GenericStatusException;
	protected abstract BasicDTO _callSaveMasterVacancyStrategy(RequestBean<InformacionEstrategiaVacanteMaestraDTO> genericRequestbean)throws GenericStatusException;
	protected abstract List<ContenidoCodigoBolsaVO> __callGenerateContent(RequestBean<GenerarContenidoBolsaInput> genericRequestBean) throws GenericStatusException;
	protected abstract List<PersonaProfuturoVO> __callSearchCollaborator(RequestBean<Object> genericRequestBean) throws GenericStatusException;
	protected abstract LinkedList<ResumenVacanteCorp> __callGetCorpObserverVacancies(RequestBean request) throws GenericStatusException;
	protected abstract LinkedList<ResumenVacanteMaestraAnalistaVO> __callGetAnalystVacancies(RequestBean request) throws GenericStatusException;
	protected abstract ResumenPublicacionesVO  __callGetRecruiterVacancies(RequestBean requestBean) throws GenericStatusException;
	protected abstract LinkedList<InformacionGeneralVacanteMaestraVO> _callGetMasterVacanciesByUser(RequestBean request) throws GenericStatusException;
	protected abstract List<PersonaProfuturoVO> __callSearchObserver(RequestBean<String> genericRequestBean);
	protected abstract GenericTextResponseBean _callAssignPublication(RequestBean<AsignarPublicacionDTO> genericRequestBean) throws GenericStatusException;
	//protected abstract  SuccessCreateResponseBean _callGetVacantMaster(RequestBean request) throws GenericStatusException;
	//protected abstract  SuccessCreateResponseBean _callSaveVacantMaster(RequestBean request) throws GenericStatusException;
	protected abstract  BasicDTO _callcreateSeat(RequestBean request) throws GenericStatusException;
	//protected abstract  SuccessGetSeatsResponseBean _callGetSeats(RequestBean request) throws GenericStatusException;

	@Override
	public ResumenVacantesVO getAllVacancies(String role, String idUsuario,int PaginaActual) throws GenericStatusException{
		ResumenVacantesVO response = new ResumenVacantesVO();


		RequestBean<ObtenerEscritorioVacanteDTO> genericRequestBean = new RequestBean();
		ObtenerEscritorioVacanteDTO data = new ObtenerEscritorioVacanteDTO();
		data.setIdUsuario(idUsuario);
		data.setIdRol(role);
		data.setPaginaActual(PaginaActual);
		genericRequestBean.setPayload(data);

		response = this.__callGetallVacancies(genericRequestBean);

		if(null!=response.getVacantesMaestrasAtnClientes()){
			for(ResumenVacanteMaestraAnalistaVO resumen : response.getVacantesMaestrasAtnClientes()){
				if(resumen.getResumen()==null){
					resumen.setResumen(new LinkedList<ResumenPlazaMaestraAnalistaVO>());
				}
			}
		}
		if(null!=response.getVacantesReclutadorComercial()){
			response.getVacantesReclutadorComercial().setIdUsuario(idUsuario);
			if(null==response.getVacantesReclutadorComercial().getPublicaciones()){
				response.getVacantesReclutadorComercial().setPublicaciones(new LinkedList<ResumenReclutadorComercialVO>());
			}else{
				for(ResumenReclutadorComercialVO plaza : response.getVacantesReclutadorComercial().getPublicaciones()){
					plaza.setIdVacante(response.getVacantesReclutadorComercial().getIdVacante());
				}
			}
		}

		return response;
	}

	@Override
	public ResumenVacanteMaestraVO getMasterVacancySummary(int idVacante,String idUsuario, String role)throws GenericStatusException{

		Map<String,Object> m = new HashMap<>();
		m.put("idVacante",idVacante);
		m.put("idUsuario",idUsuario);
		m.put("role",role);
		RequestBean<Integer> genericRequestBean = new RequestBean<>();
		genericRequestBean.setUriVariables(m);
		ResumenVacanteMaestraVO respuesta = this.__callGetMasterVacancySummary(genericRequestBean);

		if(respuesta.getPublicaciones()==null){
			respuesta.setPublicaciones(new LinkedList<ResumenPlazaMaestraVO>());
		}
		return respuesta;

	}

	@Override
	public ListaPublicacionesVO getPublications(ObtenerPublicacionesDTO obtenerPublicacionesDTO)throws GenericStatusException{
		RequestBean<ObtenerPublicacionesDTO> genericRequestBean = new RequestBean();
		genericRequestBean.setPayload(obtenerPublicacionesDTO);
		return __callGetPublications(genericRequestBean);
	}

	protected abstract ResumenVacantesAdminCorpVO __callGetAdministratorVacancies(RequestBean genericRequestBean);


	protected ResumenVacantesVO getAllAdministratorVacancies(RequestBean genericRequestBean){

		ResumenVacantesVO result = new ResumenVacantesVO();
		result.setResumenVacantesAdminCorpVO(__callGetAdministratorVacancies(genericRequestBean));

		return result;
	}

	protected ResumenVacantesVO getCommercialRecruiterVacancies(RequestBean genericRequestBean)throws GenericStatusException{

		ResumenVacantesVO result = new ResumenVacantesVO();
		result.setVacantesReclutadorComercial(_callGetCommercialRecruiterVacancies(genericRequestBean));

		return result;
	}

	protected ResumenVacantesVO getCommerCialObserverVacancies(RequestBean genericRequestBean)throws GenericStatusException{

		ResumenVacantesVO result = new ResumenVacantesVO();
		result.setResumenObservadorComercial(__callGetAdministratorVacancies(genericRequestBean));

		return result;
	}


	protected ResumenVacantesVO getAnalystMasterVacancies(RequestBean request)throws GenericStatusException{
		ResumenVacantesVO result = new ResumenVacantesVO();

		result.setVacantesMaestrasAnalista(__callGetAnalystVacancies(request));

		return result;


	}

	protected ResumenVacantesVO getClientAtnMasterVacancies(RequestBean request)throws GenericStatusException{
		ResumenVacantesVO result = new ResumenVacantesVO();

		result.setVacantesMaestrasAtnClientes(__callGetAnalystVacancies(request));

		return result;
	}



	protected  ResumenVacantesVO getAllRecruiterVacancies(RequestBean genericRequestBean)throws GenericStatusException{
		ResumenVacantesVO result = new ResumenVacantesVO();
		result.setResumenPublicaciones(__callGetRecruiterVacancies(genericRequestBean));
		return result;
	}

	public  LinkedList<VacanteDatosBasicos> search(){

		return null;
	}

	/*@Override
	public GetJobBoardResponse getJobBoard(int idVacante){
		VacanteDTO request = new VacanteDTO();
		request.setIdVacante(idVacante);
		RequestBean<VacanteDTO> genericRequestBean = new RequestBean();
		genericRequestBean.setUriVariables(request.getAsMap());
		return this.callGetJobBoard(genericRequestBean);

	}*/

	@Override
	public ArrayList<BasicCatalogVO> getExternalJobBoardBranch(int idRama)throws GenericStatusException{
		GetExternalBranchRequest request = new GetExternalBranchRequest();
		request.setIdRama(idRama);
		RequestBean<GetExternalBranchRequest> genericRequestBean = new RequestBean();
		genericRequestBean.setUriVariables(request.getAsMap());
		return callGetExternalJobBoardBranch(genericRequestBean);
	}

	@Override
	public GetVacancyResponse getVacancy(int idVacante){
		VacanteDTO request = new VacanteDTO();
		request.setIdVacante(idVacante);
		RequestBean<VacanteDTO> genericRequestBean = new RequestBean();
		genericRequestBean.setUriVariables(request.getAsMap());
		return callGetVacancy(genericRequestBean);
	}

	@Override
	public DetalleVacanteVO vacancyDetail(BuscarDatosVacanteDTO request, String role)throws GenericStatusException{

		request.setIsAdmin(false);
		if(role.equals(CommercialRoles.ADMN)|| role.equals(CorporateRoles.ADMN)){
			request.setIsAdmin(true);}
		RequestBean<BuscarDatosVacanteDTO> genericRequestBean = new RequestBean();
		genericRequestBean.setPayload(request);
		return callVacancyDetail(genericRequestBean);
	}

	@Override
	public IsObserverNeeded getRecruitentType(BuscarDatosVacanteDTO request, String role)throws GenericStatusException{

		request.setIsAdmin(false);
		if(role.equals(CommercialRoles.ADMN)|| role.equals(CorporateRoles.ADMN)){
			request.setIsAdmin(true);}
		RequestBean<BuscarDatosVacanteDTO> genericRequestBean = new RequestBean();
		genericRequestBean.setPayload(request);
		DetalleVacanteVO vacante = callVacancyDetail(genericRequestBean);
		IsObserverNeeded isObserverNeeded = new IsObserverNeeded();
		isObserverNeeded.setTieneObservador(!RecruitmentType.ASESOR.equals(vacante.getTipoReclutamiento()));
		return isObserverNeeded;
	}

	@Override
	public boolean editVacancySpots(EditarPlazasVacanteMaestraDTO editarPlazas)throws GenericStatusException{

		RequestBean<EditarPlazasVacanteMaestraDTO> genericRequestBean = new RequestBean();
		genericRequestBean.setPayload(editarPlazas);
		GenericTextResponseBean response= __callEditMasterVacancySpots(genericRequestBean);
		if (response.getRespuesta().equals("Exito")){
			return true;
		}
		throw new GenericStatusException();
	}


	@Override
	public ArrayList<BasicCatalogVO> getJobBoard(int idVacante)throws GenericStatusException{
		VacanteDTO request = new VacanteDTO();
		request.setIdVacante(idVacante);
		RequestBean<VacanteDTO> genericRequestBean = new RequestBean();
		genericRequestBean.setUriVariables(request.getAsMap());
		return callGetJobBoard(genericRequestBean);
	}

	@Override
	public boolean changeStateVacancy(EstadoVacanteDTO request) throws GenericStatusException{
		RequestBean<EstadoVacanteDTO> genericRequestBean = new RequestBean();
		genericRequestBean.setPayload(request);
		BaseResponseBean response = callChangeStateVacancyResponse(genericRequestBean);
		if(response.isSuccess()){return true;}
		throw new GenericStatusException();
	}

	public void create(){

	}

	public void update(){

	}

	public void delete(){

	}

	public VacanteDatosBasicos get(){
		return null;
	}

	@Override
	public List<VacanteDatosBasicos> searchVacanciesByName(BusquedaDTO busquedaDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VacanteVO getVacancyById(BasicDTO basicDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	public abstract BasicDTO updateVacancy(VacanteDTO vacanteDTO);


	@Override
	public boolean cancelVacancy(Integer idPublicacion, String idUsuario) throws GenericStatusException {
		Map<String,Object> m = new HashMap<>();
		m.put("idPublicacion",idPublicacion);
		m.put("idUsuario",idUsuario);
		RequestBean<Object> genericRequestBean = new RequestBean<>();
		genericRequestBean.setUriVariables(m);
		GenericTextResponseBean respuesta = this.__callCancelVacancy(genericRequestBean);
		String responseText = respuesta.getRespuesta();
		if(responseText.equals("Exito")){
			return true;
		}
		throw new GenericStatusException();
	}

	@Override
	public ArrayList<SugerenciaCandidatoVO> getCandidateSuggestion(Integer idVacante, Integer idSede, Integer idPublicacion ) throws GenericStatusException{
		ArrayList<SugerenciaCandidatoVO> sugerencias = new ArrayList<>();
		Map<String,Integer> m = new HashMap<>();
		m.put("idVacante",idVacante);
		m.put("idSede",idSede);
		m.put("idPublicacion",idPublicacion);

		RequestBean<Integer> genericRequestBean = new RequestBean<>();
		genericRequestBean.setUriVariables(m);
		sugerencias = this.__callGetCandidateSuggestions(genericRequestBean);
		for(SugerenciaCandidatoVO sugerencia : sugerencias){
			if(sugerencia.getNivelEstudios()==null){
				sugerencia.setNivelEstudios("");
			}
			if(sugerencia.getTelefono()==null){
				sugerencia.setTelefono("");
			}
		}

		return sugerencias;
	}

	@Override
	public EstrategiaVO getStrategyById(int idPublicacion)throws GenericStatusException{
		EstrategiaVO estrategia = new EstrategiaVO();
		Map<String,Integer> m = new HashMap<>();
		m.put("idPublicacion",idPublicacion);
		RequestBean<Integer> genericRequestBean = new RequestBean<>();
		genericRequestBean.setUriVariables(m);
		estrategia = this.__callGetStrategyById(genericRequestBean);

		return estrategia;

	}


	@Override
	public EstrategiaVacanteMaestraVO getMasterVacancyStrategy(int idPublicacion)throws GenericStatusException{

		EstrategiaVacanteMaestraVO estrategiaVacanteMaestraVO = new EstrategiaVacanteMaestraVO();
		Map<String,Integer> m = new HashMap<>();
		m.put("idPublicacion",idPublicacion);
		RequestBean<Integer> genericRequestBean = new RequestBean<>();
		genericRequestBean.setUriVariables(m);
		estrategiaVacanteMaestraVO = this.__callGetMasterVacanctStrategy(genericRequestBean);
		if(null != estrategiaVacanteMaestraVO.getEmailObservador()){
			estrategiaVacanteMaestraVO.setCorreoObservador(estrategiaVacanteMaestraVO.getEmailObservador());
		}else{
			estrategiaVacanteMaestraVO.setCorreoObservador("");
			estrategiaVacanteMaestraVO.setNumeroEmpleadoObservador("0");
		}
		if(null == estrategiaVacanteMaestraVO.getBolsasExternas()){
			estrategiaVacanteMaestraVO.setBolsasExternas(new ArrayList<Integer>());
		}
		return estrategiaVacanteMaestraVO;
	}

	@Override
	public ArrayList<CatalogoVO> getPositionLevels()throws GenericStatusException{
		ArrayList<CatalogoVO> positionLevels = new ArrayList();

		RequestBean<GenericRequestBean> genericRequestBean = new RequestBean<>();
		positionLevels = this.__callGetPositionLevels(genericRequestBean);

		return positionLevels;

	}


	@Override
	public CatalogoVO getStrategyForm(BasicDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	protected abstract GenericTextResponseBean __callEditMasterVacancySpots(RequestBean<EditarPlazasVacanteMaestraDTO> genericRequestBean)throws GenericStatusException;
	protected abstract ListaPublicacionesVO __callGetPublications(RequestBean<ObtenerPublicacionesDTO> genericRequestBean) throws GenericStatusException;
	protected abstract ResumenVacantesVO __callGetallVacancies(RequestBean<ObtenerEscritorioVacanteDTO> requestBean) throws GenericStatusException;
	protected abstract VacanteGerenteComercialVO _callGetCommercialRecruiterVacancies(RequestBean request) throws GenericStatusException;
	protected abstract ResumenVacanteMaestraVO __callGetMasterVacancySummary(RequestBean<Integer> genericRequestBean)throws GenericStatusException;
	protected abstract ArrayList<SugerenciaCandidatoVO>__callGetCandidateSuggestions(RequestBean<Integer> genericRequestBean)throws GenericStatusException;
	protected abstract ArrayList<CatalogoVO> __callGetPositionLevels(RequestBean<GenericRequestBean> genericRequestBean)throws GenericStatusException;
	protected abstract EstrategiaVacanteMaestraVO __callGetMasterVacanctStrategy(RequestBean<Integer> genericRequestBean)throws GenericStatusException;
	protected abstract GenericTextResponseBean __callCancelVacancy(RequestBean<Object> genericRequestBean)throws GenericStatusException;
	protected abstract ArrayList<BasicCatalogVO> callGetJobBoard(RequestBean<VacanteDTO> genericRequestBean)throws GenericStatusException;
	protected abstract ArrayList<BasicCatalogVO> callGetExternalJobBoardBranch(RequestBean<GetExternalBranchRequest> genericRequestBean)throws GenericStatusException;
	protected abstract GetVacancyResponse callGetVacancy(RequestBean<VacanteDTO> genericRequestBean);
	protected abstract DetalleVacanteVO callVacancyDetail(RequestBean<BuscarDatosVacanteDTO> genericRequestBean)throws GenericStatusException;
	protected abstract BaseResponseBean callChangeStateVacancyResponse(RequestBean<EstadoVacanteDTO> genericRequestBean) throws GenericStatusException;


}


