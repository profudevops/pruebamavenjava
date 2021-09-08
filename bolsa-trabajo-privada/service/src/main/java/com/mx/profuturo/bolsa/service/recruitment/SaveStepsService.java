package com.mx.profuturo.bolsa.service.recruitment;

import com.mx.profuturo.bolsa.model.recruitment.dto.CompletePhaseDTO;
import com.mx.profuturo.bolsa.util.exception.custom.GenericStatusException;

public interface SaveStepsService {

	void saveStageData(CompletePhaseDTO query) throws GenericStatusException;

}
