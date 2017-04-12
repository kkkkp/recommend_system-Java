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
	
	private HashMap<Integer, User> users;
	private HashMap<Integer, Movie> movies;
	private FileReader fr;
	
	/**
	 * 
	 */
	public DataCenter() {
		this.users = new HashMap<>();
		this.movies = new HashMap<>();
	}
	
	/**
	 * TODO
	 * @param filename
	 */
	public void loadData(String filename) {
		this.fr = new FileReader(filename, users, movies);	
	} 
	
	/**
	 * 
	 * @return
	 */
	public HashMap<Integer, User> getUsers() {
		return users;
	}
	
	/**
	 * 
	 * @return
	 */
	public HashMap<Integer, Movie> getMovies() {
		return movies;
	}

	
	/**
	 * 
	 * @param movie
	 * @return
	 */
	public Set<Integer> getUsersByMovie(int mid) {
		return movies.get(mid).getUsers();
	}
	
	/**
	 * 
	 * @param user
	 * @return
	 */
	public Set<Integer> getMoviesByUser(int uid) {
		return users.get(uid).getMovies();
	}
	
	/**
	 * 
	 * @param user
	 * @param movie
	 * @return
	 */
	public double getRating(int uid, int mid) {
		return users.get(uid).getScore(mid);
	}
	
	/**
	 * 
	 * @param user
	 * @return
	 */
	public double getAvgRatingScoreByUser(int uid) {
		return users.get(uid).getAvg();
	}
	
	/**
	 * 
	 * @param movie
	 * @return
	 */
	public double getAvgRatingScoreByMovie(int mid) {
		return movies.get(mid).getAvg();
	}
}
