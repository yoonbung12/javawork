package collection;

import java.util.Iterator;
import java.util.TreeSet;

public class Q3 {

	public static void main(String[] args) {
		
		TreeSet<FootballPlayer> players = new TreeSet<FootballPlayer>();
		players.add(new FootballPlayer("손흥민", 7, "토트넘", 30));
		players.add(new FootballPlayer("디발라", 10, "유벤투스", 29));
		players.add(new FootballPlayer("케인", 10, "토트넘", 29));
		players.add(new FootballPlayer("손흥민", 7, "토트넘", 30));
		players.add(new FootballPlayer("박지성", 12, "맨유", 35));
		players.add(new FootballPlayer("나니", 5, "맨유", 37));
		players.add(new FootballPlayer("박지성", 12, "맨유", 35));
		
		Iterator<FootballPlayer> itr = players.iterator();
		while(itr.hasNext()) {
			itr.next().showInfo();
		}
	}

}
