package com.bitcamp.app.member.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitcamp.app.member.domain.Member;
import com.bitcamp.app.member.domain.SearchType;
import com.bitcamp.app.member.mapper.MemberMapper;

@Service
public class MemberListService {

	private MemberMapper dao;
	
	@Autowired
	private SqlSessionTemplate template;
	
	public List<Member> getMemberList(){
			return template.getMapper(MemberMapper.class).selectAll();
			
	}
	
	public List<Member> getMemberList(SearchType searchType){
			return template.getMapper(MemberMapper.class).selectMember(searchType);
	}
	
	
}
