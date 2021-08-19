package member.service;

import java.util.Date;

import javax.annotation.Resource;

import member.dao.Dao;
import member.domain.Member;
import member.domain.RegRequest;

public class MemberRegService2 {	// 8/3

	//@Autowired(required = false)
	//@Qualifier("member1")
	@Resource(name = "guestDao")
	private Dao dao;	
	
	
	// 8/3
	
	public void regMember(RegRequest request) throws Exception {
		
		// 중복 이메일 체크
		Member member = dao.selectByEmail(request.getEmail());
		
		if(member != null) {
			throw new Exception("중복 이메일!!");
		}
		
		Member newMember = new Member(0,
				request.getEmail(),
				request.getPassword(),
				request.getName(),
				new Date());
		
		dao.insert(member);
	}
	
}
