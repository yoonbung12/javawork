package member.service;

import member.dao.Dao;
import member.domain.Member;
import member.domain.RegRequest;

public class MemberRegService {

	//Dao dao = new MemberDao();
	Dao dao;
	
	public MemberRegService(Dao dao) {
		this.dao = dao;
	}
	
	public void regMember(RegRequest request) {
		
		// 중복 이메일 체크
		Member member = dao.selectByEmail(request.getEmail());
		
		if(member != null) {
			throw new Exception("중복 이메일!!");
		}
		
		
		
		Member newmember = new Member(
				0,
				request.getEmail(),
				request.getPassword(),
				request.getName(),
				request.getRegDate());
		
		dao.insert(newmember);
	}
	
}
