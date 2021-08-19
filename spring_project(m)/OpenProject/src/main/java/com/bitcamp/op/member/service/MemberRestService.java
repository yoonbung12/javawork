package com.bitcamp.op.member.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitcamp.op.member.dao.Dao;
import com.bitcamp.op.member.domain.Member;

@Service
public class MemberRestService {

	private Dao dao;
	
	@Autowired
	private SqlSessionTemplate template;
	
	// idx 로 검색한 Member정보
	public Member getMember(int idx) {
		
		dao = template.getMapper(Dao.class);		
		return dao.selectByIdx(idx);
	}

	// 모든 Member 정보 
	public List<Member> getMembers() {
		dao = template.getMapper(Dao.class);
		return dao.selectAll();
	}
	
	public Map<Integer, Member> getMembers1(){
		
		List<Member> list = getMembers();
		Map<Integer, Member> members = new HashMap<Integer, Member>();
		for(int i =0; i<list.size(); i++) {
			members.put(list.get(i).getIdx(), list.get(i));
		}
		
		return members;
	}
}
