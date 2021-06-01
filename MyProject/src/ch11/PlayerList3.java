package ch11;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class PlayerList3 {

	public static void main(String[] args) {
		//4.축구선수의 번호를 Key로 하고 축구선수 인스턴스 저장하는 Map<K,V> 인스턴스를 이용해서 프로그램을 만들어보자
		HashMap<Integer, FootballPlayer> map = new HashMap<Integer, FootballPlayer>();
		
		//데이터 저장.
		map.put(5, new FootballPlayer("son", 5, "totum", 27));
		map.put(10, new FootballPlayer("son2", 10, "totum", 25));
		map.put(3, new FootballPlayer("son3", 3, "totum", 26));
		map.put(7, new FootballPlayer("son4", 7, "totum", 26));
		
		System.out.println("***********************");
		Set<Integer> set = map.keySet();
		
		//정렬
		Iterator<Integer> itr = set.iterator();
		while(itr.hasNext()) {
			 System.out.println(map.get(itr.next()));
			
		
		}
	}
}
