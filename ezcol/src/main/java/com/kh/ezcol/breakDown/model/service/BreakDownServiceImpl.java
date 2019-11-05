package com.kh.ezcol.breakDown.model.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.ezcol.breakDown.model.dao.BreakDownDao;
import com.kh.ezcol.breakDown.model.vo.BreakDown;

@Service
public class BreakDownServiceImpl implements BreakDownService {

	
	@Autowired
	private BreakDownDao breakdownDao;

	@Override
	public List<BreakDown> selectAll(HashMap<String, String> map) {
		return breakdownDao.selectAll(map);
	}

	@Override
	public int cancelClass(HashMap<String, String> map) {
		return breakdownDao.cancelClass(map);
	}

	@Override
	public int countAll(String classno) {
		return breakdownDao.countAll(classno);
	}

	@Override
	public int classApply(HashMap<String, String> map) {
		return breakdownDao.classApply(map);
	}

	@Override
	public List<BreakDown> gradeView(HashMap<String, String> map) {
		return breakdownDao.gradeView(map);
	}

	@Override
	public List<BreakDown> gradePrint(String studentno) {
		return breakdownDao.gradePrint(studentno);
	}



	
}
