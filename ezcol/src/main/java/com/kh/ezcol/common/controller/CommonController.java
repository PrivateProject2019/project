package com.kh.ezcol.common.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kh.ezcol.article.model.service.ArticleService;
import com.kh.ezcol.article.model.vo.Article;
import com.kh.ezcol.common.model.vo.Common;
import com.kh.ezcol.employee.model.service.EmployeeService;
import com.kh.ezcol.employee.model.vo.Employee;
import com.kh.ezcol.student.model.service.StudentService;
import com.kh.ezcol.student.model.vo.Student;
import com.kh.ezcol.teacher.model.service.TeacherService;
import com.kh.ezcol.teacher.model.vo.Teacher;

@Controller
public class CommonController {

	private static final Logger logger = LoggerFactory.getLogger(CommonController.class);

	@Autowired
	private StudentService studentService;
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private TeacherService teacherService;
	
	@Autowired
	private ArticleService articleService;

	
	
	//홈페이지로 이동 
	@RequestMapping("home.do")
	public ModelAndView main(ModelAndView mv) {

		
		
		
		List<Article> list = articleService.homeArticle();
		
		if(list != null) {
			
			mv.addObject("article1", list.get(0));
			mv.addObject("article2", list.get(1));
			mv.addObject("article3", list.get(2));
		
		}
		
		logger.info("size : " + list.size());
		
		
		mv.setViewName("home");
		
		
		return mv;
	}

	@RequestMapping("loginForm.do")
	public ModelAndView loginForm(@RequestParam(value="message", required=false) String message,ModelAndView mv) {

		if(message != null) {
			mv.addObject("message", message);
		}
		
		
		
		mv.setViewName("login");
		
		return mv;
	}
	
	

	// 로그인 컨트롤러
	@RequestMapping(value = "login.do", method = RequestMethod.POST)
	public ModelAndView login(Common common, HttpSession session,ModelAndView mv) {

		
		logger.info("login.do run...");

		// 학생 로그인일때
		if (common.getType().equals("student")) {

			logger.info("login : " + common.toString());
			Student student = studentService.login(common);
			
			

			if (student != null) {
				student.setDeptname(studentService.getDeptName(student.getDeptno()));
				session.setAttribute("loginMember", student);

				mv.setViewName("redirect:home.do");

			}else {
				mv.addObject("message", "아이디, 비밀번호를 확인해주세요");
				mv.setViewName("login");
			} 

			//직원 로그인일때
		}else if(common.getType().equals("employee")) {
			logger.info("login : " + common.toString());
			Employee employee = employeeService.login(common);

			if (employee != null) {

				session.setAttribute("loginMember", employee);

				mv.setViewName("redirect:home.do");

			}else {
				mv.addObject("message", "아이디, 비밀번호를 확인해주세요");
				mv.setViewName("login");
			} 
			
			//교수일때
		}else if(common.getType().equals("teacher")) {
			logger.info("login : " + common.toString());
			Teacher teacher = teacherService.login(common);
			teacher.setDeptname(studentService.getDeptName(teacher.getDeptno()));

			if (teacher != null) {

				session.setAttribute("loginMember", teacher);

				mv.setViewName("redirect:home.do");
				
				
			}else {
				mv.addObject("message", "아이디, 비밀번호를 확인해주세요");
				mv.setViewName("login");
			} 
		}

		return mv;

	}

	// 로그아웃 컨트롤러
	@RequestMapping("logout.do")
	public ModelAndView logout(HttpSession session, HttpServletRequest request, ModelAndView mv) {

		session = request.getSession(false);

		if (session != null) {
			session.invalidate();
		}
		
		mv.setViewName("redirect:home.do");
		
		return mv;

	}

}
