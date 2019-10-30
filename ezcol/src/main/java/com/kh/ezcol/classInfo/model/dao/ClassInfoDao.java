package com.kh.ezcol.classInfo.model.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.ezcol.classInfo.model.vo.ClassInfo;

@Repository
public class ClassInfoDao {

	@Autowired
	private SqlSessionTemplate sqlSession;

	public int listCount() {
		return sqlSession.selectOne("classMapper.listCount");
	}

	public List<ClassInfo> selectAll(HashMap<String, Object> map) {
		return sqlSession.selectList("classMapper.selectAll",map);
	}

	public int searchListCount(String keyword) {
		return sqlSession.selectOne("classMapper.searchListCount",keyword);
	}

	public List<ClassInfo> searchClass(HashMap<String, Object> map) {
		return sqlSession.selectList("classMapper.searchClass",map);
	}

	public int insertClass(ClassInfo classInfo) {
		return sqlSession.insert("classMapper.insertClass",classInfo);
	}

	public ClassInfo selectOne(String classno) {
		return sqlSession.selectOne("classMapper.selectOne",classno);
	}

	public int deleteFile(ClassInfo classInfo) {
		return sqlSession.update("classMapper.deleteFile",classInfo);
	}

	public int updateClass(ClassInfo classInfo) {
		return sqlSession.update("classMapper.updateClass",classInfo);
	}

	public int deleteClass(String classno) {
		return sqlSession.delete("classMapper.deleteClass",classno);
	}

	public List<ClassInfo> classApplyList(HashMap<String, Object> map) {
		return sqlSession.selectList("classMapper. classApplyList", map);
	}

	
	
	
}
