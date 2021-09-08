package com.mx.profuturo.bolsa.model.reports.templates;

import com.mx.profuturo.bolsa.model.reports.vo.data.DatosFiltrosVO;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public abstract class CandidateInfoBaseTemplate implements XLSTemplate{

    protected String mode;
    protected String fileName;

    @Override
    public Row getXLSHeaders(Row row, Sheet s, Integer tipoVacante) {

        if("CORP".equals(this.mode)) {
            int p = 0;
            row = this.getXLSCORPHeaders(row,s, p);
        }else if("COM".equals(this.mode)) {
            int p = 0;
            row = this.getXLSCOMHeaders(row,s, p);

        }

        return row;
    }

    abstract protected Row getXLSCORPHeaders(Row row, Sheet s, Integer  p);
    abstract protected Row getXLSCOMHeaders(Row row,Sheet s, Integer p);


    @Override
    public Row getXLSRowMapping(Row row, Sheet s, Object vo, Integer tipoVacante) {
        Integer p = 0;
        if("CORP".equals(this.mode)) {
            row = this.getXLSCORPRowMapping(row,s, vo, p);
        }else if("COM".equals(this.mode)) {
            row = this.getXLSCOMRowMapping(row,s, vo, p);            
        }

        return row;
    }

    @Override
    public Row getXLSGeneralHeader(Row row, Object vo) {
        return row;
    }


    protected Row getFilterCOMRows(Row row, Sheet s, DatosFiltrosVO vo, Integer p) {
    // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue(vo.getDivision());
    // s.autoSizeColumn(p);
        row.createCell((p++)).setCellValue(vo.getRegional());
    // s.autoSizeColumn(p);
        row.createCell((p++)).setCellValue(vo.getGerencia());
    // s.autoSizeColumn(p);
        return row;
    }

    protected Row getFilterCOMHeaders(Row row, Sheet s, Integer p) {
    // s.autoSizeColumn(p);
        row.createCell(p++).setCellValue("División");
    // s.autoSizeColumn(p);
        row.createCell((p++)).setCellValue("Regional");
    // s.autoSizeColumn(p);
        row.createCell((p++)).setCellValue("Gerencia");
    // s.autoSizeColumn(p);
        return row;
    }


    protected Row getFilterCORPHeaders(Row row,Sheet s, Integer p, Integer tipoVacante) {
        if(tipoVacante == 1) {
        // s.autoSizeColumn(p);
            row.createCell(p++).setCellValue("Área de Interes");
        // s.autoSizeColumn(p);
            row.createCell((p++)).setCellValue("Subarea de interes");
        // s.autoSizeColumn(p);
        }else{
        // s.autoSizeColumn(p);
            row.createCell(p++).setCellValue("Centro de Costos");
        // s.autoSizeColumn(p);
        }

        return row;
    }


    protected Row getFilterCORPRows(Row row, Sheet s, DatosFiltrosVO vo, Integer p, Integer tipoVacante) {
        if(tipoVacante == 1) {
            row.createCell(p++).setCellValue(vo.getAreaInteres());
        // s.autoSizeColumn(p);
            row.createCell((p++)).setCellValue(vo.getSubAreaInteres());
        // s.autoSizeColumn(p);
        }else{
        // s.autoSizeColumn(p);
            row.createCell(p++).setCellValue(vo.getCentroCostos());
        // s.autoSizeColumn(p);
        }

        return row;
    }


    abstract protected Row getXLSCORPRowMapping(Row row, Sheet s, Object vo, Integer p);
    abstract protected Row getXLSCOMRowMapping(Row row,Sheet s, Object vo, Integer p);


    abstract public String getFileName();



}
