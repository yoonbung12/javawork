package member.main;

import member.service.MemberRegService;

public class SpringMain3 {	// 8/3

	public static void main(String[] args) {

		
		//ctx = new GenericXmlApplicationContext("classpath:appCtx1.xml");
		//ctx = new GenericXmlApplicationContext("classpath:appCtx2.xml");
		//ctx = new GenericXmlApplicationContext("classpath:appCtx4.xml");
		ctx = new GenericXmlApplicationContext("classpath:appCtx5.xml");

		
		// MemberRegService 객체를컨테이너로부터 받는다 ==
		Dao dao1 = ctx.getBean("memberDao", Dao.class);
		Dao dao2 = ctx.getBean("memberDao", Dao.class);
		System.out.println("=============================");
		System.out.println("dao1==dao2 : " + (dao1 ==dao2));
		
		//MemberRegService regService1 = ctx.getBean("regService", MemberRegService.class);
		//MemberRegService regService2 = ctx.getBean("regService", MemberRegService.class);
		MemberRegService regService2 = ctx.getBean("regService", MemberRegService.class);

		System.out.println("regService1 == regService2 = " + regService1 == regService2);
		
		ChangePasswordService passwordService1 = ctx.getBean("changerPwService", ChangePasswordService.class);
		ChangePasswordService passwordService2 = ctx.getBean("changerPwService", ChangePasswordService.class);

		System.out.println("passwordService1 == passwordService2 = " + passwordService1 == passwordService2);

	}

}
