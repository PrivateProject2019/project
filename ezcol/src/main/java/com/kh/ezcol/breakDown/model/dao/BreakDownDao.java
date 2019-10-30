package com.kh.ezcol.breakDown.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BreakDownDao {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
}
