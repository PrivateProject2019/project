package com.kh.ezcol.student.model.service;

import java.util.HashMap;
import java.util.List;

import com.kh.ezcol.common.model.vo.Common;

import com.kh.ezcol.student.model.vo.Student;


public interface StudentService {

	Student login(Common common);

	int listCount();

	List<Student> selectAll(HashMap<String, Object> map);

	String getDeptName(String deptno);

	String getTeacherName(String teacherno);

	int searchListCount(String keyword);

	List<Student> searchStudent(HashMap<String, Object> map);

	int insertStudent(Student student);

	Student selectOne(String no);

	int updateStudent(Student student);

}
