package com.kh.ezcol.classInfo.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kh.ezcol.classInfo.model.vo.ClassInfo;


public interface ClassInfoService {

	int listCount();

	List<ClassInfo> selectAll(HashMap<String, Object> map);

	int searchListCount(String keyword);

	List<ClassInfo> searchClass(HashMap<String, Object> map);

	int insertClass(ClassInfo classInfo);

	ClassInfo selectOne(String classno);

	int deleteFile(ClassInfo classInfo);

	int updateClass(ClassInfo classInfo);

	int deleteClass(String classno);

	List<ClassInfo> classApplyList(HashMap<String, Object> map);





}
