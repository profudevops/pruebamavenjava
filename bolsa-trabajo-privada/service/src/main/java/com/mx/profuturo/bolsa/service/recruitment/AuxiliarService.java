package com.mx.profuturo.bolsa.service.recruitment;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import com.mx.profuturo.bolsa.model.recruitment.dto.InterviewInServiceDTO;
import com.mx.profuturo.bolsa.model.recruitment.vo.FolioVO;
import com.mx.profuturo.bolsa.model.vo.common.CatalogoVO;
import com.mx.profuturo.bolsa.util.exception.custom.GenericStatusException;

public interface AuxiliarService {
	
	LinkedList<FolioVO> searchFolio(String folio) throws GenericStatusException;

	List<CatalogoVO> getRejectReassons() throws GenericStatusException;

	LinkedList<CatalogoVO> getInterviewType() throws GenericStatusException;

	LinkedHashMap<String, LinkedList<CatalogoVO>> getCatalogsByStage(String idUsuario, String codigo)
			throws GenericStatusException;

	List<CatalogoVO> getInterviewHours(InterviewInServiceDTO dto) throws GenericStatusException;

}
