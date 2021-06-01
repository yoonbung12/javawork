package ch11;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class PlayerList1 {

	public static void main(String[] args) {
		
		//문제 2번에 필요한 값들
		HashSet<FootballPlayer> hashSet = new HashSet<FootballPlayer>();
		
		hashSet.add(new FootballPlayer("손흥민", 7, "토트넘", 29));
		hashSet.add(new FootballPlayer("헤리케인", 11, "토트넘", 27));
		hashSet.add(new FootballPlayer("델리알리", 12, "토트넘", 24));
		hashSet.add(new FootballPlayer("에릭센", 15, "토트넘", 25));
		hashSet.add(new FootballPlayer("손흥민", 7, "토트넘", 29));
		
		//2의 데이터 출력
		Iterator<FootballPlayer> itr = hashSet.iterator();
		
		
		while(itr.hasNext()) {
			itr.next().playerInfo();
		}
	}

}
