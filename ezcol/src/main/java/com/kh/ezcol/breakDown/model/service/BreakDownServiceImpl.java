package com.kh.ezcol.breakDown.model.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.kh.ezcol.breakDown.model.dao.BreakDownDao;

public class BreakDownServiceImpl implements BreakDownService {

	
	@Autowired
	private BreakDownDao breakdownDao;
	
}
