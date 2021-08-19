package com.bitcamp.firstSpring;

import org.springframework.beans.factory.annotation.Autowired;
import com.bitcamp.firstSpring.dao.Dao;

public class FileUploadService {

	@Autowired
	Dao dao;
	
	
	
	public void fileUpload() {
		dao.insert();
	}
	
}
