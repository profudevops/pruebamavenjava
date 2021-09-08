package com.mx.profuturo.bolsa.service.candidates;

import com.mx.profuturo.bolsa.model.service.areasinteres.dto.GenericTextResponseBean;
import com.mx.profuturo.bolsa.model.service.auth.response.UserCredentialsVO;
import com.mx.profuturo.bolsa.model.service.candidates.dto.*;
import com.mx.profuturo.bolsa.model.vo.Candidato.*;
import com.mx.profuturo.bolsa.model.vo.vacancy.VacanteListaVacanteVO;
import com.mx.profuturo.bolsa.util.exception.custom.GenericStatusException;

import java.util.LinkedList;
import java.util.List;

public interface CandidateService{

	ResumenCandidatosListVO getInitCandidates(UserCredentialsVO user, int paginaActual, String search, Integer idRama) throws GenericStatusException;
	CandidatoDetallesVO getCandidateDetails(int idCandidato) throws GenericStatusException;
	RecruitmentHistoryVO getCandidateHistory(int idCandidato) throws GenericStatusException;
	ScoreVO setScoreCandidate(SetScoreCandidateInDTO score) throws GenericStatusException;
	LinkedList<ScoreVO> getScoreTags()throws GenericStatusException;
	boolean editEmail(CandidateEditEmailRequestBean requestBean) throws GenericStatusException;
	boolean getCandidateInformation(CandidateInfoRequestBean request) throws GenericStatusException;
	boolean recommendCandidate(GenericIdCandidateInDTO genericIdCandidateInDTO) throws GenericStatusException;
	boolean setVacantCandidate(GenericIdCandidateInDTO assignVacancyDTO) throws GenericStatusException;
	GetVacantResponseBean getVacant(String idCandidato) throws GenericStatusException;
	AssignVacantResponseBean assignVacant(int idCandidato, int idPublicacion) throws GenericStatusException;
	QualifyCandidateResponseBean qualifyCandidate(String codigoCalificacion, List<String> codigoEtiquetas, int idCandidato) throws GenericStatusException;
	boolean sendToPortofolio(SendWalletRequestBean idCandidato) throws GenericStatusException;
	List<CandidatesBean> recruitmentCandidates()  throws GenericStatusException;
	CandidateHistoryResponseBean candidateHistory(int idCandidato) throws GenericStatusException;
	List<VacanteListaVacanteVO> getSimpleListMyVacancy(String idUsuario) throws GenericStatusException;
	List<VacanteListaVacanteVO> simpleListOtherVacancy(String idUsuario) throws GenericStatusException;
	RecommendVacancyResponseBean recommendVacancy(int idCandidato, int idPublicacion) throws GenericStatusException;
	CandidatesRecruitmentDTO buildInRecruitmentDTO(UserCredentialsVO user, int paginaActual,Integer idRama) throws GenericStatusException;

}
