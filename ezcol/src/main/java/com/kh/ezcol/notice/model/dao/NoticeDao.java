package com.kh.ezcol.notice.model.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.ezcol.notice.model.vo.Notice;

@Repository
public class NoticeDao {

	@Autowired
	private SqlSessionTemplate sqlSession;

	public int listCount() {
		return sqlSession.selectOne("noticeMapper.listCount");
	}

	public List<Notice> selectAll(HashMap<String, Object> map) {
		return sqlSession.selectList("noticeMapper.selectAll",map);
	}

	public String getEmpName(String empno) {
		return sqlSession.selectOne("noticeMapper.getEmpName", empno);
	}

	public int insertNotice(Notice notice) {
		return sqlSession.insert("noticeMapper.insertNotice", notice);
	}

	public Notice selectOne(String noticeno) {
		return sqlSession.selectOne("noticeMapper.selectOne", noticeno);
	}

	public int deleteFile(Notice notice) {
		return sqlSession.update("noticeMapper.deleteFile",notice);
	}

	public int udateNotice(Notice notice) {
		return sqlSession.update("noticeMapper.updateNotice",notice);
	}

	public int deleteNotice(String noticeno) {
		return sqlSession.delete("noticeMapper.deleteNotice", noticeno);
	}

	public Notice classApplyGuide() {
		return sqlSession.selectOne("noticeMapper.classApplyGuide");
	}


}
