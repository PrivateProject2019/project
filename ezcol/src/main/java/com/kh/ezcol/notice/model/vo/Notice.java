package com.kh.ezcol.notice.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Notice implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4963635229168479127L;

	private int noticeno;
	private String empno;
	private String title;
	private String noticecontent;
	private String ofilename;
	private String rfilename;
	private String noticetype;
	private Date noticedate;
	
	private String empname;
	
	public Notice() {
		// TODO Auto-generated constructor stub
	}

	public Notice(int noticeno, String empno, String title, String noticecontent, String ofilename, String rfilename,
			String noticetype, Date noticedate, String empname) {
		super();
		this.noticeno = noticeno;
		this.empno = empno;
		this.title = title;
		this.noticecontent = noticecontent;
		this.ofilename = ofilename;
		this.rfilename = rfilename;
		this.noticetype = noticetype;
		this.noticedate = noticedate;
		this.empname = empname;
	}

	@Override
	public String toString() {
		return "Notice [noticeno=" + noticeno + ", empno=" + empno + ", title=" + title + ", noticecontent="
				+ noticecontent + ", ofilename=" + ofilename + ", rfilename=" + rfilename + ", noticetype=" + noticetype
				+ ", noticedate=" + noticedate + ", empname=" + empname + "]";
	}

	public int getNoticeno() {
		return noticeno;
	}

	public void setNoticeno(int noticeno) {
		this.noticeno = noticeno;
	}

	public String getEmpno() {
		return empno;
	}

	public void setEmpno(String empno) {
		this.empno = empno;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getNoticecontent() {
		return noticecontent;
	}

	public void setNoticecontent(String noticecontent) {
		this.noticecontent = noticecontent;
	}

	public String getOfilename() {
		return ofilename;
	}

	public void setOfilename(String ofilename) {
		this.ofilename = ofilename;
	}

	public String getRfilename() {
		return rfilename;
	}

	public void setRfilename(String rfilename) {
		this.rfilename = rfilename;
	}

	public String getNoticetype() {
		return noticetype;
	}

	public void setNoticetype(String noticetype) {
		this.noticetype = noticetype;
	}

	public Date getNoticedate() {
		return noticedate;
	}

	public void setNoticedate(Date noticedate) {
		this.noticedate = noticedate;
	}

	public String getEmpname() {
		return empname;
	}

	public void setEmpname(String empname) {
		this.empname = empname;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	

	
	
	
	
	
}
