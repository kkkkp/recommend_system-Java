import java.util.*;

/**
 * Wrapper class for a movie.
 * @author Han Zhu
 *
 */
public class Movie {
	private HashMap<Integer, Double> ratings;
	private double avg;
	
<<<<<<< HEAD
=======
	/**
	 * Initialize instance variables.
	 */
>>>>>>> xpu2
	public Movie() {
		this.ratings = new HashMap<>();
		this.avg = -1;
	}
	
<<<<<<< HEAD
=======
	/**
	 * Put data into hashmap.
	 * @param uid user who rated this movie
	 * @param score rating
	 */
>>>>>>> xpu2
	public void insert(int uid, double score) {
		ratings.put(uid, score);
	}
	
<<<<<<< HEAD
=======
	/**
	 * Calcuate average rating of this movie.
	 * @return average rating of this movie.
	 */
>>>>>>> xpu2
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
	
<<<<<<< HEAD
=======
	/**
	 * Get a user's rating on this movie.
	 * @param uid user
	 * @return rating on this movie.
	 */
>>>>>>> xpu2
	public double getScore(int uid) {
		return ratings.get(uid);
	}
	
<<<<<<< HEAD
=======
	/**
	 * Get all users who rated this movie.
	 * @return all users who rated this movie.
	 */
>>>>>>> xpu2
	public Set<Integer> getUsers() {
		return ratings.keySet();
	}
}
