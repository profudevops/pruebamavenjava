package com.mx.profuturo.bolsa.model.reports.templates;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.mx.profuturo.bolsa.model.reports.templates.XLSTemplate;
import com.mx.profuturo.bolsa.model.reports.vo.data.FinalistasContratadosVO;
import com.mx.profuturo.bolsa.model.reports.vo.data.MotivosRechazoVO;

public class CandidatosFinalistasTemplate extends BaseTemplate implements XLSTemplate {

	public CandidatosFinalistasTemplate(String branch) {
		this.mode = branch;
	}
		
	
	//fecha	candidato	motivo	etapa	analista	observador/gerente	gerencia	regional	division

	@Override
	public String getFileName() {
		
		return "candidatos-finalistas.xlsx";
	}


	@Override
	protected Row getXLSCORPHeaders(Row row, Sheet s, Integer p) {
		return getXLSCOMHeaders(row, s, p);
	}

	@Override
	protected Row getXLSCORPRowMapping(Row row, Sheet s, Object o, Integer p) {
		 return getXLSCOMRowMapping(row, s, o, p);
	}

	
	@Override
	protected Row getXLSCOMHeaders(Row row, Sheet s, Integer p) {
		// s.autoSizeColumn(p);;
		row.createCell(p++).setCellValue("Fecha");
		// s.autoSizeColumn(p);;
        row.createCell(p++).setCellValue("Candidato");
    // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue("Etapa");
    // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue("Analista");
    // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue("Estatus");
    // s.autoSizeColumn(p);
        
        return row;
	}

	@Override
	protected Row getXLSCOMRowMapping(Row row, Sheet s, Object o, Integer p) {
		FinalistasContratadosVO vo = (FinalistasContratadosVO)o;
		
		// s.autoSizeColumn(p);;
        row.createCell(p++).setCellValue(vo.getFecha());
    // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue(vo.getCandidato());
    // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue(vo.getEtapa());
    // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue(vo.getAnalista());
    // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue(vo.getStatus());
    // s.autoSizeColumn(p);
        
        return row;
	}

}
