package com.kh.ezcol.breakDown.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.ezcol.breakDown.model.service.BreakDownService;
import com.kh.ezcol.breakDown.model.vo.BreakDown;

@Controller
public class BreakDownController {

	private static final Logger logger = LoggerFactory.getLogger(BreakDownController.class);

	@Autowired
	private BreakDown breakDown;

	@Autowired
	private BreakDownService breakDownService;

	@RequestMapping("breakDownValidate.do")
	public void breakDownValidate(String classno, String studentno, HttpServletResponse response) {

		logger.info("breakDownValidate run...");
		logger.info("studentno : " + studentno);
		logger.info("classno : " + classno);

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

		map.put("classno", classno);
		map.put("studentno", studentno);
		map.put("semester", semester);

		BreakDown breakDown = breakDownService.selectAll(map);

		PrintWriter out = null;

		try {
			out = response.getWriter();

		} catch (IOException e) {

			e.printStackTrace();
		}

		out.close();
	}

}
