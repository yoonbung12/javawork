package collection;

import java.io.Serializable;

public class FootballPlayer implements Comparable<FootballPlayer>, Serializable {
	private String name;
	private int number;
	private String team;
	private int age;
	
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
	
	public FootballPlayer(String name, int number, String team, int age) {
		this.name = name;
		this.number = number;
		this.team = team;
		this.age = age;
	}
	
	public void showInfo() {
		System.out.println("---------------");
		System.out.println("이 름:"+name);
		System.out.println("번 호:"+number);
		System.out.println("소속팀: "+team);
		System.out.println("나 이:" + age);
		System.out.println("----------------");
	}
	
	//문제 2번
	@Override
	public int hashCode() {
		return age%3;
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean result = false;
		if(obj != null && obj instanceof FootballPlayer) {
			//arg를 형변환 먼저함
			FootballPlayer fp = (FootballPlayer)obj;
			if(this.team.equals(fp.team) &&
					this.name.equals(fp.name) &&
					this.age == fp.age) {
					result = true;
			}
		}
		return result;
	}
	//문제 3번
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
	
}
