package com.kh.ezcol.notice.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
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

import com.kh.ezcol.absence.model.vo.Absence;
import com.kh.ezcol.common.model.vo.FileName;
import com.kh.ezcol.common.model.vo.Paging;
import com.kh.ezcol.employee.controller.EmployeeController;
import com.kh.ezcol.employee.model.service.EmployeeService;
import com.kh.ezcol.employee.model.vo.Employee;
import com.kh.ezcol.notice.model.service.NoticeService;
import com.kh.ezcol.notice.model.vo.Notice;

@Controller
public class NoticeController {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	private NoticeService noticeService;

	@Autowired
	private Paging paging;

	// 공지사항 작성 폼
	@RequestMapping("insertNoticeForm.do")
	public String insertNoticeForm() {

		return "notice/insertNoticeForm";
	}

	//수강신청시 수강신청안내
	@RequestMapping("classApplyGuide.do")
	public ModelAndView classApplyGuide(ModelAndView mv) {

		Notice notice = noticeService.classApplyGuide();

		if (notice != null &&notice.getOfilename() != null) {

			List<FileName> fileList = new ArrayList<FileName>();

			String[] oarray = notice.getOfilename().split(",");
			String[] rarray = notice.getRfilename().split(",");

			for (int i = 0; i < oarray.length; i++) {
				FileName filename = new FileName();

				filename.setOfilename(oarray[i]);
				filename.setRfilename(rarray[i]);

				fileList.add(filename);
				mv.addObject("fileList", fileList);
			}

		}

		if (notice != null) {
			String empname = noticeService.getEmpName(notice.getEmpno()); //작성자 이름 가져오기 
			notice.setEmpname(empname);
			mv.addObject("notice", notice);
			mv.setViewName("notice/noticeDetail");

		} else {
			mv.addObject("message", "공지사항 상세 조회 실패");
			mv.setViewName("common/errorPage");
		}

		return mv;
	}

	// 공지사항 타입으로 분류
	@RequestMapping("noticeType.do")
	public ModelAndView absenceType(String currentPage, String type, ModelAndView mv) {

		//페이징 처리 
		int curPage = Integer.parseInt(currentPage);
		int listCount = noticeService.listCountType(type);
		Paging paging = new Paging();

		paging.makePage(listCount, curPage);

		logger.debug(paging.toString());

		HashMap<String, Object> map = new HashMap<String, Object>();

		map.put("type", type); //공지사항 타입 
		map.put("startRow", paging.getStartRow());
		map.put("endRow", paging.getEndRow());

		List<Notice> list = noticeService.noticeType(map);

		logger.debug("list Size : " + list.size());

		mv.setViewName("notice/noticeMain");
		mv.addObject("list", list);
		mv.addObject("kind", "sort"); //출력타입은 전체(all) 과 분류(sort)로 나뉘어져있음 
		mv.addObject("type", type); 
		mv.addObject("paging", paging);

		return mv;
	}

	// 공지사항 목록 출력
	@RequestMapping("noticeMain.do")
	public ModelAndView empMain(@RequestParam("currentPage") String currentPage, ModelAndView mv) {

		//페이징처리 
		int curPage = Integer.valueOf(currentPage);
		int listCount = noticeService.listCount();
		paging.makePage(listCount, curPage);

		HashMap<String, Object> map = new HashMap<String, Object>();

		map.put("startRow", paging.getStartRow());
		map.put("endRow", paging.getEndRow());

		logger.debug(paging.toString());

		List<Notice> list = noticeService.selectAll(map);

		for (Notice notice : list) {
			String empname = noticeService.getEmpName(notice.getEmpno());
			notice.setEmpname(empname);

		}

		mv.setViewName("notice/noticeMain");
		mv.addObject("paging", paging);
		mv.addObject("list", list);
		mv.addObject("kind", "all");  //출력타입은 전체(all) 과 분류(sort)로 나뉘어져있음 

		return mv;

	}

	//직원용 공지사항 추가
	@RequestMapping(value = "insertNotice.do", method = RequestMethod.POST)
	public ModelAndView insertNotice(MultipartHttpServletRequest mtfRequest, HttpServletRequest request, Notice notice,
			ModelAndView mv) {

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

			count++;

			// 원본 파일명을 불러온다
			String originalFileName = multipartFile.getOriginalFilename();
			String renameFileName = now + originalFileName;

			//","를 구분자로하여 파일명을 전부 합침 
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

		logger.info("파일갯수 : " + count + ", 원본파일이름 : " + ofilename + ", 수정파일이름 : " + rfilename);

		notice.setOfilename(ofilename);
		notice.setRfilename(rfilename);

		logger.info(notice.toString());

		int result = noticeService.insertNotice(notice);

		if (result > 0) {
			mv.addObject("currentPage", "1");
			mv.setViewName("redirect:noticeMain.do");
		} else {
			mv.addObject("message", "공지사항 등록 실패");
			mv.setViewName("common/errorPage");
		}

		return mv;
	}

