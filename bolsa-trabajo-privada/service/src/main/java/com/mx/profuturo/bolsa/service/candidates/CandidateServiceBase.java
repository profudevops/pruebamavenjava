package com.mx.profuturo.bolsa.service.candidates;

import com.mx.profuturo.bolsa.model.service.areasinteres.dto.GenericTextResponseBean;
import com.mx.profuturo.bolsa.model.service.auth.response.UserCredentialsVO;
import com.mx.profuturo.bolsa.model.service.candidates.dto.*;
import com.mx.profuturo.bolsa.model.service.common.GenericRequestBean;
import com.mx.profuturo.bolsa.model.service.vacancies.vo.PersonaProfuturoVO;
import com.mx.profuturo.bolsa.model.vo.Candidato.*;
import com.mx.profuturo.bolsa.model.vo.vacancy.VacanteListaVacanteVO;
import com.mx.profuturo.bolsa.service.vacantes.VacancyService;

import org.springframework.beans.factory.annotation.Autowired;

import com.mx.profuturo.bolsa.model.restclient.RequestBean;
import com.mx.profuturo.bolsa.model.role.CommercialRoles;
import com.mx.profuturo.bolsa.model.role.CorporateRoles;
import com.mx.profuturo.bolsa.util.exception.custom.GenericStatusException;

import java.util.*;

public abstract class CandidateServiceBase implements CandidateService{

	@Autowired
	VacancyService vacancyService;

	@Override
	public ResumenCandidatosListVO getInitCandidates(UserCredentialsVO user, int paginaActual, String search, Integer idRama) throws GenericStatusException {
		//GetInitCandidatesRequestBean request = new GetInitCandidatesRequestBean();
		//request.setPaginaActual(paginaActual);
		CandidatesRecruitmentDTO dto = this.buildInRecruitmentDTO(user,paginaActual,idRama);
		if( null != search && !search.equals("")){
			dto.setCandidato(search);
		}
		RequestBean<CandidatesRecruitmentDTO> genericRequestBean = new RequestBean<CandidatesRecruitmentDTO>();
		genericRequestBean.setPayload(dto);
		return this.callGetInitCandidates(genericRequestBean);
	}


	@Override
	public CandidatesRecruitmentDTO buildInRecruitmentDTO(UserCredentialsVO user, int paginaActual, Integer idRama) throws GenericStatusException {

		CandidatesRecruitmentDTO dto = new CandidatesRecruitmentDTO();
		String role = user.getRole();
		dto.setRole(role);
		if(CorporateRoles.RECRUITER.equals(role) ||
				CorporateRoles.COSTUMER_RECRUITER.equals(role) ||
				CommercialRoles.RECRUITER.equals(role) ||
				CommercialRoles.ANALYST.equals(role)) { 
			dto.setIdUsuario(user.getId());
			dto.setIsAdmin(false);
			dto.setIsObserver(false);
		}else if(CommercialRoles.CONSULTOR.equals(role)){ 
			dto.setIsAdmin(false); 
			dto.setIsObserver(false);
			dto.setIdUsuario(user.getId());
			dto.setIdColaboradores(this.getCollaboratorsList(user.getId(), idRama));
		}else if(CorporateRoles.ADMN.equals(role) || CommercialRoles.ADMN.equals(role)) {
			dto.setIsAdmin(true);
			dto.setIsObserver(false);
			dto.setIdUsuario(user.getId());
		}else if(CommercialRoles.OBSERVER.equals(role) || CorporateRoles.OBSERVER.equals(role)) {
			dto.setIdUsuario(user.getId());
			dto.setIsAdmin(false);
			dto.setIsObserver(true);
		}
		dto.setIdRama(user.getIdBranch());
		dto.setPaginaActual(paginaActual);
		return dto;
	}


	private List<String> getCollaboratorsList(String id, Integer idRama) throws GenericStatusException {
		List<PersonaProfuturoVO> colaboradores = vacancyService.searchCollaborator(id, "",idRama);
		List<String> ids = new ArrayList<String>();
		for(PersonaProfuturoVO colaborador:colaboradores) {
			ids.add(colaborador.getIdUsuario());
		}
		return ids;
	}


