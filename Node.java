
public class Node {
	private User user;
	private Movie movie;
	
	public Node(User user, Movie movie) {
		this.user = user;
		this.movie = movie;
	}
	
	public User getUser() {
		return user;
	}
	
	public Movie getMovie() {
		return movie;
	}
}
