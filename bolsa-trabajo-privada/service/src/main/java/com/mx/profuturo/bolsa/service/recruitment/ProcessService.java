package com.mx.profuturo.bolsa.service.recruitment;

import com.mx.profuturo.bolsa.model.recruitment.response.ProcesoReclutamientoResponseBean;
import com.mx.profuturo.bolsa.model.recruitment.vo.RecruitmentDetailVO;

public interface ProcessService {

	RecruitmentDetailVO processStages(ProcesoReclutamientoResponseBean data, String role, String idUsuario);

	ProcesoReclutamientoResponseBean handleStrategy(ProcesoReclutamientoResponseBean data);
}
