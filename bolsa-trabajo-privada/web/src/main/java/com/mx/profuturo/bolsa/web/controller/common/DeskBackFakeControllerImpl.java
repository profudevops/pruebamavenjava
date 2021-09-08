package com.mx.profuturo.bolsa.web.controller.common;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mx.profuturo.bolsa.model.desk.dto.DeskDTO;
import com.mx.profuturo.bolsa.model.desk.vo.EscritorioVO;
import com.mx.profuturo.bolsa.model.recruitment.dto.CompletePhaseDTO;
import com.mx.profuturo.bolsa.model.recruitment.dto.GenericStageQueryDTO;
import com.mx.profuturo.bolsa.model.recruitment.dto.form.SEESGStageForm;
import com.mx.profuturo.bolsa.model.recruitment.response.DatosEntrevistaResponseBean;
import com.mx.profuturo.bolsa.model.recruitment.response.ProcesoReclutamientoResponseBean;
import com.mx.profuturo.bolsa.model.recruitment.service.dto.FolioDTO;
import com.mx.profuturo.bolsa.model.recruitment.service.dto.InterviewAppointmentDTO;
import com.mx.profuturo.bolsa.model.recruitment.service.dto.InterviewResponseDTO;
import com.mx.profuturo.bolsa.model.recruitment.service.dto.PsychometricTestDTO;
import com.mx.profuturo.bolsa.model.recruitment.service.dto.TrustTestDTO;
import com.mx.profuturo.bolsa.model.recruitment.vo.FolioVO;
import com.mx.profuturo.bolsa.model.restclient.RequestBean;
import com.mx.profuturo.bolsa.model.vo.common.CatalogoVO;
import com.mx.profuturo.bolsa.service.desk.AdministratorCommDeskResponse;
import com.mx.profuturo.bolsa.service.desk.AdministratorCorpDeskResponse;
import com.mx.profuturo.bolsa.service.desk.AnalystCommDeskResponse;
import com.mx.profuturo.bolsa.service.desk.ConsultorCommDeskResponse;
import com.mx.profuturo.bolsa.service.desk.CustomerRecruiterCorpDeskResponse;
import com.mx.profuturo.bolsa.service.desk.DeskService;
import com.mx.profuturo.bolsa.service.desk.ObserverCommDeskResponse;
import com.mx.profuturo.bolsa.service.desk.ObserverCorpDeskResponse;
import com.mx.profuturo.bolsa.service.desk.RecruiterCorpDeskResponse;
import com.mx.profuturo.bolsa.service.recruitment.OperationsService;
import com.mx.profuturo.bolsa.util.exception.custom.GenericStatusException;

@Controller
@Scope("request")
public class DeskBackFakeControllerImpl{

	@Autowired
	DeskService deskService;
	
	
	private RequestBean<String> generateIdRequest(String idUsuario){
		RequestBean<String> rq = new RequestBean<String>();
		Map<String, String> uri = new HashMap<>();
		uri.put("idUsuario", idUsuario);
		rq.setUriVariables(uri);
		return rq ;
	}
	
	@ResponseBody
    @RequestMapping(value="escritorio/corporativo/obtener-corp-rec", method = RequestMethod.GET)
	public RecruiterCorpDeskResponse __getRecruiterCorpData(@RequestParam String idUsuario) throws GenericStatusException {
		// TODO Auto-generated method stub
		return deskService.__getRecruiterCorpData(this.generateIdRequest(idUsuario));
	}

	@ResponseBody
    @RequestMapping(value="escritorio/corporativo/obtener-corp-admn", method = RequestMethod.GET)
	public AdministratorCorpDeskResponse __getAdministratorCorpData(@RequestParam String idUsuario) throws GenericStatusException {
		// TODO Auto-generated method stub
		return deskService.__getAdministratorCorpData(this.generateIdRequest(idUsuario));
	}


	@ResponseBody
    @RequestMapping(value="escritorio/comercial/obtener-comm-admn", method = RequestMethod.GET)
	public AdministratorCommDeskResponse __getAdministratorCommData(@RequestParam String idUsuario) throws GenericStatusException {
		// TODO Auto-generated method stub
		return deskService.__getAdministratorCommData(this.generateIdRequest(idUsuario));
	}


	@ResponseBody
    @RequestMapping(value="escritorio/comercial/obtener-comm-cons", method = RequestMethod.GET)
	public ConsultorCommDeskResponse __getCommConsultorData( @RequestParam String idUsuario) throws GenericStatusException {
		// TODO Auto-generated method stub
		return deskService.__getCommConsultorData(this.generateIdRequest(idUsuario));
	}


	@ResponseBody
    @RequestMapping(value="escritorio/comercial/obtener-comm-anl", method = RequestMethod.GET)
	public AnalystCommDeskResponse __getCommAnalystData(@RequestParam String idUsuario) throws GenericStatusException {
		// TODO Auto-generated method stub
		return deskService.__getCommAnalystData(this.generateIdRequest(idUsuario));
	}


	@ResponseBody
    @RequestMapping(value="escritorio/comercial/obtener-comm-obs", method = RequestMethod.GET)
	public ObserverCommDeskResponse __getCommObserver(@RequestParam String idUsuario) throws GenericStatusException {
		// TODO Auto-generated method stub
		return deskService.__getCommObserver(this.generateIdRequest(idUsuario));
	}

	
	@ResponseBody
    @RequestMapping(value="escritorio/corporativo/obtener-corp-obs", method = RequestMethod.GET)
	public ObserverCorpDeskResponse __getCorpObserver(@RequestParam String idUsuario) throws GenericStatusException {
		// TODO Auto-generated method stub
		return deskService.__getObserverCorpData(this.generateIdRequest(idUsuario));
	}
	

	@ResponseBody
    @RequestMapping(value="escritorio/corporativo/obtener-corp-rec-cts", method = RequestMethod.GET)
	public CustomerRecruiterCorpDeskResponse __getCorpRecruiterCostumers(@RequestParam String idUsuario) throws GenericStatusException {
		// TODO Auto-generated method stub
		return deskService.__getCustomerRecruiterCorpData(this.generateIdRequest(idUsuario));
	}
    
}
