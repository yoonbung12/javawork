package com.bitcamp.op.member.controller.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitcamp.op.member.controller.member.dao.MemberDao;

@Service
public class LoginService {

	@Autowired
	MemberDao dao;
	
	public void login() {
		dao.login();
	}
}
