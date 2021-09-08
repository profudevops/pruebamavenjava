package com.mx.profuturo.bolsa.model.reports.templates;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.mx.profuturo.bolsa.model.reports.templates.XLSTemplate;
import com.mx.profuturo.bolsa.model.reports.vo.data.ContratacionesAnalistaVO;
import com.mx.profuturo.bolsa.model.reports.vo.data.MotivosRechazoVO;

public class ContratacionesPorAnalistaTemplate extends BaseTemplate implements XLSTemplate {

	public ContratacionesPorAnalistaTemplate(String branch) {
		this.mode = branch;
	}
		
	
	//posición con alta	fecha contratación	nivel puesto	analista	observador/gerente	gerencia	regional	division
	

	//contratadciones x: analista

	@Override
	public String getFileName() {
		
		return "contrataciones-analista.xlsx";
	}


	@Override
	protected Row getXLSCORPHeaders(Row row,Sheet s, Integer p) {
		return this.getXLSCOMHeaders(row,s, p);
	}

	@Override
	protected Row getXLSCORPRowMapping(Row row, Sheet s, Object o, Integer p) {
		return this.getXLSCOMRowMapping(row,s, o, p);
	}

	
	@Override
	protected Row getXLSCOMHeaders(Row row, Sheet s, Integer p) {
		// s.autoSizeColumn(p);;
		row.createCell(p++).setCellValue("Fecha");
		// s.autoSizeColumn(p);;
        row.createCell(p++).setCellValue("Analista");
    // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue("Contratación");
    // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue("Nivelpuesto");
    // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue("Vacante");
    // s.autoSizeColumn(p);
        return row;
	}

	@Override
	protected Row getXLSCOMRowMapping(Row row,Sheet s, Object o, Integer p) {
		ContratacionesAnalistaVO vo = (ContratacionesAnalistaVO)o;
		// s.autoSizeColumn(p);;
        row.createCell(p++).setCellValue(vo.getFecha());
    // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue(vo.getAnalista());
    // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue(vo.getContratacion());
    // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue(vo.getNivelPuesto());
    // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue(vo.getTituloVacante());
    // s.autoSizeColumn(p);
        return row;
	}

}
