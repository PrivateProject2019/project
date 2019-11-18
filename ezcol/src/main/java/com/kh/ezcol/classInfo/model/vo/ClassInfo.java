package com.kh.ezcol.classInfo.model.vo;

import java.io.Serializable;

public class ClassInfo implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 2713027265467146231L;
	
	
	private String classno; //강의번호 
	private String deptno; //학과번호 
	private String teacherno; //담당교수번호 
	private String classname; //강의이름
	private String classtype; //강의종류 
	private String classday; //강의 요일 
	private String classstart; //강의 시작 교시 
	private String classend; //강의 끝 교시 
	private int admission; // 수업 정원 
	private int score;   //배당 학점 
	private String place; //강의장소 
	private String curriculum; //커리큘럼 내용 
	private String ofilename; //첨부파일 원본 파일명 
	private String rfilename; //첨부파일 수정 파일명 
	private String semester; //학기
	 
	private String teachername; //담당교수이름 
	private String deptname; //학과이름 
	private double bScore; //명세서에 해당하는 점수 
	private int addAll; //총 인원수 
	
	private String survey; //설문 조사 유무 
	
	public ClassInfo() {
		// TODO Auto-generated constructor stub
	}

	public ClassInfo(String classno, String deptno, String teacherno, String classname, String classtype,
			String classday, String classstart, String classend, int admission, int score, String place,
			String curriculum, String ofilename, String rfilename, String semester, String teachername, String deptname,
			double bScore, int addAll, String survey) {
		super();
		this.classno = classno;
		this.deptno = deptno;
		this.teacherno = teacherno;
		this.classname = classname;
		this.classtype = classtype;
		this.classday = classday;
		this.classstart = classstart;
		this.classend = classend;
		this.admission = admission;
		this.score = score;
		this.place = place;
		this.curriculum = curriculum;
		this.ofilename = ofilename;
		this.rfilename = rfilename;
		this.semester = semester;
		this.teachername = teachername;
		this.deptname = deptname;
		this.bScore = bScore;
		this.addAll = addAll;
		this.survey = survey;
	}

	@Override
	public String toString() {
		return "ClassInfo [classno=" + classno + ", deptno=" + deptno + ", teacherno=" + teacherno + ", classname="
				+ classname + ", classtype=" + classtype + ", classday=" + classday + ", classstart=" + classstart
				+ ", classend=" + classend + ", admission=" + admission + ", score=" + score + ", place=" + place
				+ ", curriculum=" + curriculum + ", ofilename=" + ofilename + ", rfilename=" + rfilename + ", semester="
				+ semester + ", teachername=" + teachername + ", deptname=" + deptname + ", bScore=" + bScore
				+ ", addAll=" + addAll + ", survey=" + survey + "]";
	}

	public String getClassno() {
		return classno;
	}

	public void setClassno(String classno) {
		this.classno = classno;
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

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public String getClasstype() {
		return classtype;
	}

	public void setClasstype(String classtype) {
		this.classtype = classtype;
	}

	public String getClassday() {
		return classday;
	}

	public void setClassday(String classday) {
		this.classday = classday;
	}

	public String getClassstart() {
		return classstart;
	}

	public void setClassstart(String classstart) {
		this.classstart = classstart;
	}

	public String getClassend() {
		return classend;
	}

	public void setClassend(String classend) {
		this.classend = classend;
	}

	public int getAdmission() {
		return admission;
	}

	public void setAdmission(int admission) {
		this.admission = admission;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getCurriculum() {
		return curriculum;
	}

	public void setCurriculum(String curriculum) {
		this.curriculum = curriculum;
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

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
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

	public double getbScore() {
		return bScore;
	}

	public void setbScore(double bScore) {
		this.bScore = bScore;
	}

	public int getAddAll() {
		return addAll;
	}

	public void setAddAll(int addAll) {
		this.addAll = addAll;
	}

	public String getSurvey() {
		return survey;
	}

	public void setSurvey(String survey) {
		this.survey = survey;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

   	
	
   
	
}