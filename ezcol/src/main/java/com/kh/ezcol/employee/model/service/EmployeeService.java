package com.kh.ezcol.employee.model.service;

import java.util.HashMap;
import java.util.List;

import com.kh.ezcol.common.model.vo.Common;
import com.kh.ezcol.employee.model.vo.Employee;

public interface EmployeeService {

	Employee login(Common common);

	int listCount();

	List<Employee> selectAll(HashMap<String, Object> map);

	List<Employee> searchEmp(HashMap<String, Object> map);

	int searchListCount(String keyword);

	int insertEmp(Employee employee);

	Employee selectOne(String empno);

	int updateEmp(Employee employee);

	int deleteEmp(String empno);

}
