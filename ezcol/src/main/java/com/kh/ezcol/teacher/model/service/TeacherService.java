package com.kh.ezcol.teacher.model.service;

import java.util.HashMap;
import java.util.List;

import com.kh.ezcol.common.model.vo.Common;
import com.kh.ezcol.employee.model.vo.Employee;
import com.kh.ezcol.teacher.model.vo.Teacher;

public interface TeacherService {

	Teacher login(Common common);

	int listCount();

	List<Teacher> selectAll(HashMap<String, Object> map);

	String deptNoToName(String deptno);

	int searchListCount(String keyword);

	List<Teacher> searchTeacher(HashMap<String, Object> map);

	int insertTeacher(Teacher teacher);

	Teacher selectOne(String no);

	int updateTeacher(Teacher teacher);

	int deleteTeacher(String no);

	String getTeacherName(String teacherno);


}
