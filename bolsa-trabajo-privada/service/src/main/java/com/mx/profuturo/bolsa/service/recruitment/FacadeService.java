package com.mx.profuturo.bolsa.service.recruitment;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import com.mx.profuturo.bolsa.model.recruitment.dto.CompletePhaseDTO;
import com.mx.profuturo.bolsa.model.recruitment.dto.InterviewHoursDTO;
import com.mx.profuturo.bolsa.model.recruitment.dto.form.SEAGStageForm;
import com.mx.profuturo.bolsa.model.recruitment.response.DatosEntrevistaResponseBean;
import com.mx.profuturo.bolsa.model.recruitment.vo.EventVO;
import com.mx.profuturo.bolsa.model.recruitment.vo.FolioVO;
import com.mx.profuturo.bolsa.model.recruitment.vo.RecruitmentDetailVO;
import com.mx.profuturo.bolsa.model.service.candidates.dto.IniciarReclutamientoDTO;
import com.mx.profuturo.bolsa.model.vo.common.CatalogoVO;
import com.mx.profuturo.bolsa.util.exception.custom.GenericStatusException;

public interface FacadeService {

	RecruitmentDetailVO getData(Integer idReclutamiento, String role, Integer step, String idUsuario) throws GenericStatusException;

	void saveStageData(CompletePhaseDTO query) throws GenericStatusException;

	LinkedList<EventVO> getHistory(Integer idReclutamiento, Integer iStep) throws GenericStatusException;

	LinkedList<FolioVO> searchFolio(String folio) throws GenericStatusException;

	void create(IniciarReclutamientoDTO dto);

	List<CatalogoVO> getRejectReassons() throws GenericStatusException;

	LinkedHashMap<String, LinkedList<CatalogoVO>> getCatalogsByStage(String idUsuario, String codigo) throws GenericStatusException;

	List<CatalogoVO> getInterviewHours(String appUser, InterviewHoursDTO dto) throws GenericStatusException;



	Object getStageData(Integer idReclutamiento, String codigo, Integer idEtapa, Integer idEntrevista)
			throws GenericStatusException;

	SEAGStageForm getInterviewDataByStage(Integer idReclutamiento, Integer idEntrevista, String codigo)
			throws GenericStatusException;

}
