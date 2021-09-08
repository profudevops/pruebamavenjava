package com.mx.profuturo.bolsa.service.recruitment;

import java.util.LinkedHashMap;
import java.util.LinkedList;

import com.mx.profuturo.bolsa.model.recruitment.bean.PhaseBean;
import com.mx.profuturo.bolsa.model.recruitment.response.ProcesoReclutamientoResponseBean;
import com.mx.profuturo.bolsa.model.recruitment.vo.EventVO;
import com.mx.profuturo.bolsa.model.recruitment.vo.RecruitmentStageVO;
import com.mx.profuturo.bolsa.util.exception.custom.GenericStatusException;

public interface HistoryService {
	LinkedList<EventVO> getHistory(Integer idReclutamiento, Integer step, LinkedList<RecruitmentStageVO> linkedList) throws GenericStatusException;


	LinkedList<EventVO> getHistory(ProcesoReclutamientoResponseBean data, LinkedHashMap<String, PhaseBean> processMap,
			String estado, String role);


}
