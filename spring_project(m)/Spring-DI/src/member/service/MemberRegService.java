package member.service;

import java.util.Date;

import member.dao.Dao;
import member.domain.Member;
import member.domain.RegRequest;

public class MemberRegService {

	//Dao dao = new MemberDao();
	Dao dao;
	
	public MemberRegService(Dao dao) {
		this.dao = dao;
	}
	
	public void regMember(Member member) {
		
		dao.insert(member);
	}
	
	public void regMember(RegRequest request) {
		
		// 중복 이메일 체크
		Member member = dao.selectByEmail(request.getEmail());
		
		
		
		Member newMember = new Member(0,
				request.getEmail(),
				request.getPassword(),
				request.getName(),
				new Date());
		
		dao.insert(member);
	}
	
}
