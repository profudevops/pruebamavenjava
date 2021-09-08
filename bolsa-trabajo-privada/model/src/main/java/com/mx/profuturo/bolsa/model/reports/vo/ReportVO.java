package com.mx.profuturo.bolsa.model.reports.vo;

import java.util.LinkedList;

public class ReportVO {

	private GraphicVO graphicVO;
	private LinkedList reportData;
	private String title;
	private String code;
	private Integer tipoVacante;
	
	private String branch;
	
	public GraphicVO getGraphicVO() {
		return graphicVO;
	}
	public void setGraphicVO(GraphicVO graphicVO) {
		this.graphicVO = graphicVO;
	}
	public LinkedList getReportData() {
		return reportData;
	}
	public void setReportData(LinkedList reportData) {
		this.reportData = reportData;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public Integer getTipoVacante() {
		return tipoVacante;
	}
	public void setTipoVacante(Integer tipoVacante) {
		this.tipoVacante = tipoVacante;
	}
	
}
