package com.mx.profuturo.bolsa.service.interview;

import com.mx.profuturo.bolsa.model.interview.InterviewAvailableHoursRequestDTO;
import com.mx.profuturo.bolsa.model.recruitment.vo.InterviewsListVO;
import com.mx.profuturo.bolsa.model.service.auth.response.UserCredentialsVO;
import com.mx.profuturo.bolsa.model.vo.common.CatalogoVO;
import com.mx.profuturo.bolsa.util.exception.custom.GenericStatusException;

import java.util.LinkedList;

public interface InterviewService {

    LinkedList<CatalogoVO> getAvailableShcedule(InterviewAvailableHoursRequestDTO interviewHourDTO) throws GenericStatusException;

	InterviewsListVO getInterviews(UserCredentialsVO user, int paginaActual, Integer idRama) throws GenericStatusException;
}
