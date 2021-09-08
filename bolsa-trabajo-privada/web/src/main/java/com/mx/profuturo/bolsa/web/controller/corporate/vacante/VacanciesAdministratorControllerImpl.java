package com.mx.profuturo.bolsa.web.controller.corporate.vacante;

import com.mx.profuturo.bolsa.model.service.BaseResponseBean;
import com.mx.profuturo.bolsa.model.service.EntityResponseBean;
import com.mx.profuturo.bolsa.model.service.vacancies.dto.BusquedaPersonaDTO;
import com.mx.profuturo.bolsa.model.service.vacancies.dto.ReasignarPublicacionDTO;
import com.mx.profuturo.bolsa.model.service.vacancies.vo.PersonaProfuturoVO;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


import java.util.List;

@Controller("vacanciesAdminstratorController")
@Scope("request")
public class VacanciesAdministratorControllerImpl {


    public BaseResponseBean assignPublicationResponsability(ReasignarPublicacionDTO dto){
        return null;
    }



    public EntityResponseBean<List<PersonaProfuturoVO>> searchCorporateRecruitmentTeam(BusquedaPersonaDTO dto){
        return null;
    }


}
