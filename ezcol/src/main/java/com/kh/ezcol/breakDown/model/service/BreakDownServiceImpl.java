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

	
}
