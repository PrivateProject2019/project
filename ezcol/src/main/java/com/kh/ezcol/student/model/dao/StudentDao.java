package com.kh.ezcol.student.model.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.ezcol.common.model.vo.Common;
import com.kh.ezcol.student.model.vo.Student;

@Repository
public class StudentDao {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public Student login(Common common) {
		
		Student student = sqlSession.selectOne("studentMapper.login", common);
		
		return student;
	}

	public int listCount() {
		return sqlSession.selectOne("studentMapper.listCount");
	}

	public List<Student> selectAll(HashMap<String, Object> map) {
		return sqlSession.selectList("studentMapper.selectAll",map);
	}

	public String getDeptName(String deptno) {
		return sqlSession.selectOne("studentMapper.getDeptName",deptno);
	}

	public String getTeacherName(String teacherno) {
		return sqlSession.selectOne("studentMapper.getTeacherName", teacherno);
	}

	public int searchListCount(String keyword) {
		return sqlSession.selectOne("studentMapper.searchListCount",keyword);
	}

	public List<Student> searchStudent(HashMap<String, Object> map) {
		return sqlSession.selectList("studentMapper.searchStudent",map);
	}

	public int insertStudent(Student student) {
		return sqlSession.insert("studentMapper.insertStudent", student);
	}

	public Student selectOne(String no) {
		return sqlSession.selectOne("studentMapper.selectOne",no);
	}

	public int updateStudent(Student student) {
		return sqlSession.update("studentMapper.updateStudent", student);
	}

}
