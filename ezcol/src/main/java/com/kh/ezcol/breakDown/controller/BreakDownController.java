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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kh.ezcol.breakDown.model.service.BreakDownService;
import com.kh.ezcol.breakDown.model.vo.BreakDown;
import com.kh.ezcol.classInfo.model.service.ClassInfoService;
import com.kh.ezcol.classInfo.model.vo.ClassInfo;
import com.kh.ezcol.student.model.service.StudentService;
import com.kh.ezcol.survey.model.service.SurveyService;

@Controller
public class BreakDownController {

	private static final Logger logger = LoggerFactory.getLogger(BreakDownController.class);

	@Autowired
	private BreakDownService breakDownService;

	@Autowired
	private ClassInfoService classInfoService;

	@Autowired
	private StudentService studentService;

	
	//학점 증명서 출력 
	@RequestMapping("gradePrint.do")
	public ModelAndView gradePrint(String teacherno, String studentno, String deptno, ModelAndView mv) {

		logger.debug("studnetno : " + studentno);
		
		//학생의 학과이름 
		String deptname = studentService.getDeptName(deptno);
		//학생의 지도교수 이름 
		String teachername= studentService.getTeacherName(teacherno);
		
		
		
		// 학생이 수강한 모든 명세서 가져옴
		List<BreakDown> breakdownList = breakDownService.gradePrint(studentno);

		
		
		// 명세서 정보를 수업정보로 변환함
		List<ClassInfo> classInfoList = new ArrayList<ClassInfo>();

		for (BreakDown breakDown : breakdownList) {

			ClassInfo classInfo = classInfoService.selectOne(breakDown.getClassno());
			
			//해당과목의 담당교수 이름 
			String teachername2 = studentService.getTeacherName(classInfo.getTeacherno());
			
			classInfo.setTeachername(teachername2);
			classInfo.setbScore(breakDown.getScore());
			
			classInfoList.add(classInfo);
			
		}
		
		logger.debug("size : " + classInfoList.size());
		
		mv.setViewName("grade/gradePrint");
		mv.addObject("list", classInfoList);
		mv.addObject("deptname",deptname);
		mv.addObject("teachername",teachername);
		
		

		return mv;
	}

