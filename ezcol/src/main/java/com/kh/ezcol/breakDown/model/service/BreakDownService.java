package com.kh.ezcol.breakDown.model.service;

import java.util.HashMap;
import java.util.List;

import com.kh.ezcol.breakDown.model.vo.BreakDown;

public interface BreakDownService {

	List<BreakDown> selectAll(HashMap<String, String> map);

	int cancelClass(HashMap<String, String> map);

	int countAll(String classno);

	int classApply(HashMap<String, String> map);

	List<BreakDown> gradeView(HashMap<String, String> map);

	List<BreakDown> gradePrint(String studentno);

	

}
