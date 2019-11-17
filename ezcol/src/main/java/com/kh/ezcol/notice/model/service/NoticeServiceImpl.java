package com.kh.ezcol.notice.model.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.ezcol.notice.model.dao.NoticeDao;
import com.kh.ezcol.notice.model.vo.Notice;

@Service
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	private NoticeDao noticeDao;

	@Override
	public int listCount() {
		return noticeDao.listCount();
	}

	@Override
	public List<Notice> selectAll(HashMap<String, Object> map) {
		return noticeDao.selectAll(map);
	}

	@Override
	public String getEmpName(String empno) {
		return noticeDao.getEmpName(empno);
	}

	@Override
	public int insertNotice(Notice notice) {
		return noticeDao.insertNotice(notice);
	}

	@Override
	public Notice selectOne(String noticeno) {
		return noticeDao.selectOne(noticeno);
	}

	@Override
	public int deleteFile(Notice notice) {
		return noticeDao.deleteFile(notice);
	}

	@Override
	public int updateNotice(Notice notice) {
		return noticeDao.udateNotice(notice);
	}

	@Override
	public int deleteNotice(String noticeno) {
		return noticeDao.deleteNotice(noticeno);
	}

	@Override
	public Notice classApplyGuide() {
		return noticeDao.classApplyGuide();
	}

	@Override
	public int listCountType(String type) {
		return noticeDao.listCountType(type);
	}

	@Override
	public List<Notice> noticeType(HashMap<String, Object> map) {
		return noticeDao.noticeType(map);
	}
	
}
