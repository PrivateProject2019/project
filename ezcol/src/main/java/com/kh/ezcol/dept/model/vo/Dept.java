package com.kh.ezcol.dept.model.vo;

import java.io.Serializable;

public class Dept implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6337790451866285984L;

	
	private String deptno; //학과코드 
	private String teacherno; //학과장 코드 
	private String deptname; //학과이름 
	private String admission; //학과정원
	
	public Dept() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Dept [deptno=" + deptno + ", teacherno=" + teacherno + ", deptname=" + deptname + ", admission="
				+ admission + "]";
	}

	public Dept(String deptno, String teacherno, String deptname, String admission) {
		super();
		this.deptno = deptno;
		this.teacherno = teacherno;
		this.deptname = deptname;
		this.admission = admission;
	}

	public String getDeptno() {
		return deptno;
	}

	public void setDeptno(String deptno) {
		this.deptno = deptno;
	}

	public String getTeacherno() {
		return teacherno;
	}

	public void setTeacherno(String teacherno) {
		this.teacherno = teacherno;
	}

	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	public String getAdmission() {
		return admission;
	}

	public void setAdmission(String admission) {
		this.admission = admission;
	}
	
	
	
}
