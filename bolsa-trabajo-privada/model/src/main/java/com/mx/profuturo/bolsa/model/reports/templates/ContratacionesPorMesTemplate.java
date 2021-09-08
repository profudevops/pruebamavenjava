package com.mx.profuturo.bolsa.model.reports.templates;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.mx.profuturo.bolsa.model.reports.templates.XLSTemplate;
import com.mx.profuturo.bolsa.model.reports.vo.data.ContratacionesMesVO;
import com.mx.profuturo.bolsa.model.reports.vo.data.MotivosRechazoVO;

public class ContratacionesPorMesTemplate extends BaseTemplate implements XLSTemplate {

	public ContratacionesPorMesTemplate(String branch) {
		this.mode = branch;
	}
		

	@Override
	public String getFileName() {
		
		return "contrataciones-mes.xlsx";
	}

	//posición con alta	fecha contratación	nivel puesto	analista	observador/gerente	gerencia	regional	division
	//X:mes Y:cantidad

	@Override
	protected Row getXLSCORPHeaders(Row row, Sheet s, Integer p) {
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
        row.createCell(p++).setCellValue("Mes");
    // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue("Vacante");
    // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue("Contratación");
    // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue("Nivel de Puesto");
    // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue("Analista");
    // s.autoSizeColumn(p);
        return row;
	}

	@Override
	protected Row getXLSCOMRowMapping(Row row, Sheet s, Object o, Integer p) {
		ContratacionesMesVO vo = (ContratacionesMesVO)o;
		// s.autoSizeColumn(p);;
        row.createCell(p++).setCellValue(vo.getFecha());
    // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue(vo.getMes());
    // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue(vo.getTituloVacante());
    // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue(vo.getContratacion());
    // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue(vo.getNivelPuesto());
    // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue(vo.getAnalista());
    // s.autoSizeColumn(p);
        return row;
	}

}
