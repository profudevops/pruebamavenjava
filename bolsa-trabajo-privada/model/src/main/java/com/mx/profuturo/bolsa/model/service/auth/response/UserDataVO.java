package com.mx.profuturo.bolsa.model.service.auth.response;

import com.mx.profuturo.bolsa.model.vo.common.PersonVO;
import com.mx.profuturo.bolsa.model.vo.common.SystemSectionVO;

import java.util.ArrayList;

public class UserDataVO extends PersonVO {

    private String idRole;
    private String roleDescription;
    private String branch;
    private ArrayList<SystemSectionVO> systemSections;
    private String fullName;
    private String token;

    public UserDataVO() {
    	
    }
    
    public String getIdRole() {
        return idRole;
    }

    public void setIdRole(String idRole) {
        this.idRole = idRole;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }

    public ArrayList<SystemSectionVO> getSystemSections() {
        return systemSections;
    }

    public void setSystemSections(ArrayList<SystemSectionVO> systemSections) {
        this.systemSections = systemSections;
    }

    public UserDataVO(PersonVO vo){
        super(vo);
    }

    public UserDataVO(UserCredentialsVO vo){
        super(vo);
        this.idRole = vo.getRole();
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
    
}
