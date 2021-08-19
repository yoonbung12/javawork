package member.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import member.domain.Member;

public class MemberDao implements Dao {

	private static int nextId = 0;
	private Map<String, Member> map = new HashMap();
	// 			email, Member
	
	
	@Override
	public Member selectByEmail(String email) {
		return map.get(email);
	}

	@Override
	public void insert(Member member) {
		member.setId(nextId++);
		map.put(member.getEmail(), member);

	}

	@Override
	public void update(Member member) {
		map.put(member.getEmail(), member);
		
	}

	@Override
	public Collection<Member> selectAll() {
		return map.values();
	}

}
