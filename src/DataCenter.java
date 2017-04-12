import java.util.*;
/**
 * 
 * @author Patrick_Pu
 *
 */
public class DataCenter {
	private Map<Node, Double> map;
	private Map<User, Set<Movie>> rated;
	private Set<Movie> movies;
	private FileReader fr;
	
	/**
	 * 
	 */
	public DataCenter() {
		this.map = new HashMap<>();
		this.rated = new HashMap<>();
		this.movies = new HashSet<>();
	}
	
	/**
	 * 
	 * @param filename
	 */
	public void loadData(String filename) {
		this.fr = new FileReader(filename);
		
		for (String line : fr.getLines()) {
//			System.out.println(line);
			StringTokenizer st = new StringTokenizer(line, "::");
			User user = new User(Integer.parseInt(st.nextToken()));
			Movie movie = new Movie(Integer.parseInt(st.nextToken()));
			Node node = new Node(user, movie);
			double rating = Double.parseDouble(st.nextToken());
			
			map.put(node, rating);
			if (!rated.containsKey(user)) {
				rated.put(user, new HashSet<>());
			}
			rated.get(user).add(movie);
			movies.add(movie);
		}
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
	public Set<Movie> getMoviesByUser(User user) {
		return rated.get(user);
	}
	
	/**
	 * 
	 * @param user
	 * @param movie
	 * @return
	 */
	public double getRating(User user, Movie movie) {
		Node n = new Node(user, movie);
		if (!map.containsKey(n)) {
			return -1;
		}
		else {
			return map.get(n);
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
				sum += map.get(n);
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
				sum += map.get(n);
			}
		}
		return 1.0 * sum / count;
	}
	
	/**
	 * 
	 * @return
	 */
	public Set<Movie> getMovies() {
		return movies;
	}
}
