package ch11;

import java.util.Iterator;
import java.util.TreeSet;

public class PlayerList2 {
	public static void main(String[] args) {
	TreeSet<FootballPlayer> set = new TreeSet<FootballPlayer>();
	
	//3번 데이터 저장
	set.add(new FootballPlayer("손흥민", 7, "토트넘", 29));
	set.add(new FootballPlayer("박지성", 10, "맨유", 37));
	set.add(new FootballPlayer("델리알리", 12, "토트넘", 27));
	set.add(new FootballPlayer("헤리케인", 15, "토트넘", 29));
	set.add(new FootballPlayer("루니", 17, "맨유", 36));
	set.add(new FootballPlayer("퍼니난드", 5, "맨유", 37));
	
	//3번의 데이터 정렬
	Iterator<FootballPlayer> itr = set.iterator();
	
	while(itr.hasNext()) {
		itr.next().playerInfo();
	}
	
	
	}

	
}
