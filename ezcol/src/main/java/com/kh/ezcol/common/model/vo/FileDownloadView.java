package com.kh.ezcol.common.model.vo;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

import com.kh.ezcol.common.model.vo.FileDownloadView;


@Component("filedown")
public class FileDownloadView extends AbstractView {
	
	
private static final Logger logger = LoggerFactory.getLogger(FileDownloadView.class);

@Override
protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
		HttpServletResponse response) throws Exception {

	
	
	//컨트롤러에서 BeanNameViewResolver 로 리턴된 model 정보가 자동으로 전달됨 
	
			logger.info("fileDown run....");
			
			File downFile = (File)model.get("downFile");
			
			String originalFileName = (String)model.get("ofile");
			
			
			logger.info("path : " + downFile.getPath());
			logger.info("filename : " + downFile.getName());
			logger.info("ofile : " + originalFileName);
			
			//다운로드 처리 셋팅 
			response.setContentType("text/plain; charset=UTF-8"); 
			//다운될 파일명 지정 (한글깨짐 방지 처리 : 인코딩)
			response.addHeader("Content-Disposition", "attachment; filename=\"" + new String(originalFileName.getBytes("UTF-8"), "ISO-8859-1") +"\"");
			//다운될 파일의 길이 
			response.setContentLength((int)downFile.length());
			
			//저장폴더에서 파일 읽어서 클라이언트에게 네트워크 전송 처리 
			//파일읽기용 입력스트림과 클라이언트로 출력스트림 생성함 
			
			FileInputStream fin = new FileInputStream(downFile);
			OutputStream out = response.getOutputStream();
			
			FileCopyUtils.copy(fin, out);
			
			fin.close();
			out.flush();
			out.close();
	
}


	
	
	


	
}
