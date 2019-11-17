package com.kh.ezcol.notice.model.service;

import java.util.HashMap;
import java.util.List;

import com.kh.ezcol.notice.model.vo.Notice;

public interface NoticeService {

	int listCount();

	List<Notice> selectAll(HashMap<String, Object> map);

	String getEmpName(String empno);

	int insertNotice(Notice notice);

	Notice selectOne(String noticeno);

	int deleteFile(Notice notice);

	int updateNotice(Notice notice);

	int deleteNotice(String noticeno);

	Notice classApplyGuide();

	int listCountType(String type);

	List<Notice> noticeType(HashMap<String, Object> map);

}
