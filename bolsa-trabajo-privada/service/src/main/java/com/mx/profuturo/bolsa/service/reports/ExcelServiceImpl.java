package com.mx.profuturo.bolsa.service.reports;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.mx.profuturo.bolsa.model.reports.templates.CandidateComInformationTemplate;
import com.mx.profuturo.bolsa.model.reports.templates.CandidateCorpInformationTemplate;
import com.mx.profuturo.bolsa.model.reports.templates.CandidatosFinalistasTemplate;
import com.mx.profuturo.bolsa.model.reports.templates.ContratacionesNivelPuestoTemplate;
import com.mx.profuturo.bolsa.model.reports.templates.ContratacionesPorAnalistaTemplate;
import com.mx.profuturo.bolsa.model.reports.templates.ContratacionesPorMesTemplate;
import com.mx.profuturo.bolsa.model.reports.templates.FuentesReclutamientoTemplate;
import com.mx.profuturo.bolsa.model.reports.templates.MotivosRechazoTemplate;
import com.mx.profuturo.bolsa.model.reports.templates.TipoEntrevistaTemplate;
import com.mx.profuturo.bolsa.model.reports.templates.XLSTemplate;
import com.mx.profuturo.bolsa.model.reports.vo.ReportVO;
import com.mx.profuturo.bolsa.model.service.candidates.dto.CandidateComInfo;
import com.mx.profuturo.bolsa.model.service.candidates.dto.CandidateCorpInfo;

