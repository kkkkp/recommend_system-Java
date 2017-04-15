import java.util.*;

/**
 * Wrapper class for a user.
 * @author Han Zhu
 *
 */
public class User {

	private HashMap<Integer, Double> ratings;
	private double avg;
	
	/**
<<<<<<< HEAD
	 * 
=======
	 * Initialize instance variables.
>>>>>>> xpu2
	 */
	public User() {
		this.ratings = new HashMap<>();
		this.avg = -1;
	}
	
	/**
<<<<<<< HEAD
	 * 
	 * @param mid
	 * @param score
=======
	 * Put data into hashmap.
	 * @param mid movie
	 * @param score rating on the movie.
>>>>>>> xpu2
	 */
	public void insert(int mid, double score) {
		ratings.put(mid, score);
	}
	
	/**
<<<<<<< HEAD
	 * 
	 * @return
=======
	 * Get a user's average rating for all items.
	 * @return user's average rating for all items.
>>>>>>> xpu2
	 */
	public double getAvg() {
		if (avg == -1) {
			double sum = 0;
			for (double d: ratings.values()) {
				sum += d;
			}
			avg = sum / ratings.size();
		}
		return avg;
	}
	
	/**
<<<<<<< HEAD
	 * 
	 * @param mid
	 * @return
=======
	 * Get this user's rating on a movie.
	 * @param mid movie
	 * @return this user's rating on the movie.
>>>>>>> xpu2
	 */
	public double getScore(int mid) {
		return ratings.get(mid);
	}
	
	/**
<<<<<<< HEAD
	 * 
	 * @return
=======
	 * Get all movies rated by this user.
	 * @return all movies rated by this user.
>>>>>>> xpu2
	 */
	public Set<Integer> getMovies() {
		return ratings.keySet();
	}
}
