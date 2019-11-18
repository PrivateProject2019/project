package com.kh.ezcol.common.model.vo;

import java.io.Serializable;

public class Common implements Serializable{

	


	private static final long serialVersionUID = 1111L;
	
	private String id; //학번,교번,사번 
	private String password; //주민번호 뒤 7자리 
	private String type; //학생,교수,직원
	
	
	public Common() {
		// TODO Auto-generated constructor stub
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


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	@Override
	public String toString() {
		return "Common [id=" + id + ", password=" + password + ", type=" + type + "]";
	}


	public Common(String id, String password, String type) {
		super();
		this.id = id;
		this.password = password;
		this.type = type;
	}
	
	
	
	
	
	
}
