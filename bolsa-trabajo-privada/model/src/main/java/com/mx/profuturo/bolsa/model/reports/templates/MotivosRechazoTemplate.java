package com.mx.profuturo.bolsa.model.reports.templates;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.mx.profuturo.bolsa.model.reports.vo.data.MotivosRechazoVO;

public class MotivosRechazoTemplate extends BaseTemplate {
	
	
	//fecha	candidato	motivo	etapa	analista	observador/gerente	gerencia	regional	division
	
	//X:motivo Y: cantidad
	
	public MotivosRechazoTemplate() {
		
	}
	
	
	public MotivosRechazoTemplate(String branch) {
		this.mode= branch;
	}
		

	@Override
	public String getFileName() {
		
		return "motivos-rechazo.xlsx";
	}


	@Override
	public Row getXLSCORPHeaders(Row row,Sheet s, Integer p) {
		row.createCell(p++).setCellValue("Fecha");
		// s.autoSizeColumn(p);;
        row.createCell(p++).setCellValue("Candidato");
    // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue("Motivo");
    // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue("Etapa");
    // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue("Analista");
    // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue("Observador");
    // s.autoSizeColumn(p);
    	
        return row;
	}

	@Override
	protected Row getXLSCORPRowMapping(Row row, Sheet s, Object o, Integer p) {
		MotivosRechazoVO vo = (MotivosRechazoVO)o;
		
        row.createCell(p++).setCellValue(vo.getFecha());
    // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue(vo.getCandidato());
    // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue(vo.getMotivo());
    // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue(vo.getEtapa());
    // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue(vo.getAnalista());
    // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue(vo.getObservador());
        
        
        
        return row;
	}

	
	@Override
	protected Row getXLSCOMHeaders(Row row, Sheet s, Integer p) {
		row.createCell(p++).setCellValue("Fecha");
		// s.autoSizeColumn(p);;
        row.createCell(p++).setCellValue("Candidato");
    // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue("Motivo");
    // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue("getEtapa");
    // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue("getAnalista");
    // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue("Observador");
    // s.autoSizeColumn(p);
        
        
        return row;
	}

	@Override
	protected Row getXLSCOMRowMapping(Row row, Sheet s, Object o, Integer p) {
		MotivosRechazoVO vo = (MotivosRechazoVO)o;
		// s.autoSizeColumn(p);;
        row.createCell(p++).setCellValue(vo.getFecha());
    // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue(vo.getCandidato());
    // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue(vo.getMotivo());
    // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue(vo.getEtapa());
    // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue(vo.getAnalista());
    // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue(vo.getObservador());
    // s.autoSizeColumn(p);
        
        
        
       	
        return row;
	}


}
