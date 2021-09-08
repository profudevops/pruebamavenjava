package com.mx.profuturo.bolsa.service.reports;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.mx.profuturo.bolsa.model.reports.vo.ReportVO;

public interface ExcelService {

	void downloadXLSXFile(HttpServletResponse response, ReportVO report) throws IOException;

}
