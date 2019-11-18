package com.kh.ezcol.classInfo.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.kh.ezcol.breakDown.model.service.BreakDownService;
import com.kh.ezcol.breakDown.model.vo.BreakDown;
import com.kh.ezcol.classInfo.model.service.ClassInfoService;
import com.kh.ezcol.classInfo.model.vo.ClassInfo;
import com.kh.ezcol.common.model.vo.FileName;
import com.kh.ezcol.common.model.vo.Paging;
import com.kh.ezcol.employee.controller.EmployeeController;
import com.kh.ezcol.employee.model.vo.Employee;
import com.kh.ezcol.notice.model.vo.Notice;
import com.kh.ezcol.student.model.service.StudentService;
import com.kh.ezcol.student.model.vo.Student;

@Controller
public class ClassInfoController {

	private static final Logger logger = LoggerFactory.getLogger(ClassInfoController.class);

	@Autowired
	private ClassInfoService classInfoService;

	@Autowired
	private StudentService studentService;

	@Autowired
	private Paging paging;

	@Autowired
	private BreakDownService breakDownService;

	@RequestMapping("insertClassForm.do")
	public String insertClassForm() {

		return "class/classInsertForm";
	}

	// 수강신청 목록 출력
	@RequestMapping("classApplyList.do")
	public ModelAndView classApplyList(String studentno, String deptno,@RequestParam(value="keyword", required=false) String keyword ,ModelAndView mv) {

		
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


		HashMap<String, String> map1 = new HashMap<String, String>();

		map1.put("studentno", studentno);
		map1.put("semester", semester);
		
		
		

		// 로그인한 학생의 이번학기 이번년도 신청한 수업 명세서 리스트 가져옴
		List<BreakDown> breakDownList = breakDownService.selectAll(map1);
		logger.debug("list Size : " + breakDownList.size());

		// 명세서의 학점 총합
		int addAll = 0;

		for (BreakDown breakDown : breakDownList) {
			ClassInfo classInfo2 = classInfoService.selectOne(breakDown.getClassno());
			addAll += classInfo2.getScore();
		}

		// 화면하단에 출력될 로그인한 학생이 이미 수강신청한 수업 정보 리스트
		List<ClassInfo> list2 = new ArrayList<ClassInfo>();
		
		//명세서 정보를 수강정보로 변환함 
		for (BreakDown breakDown : breakDownList) {
			ClassInfo classInfo = classInfoService.selectOne(breakDown.getClassno());

			String deptName = studentService.getDeptName(classInfo.getDeptno());
			String teacherName = studentService.getTeacherName(classInfo.getTeacherno());

			classInfo.setDeptname(deptName);
			classInfo.setTeachername(teacherName);

			list2.add(classInfo);
		}

		logger.debug("명세서 학점의 총합 : " + addAll);

		HashMap<String, Object> map2 = new HashMap<String, Object>();

		map2.put("semester", semester);
		map2.put("deptno", deptno);

		if(keyword != null) {
			logger.debug("수강종류  : " + keyword);
			map2.put("keyword", keyword);
		}
		
		logger.debug("현재학기 : " + semester +"학기");

		
		
		
		// 화면 상단에 출력할 리스트 (사용자가 아직 수강신청하지않은 강의목록)
		List<ClassInfo> list = classInfoService.classApplyList(map2);
		logger.debug("deptno : " + deptno);
		logger.debug("상단 리스트 사이즈 : " + list.size());
	    

		for (ClassInfo classInfo : list) {
			String deptName = studentService.getDeptName(classInfo.getDeptno());
			String teacherName = studentService.getTeacherName(classInfo.getTeacherno());

			classInfo.setDeptname(deptName);
			classInfo.setTeachername(teacherName);
		}
		
		mv.setViewName("class/classApply");
		mv.addObject("list", list);
		mv.addObject("addAll", addAll);
		mv.addObject("list2", list2);

		return mv;

	}

