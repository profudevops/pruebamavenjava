package com.mx.profuturo.bolsa.service.recruitment;


import java.util.LinkedList;

import com.mx.profuturo.bolsa.model.recruitment.dto.CompletePhaseDTO;
import com.mx.profuturo.bolsa.model.recruitment.dto.GenericStageQueryDTO;
import com.mx.profuturo.bolsa.model.recruitment.dto.InterviewDTO;
import com.mx.profuturo.bolsa.model.recruitment.dto.form.SEAGStageForm;
import com.mx.profuturo.bolsa.model.recruitment.dto.form.SEESGStageForm;
import com.mx.profuturo.bolsa.model.recruitment.response.DatosEntrevistaResponseBean;
import com.mx.profuturo.bolsa.model.recruitment.response.ProcesoReclutamientoResponseBean;
import com.mx.profuturo.bolsa.model.recruitment.service.dto.FolioDTO;
import com.mx.profuturo.bolsa.model.recruitment.service.dto.InterviewAppointmentDTO;
import com.mx.profuturo.bolsa.model.recruitment.service.dto.InterviewResponseDTO;
import com.mx.profuturo.bolsa.model.recruitment.service.dto.PsychometricTestDTO;
import com.mx.profuturo.bolsa.model.recruitment.service.dto.TrustTestDTO;
import com.mx.profuturo.bolsa.model.recruitment.vo.FolioVO;
import com.mx.profuturo.bolsa.model.service.candidates.dto.IniciarReclutamientoDTO;
import com.mx.profuturo.bolsa.model.vo.common.CatalogoVO;
import com.mx.profuturo.bolsa.util.exception.custom.GenericStatusException;


public interface OperationsService {
	
	public  void handleSaveStep(CompletePhaseDTO dto);
	public ProcesoReclutamientoResponseBean getDataById(Integer idReclutamiento) throws GenericStatusException;
	public ProcesoReclutamientoResponseBean getDataById(Integer idReclutamiento, Integer step) throws GenericStatusException;
	public void saveGenericStage(GenericStageQueryDTO genericStageQuery) throws GenericStatusException;
	public void savePsychometricTest(PsychometricTestDTO pDto) throws GenericStatusException;
	public void saveTrustTest(TrustTestDTO tDto) throws GenericStatusException;
	public void saveFolio(FolioDTO fDto) throws GenericStatusException;
	public void saveInterviewResponse(InterviewResponseDTO iRDto) throws GenericStatusException;
	public LinkedList<FolioVO> searchFolio(String folio);
	LinkedList<CatalogoVO> getStateProperties();
	public void create(IniciarReclutamientoDTO dto);
	public void saveInterviewAppointment(InterviewAppointmentDTO iDto) throws GenericStatusException;
	public SEESGStageForm getNoHandledInterviewData(Integer idReclutamiento) throws GenericStatusException;
	public LinkedList<DatosEntrevistaResponseBean> callGetNoHandledInterviewData(Integer idReclutamiento)
			throws GenericStatusException;
	public Boolean hasPublicationSpaces(Integer idReclutamiento, Integer idPublicacion) throws GenericStatusException;
	SEAGStageForm getInterviewDataByStage(Integer idReclutamiento, Integer idEntrevista, String modo)
			throws GenericStatusException;
}
