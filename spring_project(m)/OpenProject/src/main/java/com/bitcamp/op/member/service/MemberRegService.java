package com.bitcamp.op.member.service;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bitcamp.op.member.dao.Dao;
import com.bitcamp.op.member.domain.Member;
import com.bitcamp.op.member.domain.MemberRegRequest;

@Service
public class MemberRegService {

	final String UPLOAD_URI = "/uploadfile"; // 파일저장하고자 하는 경로

	//@Autowired
	//private MemberDao dao;
	
	//@Autowired
	//private mybatisMemberDao dao;

	//@Autowired
	//private mybatisMemberDao dao;
	
	private Dao dao;
	
	@Autowired
	private SqlSessionTemplate template;
	
	public int memberReg(MemberRegRequest regRequest, HttpServletRequest request) {
		
		int resultCnt = 0;
		// Connection conn = null;
		File newFile = null;
	
		try {
				// 1. 파일 저장
				
				// Member 객체 생성 -> 저장된 파일의 이름을 set
				Member member = regRequest.toMember();
				
				// 파일 저장
				if(regRequest.getPhoto() != null && !regRequest.getPhoto().isEmpty()) {
						
					// 새로운 저장 폴더 : File
					File newDir = new File(request.getSession().getServletContext().getRealPath(UPLOAD_URI));
					
					// 폴더가 존재하지 않으면 폴더 생성
					if(!newDir.exists()) {
						newDir.mkdir();
						System.out.println("저장 폴더를 생성했습니다.");
					}
					// 파일 저장시에 파일 이름이 같으면 덮어쓴다 -> 회원별 고유한 파일 이름을 만들자!!!, 새로운 파일이름에 확장자 추가
					String newFileName = regRequest.getMemberid()
									+ System.currentTimeMillis()
									+ chkFileType(regRequest.getPhoto());
					
					// cool1231238936798123987
					
					// 새로운 File 객체
					newFile = new File(newDir, newFileName);
					
					regRequest.getPhoto().transferTo(newFile);
					member.setMemberphoto(newFileName);
				} else {
						member.setMemberphoto("photo.png");
				}
				
				// 2. dao 저장
				// conn = ConnectionProvider.getConnection();
				
				dao = template.getMapper(Dao.class);
				
				resultCnt = dao.insertMember(member);
				
				System.out.println("새롭게 등록된 idx => " + member.getIdx());
				
				// idx 값은 자식 테이블의 insert 시 외래키로 사용
				
				// 자식테이블 insert 구문....
				
			
		} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
		} catch (SQLException e) {
				// DB 예외 발생 시 -> 저장된 파일을 삭제
				if(newFile != null && newFile.exists()) {
					newFile.delete();
				}
				e.printStackTrace();
		} catch(Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
		}
		return resultCnt;
	}
	
	
	// 파일의 ContentType과 파일 확장자를 체크
	private String chkFileType(MultipartFile file) throws Exception {
			String extension = "";
			
			// 업로드 파일의 contentType
			String contentType = file.getContentType();
			if(!(contentType.equals("image/jpeg") || contentType.equals("image/jpg") || contentType.equals("image/png") || contentType.equals("image/gif"))) {
					throw new Exception("허용하지 않는 파일 타입 :" + contentType);
	
	}
			
			// 파일 확장자 구하기
			String fileName = file.getOriginalFilename();
			
			// String[]	java.lang.String.split(String regex)
			// : 정규식의 패턴 문자열을 전달해야하기 때문에 \\. 으로 처리
			String[] nameTokens = fileName.split("\\."); // tet.mini2.jpg PNG png
			
			// 토큰의 마지막 index의 문자열을 가져옴 : 배열의 개수 -1
			extension = nameTokens[nameTokens.length - 1].toLowerCase();
			
			// 이미지 파일 이외의 파일 업로드 금지
			// 파일 확장자 체크
			if(!(extension.equals("jpg") || extension.equals("png") || extension.equals("gif"))) {
					throw new Exception("허용하지 않는 파일 확장자 타입 : " + contentType);
					
			}
			
			return extension;
	}
}
	
