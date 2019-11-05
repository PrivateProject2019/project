package com.kh.ezcol.breakDown.model.vo;

import java.io.Serializable;


public class BreakDown implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7658032219691209146L;

	private String classno;
	private String studentno;
	private double score;
	
	
	public BreakDown() {
		// TODO Auto-generated constructor stub
	}


	@Override
	public String toString() {
		return "BreakDown [classno=" + classno + ", studentno=" + studentno + ", score=" + score + "]";
	}


	public BreakDown(String classno, String studentno, double score) {
		super();
		this.classno = classno;
		this.studentno = studentno;
		this.score = score;
	}


	public String getClassno() {
		return classno;
	}


	public void setClassno(String classno) {
		this.classno = classno;
	}


	public String getStudentno() {
		return studentno;
	}


	public void setStudentno(String studentno) {
		this.studentno = studentno;
	}


	public double getScore() {
		return score;
	}


	public void setScore(double score) {
		this.score = score;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