	@Override
	public LinkedList<ScoreVO> getScoreTags()throws GenericStatusException{
		GenericRequestBean request = new GenericRequestBean();
		LinkedList<ScoreVO> scoreList = new LinkedList<>();
		RequestBean<GenericRequestBean> genericRequestBean = new RequestBean();
		scoreList.addAll(__callGetScoreTags(genericRequestBean));

		return scoreList;

	}

	@Override
	public ScoreVO setScoreCandidate(SetScoreCandidateInDTO score) throws GenericStatusException {
		RequestBean<SetScoreCandidateInDTO> genericRequestBean = new RequestBean();
		genericRequestBean.setPayload(score);

		GenericTextResponseBean respuesta = callSetScoreCandidate(genericRequestBean);
		String responseText = respuesta.getRespuesta();
		if("Exito".equals(responseText)){

			ScoreVO response = new ScoreVO();
			LinkedList<ScoreVO> list = this.getScoreTags();
			for(ScoreVO q: list) {
				if(q.getId() == score.getIdCalificacion()) {
					for(TagVO tag:q.getTags()) {
						if(score.getSubCalificacion().contains(tag.getId())) {
							response.getTags().add(tag);
						}
					}
					response.setId(q.getId());
					response.setCodigo(q.getCodigo());
					response.setDescripcion(q.getDescripcion());
					break;
				}
			}

			return response;

		}
		throw new GenericStatusException();
	}

	@Override
	public boolean setVacantCandidate(GenericIdCandidateInDTO dto) throws GenericStatusException {

		RequestBean<GenericIdCandidateInDTO> genericRequestBean = new RequestBean();
		if(null != dto.getIdVacante()){
			dto.setIdPublicacion(dto.getIdVacante());
		}
		genericRequestBean.setPayload(dto);

		GenericTextResponseBean respuesta = callSetVacantCandidate(genericRequestBean);
		String responseText = respuesta.getRespuesta();
		if("Exito".equals(responseText)){
			return true;
		}
		throw new GenericStatusException();
	}

	@Override
	public boolean recommendCandidate(GenericIdCandidateInDTO genericIdCandidateInDTO) throws GenericStatusException {
		RequestBean<GenericIdCandidateInDTO> genericRequestBean = new RequestBean();
		if(null != genericIdCandidateInDTO.getIdVacante()){
			genericIdCandidateInDTO.setIdPublicacion(genericIdCandidateInDTO.getIdVacante());
		}
		genericRequestBean.setPayload(genericIdCandidateInDTO);

		GenericTextResponseBean respuesta = callRecommendCandidate(genericRequestBean);
		String responseText = respuesta.getRespuesta();
		if("Exito".equals(responseText)){
			return true;
		}
		throw new GenericStatusException();
	}

	@Override
	public GetVacantResponseBean getVacant(String idCandidato) throws GenericStatusException {
		GenericIdCandidateRequestBean request = new GenericIdCandidateRequestBean();
		request.setIdCandidato(Integer.parseInt(idCandidato));
		RequestBean<GenericIdCandidateRequestBean> genericRequestBean = new RequestBean();
		genericRequestBean.setPayload(request);
		return this.callGetVacant(genericRequestBean);
	}

	@Override
	public AssignVacantResponseBean assignVacant(int idCandidato, int idPublicacion) throws GenericStatusException{
		AssignVacantRequestBean request = new AssignVacantRequestBean();
		request.setIdCandidato(idCandidato);
		request.setIdPublicacion(idPublicacion);
		RequestBean<AssignVacantRequestBean> genericRequestBean = new RequestBean();
		genericRequestBean.setPayload(request);
		return this.callAssignVacant(genericRequestBean);
	}

	@Override
	public QualifyCandidateResponseBean qualifyCandidate(String codigoCalificacion, List<String> codigoEtiquetas, int idCandidato) throws GenericStatusException{
		QualifyCandidateRequestBean request = new QualifyCandidateRequestBean();
		request.setCodigoCalificacion(codigoCalificacion);
		request.setCodigoEtiquetas(codigoEtiquetas);
		request.setIdCandidato(idCandidato);
		RequestBean<QualifyCandidateRequestBean> genericRequestBean = new RequestBean();
		genericRequestBean.setPayload(request);
		return this.callQualifyCandidate(genericRequestBean);
	}

