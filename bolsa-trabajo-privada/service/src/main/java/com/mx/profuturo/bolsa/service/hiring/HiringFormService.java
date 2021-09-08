package com.mx.profuturo.bolsa.service.hiring;

import com.mx.profuturo.bolsa.model.service.hiringform.dto.GuardarEtapaFormularioDTO;
import com.mx.profuturo.bolsa.model.service.hiringform.dto.RecuperarFormularioDTO;
import com.mx.profuturo.bolsa.model.service.hiringform.dto.TerminarFormularioDTO;
import com.mx.profuturo.bolsa.model.service.hiringform.dto.AccesoFormularioContratacionDTO;
import com.mx.profuturo.bolsa.model.service.hiringform.vo.DatosFormularioVO;
import com.mx.profuturo.bolsa.model.service.hiringform.vo.CandidatoContratacionVO;
import com.mx.profuturo.bolsa.util.exception.custom.GenericStatusException;

public interface HiringFormService {

	public CandidatoContratacionVO  validateHiringForm(AccesoFormularioContratacionDTO dto);
    public Boolean personalData (GuardarEtapaFormularioDTO dto) throws GenericStatusException; //Boolen true;
    public DatosFormularioVO getPersonalDataHiring(RecuperarFormularioDTO recudto)throws GenericStatusException;
    public Boolean endHiringForm(TerminarFormularioDTO endFormdto) throws GenericStatusException;
}
