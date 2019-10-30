package com.kh.ezcol.teacher.model.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.ezcol.common.model.vo.Common;
import com.kh.ezcol.employee.model.vo.Employee;
import com.kh.ezcol.teacher.model.vo.Teacher;

@Repository
public class TeacherDao {

	@Autowired
	private SqlSessionTemplate sqlSession;

	public Teacher login(Common common) {
		return sqlSession.selectOne("teacherMapper.login",common);
	}

	public int listCount() {
		return sqlSession.selectOne("teacherMapper.listCount");
	}

	public List<Teacher> selectAll(HashMap<String, Object> map) {
		return sqlSession.selectList("teacherMapper.selectAll",map);
	}

	public String deptNoToName(String deptno) {
		return sqlSession.selectOne("teacherMapper.deptNoToName",deptno);
	}

	public int searchListCount(String keyword) {
		return sqlSession.selectOne("teacherMapper.searchListCount",keyword);
	}

	public List<Teacher> searchTeacher(HashMap<String, Object> map) {
		return sqlSession.selectList("teacherMapper.searchTeacher", map);
	}

	public int insertTeacher(Teacher teacher) {
		return sqlSession.insert("teacherMapper.insertTeacher",teacher);
	}

	public Teacher selectOne(String no) {
		return sqlSession.selectOne("teacherMapper.selectOne", no);
	}

	public int updateTeacher(Teacher teacher) {
		return sqlSession.update("teacherMapper.updateTeacher", teacher);
	}

	public int deleteTeacher(String no) {
		return sqlSession.delete("teacherMapper.deleteTeacher", no);
	}

	public String getTeacherName(String teacherno) {
		return sqlSession.selectOne("teacherMapper.getTeacherName", teacherno);
	}
	
	
	
}
