package member.service;

import member.dao.Dao;
import member.domain.Member;

public class ChangePasswordService {

	private Dao dao;
	
	// dao 주입을 위한 setter
	public void setDao(Dao dao) {
		this.dao = dao;
	}
	
	// 기본 생성자
	public ChangePasswordService() {}
	
	
	public ChangePasswordService(Dao dao) {
		this.dao = dao;
		System.out.println("ChangePasswordService 인스턴스 생성");
	}
	
	public void changePassword(String email,  String oldPw, String newPw) throws Exception {
		
		System.out.println("change : " + email);
		
		Member member = dao.selectByEmail(email);
	
		if(member == null) {
			throw new Exception("존재하지 않는 회원!");
		}
		
		member.changePassword(oldPw, newPw);
		
		//예외가 발생하지 않고 들어간다면
		dao.update(member);
		
	}
}
