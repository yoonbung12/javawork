package ch11;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.HashSet;

public class FootballPlayer implements Comparable<FootballPlayer>{
	String name;
	int number;
	String team;
	int age;
	
	FootballPlayer(String name, int number, String team, int age) {
		this.name = name;
		this.number = number;
		this.team = team;
		this.age = age;
	}
	
	

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "FootballPlayer [name=" + name + ", number=" + number + ", team=" + team + ", age=" + age + "]";
	}
	
	//2번문제를 위한
		@Override
		public int hashCode() {
		return age%3;
	
}		
	@Override
	public boolean equals(Object obj) {
		
		boolean result = false;
		if(obj != null && obj instanceof FootballPlayer) {
			FootballPlayer ft = (FootballPlayer)obj;
			if(this.age == ft.age) {
				result = true;
			}
		}
		return result;
		
	}
	//3번의 오버라이딩
	//팀 이름순으로 정렬.
	//같은 팀의 선수들은 이름순으로 정렬
	//같은 이름의 선수는 번호순으로 정렬
	@Override
	public int compareTo(FootballPlayer o) {
		int result = this.team.compareTo(o.getTeam());
		if(result == 0) {
			result = this.name.compareTo(o.getName());
			if(result == 0) {
				result = this.number - o.getNumber();
			}
		}
		return result;
	}

	public void playerInfo() {
		System.out.println("---------");
		System.out.println("이름: " + name);
		System.out.println("넘버: " + number);
		System.out.println("팀이름: " + team);
		System.out.println("나이:" + age);
	}
	
	public static void main(String[] args) {
		
		//1.축구선수 인스턴스를 저장할 수 있는 List<E>컬렉션 인스턴스를 생성해서 인스턴스를 저장하고 출력하는 프로그램을 만들어 봅시다.
		ArrayList<FootballPlayer> list = new ArrayList<FootballPlayer>();
		
		
		//1의 데이터 저장
		list.add(new FootballPlayer("손흥민", 7, "토트넘", 27));
//		list.add(new FootballPlayer("손흥민", 7, "토트넘", 27));
		list.add(new FootballPlayer("케인", 10, "토트넘", 25));
		list.add(new FootballPlayer("덕배", 12, "맨시티", 29));
		list.add(new FootballPlayer("델리알리", 5, "토트넘", 26));
		list.add(new FootballPlayer("호나우지뉴", 11, "바르셀로나", 31));
		
		//1의 데이터 출력
		Iterator<FootballPlayer> itr = list.iterator();
		while(itr.hasNext()) {
			itr.next().playerInfo();
			
		
			
		}
	}



	



	

}
