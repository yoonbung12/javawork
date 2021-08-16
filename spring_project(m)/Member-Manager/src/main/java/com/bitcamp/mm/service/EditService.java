package com.bitcamp.mm.service;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitcamp.mm.dao.MemberDao;
import com.bitcamp.mm.dto.Member;

@Service
public class EditService {

	@Autowired
	MemberDao dao;
	
	public void editMember(HttpServletRequest request) throws FileUploadException  {
		int resultCnt = 0;
		
		Member member = new Member();
		
		Connection conn = null;
				
		File newFile = null;
	
		try {
				boolean isMultipart = ServletFileUpload.isMultipartContent(request);
	
				if(isMultipart) {
					DiskFileItemFactory factory = new DiskFileItemFactory();
					
					ServletFileUpload upload = new ServletFileUpload(factory);
				
					List<FileItem> items = upload.parseRequest(request);
				
					Iterator<FileItem> itr = items.iterator();
					
					while(itr.hasNext()) {
						FileItem item = itr.next();
						String paramName = item.getFieldName();
						
						if(item.isFormField()) {
							if(paramName.equals("memberid")) {
								member.setMemberid(item.getString("utf-8"));
							} else if(paramName.equals("password")) {
								member.setPassword(item.getString("utf-8"));
							} else if(paramName.equals("membername")) {
								member.setMembername(item.getString("utf-8"));
							}
						} else {
							String uploadUri = "uploadfile";
							String dir = request.getSession().getServletContext().getRealPath(uploadUri);
							
							File saveDir = new File(dir);
							
							if(!saveDir.exists()) {
								saveDir.mkdir();
							}
							
							if(paramName.equals("photo")) {
								if(item.getName()!= null && item.getSize()>0) {
									newFile = new File(saveDir, item.getName());
									item.write(newFile);
									member.setMemberphoto(item.getName());
								} else {
									member.setMemberphoto("photo.png");
								}
							}
							
						}
					}
				
				
				} else {
					throw new Exception("multipart 타입이 아닙니다.");
				}
				
				conn = ConnectionProvider.getConnection();
				
				resultCnt = dao.updateMember(conn, member);
				
		} catch(SQLException e) {
			e.printStackTrace();
			if(newFile != null && newFile.exists()) {
				newFile.delete();
			}
		} catch(UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn);
		}
		request.setAttribute("result", resultCnt);
	}	
}
