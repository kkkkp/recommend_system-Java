import java.util.*;
public class DataCenter {
	private HashMap<Node, Rating> map;
	private FileReader fr;
	
	public DataCenter() {
		
	}
	
	public void loadData(String filename) {
		this.fr = new FileReader(filename);
	}
	
	public List<User> getUsersByMovie(Movie movie) {
		return null;
	}
	
	public List<Movie> getMoviesByUser(User user) {
		return null;
	}
	
	public int getRating(User user, Movie movie) {
		return 0;
	}
	
	public int getAvgRatingScoreByUser(User user) {
		return 0;
	}
	
	public int getAvgRatingScoreByMovie(Movie movie) {
		return 0;
	}
}
