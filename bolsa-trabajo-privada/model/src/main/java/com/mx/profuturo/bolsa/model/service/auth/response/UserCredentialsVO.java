package com.mx.profuturo.bolsa.model.service.auth.response;

import com.mx.profuturo.bolsa.model.vo.common.PersonVO;

public class UserCredentialsVO extends PersonVO {

    private String id;
    private String password;
    private String role;
    private Long sessionTime;
    private String branch;
    private Integer idBranch;

    public Integer getIdBranch() {
        return idBranch;
    }

    public void setIdBranch(Integer idBranch) {
        this.idBranch = idBranch;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public UserCredentialsVO(){}

    public UserCredentialsVO(String id, String password, String role){
        this.id = id;
        this.password = password;
        this.role = role;
    }



    public Long getSessionTime() {
		return sessionTime;
	}

	public void setSessionTime(Long sessionTime) {
		this.sessionTime = sessionTime;
	}

	public UserCredentialsVO(PersonVO vo){
        super(vo);
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String division) {
        this.branch = division;
    }
}

