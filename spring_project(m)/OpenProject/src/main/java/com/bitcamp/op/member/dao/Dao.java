package com.bitcamp.op.member.dao;

import java.util.List;

import com.bitcamp.op.member.domain.Member;
import com.bitcamp.op.member.domain.SearchType;

public interface Dao {

	Member selectByIdPw(String id, String pw);
	int selectById
	
	
	
	
	// 검색을 통한 회원 리스트 8/13
	List<Member> selectMember(SearchType searchType);
}
