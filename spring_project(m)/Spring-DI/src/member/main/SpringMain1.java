package member.main;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import member.dao.MemberDao;
import member.domain.RegRequest;
import member.service.ChangePasswordService;
import member.service.MemberRegService;

public class SpringMain1 {

	static ApplicationContext ctx;
	
	
	public static void main(String[] args) {
	
		ctx = new GenericXmlApplicationContext("classpath:appCtx1.xml");
		
		Scanner sc = new Scanner(System.in);
		
		
		
		while(true) {
			printMenu();
			
			String command = sc.nextLine();
			// 프로그램의 종료 분기
			if(command.equalsIgnoreCase("exit")) {
				System.out.println("프로그램 종료");
				return;
			}
			
			if(command.startsWith("new")) {
				String[] values = command.split(" ");
				// values[0] - > king@gmail.com
				processNewMember(values);
				continue;
				
			}
			
			if(command.startsWith("change")) {
				String[] values = command.split(" ");
				processChangePw(values);
			}
			
		}
		
	}
	
	private static void processChangePw(String[] values) {
		
		//ChangePasswordService service = new ChangePasswordService(dao);
		//ChangePasswordService service = assembler.getPasswordService();
		ChangePasswordService service = ctx.getBean("changePwService", ChangePasswordService.class);
		
		try {
			service.changePassword(values[1], values[2], values[3]);
		
			System.out.println("비밀번호를 변경했습니다.!!!");
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			
		}
		
	}

	private static void processNewMember(String[] values) {
		// MemberRegService 이용해서 정보 저장
		
		//MemberRegService service = new MemberRegService(dao);
		//MemberRegService service = assembler.getRegService();
		MemberRegService service = ctx.getBean("regService", MemberRegService.class);
		
		RegRequest request = new RegRequest();
		request.setEmail(values[1]);
		request.setName(values[2]);
		request.setPassword(values[3]);
		request.setConfirmPassword(values[4]);
		
		if(!request.isPasswordEqualToConfirmPassword()) {
			System.out.println("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
			return;
		}
		
		try {
			service.regMember(request);
			
			System.out.println("등록되었습니다.");
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	static void printMenu() {
		System.out.println();
		System.out.println("명령어 사용법");
		System.out.println("회원 등록--------------------------");
		System.out.println("new 이메일 이름 비밀번호 비밀번호확인");
		System.out.println("new king@gmail.com KING 123 123");
		System.out.println("회원 수정---------------------------");
		System.out.println("change 이메일 현재비번 새로운비번");
		System.out.println("change king@gmail.com 123 0000");
		System.out.println("-----------------------------------");
		
	}

}
