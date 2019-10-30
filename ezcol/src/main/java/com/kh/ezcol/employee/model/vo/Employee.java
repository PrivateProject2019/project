package com.kh.ezcol.employee.model.vo;

import java.io.Serializable;

public class Employee implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2670659752634369229L;
	
	private String empno;
	private String name;
	private String job;
	private String belong;
	private String ssn;
	private String email;
	private String address;
	private String detailaddress;
	private String post;
	private String phone;
	private String emergency;
	private String identity;
	
	public Employee() {
		// TODO Auto-generated constructor stub
	}

	public Employee(String empno, String name, String job, String belong, String ssn, String email, String address,
			String detailaddress, String post, String phone, String emergency, String identity) {
		super();
		this.empno = empno;
		this.name = name;
		this.job = job;
		this.belong = belong;
		this.ssn = ssn;
		this.email = email;
		this.address = address;
		this.detailaddress = detailaddress;
		this.post = post;
		this.phone = phone;
		this.emergency = emergency;
		this.identity = identity;
	}

	@Override
	public String toString() {
		return "Employee [empno=" + empno + ", name=" + name + ", job=" + job + ", belong=" + belong + ", ssn=" + ssn
				+ ", email=" + email + ", address=" + address + ", detailaddress=" + detailaddress + ", post=" + post
				+ ", phone=" + phone + ", emergency=" + emergency + ", identity=" + identity + "]";
	}

	public String getEmpno() {
		return empno;
	}

	public void setEmpno(String empno) {
		this.empno = empno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getBelong() {
		return belong;
	}

	public void setBelong(String belong) {
		this.belong = belong;
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
	 
	
	
	
}
