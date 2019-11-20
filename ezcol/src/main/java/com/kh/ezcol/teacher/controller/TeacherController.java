package com.kh.ezcol.teacher.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kh.ezcol.common.model.vo.Paging;
import com.kh.ezcol.employee.controller.EmployeeController;
import com.kh.ezcol.employee.model.vo.Employee;
import com.kh.ezcol.teacher.model.service.TeacherService;
import com.kh.ezcol.teacher.model.vo.Teacher;

@Controller
public class TeacherController {

	private static final Logger logger = LoggerFactory.getLogger(TeacherController.class);

	@Autowired
	private TeacherService teacherService;

	@Autowired
	private Paging paging;

	@RequestMapping("insertTeacherForm.do")
	public String insertTeacherForm() {

		return "teacher/teacherInsertForm";
	}

	//직원 교수 목록 출력
	@RequestMapping("teacherMain.do")

	public ModelAndView teahcerMain(@RequestParam("currentPage") String currentPage, ModelAndView mv) {

		//페이징처리 
		int curPage = Integer.valueOf(currentPage);
		int listCount = teacherService.listCount(); //DB에 존재하는 총 교수 Row수를 가져옴 
		paging.makePage(listCount, curPage);

		HashMap<String, Object> map = new HashMap<String, Object>();

		map.put("startRow", paging.getStartRow());
		map.put("endRow", paging.getEndRow());

		logger.debug(paging.toString());

		List<Teacher> list = teacherService.selectAll(map);

		mv.setViewName("teacher/teacherMain");
		mv.addObject("paging", paging);
		mv.addObject("list", list);
		mv.addObject("type", "all"); //출력타입은 전체(all) 과 검색(search)로 나뉘어져있음 

		return mv;

	}

	//직원용 교수 검색
	@RequestMapping(value = "searchTeacher.do")
	public ModelAndView searchEmp(@RequestParam("keyword") String keyword,
			@RequestParam("currentPage") String currentPage, ModelAndView mv) {

		//페이징처리 
		int curPage = Integer.valueOf(currentPage);
		int listCount = teacherService.searchListCount(keyword); //DB에 키워드로 검색한 총 교수 Row수를 가져옴
		paging.makePage(listCount, curPage);
		
		
		HashMap<String, Object> map = new HashMap<String, Object>();

		map.put("startRow", paging.getStartRow());
		map.put("endRow", paging.getEndRow());
		map.put("keyword", keyword);

		List<Teacher> list = teacherService.searchTeacher(map);

		if(list.size() != 0) {
		
		logger.debug(keyword);
		logger.debug(paging.toString());
		logger.debug("listSize : " + list.size());

		mv.setViewName("teacher/teacherMain");
		mv.addObject("paging", paging);
		mv.addObject("list", list);
		mv.addObject("type", "search");
		mv.addObject("keyword", keyword);
		
		}else {
			mv.setViewName("common/errorPage");
			mv.addObject("message","교수 정보 검색 실패");
		}

		return mv;

	}

	//직원용 교수 정보 추가
	@RequestMapping(value = "insertTeacher.do", method = RequestMethod.POST)
	public ModelAndView insertTeacher(Teacher teacher) {

		//status라는 필드명 때문에 HttpServelt의 필드명과 중복되는 문제때문에 매개변수가 아닌 객체생성함
		ModelAndView mv = new ModelAndView();

		logger.debug("교수 추가 : " + teacher.toString()); //커맨드객체 확인 

		int result = teacherService.insertTeacher(teacher);

		if (result > 0) {
			mv.addObject("currentPage", "1");
			mv.setViewName("redirect:teacherMain.do");
		} else {
			mv.addObject("message", "교수 추가 에러");
			mv.setViewName("common/errorPage");
		}

		return mv;
	}

	//직원용 교수 상세 정보
	@RequestMapping("detailTeacher.do")
	public ModelAndView detailTeacher(@RequestParam("teacherno") String no) {

		//status라는 필드명 때문에 HttpServelt의 필드명과 중복되는 문제때문에 매개변수가 아닌 객체생성함
		ModelAndView mv = new ModelAndView();

		Teacher teacher = teacherService.selectOne(no); //교수번호로 해당 교수정보를 DB에서 가져옴 

		if (teacher != null) {
			mv.addObject("teacher", teacher);
			mv.setViewName("teacher/teacherDetail");
		} else {
			mv.addObject("message", "교수 정보 조회 실패");
			mv.setViewName("common/error");
		}

		return mv;
	}

	//직원용 교수 정보 수정 페이지로 이동
	@RequestMapping("updateTeacherForm.do")
	public ModelAndView updateTeacherForm(@RequestParam("teacherno") String no) {
		
		//status라는 필드명 때문에 HttpServelt의 필드명과 중복되는 문제때문에 매개변수가 아닌 객체생성함
		ModelAndView mv = new ModelAndView();

		Teacher teacher = teacherService.selectOne(no);//교수번호로 해당 교수정보를 DB에서 가져옴 

		if (teacher != null) {
			mv.addObject("teacher", teacher);
			mv.setViewName("teacher/teacherUpdateForm");
		} else {
			mv.addObject("message", "교수 정보 조회 실패");
			mv.setViewName("common/error");
		}

		return mv;
	}
	
	//직원용 교수 정보 수정 
	@RequestMapping(value="updateTeacher.do", method=RequestMethod.POST)
	public ModelAndView updateTeacher(Teacher teacher) {
		
		logger.debug(teacher.toString()); 
		
		//status라는 필드명 때문에 HttpServelt의 필드명과 중복되는 문제때문에 매개변수가 아닌 객체생성함
		ModelAndView mv = new ModelAndView();
		
		int result = teacherService.updateTeacher(teacher);
		
		if(result > 0) {
			mv.addObject("teacherno",teacher.getTeacherno());
			mv.setViewName("redirect:detailTeacher.do");
			
		}else {
			mv.addObject("message", "교수 정보 수정 실패");
			mv.setViewName("common/error");
		}
		
		
		return mv;
		
	}
	
	//직원용 교수 정보 삭제 
	@RequestMapping("deleteTeacher.do")
	public ModelAndView deleteTeacher(@RequestParam("teacherno") String no) {
		
		ModelAndView mv = new ModelAndView();
		
		int result = teacherService.deleteTeacher(no);
		
		if(result > 0) {
			mv.addObject("currentPage","1");
			mv.setViewName("redirect:teacherMain.do");
			
		}else {
			mv.addObject("message", "교수 정보 삭제 실패");
			mv.setViewName("common/error");
		}
		
		
		return mv;
	}
	
	//직원용 교수 이름 불러오기 Ajax
	@RequestMapping("getTeacherName.do")
	public void getDeptName(String teacherno, HttpServletResponse response) {

		logger.debug("geTeacherName.do run...");
		logger.debug(teacherno);

		String teachername = teacherService.getTeacherName(teacherno);
		
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = null;
		
		try {
			out = response.getWriter();
			
			if (teachername != null) {
				out.append(teachername+" 교수");
				out.flush();
			} else {
				out.append("없는 코드 입니다.");
				out.flush();
			}
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		out.close();
	}
	

}
