package com.mx.profuturo.bolsa.web.controller.recruitment;

import java.util.LinkedList;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mx.profuturo.bolsa.model.service.EntityResponseBean;
import com.mx.profuturo.bolsa.web.controller.common.BaseController;

public class RecruitmentStepOperationsController extends BaseController{
	
	
	
	@ResponseBody
    @RequestMapping(value="reclutamiento/puede-contratar", method = RequestMethod.GET)
	public EntityResponseBean<LinkedList<Object>> canHire(@RequestParam Object x) {
		
		return this.buildEntitySuccessResponse(null);
	}
	
	
	
	@ResponseBody
    @RequestMapping(value="reclutamiento/search-request-folio", method = RequestMethod.GET)
	public EntityResponseBean<LinkedList<Object>> searchRequestFolio(@RequestParam Object x) {
		
		return this.buildEntitySuccessResponse(null);
	}
	
	
	@ResponseBody
    @RequestMapping(value="reclutamiento/save-request-folio", method = RequestMethod.GET)
	public EntityResponseBean<LinkedList<Object>> saveRequestFolio(@RequestParam Object x) {
		
		return this.buildEntitySuccessResponse(null);
	}
	
	

}
