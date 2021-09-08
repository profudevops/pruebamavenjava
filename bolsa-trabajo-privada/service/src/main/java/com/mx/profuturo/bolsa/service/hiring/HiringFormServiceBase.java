package com.mx.profuturo.bolsa.service.hiring;


import com.mx.profuturo.bolsa.model.service.hiringform.dto.GuardarEtapaFormularioDTO;
import com.mx.profuturo.bolsa.model.service.hiringform.dto.RecuperarFormularioDTO;
import com.mx.profuturo.bolsa.model.service.hiringform.dto.TerminarFormularioDTO;
import com.mx.profuturo.bolsa.model.service.hiringform.dto.AccesoFormularioContratacionDTO;
import com.mx.profuturo.bolsa.model.service.hiringform.vo.DatosFormularioVO;
import com.mx.profuturo.bolsa.model.service.hiringform.vo.CandidatoContratacionVO;
import com.mx.profuturo.bolsa.util.exception.custom.GenericStatusException;


public abstract class HiringFormServiceBase implements HiringFormService {


	public CandidatoContratacionVO getValidateCredential(AccesoFormularioContratacionDTO dto) {
		return this.__callHiValidateCredentials(dto);
	}

	@Override
	public Boolean personalData (GuardarEtapaFormularioDTO dto) throws GenericStatusException {
		return this.__callSaveFormData(dto);

	}
	
	
   @Override
public DatosFormularioVO getPersonalDataHiring (RecuperarFormularioDTO recudto) throws GenericStatusException {
		DatosFormularioVO respuesta = this.__callDataHiring(recudto);

		if(null!=respuesta.getEtapa2()){
			respuesta.getEtapa2().setNombreJefeInmediato(
					respuesta.getEtapa2().getNombreJefeInmediato()+" "
					+respuesta.getEtapa2().getApellidoPaternoJefeInmediato()+" "
					+respuesta.getEtapa2().getApellidoMaternoJefeInmediato()
			);
		}
		if(null!=respuesta.getEtapa3()){
			respuesta.getEtapa3().setNombreContacto(
					respuesta.getEtapa3().getNombreContacto()+""
					+respuesta.getEtapa3().getApellidoPaterno()+""
					+respuesta.getEtapa3().getApellidoMaterno()
			);
		}
	return respuesta;
	
}
   
   
@Override
	public Boolean endHiringForm (TerminarFormularioDTO endFormdto) throws GenericStatusException {
		return this.__callEndHiringForm(endFormdto);

	} 



public abstract CandidatoContratacionVO __callHiValidateCredentials(AccesoFormularioContratacionDTO dto);
public abstract Boolean __callSaveFormData(GuardarEtapaFormularioDTO dto) throws GenericStatusException;
public abstract DatosFormularioVO __callDataHiring(RecuperarFormularioDTO recudto)throws GenericStatusException; 
public abstract Boolean __callEndHiringForm(TerminarFormularioDTO endFormdto)throws GenericStatusException;

}
