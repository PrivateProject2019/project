package com.kh.ezcol.teacher.model.vo;

import java.io.Serializable;

public class Teacher implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6557141190925024L;

	private String teacherno; //교수번호 
	private String deptno; //학과번호  
	private String name; //이름  
	private String status; //재직상태 
	private String ssn; //주민번호 
	private String email; //이메일 
	private String address; //주소 
	private String detailaddress; //상세주소 
	private String post; //우편번호 
	private String phone; //연락처  
	private String emergency; //비상연락망 
	private String identity; //로그인시 사용하는 식별자 
	
	private String deptname; //학과이름 
	
	public Teacher() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Teacher [teacherno=" + teacherno + ", deptno=" + deptno + ", name=" + name + ", status=" + status
				+ ", ssn=" + ssn + ", email=" + email + ", address=" + address + ", detailaddress=" + detailaddress
				+ ", post=" + post + ", phone=" + phone + ", emergency=" + emergency + ", identity=" + identity
				+ ", deptname=" + deptname + "]";
	}

	public Teacher(String teacherno, String deptno, String name, String status, String ssn, String email,
			String address, String detailaddress, String post, String phone, String emergency, String identity,
			String deptname) {
		super();
		this.teacherno = teacherno;
		this.deptno = deptno;
		this.name = name;
		this.status = status;
		this.ssn = ssn;
		this.email = email;
		this.address = address;
		this.detailaddress = detailaddress;
		this.post = post;
		this.phone = phone;
		this.emergency = emergency;
		this.identity = identity;
		this.deptname = deptname;
	}

	public String getTeacherno() {
		return teacherno;
	}

	public void setTeacherno(String teacherno) {
		this.teacherno = teacherno;
	}

	public String getDeptno() {
		return deptno;
	}

	public void setDeptno(String deptno) {
		this.deptno = deptno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDetailaddress() {
		return detailaddress;
	}

	public void setDetailaddress(String detailaddress) {
		this.detailaddress = detailaddress;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmergency() {
		return emergency;
	}

	public void setEmergency(String emergency) {
		this.emergency = emergency;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	
	
	
}
