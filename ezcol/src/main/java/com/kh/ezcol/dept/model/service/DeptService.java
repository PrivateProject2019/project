package com.kh.ezcol.dept.model.service;

import java.util.HashMap;
import java.util.List;

import com.kh.ezcol.dept.model.vo.Dept;



public interface DeptService {

	int listCount();

	List<Dept> selectAll(HashMap<String, Object> map);

	int searchListCount(String keyword);

	List<Dept> searchDept(HashMap<String, Object> map);

	Dept selectOne(String deptno);

	int insertDept(Dept dept);

	int updateDept(Dept dept);

	int deleteDept(String deptno);

	String getDeptName(String deptno);

}
