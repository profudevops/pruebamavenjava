package com.mx.profuturo.bolsa.web.controller.corporate.vacante;

import com.mx.profuturo.bolsa.model.service.BaseResponseBean;
import com.mx.profuturo.bolsa.model.service.vacancies.vo.ReclutamientoCandidatoVO;
import com.mx.profuturo.bolsa.model.service.vacancies.vo.ReclutamientoPublicacionVO;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller("candidateActionsController")
@Scope("request")
public class RecruitmentControllerImpl {


    //obtener-proceso-reclutamiento-por-publicacion
    public Object getRecruitmentProccessByPublication(){ return null;}

    //agendar-cita
    public BaseResponseBean saveDate(){ return null;}

    //obtener-configuracion-fase ---> obtener-form-fase-reclutamiento
    public Object getRecruitmentPhaseForm(){ return null;}


    //obtener-fase-por-reclutamiento
    public Object getRecruitmentPhaseData(){ return null;}

    //obtener-todos-reclutamientos
    public ReclutamientoPublicacionVO getRecruitmentsByPublication(){ return null;}

    //obtener-detalle-reclutamiento
    public ReclutamientoCandidatoVO getRecruitmentDetail(){
        return null;
    }

    //comentar
    public BaseResponseBean comment(){return null;}

    //contratar
    public BaseResponseBean hireCandidate(){ return null;}

    //rechazar
    public BaseResponseBean rejectCandidate(){ return null;}

}
