package com.kh.ezcol.student.model.vo;

import java.io.Serializable;

public class Student implements Serializable {

	
	private static final long serialVersionUID = 8419469597238632392L;

	private String studentno;
	private String teacherno;
	private String deptno;
	private String name;
	private String status;
	private String ssn;
	private String email;
	private String address;
	private String detailaddress;
	private String post;
	private String phone;
	private String emergency;
	private String grade;
	private String identity;
	
	
	private String teachername;
	private String deptname;
	
	public Student() {
		// TODO Auto-generated constructor stub
	}

	public String getStudentno() {
		return studentno;
	}

	public void setStudentno(String studentno) {
		this.studentno = studentno;
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

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public String getTeachername() {
		return teachername;
	}

	public void setTeachername(String teachername) {
		this.teachername = teachername;
	}

	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	@Override
	public String toString() {
		return "Student [studentno=" + studentno + ", teacherno=" + teacherno + ", deptno=" + deptno + ", name=" + name
				+ ", status=" + status + ", ssn=" + ssn + ", email=" + email + ", address=" + address
				+ ", detailaddress=" + detailaddress + ", post=" + post + ", phone=" + phone + ", emergency="
				+ emergency + ", grade=" + grade + ", identity=" + identity + ", teachername=" + teachername
				+ ", deptname=" + deptname + "]";
	}

	public Student(String studentno, String teacherno, String deptno, String name, String status, String ssn,
			String email, String address, String detailaddress, String post, String phone, String emergency,
			String grade, String identity, String teachername, String deptname) {
		super();
		this.studentno = studentno;
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
		this.grade = grade;
		this.identity = identity;
		this.teachername = teachername;
		this.deptname = deptname;
	}
	
	
	
	
	
}
