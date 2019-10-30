package com.kh.ezcol.dept.model.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.ezcol.dept.model.vo.Dept;

@Repository
public class DeptDao {

	
	@Autowired
	private SqlSessionTemplate sqlSession;

	public int listCount() {
		return sqlSession.selectOne("deptMapper.listCount");
	}

	public List<Dept> selectAll(HashMap<String, Object> map) {
		return sqlSession.selectList("deptMapper.selectAll",map);
	}

	public int searchListCount(String keyword) {
		return sqlSession.selectOne("deptMapper.searchListCount",keyword);
	}

	public List<Dept> searchDept(HashMap<String, Object> map) {
		return sqlSession.selectList("deptMapper.searchDept", map);
	}

	public Dept selectOne(String deptno) {
		return sqlSession.selectOne("deptMapper.selectOne",deptno);
	}

	public int insertDept(Dept dept) {
		return sqlSession.insert("deptMapper.insertDept", dept);
	}

	public int updateDept(Dept dept) {
		return sqlSession.update("deptMapper.updateDept",dept);
	}

	public int deleteDept(String deptno) {
		return sqlSession.delete("deptMapper.deleteDept", deptno);
	}

	public String getDeptName(String deptno) {
		return sqlSession.selectOne("deptMapper.getDeptName",deptno);
	}

	
	
}