@Service
@Scope("request")
public class ExcelServiceImpl implements ExcelService {
	
	
	@Override
	public void downloadXLSXFile(HttpServletResponse response, ReportVO report) throws IOException {
		
		
		XLSTemplate template = this.buildTemplate(report.getCode(), report.getBranch());
		
        response.setHeader("Content-disposition", 
        		new StringBuilder("attachment; filename=").append(template.getFileName()).toString());
        
        Integer rowNum = 0;
        //build a file from scratch and then download
        Workbook workbook = new XSSFWorkbook();
        Sheet x = workbook.createSheet(report.getTitle());
        Row header = x.createRow(rowNum);
        header = template.getXLSHeaders(header, x, report.getTipoVacante());
        Cell tmpCell = null;
        CellStyle headerStyle = this.getHeaderStyle(workbook);
        for(int i = 0; i < header.getLastCellNum();i++) {
        	tmpCell = header.getCell(i);
        	tmpCell.setCellStyle(headerStyle);
        }
        Row row = null;
        for(Object data: report.getReportData()) {
        	row = x.createRow(++rowNum);
        	row = template.getXLSRowMapping(row,x, data, report.getTipoVacante());
        }
//        if(report.getBranch().equals("COM")){
//        	Integer p = 0;
//        	for(Object data: report.getReportData()) {
//            	row = x.createRow(++rowNum);
//            	row = template.getXLSRowMapping(row, x, data, p);
////            	System.out.println(rowNum);
//            }
//        }else if (report.getBranch().equals("CORP")){
//        	for(Object data: report.getReportData()) {
//        		Integer p = 0;
//        		row = x.createRow(++rowNum);
//        		row = template.getXLSCORPRowMapping(row, x, data, p);
////        		System.out.println(rowNum);
//        	}
//        }
         
        workbook.write(response.getOutputStream());
        
    }
	
//	protected Row getXLSCORPRowMapping(Row row, Sheet s, Object o, Integer p) {
//        CandidateCorpInfo vo = (CandidateCorpInfo)o;
//
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getFechaPostulacion());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getPostuladoOcartera());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getVacanteAplicada());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getAreaInteres());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getSubAreaInteres());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getNombreCompleto());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getFuente());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getResidencia());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getMunicipio());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getCelular());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getCorreo());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getCurp());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getRfc());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getGenero());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getEdad());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getEscolaridad());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getEspecialidad());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getEstadoCivil());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getDivisional());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getRegional());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getGerencia());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getFiltroTelefonico());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getDescarteTelefonico());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getTipoPrimerEntrevista());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getFechaPrimerEntrevista());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getHoraPrimerEntrevista());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getResultadoPrimerEntrevista());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getMotivoDescartePrimerEntrevista());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getComentariosPrimerEntrevista());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getD());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getI());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getS());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getC());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getExamenConfianza());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getResultadoExamenConfianza());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getExamenPsicometrico());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getResultadoExamenPsicometrico());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getNombreEntrevistador());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getTipoSegundaEntrevista());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getFechaSegundaEntrevista());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getHoraSegundaEntrevista());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getResultadoSegundaEntrevista());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getMotivoDescarteSegundaEntrevista());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getComentariosSegundaEntrevista());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getResponsableVacante());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getAceptaOferta());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getMotivoNoAceptaOferta());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getComentariosOferta());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getSolicitudEmpleo());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getSocioEconomico());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getContratado());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getMotivoNoContratado());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getComentarios());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getRespostulado());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getEmpresaAnterior());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getPuestoAnterior());
//        // s.autoSizeColumn(p);
//
//
//        return row;
//    }
//	
//	
//	protected Row getXLSCOMRowMapping(Row row, Sheet s, Object o, Integer p) {
//        CandidateComInfo vo = (CandidateComInfo)o;
//
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getFechaPostulacion());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getPostuladoOcartera());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getVacanteAplicada());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getAreaInteres());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getSubAreaInteres());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getNombreCompleto());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getFuente());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getResidencia());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getMunicipio());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getCelular());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getCorreo());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getCurp());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getRfc());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getGenero());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getEdad());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getEscolaridad());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getEspecialidad());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getEstadoCivil());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getDivisional());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getRegional());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getGerencia());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getFiltroTelefonico());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getDescarteTelefonico());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getTipoPrimerEntrevista());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getFechaPrimerEntrevista());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getHoraPrimerEntrevista());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getResultadoPrimerEntrevista());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getMotivoDescartePrimerEntrevista());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getComentariosPrimerEntrevista());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getD());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getI());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getS());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getC());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getAmitai());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getNombreEntrevistador());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getTipoSegundaEntrevista());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getFechaSegundaEntrevista());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getHoraSegundaEntrevista());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getResultadoSegundaEntrevista());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getMotivoDescarteSegundaEntrevista());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getComentariosSegundaEntrevista());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getResponsableVacante());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getAceptaOferta());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getMotivoNoAceptaOferta());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getComentariosOferta());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getSolicitudEmpleo());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getSocioEconomico());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getContratado());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getMotivoNoContratado());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getComentarios());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getRespostulado());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getEmpresaAnterior());
//        // s.autoSizeColumn(p);
//        row.createCell(p++).setCellValue(vo.getPuestoAnterior());
//        // s.autoSizeColumn(p);
//
//
//        return row;
//    }
	
	private CellStyle getHeaderStyle(Workbook workbook) {
		CellStyle style = workbook.createCellStyle();  
        // Setting Background color  
        style.setFillForegroundColor(IndexedColors.BLUE.getIndex()); 
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);

        Font font = workbook.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setColor(IndexedColors.WHITE.getIndex());
        style.setFont(font);
        
        return style;
	}
	
	


	private XLSTemplate buildTemplate(String reportCode, String branch) {
		XLSTemplate template = null;
		if("MOR".equals(reportCode)) {
			template = new MotivosRechazoTemplate(branch);
		}else if("MORPE".equals(reportCode)) {
			template = new MotivosRechazoTemplate(branch);
		}else if("EAB".equals(reportCode)) {
			template = new MotivosRechazoTemplate(branch);
	
		}else if("CFC".equals(reportCode)) {
			template = new CandidatosFinalistasTemplate(branch);
		
		}else if("CNP".equals(reportCode)) {
			template = new ContratacionesNivelPuestoTemplate(branch);
		
		}else if("CPM".equals(reportCode)) {
			template = new ContratacionesPorMesTemplate(branch);
			
		}else if("CAN".equals(reportCode)) {
			template = new ContratacionesPorAnalistaTemplate(branch);
	
		}else if("FRE".equals(reportCode)) {
			template = new FuentesReclutamientoTemplate(branch);
	
		}else if("TIE".equals(reportCode)) {
			template = new TipoEntrevistaTemplate(branch);
			
		}
		else if("CANINFO".equals(reportCode) && "COM".equals(branch)) {
			template = new CandidateComInformationTemplate(branch);
		}
		else if("CANINFO".equals(reportCode) && "CORP".equals(branch)) {
			template = new CandidateCorpInformationTemplate(branch);
		}
		return template ;
	}
	

}
