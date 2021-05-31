package ex.infomanager;

public class UnivFriend extends Friend {
	//대학 친구는 전공 정보를 가진다.
	String major;
	
	public UnivFriend(String name, String phoneNumber, String address, String major) {
		super(name, phoneNumber, address);
		this.major = major;
	}
	@Override //이런거 쓰는게 정말 중요하다...세세한작업!!!
	public void showData() {
		super.showData();
		System.out.println("전   공: " + major);
	}
	@Override
	public void showBasicInfo() {
		super.showBasicInfo();
		System.out.println("이름: " + name);
		System.out.println("주소: " + address);
		System.out.println("전공: " + major);
	}
}
