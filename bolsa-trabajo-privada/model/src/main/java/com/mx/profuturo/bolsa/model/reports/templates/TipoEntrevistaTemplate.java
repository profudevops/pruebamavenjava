package com.mx.profuturo.bolsa.model.reports.templates;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.mx.profuturo.bolsa.model.reports.templates.XLSTemplate;
import com.mx.profuturo.bolsa.model.reports.vo.data.MotivosRechazoVO;
import com.mx.profuturo.bolsa.model.reports.vo.data.TiposEntrevistaVO;

public class TipoEntrevistaTemplate extends BaseTemplate implements XLSTemplate {

	public TipoEntrevistaTemplate(String branch) {
		this.mode = branch;
	}
		//tipo de entrevista	tipo de puesto	candidato	estatus etapa	analista	observador	gerencia	regional	division		
	//X:Tipos de Entrevista Y:Cantidad

	@Override
	public String getFileName() {
		
		return "motivos-rechazo.xlsx";
	}


	@Override
	protected Row getXLSCORPHeaders(Row row, Sheet s, Integer p) {
		return this.getXLSCOMHeaders(row, s, p);
	}

	@Override
	protected Row getXLSCORPRowMapping(Row row, Sheet s, Object o, Integer p) {
		return this.getXLSCOMRowMapping(row, s, o, p);
	}

	
	@Override
	protected Row getXLSCOMHeaders(Row row, Sheet s, Integer p) {
		row.createCell(p++).setCellValue("Fecha");
		// s.autoSizeColumn(p);;
        row.createCell(p++).setCellValue("Tipo de entrevista");
    // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue("Estatus");
    // s.autoSizeColumn(p);

        
        return row;
	}

	@Override
	protected Row getXLSCOMRowMapping(Row row, Sheet s, Object o, Integer p) {
		TiposEntrevistaVO vo = (TiposEntrevistaVO)o;
		
        row.createCell(p++).setCellValue(vo.getFecha());
    // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue(vo.getTipo());
    // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue(vo.getStatus());
    // s.autoSizeColumn(p);
        
        return row;
	}

}
