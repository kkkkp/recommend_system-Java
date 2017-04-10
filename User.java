
public class User {
	private int id;
	
	public User(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public boolean equals(User u) {
		return id == u.getId();
	}
}
