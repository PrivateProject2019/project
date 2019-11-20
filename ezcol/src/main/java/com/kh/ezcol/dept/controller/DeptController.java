package com.kh.ezcol.dept.controller;

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
import com.kh.ezcol.dept.model.service.DeptService;
import com.kh.ezcol.dept.model.vo.Dept;
import com.kh.ezcol.employee.model.vo.Employee;

@Controller
public class DeptController {

	private static final Logger logger = LoggerFactory.getLogger(DeptController.class);

	@Autowired
	private Paging paging;

	@Autowired
	private DeptService deptService;

	//직원용 학과 입력 폼으로 이동
	@RequestMapping("insertDeptForm.do")
	public String insertDeptForm() {

		return "dept/deptInsertForm";
	}

	//직원용  학과 전체 목록 출력
	@RequestMapping("deptMain.do")
	public ModelAndView empMain(@RequestParam("currentPage") String currentPage, ModelAndView mv) {
		
		//페이징처리 
		int curPage = Integer.valueOf(currentPage);
		int listCount = deptService.listCount();
		paging.makePage(listCount, curPage);

		HashMap<String, Object> map = new HashMap<String, Object>();

		map.put("startRow", paging.getStartRow());
		map.put("endRow", paging.getEndRow());

		logger.debug(paging.toString());

		List<Dept> list = deptService.selectAll(map);

		mv.setViewName("dept/deptMain");
		mv.addObject("paging", paging);
		mv.addObject("list", list);
		mv.addObject("type", "all"); //출력타입은 전체(all) 과 검색(search)로 나뉘어져있음 

		return mv;

	}

	//직원용 학과 검색
	@RequestMapping(value = "searchDept.do")
	public ModelAndView searchEmp(@RequestParam("keyword") String keyword,
			@RequestParam("currentPage") String currentPage, ModelAndView mv) {

		//페이징 처리 
		int curPage = Integer.valueOf(currentPage);
		int listCount = deptService.searchListCount(keyword);

		paging.makePage(listCount, curPage);
		HashMap<String, Object> map = new HashMap<String, Object>();

		map.put("startRow", paging.getStartRow());
		map.put("endRow", paging.getEndRow());
		map.put("keyword", keyword);

		List<Dept> list = deptService.searchDept(map);

		logger.debug(keyword);
		logger.debug(paging.toString());

		mv.setViewName("dept/deptMain");
		mv.addObject("paging", paging);
		mv.addObject("list", list); 
		mv.addObject("type", "search"); //출력타입은 전체(all) 과 분류(sort)로 나뉘어져있음 
		mv.addObject("keyword", keyword); 

		return mv;

	}

	//직원용 학과 추가
	@RequestMapping(value = "insertDept.do", method = RequestMethod.POST)
	public ModelAndView insertEmp(Dept dept, ModelAndView mv) {

		logger.debug("학과 추가 : " + dept.toString());

		int result = deptService.insertDept(dept);

		if (result > 0) {
			mv.addObject("currentPage", "1");
			mv.setViewName("redirect:deptMain.do");
		} else {
			mv.addObject("message", "학과등록 실패");
			mv.setViewName("common/errorPage");
		}

		return mv;
	}

	//직원용 학과 상세 정보
	@RequestMapping("detailDept.do")
	public ModelAndView detailEmp(String deptno, ModelAndView mv) {

		Dept dept = deptService.selectOne(deptno);

		if (dept != null) {
			mv.addObject("dept", dept);
			mv.setViewName("dept/deptDetail");
		} else {
			mv.addObject("message", "학과 정보 조회 실패");
			mv.setViewName("common/error");
		}

		return mv;
	}

	//직원용 학과 수정 페이지로 이동
	@RequestMapping("updateDeptForm.do")
	public ModelAndView updateEmpForm(String deptno, ModelAndView mv) {

		Dept dept = deptService.selectOne(deptno);

		if (dept != null) {
			mv.addObject("dept", dept);
			mv.setViewName("dept/deptUpdateForm");
		} else {
			mv.addObject("message", "학과 정보 조회 실패");
			mv.setViewName("common/error");
		}

		return mv;
	}

	//직원 정보 수정
	@RequestMapping(value = "updateDept.do", method = RequestMethod.POST)
	public ModelAndView updateEmp(Dept dept, ModelAndView mv) {

		logger.debug(dept.toString());

		int result = deptService.updateDept(dept);

		if (result > 0) {
			mv.addObject("deptno", dept.getDeptno());
			mv.setViewName("redirect:detailDept.do");
		} else {
			mv.addObject("message", "학과 정보 수정 실패");
			mv.setViewName("common/error");
		}

		return mv;
	}

	// 직원 정보 삭제
	@RequestMapping("deleteDept.do")
	public ModelAndView deleteEmp(@RequestParam("deptno") String deptno, ModelAndView mv) {

		logger.debug(deptno);

		int result = deptService.deleteDept(deptno);

		if (result > 0) {
			mv.addObject("currentPage", "1");
			mv.setViewName("redirect:deptMain.do");
		} else {
			mv.addObject("message", "학과 정보 삭제 실패");
			mv.setViewName("common/error");
		}

		return mv;
	}
	
	//학과코드를 이용해, DB에서 학과이름 가져오기 Ajax 출력 
	@RequestMapping("getDeptName.do")
	public void getDeptName(String deptno, HttpServletResponse response) {

		logger.debug("getDeptName.do run...");
		logger.debug(deptno);

		response.setContentType("text/html; charset=UTF-8");

		String deptname = deptService.getDeptName(deptno);
		
		PrintWriter out = null;
		
		try {
			out = response.getWriter();
			
			if (deptname != null) {
				out.append(deptname);
				out.flush();
			} else {
				out.append("없는 코드 입니다."); //존재하지않는 학과코드일 경우 
				out.flush();
			}
			
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		

		out.close();
	}

}
