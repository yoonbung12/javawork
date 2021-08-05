package com.bitcamp.firstSpring;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
public class FileUploadController {
	
	final String UPLOAD_URI = "/uploadfile"; // 파일저장하고자 하는 경로
	
	
	@RequestMapping("/upload/uploadForm")
	public String getUploadForm() {
		return "upload/uploadForm";		//uploadForm.jsp
	}
	
	@RequestMapping(value="/upload/upload1")
	public String upload1(
			
			@RequestParam("sno") String sno,
			@RequestParam("sname")String sname,
			@RequestParam("report") MultipartFile report,
			Model model,
			HttpServletRequest request //절대경로를 구하기 위해서!
			
			
			
			) throws IllegalStateException, IOException {
		
		System.out.println("학번 : " + sno);
		System.out.println("이름 : " + sname);
		System.out.println("파일 : " + report.getOriginalFilename());
		
		model.addAttribute("sno", sno);
		model.addAttribute("sname", sname);
		model.addAttribute("reportfile", report.getOriginalFilename());
		
		
		saveFile(request, report);
		
		
		return "upload/upload";
	}
	
	@RequestMapping("/upload/upload2")
	public String upload2(
			
			MultipartHttpServletRequest request
			Model model
			
			
			) {
		
		String sno = request.getParameter("sno");
		String snmae = request.getParameter("sname");
		MultipartFile report = request.getFile("report");
		
		System.out.println("학번 : " + sno);
		System.out.println("이름 : " + sname);
		System.out.println("파일 : " + report.getOriginalFilename());
		
		model.addAttribute("sno", sno);
		model.addAttribute("sname", sname);
		model.addAttribute("reportfile", report.getOriginalFilename());		
		
		
		
		return "upload/upload";
		
		
	}
	
	
	
	// 사용자 업로드한 파일을 저장하는 메소드
	private void saveFile(HttpServletRequest request,  MultipartFile file) throws IllegalStateException, IOException {
		// 이 아래 3개는 서비스 보내는것!
		// 저장 경로 : 시스템 경로
		String saveDir = request.getSession().getServletContext().getRealPath(UPLOAD_URI);
		
		// 새롭게 저장할 파일
		File newFile = new File(saveDir, file.getOriginalFilename());
	
		// 파일 저장
		file.transferTo(newFile);
	
	}
	
}
