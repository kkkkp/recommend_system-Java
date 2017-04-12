import java.util.*;

/**
 * Storage for user-provided data.
 * @author Patrick_Pu
 *
 */
public class DataCenter {
	private HashMap<Integer, User> users;
	private HashMap<Integer, Movie> movies;
	private FileReader fr;
	
	/**
	 * Initialize instance variables.
	 */
	public DataCenter() {
		this.users = new HashMap<>();
		this.movies = new HashMap<>();
	}
	
	/**
	 * Initialize a FileReader object to read file.
	 * @param filename name of file to read.
	 */
	public void loadData(String filename) {
		this.fr = new FileReader(filename, users, movies);	
	} 
	
	/**
	 * Getter for users.
	 * @return all users
	 */
	public HashMap<Integer, User> getUsers() {
		return users;
	}
	
	/**
	 * Get all movies.
	 * @return all movies.
	 */
	public HashMap<Integer, Movie> getMovies() {
		return movies;
	}

	
	/**
	 * Get the users who have rated the movie.
	 * @param movie movie that users have all rated.
	 * @return a set of user ids.
	 */
	public Set<Integer> getUsersByMovie(int mid) {
		return movies.get(mid).getUsers();
	}
	
	/**
	 * Get the movies that a user have rated.
	 * @param user
	 * @return all movies that user have rated.
	 */
	public Set<Integer> getMoviesByUser(int uid) {
		return users.get(uid).getMovies();
	}
	
	/**
	 * Get a user rating on a movie.
	 * @param uid user
	 * @param mid movie
	 * @return user's rating on movie.
	 */
	public double getRating(int uid, int mid) {
		return users.get(uid).getScore(mid);
	}
	
	/**
	 * Get the average rating of a user.
	 * @param uid user id
	 * @return average rating of user.
	 */
	public double getAvgRatingScoreByUser(int uid) {
		return users.get(uid).getAvg();
	}
	
	/**
	 * Calculate average rating of a movie.
	 * @param mid movie id
	 * @return average rating of movie.
	 */
	public double getAvgRatingScoreByMovie(int mid) {
		return movies.get(mid).getAvg();
	}
}
