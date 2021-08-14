package com.bitcamp.op.member.service;

import org.springframework.beans.factory.annotation.Autowired;

public class MemberListService {

	private Dao dao;
	
	@Autowired
	private SqlSessionTemplate template;
	
	public List<Member> getMemberList(SearchType searchType) {
		
	}
}
