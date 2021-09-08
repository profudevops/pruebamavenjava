package com.mx.profuturo.bolsa.model.reports.templates;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.mx.profuturo.bolsa.model.reports.vo.data.MotivosRechazoVO;

public interface XLSTemplate {

	
	String getFileName();

	Row getXLSGeneralHeader(Row row, Object vo);

	Row getXLSRowMapping(Row row, Sheet s, Object vo, Integer tipoVacante);


	Row getXLSHeaders(Row row, Sheet s, Integer tipoVacante);


}
