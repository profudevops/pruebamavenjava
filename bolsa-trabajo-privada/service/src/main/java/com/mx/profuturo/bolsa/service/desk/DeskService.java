package com.mx.profuturo.bolsa.service.desk;

import com.mx.profuturo.bolsa.model.desk.dto.DeskDTO;
import com.mx.profuturo.bolsa.model.desk.vo.EscritorioVO;
import com.mx.profuturo.bolsa.model.restclient.RequestBean;
import com.mx.profuturo.bolsa.util.exception.custom.GenericStatusException;
import com.mx.profuturo.bolsa.service.desk.*;

import com.mx.profuturo.bolsa.util.exception.custom.GenericStatusException;

public interface DeskService {

	public EscritorioVO getDeskData(DeskDTO dto) throws GenericStatusException;

	RecruiterCorpDeskResponse __getRecruiterCorpData(RequestBean<String> request) throws GenericStatusException;

	AdministratorCorpDeskResponse __getAdministratorCorpData(RequestBean<String> request) throws GenericStatusException;

	AdministratorCommDeskResponse __getAdministratorCommData(RequestBean<String> request) throws GenericStatusException;

	ConsultorCommDeskResponse __getCommConsultorData(RequestBean<String> request) throws GenericStatusException;

	AnalystCommDeskResponse __getCommAnalystData(RequestBean<String> request)throws GenericStatusException;

	ObserverCommDeskResponse __getCommObserver(RequestBean<String> request)throws GenericStatusException;

	ObserverCorpDeskResponse __getObserverCorpData(RequestBean<String> request)throws GenericStatusException;

	CustomerRecruiterCorpDeskResponse __getCustomerRecruiterCorpData(RequestBean<String> request) throws GenericStatusException;


}
