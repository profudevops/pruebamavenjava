package com.mx.profuturo.bolsa.model.service;

/**
 * Created by Julio Arredondo on 11/07/2016.
 */
public  class BaseResponseBean {
	
	protected Integer errorCode;

	protected String rootErrorMessage;
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
	
    protected String status;
    protected String statusText;
    protected boolean success;
    protected int numericStatus = 0;

    public int getStatus() {
        return numericStatus;
    }

    public void setStatus(String status) {
        this.status = status;
        if(this.status != null) {
            this.numericStatus = Integer.decode(this.status);
        }
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean result) {
        this.success = result;
    }
}
