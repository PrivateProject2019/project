package com.kh.ezcol.breakDown.model.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.ezcol.breakDown.model.vo.BreakDown;

@Repository
public class BreakDownDao {

	@Autowired
	private SqlSessionTemplate sqlSession;

	public List<BreakDown> selectAll(HashMap<String, String> map) {
		return sqlSession.selectList("breakDownMapper.selectAll",map);
	}

	
}
