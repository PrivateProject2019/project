package com.kh.ezcol.breakDown.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kh.ezcol.breakDown.model.service.BreakDownService;
import com.kh.ezcol.breakDown.model.vo.BreakDown;
import com.kh.ezcol.classInfo.model.service.ClassInfoService;
import com.kh.ezcol.classInfo.model.vo.ClassInfo;

@Controller
public class BreakDownController {

	private static final Logger logger = LoggerFactory.getLogger(BreakDownController.class);

	@Autowired
	private BreakDownService breakDownService;

	@Autowired
	private ClassInfoService classInfoService;

	// 수강 신청시 유효성 검사
	@RequestMapping("breakDownValidate.do")
	public void breakDownValidate(String classno, String studentno, String deptno, HttpServletResponse response) {

		logger.info("breakDownValidate run...");
		logger.info("studentno : " + studentno);
		logger.info("classno : " + classno);
		logger.info("deptno : " + deptno);

		String now = new SimpleDateFormat("yyyyMM").format(new java.util.Date());

		String value = now.substring(4);

		String semester = "";

		if (value.equals("01") || value.equals("02") || value.equals("03") || value.equals("04") || value.equals("05")
				|| value.equals("06")) {
			semester = "1";
		} else {
			semester = "2";
		}

		logger.info(semester);

		response.setContentType("text/html; charset=UTF-8");

		HashMap<String, String> map = new HashMap<String, String>();

		map.put("studentno", studentno);
		map.put("semester", semester);
		map.put("deptno", deptno);

		// 로그인한 학생의 이번학기 이번년도 신청한 수업 명세서 리스트 가져옴
		List<BreakDown> list = breakDownService.selectAll(map);
		logger.info("list Size : " + list.size());

		// 1. 신청되기전에 신청한 수업학점을 더해서 23학점이 넘지않는지 확인하기

		// 신청한 수업 정보
		ClassInfo classInfo = classInfoService.selectOne(classno);
		int score = classInfo.getScore();

		logger.info("신청한 수업의 학점 : " + score);

		// 명세서의 학점 총합

		int addAll = 0;

		// 명세서로 수업정보 가져오기
		List<ClassInfo> classInfoList = new ArrayList<ClassInfo>();

		for (BreakDown breakDown : list) {
			ClassInfo classInfo2 = classInfoService.selectOne(breakDown.getClassno());

			classInfoList.add(classInfo2);
			
			addAll += classInfo2.getScore();
		}

		// 2. 정원이 넘지않았는지 확인하기

		// 신청한 수업의 현재까지 인원수
		int count = breakDownService.countAll(classno);

		logger.info("신청한 수업의 나를 제외한 현재 인원수 : " + count);
		logger.info("명세서 학점의 총합 : " + addAll);

		// 3. 수업시간이 겹치지 않는지 확인하기
		
		boolean result = false;
		
		for (ClassInfo classInfo2 : classInfoList) {
			if(classInfo2.getClassday().equals(classInfo.getClassday()) &&
					(Integer.parseInt(classInfo2.getClassstart()) >= Integer.parseInt(classInfo.getClassstart())
					|| Integer.parseInt(classInfo2.getClassend()) >= Integer.parseInt(classInfo.getClassend()) )){
				result = true;
			}
		}
		

		String message = "";

		if (addAll + score >= 23) {
			message = "학기당 최대학점은 23학점 입니다.";
		} else if (count + 1 > classInfo.getAdmission()) {
			message = "해당 수업의 정원이 가득 찼습니다.";
		} else if(result == true) {
			message = "수업시간이 중복되므로 신청할 수 없습니다.";
		}else {
			message = "수강신청이 완료되었습니다.";
		}
			
		

		PrintWriter out = null;

		try {
			out = response.getWriter();
			out.append(message);
			out.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}

		out.close();
	}

	
	
	
	
	// 수강신청
	@RequestMapping("classApply.do")
	public ModelAndView classApply(String classno, String studentno, String deptno, ModelAndView mv) {

		logger.info("classApply run...");
		logger.info("studentno : " + studentno);
		logger.info("classno : " + classno);
		logger.info("deptno : " + deptno);

		HashMap<String, String> map = new HashMap<String, String>();

		map.put("studentno", studentno);
		map.put("classno", classno);

		int result = breakDownService.classApply(map);

		if (result > 0) {

			mv.addObject("classno", classno);
			mv.addObject("studentno", studentno);
			mv.addObject("deptno", deptno);
			mv.setViewName("redirect:classApplyList.do");

		} else {
			mv.addObject("message", "수강신청 취소 실패");
			mv.setViewName("common/errorPage");
		}

		return mv;
	}

	// 수강 신청 취소
	@RequestMapping("classCancel.do")
	public ModelAndView classCancel(String classno, String studentno, String deptno, ModelAndView mv) {

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("classno", classno);
		map.put("studentno", studentno);

		int result = breakDownService.cancelClass(map);

		if (result > 0) {

			mv.addObject("classno", classno);
			mv.addObject("studentno", studentno);
			mv.addObject("deptno", deptno);
			mv.setViewName("redirect:classApplyList.do");

		} else {
			mv.addObject("message", "수강신청 실패");
			mv.setViewName("common/errorPage");
		}

		return mv;
	}
}