	//직원용 현재 강의 목록 출력
	@RequestMapping("classMain.do")
	public ModelAndView classMain(@RequestParam("currentPage") String currentPage, ModelAndView mv) {

		//페이징처리 
		int curPage = Integer.valueOf(currentPage);
		int listCount = classInfoService.listCount(); //DB에서 존재하는 총 강의 Row 를 가져옴 
		paging.makePage(listCount, curPage);

		
		
		HashMap<String, Object> map = new HashMap<String, Object>();

		map.put("startRow", paging.getStartRow());
		map.put("endRow", paging.getEndRow());

		logger.debug(paging.toString());

		List<ClassInfo> list = classInfoService.selectAll(map);

		for (ClassInfo classInfo : list) {
			String deptName = studentService.getDeptName(classInfo.getDeptno());
			String teacherName = studentService.getTeacherName(classInfo.getTeacherno());

			classInfo.setDeptname(deptName);
			classInfo.setTeachername(teacherName);
		}

		mv.setViewName("class/classMain");
		mv.addObject("paging", paging);
		mv.addObject("list", list);
		mv.addObject("type", "all");

		return mv;

	}

	//직원용 강의 검색
	@RequestMapping(value = "searchClass.do")
	public ModelAndView searchEmp(@RequestParam("keyword") String keyword,
			@RequestParam("currentPage") String currentPage, ModelAndView mv) {

		//페이징처리
		int curPage = Integer.valueOf(currentPage);
		int listCount = classInfoService.searchListCount(keyword); //DB에서 검색 키워드로 분류한 강의 Row수 가져옴 
		paging.makePage(listCount, curPage);
		
		
		HashMap<String, Object> map = new HashMap<String, Object>();

		map.put("startRow", paging.getStartRow());
		map.put("endRow", paging.getEndRow());
		map.put("keyword", keyword);

		List<ClassInfo> list = classInfoService.searchClass(map);

		for (ClassInfo classInfo : list) {
			String deptName = studentService.getDeptName(classInfo.getDeptno());
			String teacherName = studentService.getTeacherName(classInfo.getTeacherno());

			classInfo.setDeptname(deptName);
			classInfo.setTeachername(teacherName);
		}

		logger.debug(keyword);
		logger.debug(paging.toString());

		mv.setViewName("class/classMain");
		mv.addObject("paging", paging);
		mv.addObject("list", list);
		mv.addObject("type", "search"); //view에서 전체출력(all)과 검색결과출력(search) 두가지가있음 
		mv.addObject("keyword", keyword); 

		return mv;

	}

