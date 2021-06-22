package member;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Service {
	private Socket socket;
	
	private PrintWriter out;
	private BufferedReader br;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	
	private Member member;
	
	public Service() {}
	
	public Service (Socket socket, PrintWriter out, BufferedReader br, ObjectInputStream ois, ObjectOutputStream oos) {
		this.socket = socket;
		this.out = out;
		this.br = br;
		this.ois = ois;
		this.oos = oos;
	}
	
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public void join(Scanner sc) {
		boolean a = true;
		
		while(a) {
			System.out.println("회원가입");
			System.out.println("ID:");
			String id = sc.next();
			
				
				
				try {
					out.println("////idCheck");
					out.println(id); //아이디 중복 확인
					
					
					
					String idCheck = br.readLine();//중복 o: true
					
					if(idCheck.equals("0")) { //아이디가 존재 하지 않으면 0
						System.out.println("PW:");
						String pw = sc.next();
						System.out.println("이름:"); 
						String name = sc.next();
						System.out.println("면허번호: ");
						String carreg = sc.next();
						System.out.println("이메일: "); 
						String email = sc.next();
						System.out.println("주소: ");
						String address = sc.next();
						
						out.println("/insert");
						Member m = new Member(0, id, pw, name, carreg, email, address);
						oos.writeObject(m); //객체 보내기
						
						String result = br.readLine();
						if(result.equals("true")) {
							System.out.println("회원가입 성공");
							
						} else {
							System.out.println("회원가입을 실패했습니다.");
						}
						
						a = false;
					} else { //아이디 중복
						System.out.println("이미 사용중인 아이디 입니다. 다시 입력하세요.");
					}
				
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			} 
		}
	}
	
	public Member logIn(Scanner sc) {
			System.out.println("로그인");
			System.out.println("ID:");
			String id = sc.next();
			System.out.println("PW:");
			String pw = sc.next();
			
				
				
				
				try {
					member = new Member();
					out.println("/login");
					if(member instanceof Member) {
					
					
					oos.writeObject(member);
					oos.flush();
					}
					
					member = (Member) ois.readObject();
					if(member == null) {
							System.out.println("아이디/ 비밀번호가 일치하지 않습니다.");
					} else {
						System.out.println("로그인 성공");
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return member;
				
	}
	
}