	//직원용 공지사항 수정
	@RequestMapping("updateNoticeForm.do")
	public ModelAndView updateNoticeForm(String noticeno, ModelAndView mv) {

		Notice notice = noticeService.selectOne(noticeno);

		if (notice != null && notice.getOfilename() != null) {

			List<FileName> fileList = new ArrayList<FileName>();

			String[] oarray = notice.getOfilename().split(",");
			String[] rarray = notice.getRfilename().split(",");

			for (int i = 0; i < oarray.length; i++) {
				FileName filename = new FileName();

				filename.setOfilename(oarray[i]);
				filename.setRfilename(rarray[i]);

				fileList.add(filename);
				mv.addObject("fileList", fileList);
			}

		}

		if (notice != null) {
			String empname = noticeService.getEmpName(notice.getEmpno());
			notice.setEmpname(empname);

			mv.addObject("notice", notice);
			mv.setViewName("notice/updateNoticeForm");

		} else {
			mv.addObject("message", "공지사항 상세 조회 실패");
			mv.setViewName("common/errorPage");
		}

		return mv;
	}

	// 공지사항 클릭시 상세정보
	@RequestMapping("detailNotice.do")
	public ModelAndView detailNotice(String noticeno, ModelAndView mv) {

		Notice notice = noticeService.selectOne(noticeno);

		if (notice != null && notice.getOfilename() != null) {

			List<FileName> fileList = new ArrayList<FileName>();

			String[] oarray = notice.getOfilename().split(",");
			String[] rarray = notice.getRfilename().split(",");

			for (int i = 0; i < oarray.length; i++) {
				FileName filename = new FileName();

				filename.setOfilename(oarray[i]);
				filename.setRfilename(rarray[i]);

				fileList.add(filename);
				mv.addObject("fileList", fileList);
			}

		}

		if (notice != null) {
			String empname = noticeService.getEmpName(notice.getEmpno());
			notice.setEmpname(empname);
			mv.addObject("notice", notice);
			mv.setViewName("notice/noticeDetail");

		} else {
			mv.addObject("message", "공지사항 상세 조회 실패");
			mv.setViewName("common/errorPage");
		}

		return mv;
	}

	// 공지사항 파일 다운
	@RequestMapping("nfdown")
	public ModelAndView noticeFileDown(ModelAndView mv, @RequestParam("ofile") String originalFileName,
			@RequestParam("rfile") String renameFileName, HttpServletRequest request) {

		String path = request.getSession().getServletContext().getRealPath("resources/nupfiles");
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
 
	//직원용 공지사항 첨부 파일삭제 Ajax
	@RequestMapping("deleteNoticeFile.do")
	public void deleteNoticeFile(String ofilename, String rfilename, String noticeno, HttpServletRequest request) {

		Notice notice = noticeService.selectOne(noticeno);

		logger.info("삭제 전 : " + notice.toString());

		notice.setOfilename(notice.getOfilename().replace(ofilename, ""));
		notice.setRfilename(notice.getRfilename().replace(rfilename, ""));

		logger.info("삭제 후 : " + notice.toString());

		int result = noticeService.deleteFile(notice);

		if (result > 0) {

			String path = request.getSession().getServletContext().getRealPath("resources/nupfiles");
			String deleteFilePath = path + "\\" + rfilename;

			logger.debug("삭제할 파일 이름 : " + ofilename + ", " + rfilename);

			File deleteFile = new File(deleteFilePath);

			if (deleteFile.exists()) {
				deleteFile.delete();
			}

		}

	}

	//직원용 공지사항 삭제
	@RequestMapping("deleteNotice.do")
	public ModelAndView deleteNotice(String noticeno, ModelAndView mv, HttpServletRequest request) {

		Notice notice = noticeService.selectOne(noticeno);

		String[] rarray = notice.getRfilename().split(",");

		for (String rfilename : rarray) {

			String path = request.getSession().getServletContext().getRealPath("resources/nupfiles");
			String deleteFilePath = path + "\\" + rfilename;

			logger.debug("삭제할 파일 이름 : " + rfilename);

			File deleteFile = new File(deleteFilePath);

			if (deleteFile.exists()) {
				deleteFile.delete();
			}
		}

		int result = noticeService.deleteNotice(noticeno);

		if (result > 0) {
			mv.addObject("currentPage", "1");
			mv.setViewName("redirect:noticeMain.do");
		} else {
			mv.addObject("message", "공지사항 삭제 실패");
			mv.setViewName("common/errorPage");
		}

		return mv;
	}

	//직원용 공지사항 수정
	@RequestMapping(value = "updateNotice.do", method = RequestMethod.POST)
	public ModelAndView updateNotice(MultipartHttpServletRequest mtfRequest, HttpServletRequest request, Notice notice,
			ModelAndView mv) {

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
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}

			logger.debug("파일갯수 : " + count + ", 원본파일이름 : " + ofilename + ", 수정파일이름 : " + rfilename);

			notice.setOfilename(ofilename);
			notice.setRfilename(rfilename);

		}

		logger.debug(notice.toString());

		int result = noticeService.updateNotice(notice);

		if (result > 0) {
			mv.addObject("noticeno", notice.getNoticeno());
			mv.setViewName("redirect:detailNotice.do");
		} else {
			mv.addObject("message", "공지사항 등록 실패");
			mv.setViewName("common/errorPage");
		}

		return mv;
	}

}
