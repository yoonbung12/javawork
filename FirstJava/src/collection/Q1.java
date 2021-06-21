package collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Q1 {

	public static void main(String[] args) {
		
		List<FootballPlayer> players = new ArrayList<FootballPlayer>();
		players.add(new FootballPlayer("손흥민", 7, "토트넘", 30));
		players.add(new FootballPlayer("디발라", 10, "유벤투스", 29));
		players.add(new FootballPlayer("케인", 10, "토트넘", 29));
		players.add(new FootballPlayer("손흥민", 7, "토트넘", 30));
		
		Iterator<FootballPlayer> itr = players.iterator();
		while(itr.hasNext()) {
			itr.next().showInfo();
		}
	}

}