	//직원용 새로운 강의추가 
	@RequestMapping(value = "insertClass.do", method = RequestMethod.POST)
	public ModelAndView insert(MultipartHttpServletRequest mtfRequest, HttpServletRequest request, ClassInfo classInfo,
			ModelAndView mv) {

		// input 태그의 이름으로 파일을 배열로 받아옴
		List<MultipartFile> fileList = mtfRequest.getFiles("upfile");

		// 파일 갯수 확인용
		int count = 0;

		// 파일이 저장될 위치를 지정
		String path = request.getSession().getServletContext().getRealPath("resources/cupfiles");

		// 파일명이 중복되지않게 현재시간을 불러옴
		String now = new SimpleDateFormat("yyyyMMddHmsS").format(new java.util.Date());

		// DB에 구분자로 합쳐서 저장할 파일이름
		String ofilename = "";
		String rfilename = "";

		for (MultipartFile multipartFile : fileList) {

			count++;

			// 원본 파일명을 불러온다
			String originalFileName = multipartFile.getOriginalFilename();
			String renameFileName = now + originalFileName;

			ofilename += originalFileName + ",";
			rfilename += renameFileName + ",";

			try {
				multipartFile.transferTo(new File(path + "\\" + now + multipartFile.getOriginalFilename()));
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		logger.debug("파일갯수 : " + count + ", 원본파일이름 : " + ofilename + ", 수정파일이름 : " + rfilename);

		classInfo.setOfilename(ofilename);
		classInfo.setRfilename(rfilename);

		logger.debug(classInfo.toString());

		int result = classInfoService.insertClass(classInfo);

		if (result > 0) {
			mv.addObject("currentPage", "1");
			mv.setViewName("redirect:classMain.do");
		} else {
			mv.addObject("message", "수업 등록 실패");
			mv.setViewName("common/errorPage");
		}

		return mv;
	}

	// 수업 클릭시 상세정보
	@RequestMapping("detailClass.do")
	public ModelAndView detailclassInfo(String classno, ModelAndView mv) {


		ClassInfo classInfo = classInfoService.selectOne(classno);

		
		if (classInfo!= null && classInfo.getOfilename() != null) {

			//view에 출력할 첨부파일 목록 
			List<FileName> fileList = new ArrayList<FileName>();

			//"," 구분자로 묶여있던 파일명을 분리함 
			String[] oarray = classInfo.getOfilename().split(",");
			String[] rarray = classInfo.getRfilename().split(",");

			for (int i = 0; i < oarray.length; i++) {
				FileName filename = new FileName();

				filename.setOfilename(oarray[i]);
				filename.setRfilename(rarray[i]);

				fileList.add(filename);
				
				
			}
			
			mv.addObject("fileList", fileList);

		}

		if (classInfo != null) {
			String deptName = studentService.getDeptName(classInfo.getDeptno());
			String teacherName = studentService.getTeacherName(classInfo.getTeacherno());
			classInfo.setDeptname(deptName);
			classInfo.setTeachername(teacherName);

			mv.addObject("classInfo", classInfo);
			mv.setViewName("class/classDetail");

		} else {
			mv.addObject("message", "수업 상세 조회 실패");
			mv.setViewName("common/errorPage");
		}

		return mv;
	}

	
	//수업정보에 첨부되어있는 파일 다운로드 
	@RequestMapping("cfdown")
	public ModelAndView boardFileDown(ModelAndView mv, @RequestParam("ofile") String originalFileName,
			@RequestParam("rfile") String renameFileName, HttpServletRequest request) {

		String path = request.getSession().getServletContext().getRealPath("resources/cupfiles");
		String downFilePath = path + "\\" + renameFileName;

		// 다운 받을 파일을 java.io.File 객체로 만듬

		File downFile = new File(downFilePath);

		// 다운로드 처리용 클래스로 보낼 값 저장 처리

		mv.addObject("downFile", downFile);
		mv.addObject("ofile", originalFileName);

		// 뷰파일명 지정시에 다운로드 처리할 클래스 이름을 지정함
		mv.setViewName("filedown");

		return mv;
	}

	//직원용 수업정보 수정 
	@RequestMapping("updateClassForm.do")
	public ModelAndView updateNoticeForm(String classno, ModelAndView mv) {

		ClassInfo classInfo = classInfoService.selectOne(classno);

		
		if (classInfo != null && classInfo.getOfilename() != null) {

			List<FileName> fileList = new ArrayList<FileName>();

			String[] oarray = classInfo.getOfilename().split(",");
			String[] rarray = classInfo.getRfilename().split(",");

			for (int i = 0; i < oarray.length; i++) {
				FileName filename = new FileName();

				filename.setOfilename(oarray[i]);
				filename.setRfilename(rarray[i]);

				fileList.add(filename);
				mv.addObject("fileList", fileList);
			}

		}

		if (classInfo != null) {

			mv.addObject("classInfo", classInfo);
			mv.setViewName("class/updateClassForm");

		} else {
			mv.addObject("message", "수업 상세 조회 실패");
			mv.setViewName("common/errorPage");
		}

		return mv;
	}

	//수업정보 첨부파일 삭제 
	@RequestMapping("deleteClassFile.do")
	public void deleteNoticeFile(String ofilename, String rfilename, String classno, HttpServletRequest request) {

		ClassInfo classInfo = classInfoService.selectOne(classno);

		logger.debug("삭제 전 : " + classInfo.toString());

		classInfo.setOfilename(classInfo.getOfilename().replace(ofilename, ""));
		classInfo.setRfilename(classInfo.getRfilename().replace(rfilename, ""));

		logger.debug("삭제 후 : " + classInfo.toString());

		int result = classInfoService.deleteFile(classInfo); //DB에 저장되어있는 원본파일명과 수정된 파일명을 업데이트한다.

		if (result > 0) {

			String path = request.getSession().getServletContext().getRealPath("resources/cupfiles");
			String deleteFilePath = path + "\\" + rfilename;

			logger.debug("삭제할 파일 이름 : " + ofilename + ", " + rfilename);

			File deleteFile = new File(deleteFilePath);

			if (deleteFile.exists()) {
				deleteFile.delete();
			}

		}

	}

	//직원용 수업정보 수정 
	@RequestMapping(value = "updateClass.do", method = RequestMethod.POST)
	public ModelAndView updateClass(MultipartHttpServletRequest mtfRequest, HttpServletRequest request,
			ClassInfo classInfo, ModelAndView mv) {

		// input 태그의 이름으로 파일을 배열로 받아옴
		List<MultipartFile> fileList = mtfRequest.getFiles("upfile");

		// 파일 갯수 확인용
		int count = 0;

		// 파일이 저장될 위치를 지정
		String path = request.getSession().getServletContext().getRealPath("resources/nupfiles");

		// 파일명이 중복되지않게 현재시간을 불러옴
		String now = new SimpleDateFormat("yyyyMMddHmsS").format(new java.util.Date());

		// DB에 구분자로 합쳐서 저장할 파일이름
		String ofilename = "";
		String rfilename = "";

		for (MultipartFile multipartFile : fileList) {

			if (multipartFile.getOriginalFilename() != "") {

				count++;

				// 원본 파일명을 불러온다
				String originalFileName = multipartFile.getOriginalFilename();
				String renameFileName = now + originalFileName;

				ofilename += originalFileName + ",";
				rfilename += renameFileName + ",";

				try {
					multipartFile.transferTo(new File(path + "\\" + now + multipartFile.getOriginalFilename()));
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			logger.debug("파일갯수 : " + count + ", 원본파일이름 : " + ofilename + ", 수정파일이름 : " + rfilename);

			classInfo.setOfilename(ofilename);
			classInfo.setRfilename(rfilename);

		}

		logger.debug(classInfo.toString());

		int result = classInfoService.updateClass(classInfo);

		if (result > 0) {
			mv.addObject("classno", classInfo.getClassno());
			mv.setViewName("redirect:detailClass.do");
		} else {
			mv.addObject("message", "수업 정보 수정 실패");
			mv.setViewName("common/errorPage");
		}

		return mv;
	}

	//직원용 수업정보 삭제 
	@RequestMapping("deleteClass.do")
	public ModelAndView deleteNotice(String classno, ModelAndView mv, HttpServletRequest request) {

		ClassInfo classInfo = classInfoService.selectOne(classno);

		String[] rarray = classInfo.getRfilename().split(",");

		//첨부된 파일은 전부 삭제함 
		for (String rfilename : rarray) {

			String path = request.getSession().getServletContext().getRealPath("resources/cupfiles");
			String deleteFilePath = path + "\\" + rfilename;

			logger.debug("삭제할 파일 이름 : " + rfilename);

			File deleteFile = new File(deleteFilePath);

			if (deleteFile.exists()) {
				deleteFile.delete();
			}
		}

		int result = classInfoService.deleteClass(classno);

		if (result > 0) {
			mv.addObject("currentPage", "1");
			mv.setViewName("redirect:classMain.do");
		} else {
			mv.addObject("message", "수업 삭제 실패");
			mv.setViewName("common/errorPage");
		}

		return mv;
	}

}
