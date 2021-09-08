package com.mx.profuturo.bolsa.model.reports.templates;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.mx.profuturo.bolsa.model.reports.templates.XLSTemplate;
import com.mx.profuturo.bolsa.model.reports.vo.data.ContratacionesNivelPuestoVO;
import com.mx.profuturo.bolsa.model.reports.vo.data.MotivosRechazoVO;

public class ContratacionesNivelPuestoTemplate extends BaseTemplate implements XLSTemplate {

	public ContratacionesNivelPuestoTemplate(String branch) {
		this.mode = branch;
	}
	
	
	//posición con alta	fecha contratación	nivel puesto	analista	observador/gerente	gerencia	regional	division
	//Y: cantidad: X: Nivel de puesto

	@Override
	public String getFileName() {
		
		return "nivel-puesto.xlsx";
	}


	@Override
	protected Row getXLSCORPHeaders(Row row, Sheet s, Integer p) {
		
//		row.createCell(p++).setCellValue("Centro de Costos");
		// s.autoSizeColumn(p);;
		row.createCell(p++).setCellValue("Fecha");
		// s.autoSizeColumn(p);;
        row.createCell(p++).setCellValue("Vacante");
    // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue("Nivel de Puesto");
    // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue("Analista");
    // s.autoSizeColumn(p);
        return row;
	}

	@Override
	protected Row getXLSCORPRowMapping(Row row,Sheet s, Object o, Integer p) {
		ContratacionesNivelPuestoVO  vo = (ContratacionesNivelPuestoVO )o;
		
		// s.autoSizeColumn(p);;
//		row.createCell(p++).setCellValue(vo.getGerencia());
		
        row.createCell(p++).setCellValue(vo.getFecha());
    // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue(vo.getTituloVacante());
    // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue(vo.getNivelPuesto());
    // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue(vo.getAnalista());
    // s.autoSizeColumn(p);
        
        return row;
	}

	
	@Override
	protected Row getXLSCOMHeaders(Row row, Sheet s, Integer p) {
		
		// s.autoSizeColumn(p);;
		row.createCell(p++).setCellValue("Fecha");
		// s.autoSizeColumn(p);;
        row.createCell(p++).setCellValue("Analista");
    // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue("Nivel de puesto");
    // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue("Vacante");
    // s.autoSizeColumn(p);
        
        return row;
	}

	@Override
	protected Row getXLSCOMRowMapping(Row row, Sheet s, Object o, Integer p) {
		ContratacionesNivelPuestoVO vo = (ContratacionesNivelPuestoVO)o;
		
		// s.autoSizeColumn(p);;
        row.createCell(p++).setCellValue(vo.getFecha());
    // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue(vo.getAnalista());
    // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue(vo.getNivelPuesto());
    // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue(vo.getTituloVacante());
    // s.autoSizeColumn(p);
        
        return row;
	}

}
