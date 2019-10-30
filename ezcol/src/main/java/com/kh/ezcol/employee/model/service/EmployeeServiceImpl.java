package com.kh.ezcol.employee.model.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.ezcol.common.model.vo.Common;
import com.kh.ezcol.employee.model.dao.EmployeeDao;
import com.kh.ezcol.employee.model.vo.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;

	@Override
	public Employee login(Common common) {
		return employeeDao.login(common);
	}

	@Override
	public int listCount() {
		return employeeDao.listCount();
	}

	@Override
	public List<Employee> selectAll(HashMap<String, Object> map) {
		return employeeDao.selectAll(map);
	}

	@Override
	public List<Employee> searchEmp(HashMap<String, Object> map) {
		return employeeDao.searchEmp(map);
	}

	@Override
	public int searchListCount(String keyword) {
		return employeeDao.serachListCount(keyword);
	}

	@Override
	public int insertEmp(Employee employee) {
		return employeeDao.insertEmp(employee);
	}

	@Override
	public Employee selectOne(String empno) {
		return employeeDao.selectOne(empno);
	}

	@Override
	public int updateEmp(Employee employee) {
		return employeeDao.updateEmp(employee);
	}

	@Override
	public int deleteEmp(String empno) {
		return employeeDao.deleteEmp(empno);
	}
	
	
}
