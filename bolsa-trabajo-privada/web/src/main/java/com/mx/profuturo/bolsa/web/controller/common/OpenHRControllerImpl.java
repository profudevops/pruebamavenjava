package com.mx.profuturo.bolsa.web.controller.common;

import java.util.LinkedList;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mx.profuturo.bolsa.model.openhr.EmpleadoProfuturoVO;
import com.mx.profuturo.bolsa.model.service.EntityResponseBean;
import com.mx.profuturo.bolsa.model.vo.common.CatalogoVO;
import com.mx.profuturo.bolsa.util.exception.custom.GenericStatusException;

public class OpenHRControllerImpl extends BaseController{

	
	public @ResponseBody
	@RequestMapping(value="buscar-empleado-profuturo", method = RequestMethod.GET)
    EntityResponseBean<LinkedList<EmpleadoProfuturoVO>> searchProfuturoEmploye(@RequestParam String email) throws GenericStatusException {
		
		LinkedList<EmpleadoProfuturoVO> vo = new LinkedList<EmpleadoProfuturoVO>();
		EmpleadoProfuturoVO e1 = new EmpleadoProfuturoVO();
		e1.setEmail("montserrat.romero@profuturo.com.mx");
		e1.setNombre("Vanessa Montserrat Romero");
		e1.setNumeroEmpleado(6543343);
		return this.buildEntitySuccessResponse(vo);
		
	}
	
	
	public @ResponseBody
	@RequestMapping(value="buscar-subordinados", method = RequestMethod.GET)
    EntityResponseBean<LinkedList<EmpleadoProfuturoVO>> searchUserSubordinates(@RequestParam Integer numeroEmpleado) throws GenericStatusException {
		
		LinkedList<EmpleadoProfuturoVO> vo = new LinkedList<EmpleadoProfuturoVO>();
		EmpleadoProfuturoVO e1 = new EmpleadoProfuturoVO();
		e1.setEmail("david.marquesz@profuturo.com.mx");
		e1.setNombre("David Marquez");
		e1.setNumeroEmpleado(6543355);
		return this.buildEntitySuccessResponse(vo);
		
	}
	
	
}