	@Override
	public RecruitmentHistoryVO getCandidateHistory(int idCandidato) throws GenericStatusException{
		RequestBean<Integer> genericRequestBean = new RequestBean<>();
		Map<String, Integer> uriVariables = new HashMap<String, Integer>();
		uriVariables.put("idCandidato", idCandidato);
		genericRequestBean.setUriVariables(uriVariables);
		return this.__callGetCandidateHistory(genericRequestBean);
	}

	@Override
	public boolean editEmail(CandidateEditEmailRequestBean requestBean)  throws GenericStatusException {

		RequestBean<CandidateEditEmailRequestBean> genericRequestBean = new RequestBean<>();
		genericRequestBean.setPayload(requestBean);

		GenericTextResponseBean respuesta = callEditEmail(genericRequestBean);
		String responseText = respuesta.getRespuesta();
		if("Exito".equals(responseText)){
			return true;
		}
		throw new GenericStatusException();
	}

	@Override
	public boolean getCandidateInformation(CandidateInfoRequestBean request) throws GenericStatusException {
		RequestBean<CandidateInfoRequestBean> genericRequestBean = new RequestBean<>();
		genericRequestBean.setPayload(request);
		GenericTextResponseBean responseText = callGetCandidateInformation(genericRequestBean);
		if("Exito".equals(responseText.getRespuesta())){
			return true;
		}
		throw new GenericStatusException();
	}

	@Override
	public
	CandidatoDetallesVO getCandidateDetails(int idCandidato) throws GenericStatusException{

		RequestBean<Integer> genericRequestBean = new RequestBean<>();

		Map<String, Integer> uriVariables =  new HashMap<String, Integer>();
		uriVariables.put("idCandidato", idCandidato);
		genericRequestBean.setUriVariables(uriVariables);
		CandidatoDetallesVO detail = this.__callgetCanidateDetails(genericRequestBean);

		if(null == detail.getCalificacion()) {
			detail.setCalificacion(new ScoreVO());
		}

		return detail;

	}

	@Override
	public boolean sendToPortofolio(SendWalletRequestBean idCandidato) throws GenericStatusException{
		RequestBean<SendWalletRequestBean> genericRequestBean = new RequestBean();
		genericRequestBean.setPayload(idCandidato);
		GenericTextResponseBean serviceResponse = this.callSendToPortofolio(genericRequestBean);
		if("Exito".equals(serviceResponse.getRespuesta())){
			return true;
		}
		throw new GenericStatusException();

	}

	@Override
	public List<CandidatesBean> recruitmentCandidates()  throws GenericStatusException{
		RecruitmentCandidatesRequestBean request = new RecruitmentCandidatesRequestBean();
		RequestBean<RecruitmentCandidatesRequestBean> genericRequestBean = new RequestBean();
		genericRequestBean.setPayload(request);
		return this.callRecruitmentCandidates(genericRequestBean);
	}

	@Override
	public CandidateHistoryResponseBean candidateHistory(int idCandidato) throws GenericStatusException{
		CandidateHistoryRequestBean request = new CandidateHistoryRequestBean();
		request.setIdCandidato(idCandidato);
		RequestBean<CandidateHistoryRequestBean> genericRequestBean = new RequestBean();
		genericRequestBean.setUriVariables(request.getAsMap());
		return this.callCandidateHistory(genericRequestBean);
	}

	@Override
	public List<VacanteListaVacanteVO> getSimpleListMyVacancy(String idUsuario) throws GenericStatusException{
		SimpleListMyVacancyRequestBean request = new SimpleListMyVacancyRequestBean();
		request.setIdUsuario(idUsuario);
		RequestBean<SimpleListMyVacancyRequestBean> genericRequestBean = new RequestBean();
		genericRequestBean.setUriVariables(request.getAsMap());
		return this.callSimpleListMyVacancy(genericRequestBean);
	}

