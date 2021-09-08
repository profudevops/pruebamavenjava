package com.mx.profuturo.bolsa.service.recruitment;

import com.mx.profuturo.bolsa.model.recruitment.response.ProcesoReclutamientoResponseBean;
import com.mx.profuturo.bolsa.model.recruitment.vo.RecruitmentDetailVO;

public interface AsesorProcessService {


	RecruitmentDetailVO processStages(ProcesoReclutamientoResponseBean data, String role, String idUsuario);

}
