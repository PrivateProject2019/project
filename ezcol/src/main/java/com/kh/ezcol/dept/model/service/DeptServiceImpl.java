package com.kh.ezcol.dept.model.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.ezcol.dept.model.dao.DeptDao;
import com.kh.ezcol.dept.model.vo.Dept;

@Service
public class DeptServiceImpl implements DeptService {

	@Autowired
	private DeptDao deptDao;

	@Override
	public int listCount() {
		return deptDao.listCount();
	}

	@Override
	public List<Dept> selectAll(HashMap<String, Object> map) {
		return deptDao.selectAll(map);
	}

	@Override
	public int searchListCount(String keyword) {
		return deptDao.searchListCount(keyword);
	}

	@Override
	public List<Dept> searchDept(HashMap<String, Object> map) {
		return deptDao.searchDept(map);
	}

	@Override
	public Dept selectOne(String deptno) {
		return deptDao.selectOne(deptno);
	}

	@Override
	public int insertDept(Dept dept) {
		return deptDao.insertDept(dept);
	}

	@Override
	public int updateDept(Dept dept) {
		return deptDao.updateDept(dept);
	}

	@Override
	public int deleteDept(String deptno) {
		return deptDao.deleteDept(deptno);
	}

	@Override
	public String getDeptName(String deptno) {
		return deptDao.getDeptName(deptno);
	}
	
	
	
	
}
