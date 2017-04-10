import java.util.*;
/**
 * 
 * @author Patrick_Pu
 *
 */
public class DataCenter {
	/**
	 * 
	 */
	private HashMap<Node, Rating> map;
	private HashSet<User> users;
	private HashSet<Movie> movies;
	private FileReader fr;
	
	/**
	 * 
	 */
	public DataCenter() {
		this.map = new HashMap<>();
	}
	
	/**
	 * TODO
	 * @param filename
	 */
	public void loadData(String filename) {
		this.fr = new FileReader(filename);
	}
	
	/**
	 * 
	 * @param movie
	 * @return
	 */
	public List<User> getUsersByMovie(Movie movie) {
		List<User> users = new ArrayList<>();
		for (Node n: map.keySet()) {
			if (n.getMovie().equals(movie)) {
				users.add(n.getUser());
			}
		}
		return users;
	}
	
	/**
	 * 
	 * @param user
	 * @return
	 */
	public List<Movie> getMoviesByUser(User user) {
		List<Movie> movies = new ArrayList<>();
		for (Node n: map.keySet()) {
			if (n.getUser().equals(user)) {
				movies.add(n.getMovie());
			}
		}
		return movies;
	}
	
	/**
	 * 
	 * @param user
	 * @param movie
	 * @return
	 */
	public int getRating(User user, Movie movie) {
		Node n = new Node(user, movie);
		if (!map.containsKey(n)) {
			return -1;
		}
		else {
			return map.get(n).toNumber();
		}
	}
	
	/**
	 * 
	 * @param user
	 * @return
	 */
	public double getAvgRatingScoreByUser(User user) {
		int count = 0;
		int sum = 0;
		for (Node n: map.keySet()) {
			if (n.getUser().equals(user)) {
				count++;
				sum += map.get(n).toNumber();
			}
		}
		return 1.0 * sum / count;
	}
	
	/**
	 * 
	 * @param movie
	 * @return
	 */
	public double getAvgRatingScoreByMovie(Movie movie) {
		int count = 0;
		int sum = 0;
		for (Node n: map.keySet()) {
			if (n.getMovie().equals(movie)) {
				count++;
				sum += map.get(n).toNumber();
			}
		}
		return 1.0 * sum / count;
	}
	
	/**
	 * 
	 * @return
	 */
	public HashSet<User> getUsers() {
		return users;
	}
	
	/**
	 * 
	 * @return
	 */
	public HashSet<Movie> getMovies() {
		return movies;
	}
}
