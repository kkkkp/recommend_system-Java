import java.util.*;

/**
 * Storage for user-provided data.
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
<<<<<<< HEAD
	 * 
	 * @return
=======
	 * Getter for users.
	 * @return all users
>>>>>>> xpu2
	 */
	public HashMap<Integer, User> getUsers() {
		return users;
	}
	
	/**
<<<<<<< HEAD
	 * 
	 * @return
=======
	 * Get all movies.
	 * @return all movies.
>>>>>>> xpu2
	 */
	public HashMap<Integer, Movie> getMovies() {
		return movies;
	}

	
	/**
<<<<<<< HEAD
	 * 
	 * @param movie
	 * @return
=======
	 * Get the users who have rated the movie.
	 * @param movie movie that users have all rated.
	 * @return a set of user ids.
>>>>>>> xpu2
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
<<<<<<< HEAD
	 * 
	 * @param user
	 * @param movie
	 * @return
=======
	 * Get a user rating on a movie.
	 * @param uid user
	 * @param mid movie
	 * @return user's rating on movie.
>>>>>>> xpu2
	 */
	public double getRating(int uid, int mid) {
		return users.get(uid).getScore(mid);
	}
	
	/**
<<<<<<< HEAD
	 * 
	 * @param user
	 * @return
=======
	 * Get the average rating of a user.
	 * @param uid user id
	 * @return average rating of user.
>>>>>>> xpu2
	 */
	public double getAvgRatingScoreByUser(int uid) {
		return users.get(uid).getAvg();
	}
	
	/**
<<<<<<< HEAD
	 * 
	 * @param movie
	 * @return
=======
	 * Calculate average rating of a movie.
	 * @param mid movie id
	 * @return average rating of movie.
>>>>>>> xpu2
	 */
	public double getAvgRatingScoreByMovie(int mid) {
		return movies.get(mid).getAvg();
	}
}
