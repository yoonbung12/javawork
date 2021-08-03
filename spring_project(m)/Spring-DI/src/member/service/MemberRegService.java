package member.service;

import java.util.Date;

import member.dao.Dao;
import member.domain.Member;
import member.domain.RegRequest;

public class MemberRegService {

	//Dao dao = new MemberDao(); //	인스턴스의 의존성이 높아지므로 영향이 강해짐
	private Dao dao;	
	private int num; //	8/3
	
	// 생성자의 정의
	// 프로퍼티 방식의 주입 : setter 메소드를 이용 , 기본생성자가 필요	--8/3
	
	// 기본생성자
	public MemberRegService() {
		
	}
	
	public void setDao(Dao dao) { // setter 를 이용한 방
		this.dao = dao;
	}
	
	public MemberRegService(Dao dao) {
		this.dao = dao;
		System.out.println("MemberRegService 인스턴스 생성");
	}
	
//	public MemberRegService(int dao) {
//		this.dao = dao;
//		System.out.println("MemberRegService 인스턴스 생성");
//	} 8/3
	
//	public MemberRegService(Dao dao, int num) {
//		this.dao = dao;
//		this.num = num;
//		System.out.println("MemberRegService 인스턴스 생성");
//	} 8/3
	
	public void regMember(Member member) {
		
		dao.insert(member);
	}
	
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