	@Override
	public List<VacanteListaVacanteVO> simpleListOtherVacancy(String idUsuario) throws GenericStatusException{
		SimpleListMyVacancyRequestBean request = new SimpleListMyVacancyRequestBean();
		request.setIdUsuario(idUsuario);
		RequestBean<SimpleListMyVacancyRequestBean> genericRequestBean = new RequestBean();
		genericRequestBean.setUriVariables(request.getAsMap());
		return this.callSimpleListOtherVacancy(genericRequestBean);
	}

	@Override
	public RecommendVacancyResponseBean recommendVacancy(int idCandidato, int idPublicacion) throws GenericStatusException{
		RecommendVacancyRequestBean request = new RecommendVacancyRequestBean();
		request.setIdCandidato(idCandidato);
		request.setIdPublicacion(idPublicacion);
		RequestBean<RecommendVacancyRequestBean> genericRequestBean = new RequestBean();
		genericRequestBean.setPayload(request);
		return this.callRecommendVacancy(genericRequestBean);
	}

	protected abstract RecruitmentHistoryVO __callGetCandidateHistory(RequestBean<Integer> genericRequestBean) throws GenericStatusException;

	protected abstract CandidatoDetallesVO __callgetCanidateDetails(RequestBean<Integer> genericRequestBean) throws GenericStatusException;

	protected abstract List<ScoreVO> __callGetScoreTags(RequestBean<GenericRequestBean> genericRequestBean) throws GenericStatusException;

	protected abstract ResumenCandidatosListVO callGetInitCandidates(RequestBean<CandidatesRecruitmentDTO> genericRequestBean) throws GenericStatusException;

	protected abstract GetDetailsCandidateResponseBean callGetDetailsCandidate(RequestBean<GetDetailsCandidateRequestBean> genericRequestBean) throws GenericStatusException;

	protected abstract GenericTextResponseBean callSetScoreCandidate(RequestBean<SetScoreCandidateInDTO> genericRequestBean) throws GenericStatusException;

	protected abstract GenericTextResponseBean callSetVacantCandidate(RequestBean<GenericIdCandidateInDTO> genericRequestBean) throws GenericStatusException;

	protected abstract GenericTextResponseBean callRecommendCandidate(RequestBean<GenericIdCandidateInDTO> genericRequestBean) throws GenericStatusException;

	protected abstract GetVacantResponseBean callGetVacant(RequestBean<GenericIdCandidateRequestBean> genericRequestBean) throws GenericStatusException;

	protected abstract AssignVacantResponseBean callAssignVacant(RequestBean<AssignVacantRequestBean> genericRequestBean) throws GenericStatusException;

	protected abstract QualifyCandidateResponseBean callQualifyCandidate(RequestBean<QualifyCandidateRequestBean> genericRequestBean) throws GenericStatusException;

	protected abstract GenericTextResponseBean callEditEmail(RequestBean<CandidateEditEmailRequestBean> genericRequestBean) throws GenericStatusException;

	protected abstract GenericTextResponseBean callGetCandidateInformation(RequestBean<CandidateInfoRequestBean> genericRequestBean) throws GenericStatusException;

	protected abstract GenericTextResponseBean callSendToPortofolio(RequestBean<SendWalletRequestBean> genericRequestBean) throws GenericStatusException;

	protected abstract List<CandidatesBean> callRecruitmentCandidates(RequestBean<RecruitmentCandidatesRequestBean> genericRequestBean)  throws GenericStatusException;

	protected abstract CandidateHistoryResponseBean callCandidateHistory(RequestBean<CandidateHistoryRequestBean> genericRequestBean) throws GenericStatusException;

	protected abstract List<VacanteListaVacanteVO> callSimpleListMyVacancy(RequestBean<SimpleListMyVacancyRequestBean> genericRequestBean) throws GenericStatusException;

	protected abstract List<VacanteListaVacanteVO> callSimpleListOtherVacancy(RequestBean<SimpleListMyVacancyRequestBean> genericRequestBean) throws GenericStatusException;

	protected abstract RecommendVacancyResponseBean callRecommendVacancy(RequestBean<RecommendVacancyRequestBean> genericRequestBean) throws GenericStatusException;

}
