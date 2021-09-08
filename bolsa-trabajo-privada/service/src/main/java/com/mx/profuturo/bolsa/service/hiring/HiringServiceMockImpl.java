package com.mx.profuturo.bolsa.service.hiring;

import com.mx.profuturo.bolsa.model.service.hiringform.dto.*;
import com.mx.profuturo.bolsa.util.exception.custom.GenericStatusException;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.mx.profuturo.bolsa.model.service.hiringform.vo.DatosFormularioVO;
import com.mx.profuturo.bolsa.model.service.hiringform.vo.CandidatoContratacionVO;

@Service
@Scope("request")
@Profile("mock")
public class HiringServiceMockImpl extends HiringFormServiceBase {

	@Override
	public DatosFormularioVO getPersonalDataHiring(RecuperarFormularioDTO recudto) throws GenericStatusException {
		// TODO Auto-generated method stub
		return this.__callDataHiring(recudto);
	}

	@Override
	public CandidatoContratacionVO __callHiValidateCredentials(AccesoFormularioContratacionDTO dto) {
		CandidatoContratacionVO vo = new CandidatoContratacionVO();

		
		if(dto.getDato1().equals("Ricky Llamas") && dto.getDato2().equals("355")) {
			//primera vez llenado form
			vo.setIdCandidato(355);
			vo.setNombreCandidato("Ricky Llamas");
			vo.setIdProceso(124);
			vo.setEtapaFormulario(1);
			vo.setStatus(1);
		} else if(dto.getDato1().equals("Edgar Mendez") && dto.getDato2().equals("100")) {
			//enviado y cerrado
			vo.setIdCandidato(100);
			vo.setNombreCandidato("Edgar Mendez");
			vo.setIdProceso(101);
			vo.setEtapaFormulario(4);
			vo.setStatus(2);
		}else if(dto.getDato1().equals("Eduardo Mendez") && dto.getDato2().equals("255")) {
			//no tiene derecho a completar
			vo.setIdCandidato(255);
			vo.setNombreCandidato("Eduardo Mendez");
			vo.setIdProceso(111);
			vo.setEtapaFormulario(0);
			vo.setStatus(0);
		}else if(dto.getDato1().equals("Erick Fuentes") && dto.getDato2().equals("255")) {
			//sin completar form
			vo.setIdCandidato(255);
			vo.setNombreCandidato("Erick Fuentes");
			vo.setIdProceso(123);
			vo.setEtapaFormulario(3);
			vo.setStatus(1);
		}else if(dto.getDato1().equals("Juani Reyes") && dto.getDato2().equals("455")) {
			//subir documentos
			vo.setIdCandidato(255);
			vo.setNombreCandidato("Juani Reyes");
			vo.setIdProceso(123);
			vo.setEtapaFormulario(5);
			vo.setStatus(1);
		}

		return vo;
	}

	@Override
	public CandidatoContratacionVO validateHiringForm(AccesoFormularioContratacionDTO dto) {
		return this.__callHiValidateCredentials(dto);
	}

	@Override
	public Boolean __callSaveFormData(GuardarEtapaFormularioDTO dto) throws GenericStatusException {

		if (dto.getIdProceso()<=0) {
			throw new GenericStatusException();
		} else {
			return true;
		}
	}

	@Override
	public DatosFormularioVO __callDataHiring(RecuperarFormularioDTO recudto) throws GenericStatusException {
		DatosFormularioVO response = new DatosFormularioVO();
		FormularioEtapa1DTO etap1 = new FormularioEtapa1DTO();
		FormularioEtapa2DTO etap2 = new FormularioEtapa2DTO();
		FormularioEtapa3DTO etap3 = new FormularioEtapa3DTO();
		FormularioEtapa4DTO etap4 = new FormularioEtapa4DTO();
		

		etap1.setAlcaldia("Alvaro Obregon");
		etap1.setApellidoMaterno("Galvan");
		etap1.setApellidoPaterno("Llamas");
		etap1.setCalle("Gpe");
		etap1.setColonia("sn angel inn");
		etap1.setCp("01790");
		etap1.setCurp("LAGR870128HDFLC08");
		etap1.setEmail("ricky@llamas.com");
		etap1.setIdEstado(1);
		etap1.setIdGenero(1);
		etap1.setNombre("Ricky");
		etap1.setNss("123654");
		etap1.setNumExt("69");

		
	
			etap1.setAlcaldia("Alvaro Obregon");
			etap1.setApellidoMaterno("Galvan");
			etap1.setApellidoPaterno("Llamas");
			etap1.setCalle("Gpe");
			etap1.setColonia("sn angel inn");
			etap1.setCp("01790");
			etap1.setCurp("LAGR870128HDFLC08");
			etap1.setEmail("ricky@llamas.com");
			etap1.setIdEstado(1);
			etap1.setIdGenero(1);
			etap1.setNombre("Ricky");
			etap1.setNss("123654");
			etap1.setNumExt("69");
			
			etap2.setNivelEstudios(1);
			etap2.setEstatus(1);
			etap2.setCarrera("SISTEMAS");
			etap2.setNombreEmpresa("GGGGG");
			etap2.setActividad("SISTEMAS");
			etap2.setNombreJefeInmediato("Juan pablo rojas");
			etap2.setPuestoJefe("Gerente soluciones");
			etap2.setPeriodoLaboradoInicio(2010);
		    etap2.setPeriodoLaboradoFin(2014);
			etap2.setTelefono("123456780");
			etap2.setFunciones("blabla bla bla");

			
		response.setEtapa1(etap1);
		response.setEtapa2(etap2);
		response.setEtapa3(etap3);
		response.setEtapa4(etap4);
		
		return response;
	}

	
	
	@Override
	public Boolean endHiringForm(TerminarFormularioDTO endFormdto) throws GenericStatusException {
	
		return this.__callEndHiringForm(endFormdto);
	}

	
	@Override
	public Boolean __callEndHiringForm(TerminarFormularioDTO endFormdto) throws GenericStatusException {
		Boolean sucess= true;

		if(endFormdto.getIdProceso()<=0){
			throw new GenericStatusException();
		}

		return sucess;
	}




}
