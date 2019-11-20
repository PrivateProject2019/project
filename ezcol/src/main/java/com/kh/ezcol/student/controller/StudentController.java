package com.kh.ezcol.student.controller;

import java.util.HashMap;
import java.util.List;

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
import com.kh.ezcol.student.model.service.StudentService;
import com.kh.ezcol.student.model.vo.Student;
import com.kh.ezcol.teacher.model.vo.Teacher;

@Controller
public class StudentController {

	private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

	@Autowired
	private StudentService studentService;

	@Autowired
	private Paging paging;

	@RequestMapping("insertStudentForm.do")
	public String insertStudentForm() {

		return "student/studentInsertForm";
	}

	//직원용 학생 목록 출력
	@RequestMapping("studentMain.do")
	public ModelAndView empMain(@RequestParam("currentPage") String currentPage, ModelAndView mv) {

		//페이징
		int curPage = Integer.valueOf(currentPage);
		int listCount = studentService.listCount();
		paging.makePage(listCount, curPage);

		HashMap<String, Object> map = new HashMap<String, Object>();

		map.put("startRow", paging.getStartRow());
		map.put("endRow", paging.getEndRow());

		logger.debug(paging.toString());

		List<Student> list = studentService.selectAll(map);

		for (Student student : list) {

			String deptName = studentService.getDeptName(student.getDeptno());
			String teacherName = studentService.getTeacherName(student.getTeacherno());

			student.setDeptname(deptName);
			student.setTeachername(teacherName);
		}

		mv.setViewName("student/studentMain");
		mv.addObject("paging", paging);
		mv.addObject("list", list);
		mv.addObject("type", "all"); //출력타입은 전체(all) 과 검색(search)로 나뉘어져있음 

		return mv;

	}

	//직원용 학생 검색
	@RequestMapping(value = "searchStudent.do")
	public ModelAndView searchEmp(@RequestParam("keyword") String keyword,
			@RequestParam("currentPage") String currentPage) {
		 
		//status라는 필드명 때문에 HttpServelt의 필드명과 중복되는 문제때문에 매개변수가 아닌 객체생성함	
		ModelAndView mv = new ModelAndView();  

		//페이징처리 
		int curPage = Integer.valueOf(currentPage);
		int listCount = studentService.searchListCount(keyword);
		paging.makePage(listCount, curPage);
		HashMap<String, Object> map = new HashMap<String, Object>();

		map.put("startRow", paging.getStartRow());
		map.put("endRow", paging.getEndRow());
		map.put("keyword", keyword); //검색어 

		List<Student> list = studentService.searchStudent(map);

		for (Student student : list) {

			String deptName = studentService.getDeptName(student.getDeptno());
			String teacherName = studentService.getTeacherName(student.getTeacherno());

			student.setDeptname(deptName);
			student.setTeachername(teacherName);
		}

		logger.debug(keyword);
		logger.debug(paging.toString());

		mv.setViewName("student/studentMain");
		mv.addObject("paging", paging);
		mv.addObject("list", list);
		mv.addObject("type", "search"); //출력타입은 전체(all) 과 검색(search)로 나뉘어져있음 
		mv.addObject("keyword", keyword);

		return mv;

	}

	//직원용 학생 추가
	@RequestMapping(value = "insertStudent.do", method = RequestMethod.POST)
	public ModelAndView insertTeacher(Student student) {
 
		//status라는 필드명 때문에 HttpServelt의 필드명과 중복되는 문제때문에 매개변수가 아닌 객체생성함
		ModelAndView mv = new ModelAndView();

		logger.debug("학생 추가 : " + student.toString());

		int result = studentService.insertStudent(student);

		if (result > 0) {
			mv.addObject("currentPage", "1");
			mv.setViewName("redirect:studentMain.do");
		} else {
			mv.addObject("message", "학생 추가 에러");
			mv.setViewName("common/errorPage");
		}

		return mv;
	}

	//직원용 학생 상세 정보
	@RequestMapping("detailStudent.do")
	public ModelAndView detailTeacher(@RequestParam("studentno") String no) {

		//status라는 필드명 때문에 HttpServelt의 필드명과 중복되는 문제때문에 매개변수가 아닌 객체생성함
		ModelAndView mv = new ModelAndView();

		Student student = studentService.selectOne(no); //학번으로 DB에서 학생 정보 가져옴 

		

		if (student != null) {
			
			String deptName = studentService.getDeptName(student.getDeptno());
			String teacherName = studentService.getTeacherName(student.getTeacherno());

			student.setDeptname(deptName);
			student.setTeachername(teacherName);
			
			
			mv.addObject("student", student);
			mv.setViewName("student/studentDetail");
		} else {
			mv.addObject("message", "학생 정보 조회 실패");
			mv.setViewName("common/error");
		}

		return mv;
	}

	//직원용 학생 정보 수정 폼으로 이동
	@RequestMapping("updateStudentForm.do")
	public ModelAndView updateStudentForm(@RequestParam("studentno") String no) {

		//status라는 필드명 때문에 HttpServelt의 필드명과 중복되는 문제때문에 매개변수가 아닌 객체생성함
		ModelAndView mv = new ModelAndView();

		Student student = studentService.selectOne(no); //학번으로 DB에서 학생 정보 가져옴 

		if (student != null) {
			mv.addObject("student", student);
			mv.setViewName("student/studentUpdateForm");
		} else {
			mv.addObject("message", "학생 정보 조회 실패");
			mv.setViewName("common/error");
		}

		return mv;
	}

	//직원용 학생 정보 수정
	@RequestMapping(value = "updateStudent.do", method = RequestMethod.POST)
	public ModelAndView updateStudent(Student student) {

		//status라는 필드명 때문에 HttpServelt의 필드명과 중복되는 문제때문에 매개변수가 아닌 객체생성함
		 ModelAndView mv = new ModelAndView();
		
		logger.debug(student.toString());

		int result = studentService.updateStudent(student);

		if (result > 0) {
			mv.addObject("studentno", student.getStudentno());
			mv.setViewName("redirect:detailStudent.do");
		} else {
			mv.addObject("message", "학생 정보 수정 실패");
			mv.setViewName("common/error");
		}

		return mv;
	}

}