	//수강 신청시 유효성 검사 Ajax 통신 
	@RequestMapping("breakDownValidate.do")
	public void breakDownValidate(String classno, String studentno, String deptno, HttpServletResponse response) {

		logger.debug("breakDownValidate run...");
		logger.debug("studentno : " + studentno);
		logger.debug("classno : " + classno);
		logger.debug("deptno : " + deptno);

		
		/*
		 현재 학기 구하기
		 현재 Month가 1,2,3,4,5,6월이면 1학기 
		 7,8,9,10,11,12월이면 2학기로 설정함 
		 */
		String now = new SimpleDateFormat("yyyyMM").format(new java.util.Date());
		String value = now.substring(4);
		String semester = "";
		if (value.equals("01") || value.equals("02") || value.equals("03") || value.equals("04") || value.equals("05")
				|| value.equals("06")) {
			semester = "1";
		} else {
			semester = "2";
		}


		response.setContentType("text/html; charset=UTF-8");

		HashMap<String, String> map = new HashMap<String, String>();

		map.put("studentno", studentno);
		map.put("semester", semester);

		// 로그인한 학생의 이번학기 이번년도 신청한 수업 명세서 리스트 가져옴
		List<BreakDown> list = breakDownService.selectAll(map);
		logger.debug("list Size : " + list.size());

		// 1. 신청되기전에 신청한 수업학점을 더해서 23학점이 넘지않는지 확인하기

		// 신청한 수업 정보
		ClassInfo classInfo = classInfoService.selectOne(classno);
		int score = classInfo.getScore();

		logger.debug("신청한 수업의 학점 : " + score);

		// 명세서의 학점 총합
		int addAll = 0;

		// 명세서로 수업정보 가져오기
		List<ClassInfo> classInfoList = new ArrayList<ClassInfo>();

		for (BreakDown breakDown : list) {
			ClassInfo classInfo2 = classInfoService.selectOne(breakDown.getClassno());

			classInfoList.add(classInfo2);

			//각 수업의 학점을 addAll에 더함 
			addAll += classInfo2.getScore();
		}

		// 2. 정원이 넘지않았는지 확인하기

		// 신청한 수업의 현재까지 인원수
		int count = breakDownService.countAll(classno);

		logger.debug("신청한 수업의 사용자를 제외한 현재 인원수 : " + count);
		logger.debug("명세서 학점의 총합 : " + addAll);

		// 3. 수업시간이 겹치지 않는지 확인하기
		// true이면 수업시간이 중복됨, false이면 중복되지않음 
		boolean result = false;
		
		//classInfo는 현재 신청한 수업정보, oldClass는 이미 신청된 수업 정보 
		
		/*       3      5    -> 이미 신청되어있는 수업의 시작교시 끝 교시 
		 * 
		 * 
		 * 아래에 해당하는 4가지 경우 수업시간이 겹치게 됨 
		밑의 숫자는 새로 신청하는 수업의 시작교시 끝 교시 
		
		i)   2       4 

		ii)          4      6 

		iii) 2              6 

		iiii)    3          6  
		 * 
		 */
		for (ClassInfo oldClass : classInfoList) {
			if (oldClass.getClassday().equals(classInfo.getClassday()) && 
					(
					
					// i) 		
					(Integer.parseInt(oldClass.getClassstart()) < Integer.parseInt(classInfo.getClassstart()) &&
					Integer.parseInt(classInfo.getClassstart()) < Integer.parseInt(oldClass.getClassend()))
					
					||
					
					// ii) 
					 (Integer.parseInt(oldClass.getClassstart()) < Integer.parseInt(classInfo.getClassend()) && 
					 Integer.parseInt(classInfo.getClassend()) < Integer.parseInt(oldClass.getClassend()))
					
					||
					// iii) and iiii) 
					 (Integer.parseInt(classInfo.getClassstart()) <= Integer.parseInt(oldClass.getClassstart()) &&
					  Integer.parseInt(classInfo.getClassend()) >= Integer.parseInt(oldClass.getClassend()))
							
							
							
						)
					
					) {
				result = true;
				
				break;
			}
		}

		String message = "";

		if (addAll + score >= 23) {
			message = "학기당 최대학점은 23학점 입니다.";
		} else if (count + 1 > classInfo.getAdmission()) {
			message = "해당 수업의 정원이 가득 찼습니다.";
		} else if (result == true) {
			message = "수업시간이 중복되므로 신청할 수 없습니다.";
		} else {
			message = "수강신청이 완료되었습니다.";
		}

		PrintWriter out = null;

		//ajax로 메시지 전송 
		try {
			out = response.getWriter();
			out.append(message);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

		out.close();
	}

	//시간표 가져오기
	@RequestMapping("timeTable.do")
	public ModelAndView classApplyList(String studentno, String deptno, ModelAndView mv) {

		/*
		 현재 학기 구하기
		 현재 Month가 1,2,3,4,5,6월이면 1학기 
		 7,8,9,10,11,12월이면 2학기로 설정함 
		 */
		String now = new SimpleDateFormat("yyyyMM").format(new java.util.Date());
		String value = now.substring(4);
		String semester = "";
		if (value.equals("01") || value.equals("02") || value.equals("03") || value.equals("04") || value.equals("05")
				|| value.equals("06")) {
			semester = "1";
		} else {
			semester = "2";
		}

		HashMap<String, String> map = new HashMap<String, String>();

		map.put("studentno", studentno);
		map.put("semester", semester);

		// 로그인한 학생의 이번학기 이번년도 신청한 수업 명세서 리스트 가져옴
		List<BreakDown> breakDownList = breakDownService.selectAll(map);
		logger.debug("list Size : " + breakDownList.size());

		// 명세서를 수업정보 리스트로 바꿈
		List<ClassInfo> list = new ArrayList<ClassInfo>();

		for (BreakDown breakDown : breakDownList) {
			ClassInfo classInfo = classInfoService.selectOne(breakDown.getClassno());
			String deptName = studentService.getDeptName(classInfo.getDeptno());
			String teacherName = studentService.getTeacherName(classInfo.getTeacherno());

			classInfo.setDeptname(deptName);
			classInfo.setTeachername(teacherName);
			
			list.add(classInfo);

		}

		logger.debug("현재학기 : " + semester + "학기");

		mv.setViewName("class/timeTable");
		mv.addObject("list", list);

		return mv;

	}

	//학생용 수강신청
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
			mv.setViewName("redirect:classApplyList.do"); //수강신청화면으로 돌아감 

		} else {
			mv.addObject("message", "수강신청 실패");
			mv.setViewName("common/errorPage");
		}

		return mv;
	}

	//학생용 수강 신청 취소
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
			mv.setViewName("redirect:classApplyList.do"); //수강신청화면으로 되돌아감

		} else {
			mv.addObject("message", "수강신청 실패");
			mv.setViewName("common/errorPage");
		}

		return mv;
	}

	

}
