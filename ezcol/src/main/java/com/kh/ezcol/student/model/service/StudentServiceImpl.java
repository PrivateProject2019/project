package com.kh.ezcol.student.model.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.ezcol.common.model.vo.Common;
import com.kh.ezcol.student.model.dao.StudentDao;
import com.kh.ezcol.student.model.vo.Student;

@Service("studentService")
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentDao studentDao;

	@Override
	public Student login(Common common) {

		return studentDao.login(common);
	}

	@Override
	public int listCount() {
		return studentDao.listCount();
	}

	@Override
	public List<Student> selectAll(HashMap<String, Object> map) {
		return studentDao.selectAll(map);
	}

	@Override
	public String getDeptName(String deptno) {
		return studentDao.getDeptName(deptno);
	}

	@Override
	public String getTeacherName(String teacherno) {
		return studentDao.getTeacherName(teacherno);
	}

	@Override
	public int searchListCount(String keyword) {
		return studentDao.searchListCount(keyword);
	}

	@Override
	public List<Student> searchStudent(HashMap<String, Object> map) {
		return studentDao.searchStudent(map);
	}

	@Override
	public int insertStudent(Student student) {
		return studentDao.insertStudent(student);
	}

	@Override
	public Student selectOne(String no) {
		return studentDao.selectOne(no);
	}

	@Override
	public int updateStudent(Student student) {
		return studentDao.updateStudent(student);
	}

}
