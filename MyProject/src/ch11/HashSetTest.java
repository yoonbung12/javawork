package ch11;

import java.util.HashSet;
import java.util.Iterator;

public class HashSetTest {

	public static void main(String[] args) {
		
		
		//HashSet 저장공간 생성 : 
		//인스턴스 저장, 
		//중복 저장을 허용하지 않는다. 
		//저장 순서를 저장하지 않는다.
		
		//HashSet<String> hashSet = new HashSet<String>(); //앞에서 참조변수를 만들어서 뒤에 비워도 괜찮음.. 그러나 해주는게 좋은거!!
		HashSet<Integer> hashSet = new HashSet<Integer>();
		
		//데이터 저장
//		hashSet.add("First");
//		hashSet.add("Second");
//		hashSet.add("Third");
//		hashSet.add("First");
		hashSet.add(1);
		hashSet.add(2);
		hashSet.add(1);
		hashSet.add(2);
		hashSet.add(3);
		
		
		//데이터의 일괄 처리
		//Iterator<String> itr = hashSet.iterator();
		Iterator<Integer> itr = hashSet.iterator();
		
		while(itr.hasNext()) { //hasNext사용해서 확인
			System.out.println(itr.next());
		}
		

	}

}
