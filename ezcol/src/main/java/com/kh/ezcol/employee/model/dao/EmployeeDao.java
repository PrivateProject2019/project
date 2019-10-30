package com.kh.ezcol.employee.model.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.ezcol.common.model.vo.Common;
import com.kh.ezcol.employee.model.vo.Employee;

@Repository
public class EmployeeDao {

	@Autowired
	SqlSessionTemplate sqlSession;

	public Employee login(Common common) {
		
		return sqlSession.selectOne("employeeMapper.login", common);
		
	}

	public int listCount() {
		return sqlSession.selectOne("employeeMapper.listCount");
	}

	public List<Employee> selectAll(HashMap<String, Object> map) {
		return sqlSession.selectList("employeeMapper.selectAll", map);
	}

	public List<Employee> searchEmp(HashMap<String, Object> map) {
		return sqlSession.selectList("employeeMapper.searchEmp", map);
	}

	public int serachListCount(String keyword) {
		return sqlSession.selectOne("employeeMapper.searchListCount", keyword);
	}

	public int insertEmp(Employee employee) {
		return sqlSession.insert("employeeMapper.insertEmp",employee);
	}

	public Employee selectOne(String empno) {
		return sqlSession.selectOne("employeeMapper.selectOne", empno);
	}

	public int updateEmp(Employee employee) {
		return sqlSession.update("employeeMapper.updateEmp", employee);
	}

	public int deleteEmp(String empno) {
		return sqlSession.delete("employeeMapper.deleteEmp",empno);
	}
	
	
	
}
