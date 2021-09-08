package com.mx.profuturo.bolsa.service.interview;

import com.mx.profuturo.bolsa.model.interview.InterviewAvailableHoursRequestDTO;
import com.mx.profuturo.bolsa.model.recruitment.vo.InterviewInfoVO;
import com.mx.profuturo.bolsa.model.recruitment.vo.InterviewsListVO;
import com.mx.profuturo.bolsa.model.restclient.RequestBean;
import com.mx.profuturo.bolsa.model.service.auth.response.UserCredentialsVO;
import com.mx.profuturo.bolsa.model.service.candidates.dto.CandidatesRecruitmentDTO;
import com.mx.profuturo.bolsa.model.vo.common.CatalogoVO;
import com.mx.profuturo.bolsa.service.candidates.CandidateService;
import com.mx.profuturo.bolsa.util.exception.custom.GenericStatusException;

import java.util.ArrayList;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class InterviewServiceBase implements InterviewService {

	@Autowired
	CandidateService candidateService;
	
    @Override
   public LinkedList<CatalogoVO> getAvailableShcedule(InterviewAvailableHoursRequestDTO interviewHourDTO)throws GenericStatusException {

        RequestBean<InterviewAvailableHoursRequestDTO> genericRequestBean = new RequestBean<>();
        genericRequestBean.setPayload(interviewHourDTO);

        LinkedList<CatalogoVO> response = this.__callgetAvailableSchedule(genericRequestBean);

        return response;

    }
    
    
    public 	InterviewsListVO getInterviews(UserCredentialsVO user, int paginaActual, Integer idRama) throws GenericStatusException {
        
    	RequestBean<CandidatesRecruitmentDTO> genericRequestBean = new RequestBean<>();
    	CandidatesRecruitmentDTO dto = candidateService.buildInRecruitmentDTO(user,paginaActual, idRama);
        genericRequestBean.setPayload(dto);

        InterviewsListVO response = this.__callGetInterviews(genericRequestBean);


        return response;

    }
    
    
    public abstract InterviewsListVO __callGetInterviews(RequestBean genericRequestBean)throws GenericStatusException;
    public abstract LinkedList<CatalogoVO> __callgetAvailableSchedule(RequestBean genericRequestBean)throws GenericStatusException;
}
