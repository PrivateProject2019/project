package com.kh.ezcol.employee.controller;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kh.ezcol.common.model.vo.Paging;
import com.kh.ezcol.employee.model.service.EmployeeService;
import com.kh.ezcol.employee.model.vo.Employee;

@Controller
public class EmployeeController {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private Paging paging;
	
	
	//직원 정보 작성 폼으로 이동 
	@RequestMapping("insertEmpForm.do")
	public String insertEmpForm() {
		
		return "employee/insertEmpForm";
	}

	// 직원 목록 출력
	@RequestMapping("empMain.do")
	public ModelAndView empMain(@RequestParam("currentPage") String currentPage, ModelAndView mv) {

		//페이징처리 
		int curPage = Integer.valueOf(currentPage);
		int listCount = employeeService.listCount();
		paging.makePage(listCount, curPage);
		

		HashMap<String, Object> map = new HashMap<String, Object>();

		map.put("startRow", paging.getStartRow());
		map.put("endRow", paging.getEndRow());

		logger.debug(paging.toString());

		List<Employee> list = employeeService.selectAll(map);

		mv.setViewName("employee/empMain");
		mv.addObject("paging", paging);
		mv.addObject("list", list);
		mv.addObject("type", "all"); //출력타입은 전체(all) 과 검색(search)로 나뉘어져있음 

		return mv;

	}

	// 직원 검색
	@RequestMapping(value = "searchEmp.do")
	public ModelAndView searchEmp(@RequestParam("keyword") String keyword, 
			@RequestParam("currentPage") String currentPage ,ModelAndView mv) {

		//페이징처리 
		int curPage = Integer.valueOf(currentPage);
		int listCount = employeeService.searchListCount(keyword);
		paging.makePage(listCount, curPage);
		
		HashMap<String, Object> map = new HashMap<String, Object>();

		map.put("startRow", paging.getStartRow());
		map.put("endRow", paging.getEndRow());
		map.put("keyword", keyword); //검색어 
		
		List<Employee> list = employeeService.searchEmp(map);
		
		
		logger.debug(keyword);
		logger.debug(paging.toString());


		mv.setViewName("employee/empMain");
		mv.addObject("paging", paging);
		mv.addObject("list", list);
		mv.addObject("type","search"); //출력타입은 전체(all) 과 검색(search)로 나뉘어져있음 
		mv.addObject("keyword",keyword);

		return mv;

	
	}


	//직원 추가 
	@RequestMapping(value="insertEmp.do", method=RequestMethod.POST)
	public ModelAndView insertEmp(Employee employee, ModelAndView mv) {
		
		int result = employeeService.insertEmp(employee);
		
		if(result > 0) {
			mv.addObject("currentPage","1");
			mv.setViewName("redirect:empMain.do");
		}else {
			mv.addObject("message", "직원등록 실패");
			mv.setViewName("common/errorPage");
		}
		
		return mv;
	}

	
	//직원 클릭시 상세정보 
	@RequestMapping("detailEmp.do")
	public ModelAndView detailEmp(String empno, ModelAndView mv) {
		
		Employee employee = employeeService.selectOne(empno);
		
		if(employee != null) {
			mv.addObject("employee", employee);
			mv.setViewName("employee/empDetail");
		}else {
			mv.addObject("message", "직원 정보 조회 실패");
			mv.setViewName("common/error");
		}
		
		return mv;
	}
	
	//직원 수정 페이지로 이동  
	@RequestMapping("updateEmpForm.do")
	public ModelAndView updateEmpForm(String empno, ModelAndView mv) {
		
		Employee employee = employeeService.selectOne(empno);
		
		
		if(employee != null) {
			mv.addObject("employee", employee);
			mv.setViewName("employee/updateEmpForm");
		}else {
			mv.addObject("message", "직원 정보 조회 실패");
			mv.setViewName("common/error");
		}
		
		return mv;
	}
	
	//직원 정보 수정 
	@RequestMapping(value="updateEmp.do", method=RequestMethod.POST)
	public ModelAndView updateEmp(Employee employee, ModelAndView mv) {
		
		logger.debug(employee.toString());
		
		int result = employeeService.updateEmp(employee);
		
		
		
		if(result > 0) {
			mv.addObject("empno", employee.getEmpno());
			mv.setViewName("redirect:detailEmp.do");
		}else {
			mv.addObject("message", "직원 정보 수정 실패");
			mv.setViewName("common/error");
		}
		
		
		return mv;
	}
	
	//직원 정보 삭제 
	@RequestMapping("deleteEmp.do")
	public ModelAndView deleteEmp(@RequestParam("empno") String empno, ModelAndView mv) {
		
		logger.debug(empno);
		
		int result = employeeService.deleteEmp(empno);

		if(result > 0) {
			mv.addObject("currentPage", "1");
			mv.setViewName("redirect:empMain.do");
		}else {
			mv.addObject("message", "직원 정보 삭제 실패");
			mv.setViewName("common/error");
		}
		
		return mv;
	}
	
	
}
