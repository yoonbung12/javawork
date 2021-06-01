package ch11;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class HashSetTest2 {

	public static void main(String[] args) {
		
		
		//HashSet 저장공간 생성 : 
		//인스턴스 저장, 
		//중복 저장을 허용하지 않는다. 
		//저장 순서를 저장하지 않는다.
		
		//HashSet<String> hashSet = new HashSet<String>(); //앞에서 참조변수를 만들어서 뒤에 비워도 괜찮음.. 그러나 해주는게 좋은거!!
		HashSet<SimpleNumber> hashSet = new HashSet<SimpleNumber>();
		
		//데이터 저장
		hashSet.add(new SimpleNumber(10));
		hashSet.add(new SimpleNumber(20));
		hashSet.add(new SimpleNumber(20));
		

		
		
		//데이터의 일괄 처리
		//Iterator<String> itr = hashSet.iterator();
		Iterator<SimpleNumber> itr = hashSet.iterator();
		
		while(itr.hasNext()) { //hasNext사용해서 확인
			System.out.println(itr.next());
		}
		

	}

	
	

}
class SimpleNumber {
	int num;
	
	SimpleNumber(int num) {
		this.num = num;
	}
	@Override
	public int hashCode() {		
		return num%3;	//0, 1, 2
	}

	@Override
	public boolean equals(Object obj) {
		
		boolean result = false;
		
		//Object 는 null이 아니고, object 는 SimpleNumber타입으로 형변환이 가능
		if(obj != null && obj instanceof SimpleNumber) {
			SimpleNumber sNum = (SimpleNumber)obj;
			if(this.num == sNum.num) {
				result = true;
			}
			
			
		}
		
		return result;
	}
	
	public String toString() {
		return String.valueOf(num);
	}
}
