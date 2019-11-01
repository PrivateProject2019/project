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

	public int cancelClass(HashMap<String, String> map) {
		return sqlSession.delete("breakDownMapper.classCancel",map);
	}

	public int countAll(String classno) {
		return sqlSession.selectOne("breakDownMapper.countAll",classno);
	}

	public int classApply(HashMap<String, String> map) {
		return sqlSession.insert("breakDownMapper.classApply",map);
	}

	
}
