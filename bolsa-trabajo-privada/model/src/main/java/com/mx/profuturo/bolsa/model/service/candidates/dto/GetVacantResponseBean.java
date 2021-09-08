package com.mx.profuturo.bolsa.model.service.candidates.dto;

import java.util.ArrayList;

import com.mx.profuturo.bolsa.model.vo.common.BasicCatalogVO;

public class GetVacantResponseBean {
	
	private boolean success;
	private ArrayList<BasicCatalogVO> data = new ArrayList<BasicCatalogVO>();
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public ArrayList<BasicCatalogVO> getData() {
		return data;
	}
	public void setData(ArrayList<BasicCatalogVO> data) {
		this.data = data;
	}

	
}
