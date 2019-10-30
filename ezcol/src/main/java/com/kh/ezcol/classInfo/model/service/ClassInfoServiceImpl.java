package com.kh.ezcol.classInfo.model.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.ezcol.classInfo.model.dao.ClassInfoDao;
import com.kh.ezcol.classInfo.model.vo.ClassInfo;

@Service
public class ClassInfoServiceImpl implements ClassInfoService {

	@Autowired
	private ClassInfoDao classInfoDao;

	@Override
	public int listCount() {
		return classInfoDao.listCount();
	}

	@Override
	public List<ClassInfo> selectAll(HashMap<String, Object> map) {
		return classInfoDao.selectAll(map);
	}

	@Override
	public int searchListCount(String keyword) {
		return classInfoDao.searchListCount(keyword);
	}

	@Override
	public List<ClassInfo> searchClass(HashMap<String, Object> map) {
		return classInfoDao.searchClass(map);
	}

	@Override
	public int insertClass(ClassInfo classInfo) {
		return classInfoDao.insertClass(classInfo);
	}

	@Override
	public ClassInfo selectOne(String classno) {
		return classInfoDao.selectOne(classno);
	}

	@Override
	public int deleteFile(ClassInfo classInfo) {
		return classInfoDao.deleteFile(classInfo);
	}

	@Override
	public int updateClass(ClassInfo classInfo) {
		return classInfoDao.updateClass(classInfo);
	}

	@Override
	public int deleteClass(String classno) {
		return classInfoDao.deleteClass(classno);
	}

	@Override
	public List<ClassInfo> classApplyList(HashMap<String, Object> map) {
		return classInfoDao. classApplyList(map);
	}

	
	
	

}
