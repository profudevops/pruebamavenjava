package com.mx.profuturo.bolsa.model.reports.templates;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.mx.profuturo.bolsa.model.reports.templates.XLSTemplate;
import com.mx.profuturo.bolsa.model.reports.vo.data.FuentesReclutamientoVO;
import com.mx.profuturo.bolsa.model.reports.vo.data.MotivosRechazoVO;

public class FuentesReclutamientoTemplate extends BaseTemplate implements XLSTemplate {

	public FuentesReclutamientoTemplate(String branch) {
		this.mode = branch;
	}
		
//fuente	candidato	fecha inicio	fecha fin	estatus etapa	analista	observador	gerencia	regional	division
	//Y: cantidades X: fuentes
	@Override
	public String getFileName() {
		
		return "fuentes-reclutamiento.xlsx";
	}


	@Override
	protected Row getXLSCORPHeaders(Row row, Sheet s, Integer p) {
		
        return this.getXLSCOMHeaders(row, s, p);
	}

	@Override
	protected Row getXLSCORPRowMapping(Row row,  Sheet s, Object o, Integer p) {
		return this.getXLSCOMRowMapping(row,s, o, p);
	}

	
	@Override
	protected Row getXLSCOMHeaders(Row row, Sheet s, Integer p) {
		row.createCell(p++).setCellValue("Fecha");
		// s.autoSizeColumn(p);;
        row.createCell(p++).setCellValue("Candidato");
    // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue("Estatus");
    // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue("Fuente");
    // s.autoSizeColumn(p);
        
        return row;
	}

	@Override
	protected Row getXLSCOMRowMapping(Row row, Sheet s, Object o, Integer p) {
		FuentesReclutamientoVO vo = (FuentesReclutamientoVO)o;
		
        row.createCell(p++).setCellValue(vo.getFecha());
    // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue(vo.getCandidato());
    // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue(vo.getStatus());
    // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue(vo.getFuente());
    // s.autoSizeColumn(p);
        
        return row;
	}

}
