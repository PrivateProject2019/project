package com.kh.ezcol.teacher.model.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.ezcol.common.model.vo.Common;
import com.kh.ezcol.employee.model.vo.Employee;
import com.kh.ezcol.teacher.model.dao.TeacherDao;
import com.kh.ezcol.teacher.model.vo.Teacher;

@Service
public class TeacherServiceImpl implements TeacherService {

	@Autowired
	private TeacherDao teacherDao;

	@Override
	public Teacher login(Common common) {
		return teacherDao.login(common);
	}

	@Override
	public int listCount() {
		return teacherDao.listCount();
	}

	@Override
	public List<Teacher> selectAll(HashMap<String, Object> map) {
		return teacherDao.selectAll(map);
	}

	@Override
	public String deptNoToName(String deptno) {
		return teacherDao.deptNoToName(deptno);
	}

	@Override
	public int searchListCount(String keyword) {
		return teacherDao.searchListCount(keyword);
	}

	@Override
	public List<Teacher> searchTeacher(HashMap<String, Object> map) {
		return teacherDao.searchTeacher(map);
	}

	@Override
	public int insertTeacher(Teacher teacher) {
		return teacherDao.insertTeacher(teacher);
	}

	@Override
	public Teacher selectOne(String no) {
		return teacherDao.selectOne(no);
	}

	@Override
	public int updateTeacher(Teacher teacher) {
		return teacherDao.updateTeacher(teacher);
	}

	@Override
	public int deleteTeacher(String no) {
		return teacherDao.deleteTeacher(no);
	}

	@Override
	public String getTeacherName(String teacherno) {
		return teacherDao.getTeacherName(teacherno);
	}
}
