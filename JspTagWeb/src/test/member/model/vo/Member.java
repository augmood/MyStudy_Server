package test.member.model.vo;

public class Member {

	private String id;
	private String password;
	private String name;
	
	public Member() {}
	
	// 생성자
	public Member(String id, String password, String name) {
		this.id = id;
		this.password = password;
		this.name = name;
	}

	// getter setter
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	// toString 
	@Override
	public String toString() {
		return "Member [id=" + id + ", password=" + password + ", name=" + name + "]";
	}

	


	
}
