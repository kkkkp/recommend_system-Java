
public class Movie {
	private int id;
	private String title;
	
	public Movie(String title, int id) {
		this.title = title;
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}
}
