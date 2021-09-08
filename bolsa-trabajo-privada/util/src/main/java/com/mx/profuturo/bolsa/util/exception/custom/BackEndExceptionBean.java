package com.mx.profuturo.bolsa.util.exception.custom;

import java.util.List;

public class BackEndExceptionBean {

	private Integer errorCode;
	//private Integer httpStatus; 
	private String rootErrorMessage;
	private List<String> errorList;
	public Integer getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}
	
	public String getRootErrorMessage() {
		return rootErrorMessage;
	}
	public void setRootErrorMessage(String rootErrorMessage) {
		this.rootErrorMessage = rootErrorMessage;
	}
	public List<String> getErrorList() {
		return errorList;
	}
	public void setErrorList(List<String> errorList) {
		this.errorList = errorList;
	}
	
	
	
	
}
