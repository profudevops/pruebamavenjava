package com.mx.profuturo.bolsa.model.service.hiringform.vo.copy;


import com.mx.profuturo.bolsa.model.service.hiringform.dto.FormularioEtapa1DTO;
import com.mx.profuturo.bolsa.model.service.hiringform.dto.FormularioEtapa2DTO;
import com.mx.profuturo.bolsa.model.service.hiringform.dto.FormularioEtapa3DTO;
import com.mx.profuturo.bolsa.model.service.hiringform.dto.FormularioEtapa4DTO;

public class DatosFormularioVO {

	private FormularioEtapa1DTO etapa1;
	private FormularioEtapa2DTO etapa2;
	private FormularioEtapa3DTO etapa3;
	private FormularioEtapa4DTO etapa4;

	public FormularioEtapa1DTO getEtapa1() {
		return etapa1;
	}

	public void setEtapa1(FormularioEtapa1DTO etapa1) {
		this.etapa1 = etapa1;
	}

	public FormularioEtapa2DTO getEtapa2() {
		return etapa2;
	}

	public void setEtapa2(FormularioEtapa2DTO etapa2) {
		this.etapa2 = etapa2;
	}

	public FormularioEtapa3DTO getEtapa3() {
		return etapa3;
	}

	public void setEtapa3(FormularioEtapa3DTO etapa3) {
		this.etapa3 = etapa3;
	}

	public FormularioEtapa4DTO getEtapa4() {
		return etapa4;
	}

	public void setEtapa4(FormularioEtapa4DTO etapa4) {
		this.etapa4 = etapa4;
	}
}
