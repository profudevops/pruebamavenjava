package com.mx.profuturo.bolsa.service.recruitment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.mx.profuturo.bolsa.model.recruitment.constants.RecruitmentStages;
import com.mx.profuturo.bolsa.model.recruitment.response.ProcesoReclutamientoResponseBean;
import com.mx.profuturo.bolsa.model.recruitment.response.RegistroProceso;
import com.mx.profuturo.bolsa.model.recruitment.vo.RecruitmentDetailVO;
import com.mx.profuturo.bolsa.model.recruitment.vo.RecruitmentType;

@Service("Proc")
@Scope("request")
public class ProcessServiceImpl implements  ProcessService{
	
	@Autowired
	@Qualifier("Asesor")
	AsesorProcessService asesorService;

	@Autowired
	@Qualifier("General")
	AsesorProcessService generalService;
	@Override
	public RecruitmentDetailVO processStages(ProcesoReclutamientoResponseBean data, String role, String idUsuario) {
		RecruitmentDetailVO vo = null;
		
		if(RecruitmentType.ASESOR.equals(data.getDatosBasicos().getTipoReclutamiento())) {
			vo = asesorService.processStages(data, role, idUsuario);
		}else if(RecruitmentType.GENERAL.equals(data.getDatosBasicos().getTipoReclutamiento())) {
			vo = generalService.processStages(data, role, idUsuario);
		}
		return vo;
	}
	@Override
	public ProcesoReclutamientoResponseBean handleStrategy(ProcesoReclutamientoResponseBean data) {
		if(!data.getEstrategia().getExamenConfianza() 
				&& null!= data.getRegistros() &&
				data.getRegistros().size()>1) {
			RegistroProceso r6 = new RegistroProceso();
			r6.setAprobado(true);
			r6.setCodigo(RecruitmentStages.EX_CONF);

			r6.setComentario("Proceso de selección excento de examen de confianza");
			r6.setEstatus(1);
			r6.setIdEtapa(0);
			r6.setIdRechazo(0);
			r6.setIdUsuario("0");
			r6.setFecha("---");
			r6.setNombreUsuario("SISTEMA");
		
			data.getRegistros().addFirst(r6);
		}
		return data;
		
	}	
}
