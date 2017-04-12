import java.util.*;

/**
 * Wrapper class for a movie.
 * @author Han Zhu
 *
 */
public class Movie {
	private HashMap<Integer, Double> ratings;
	private double avg;
	
	/**
	 * Initialize instance variables.
	 */
	public Movie() {
		this.ratings = new HashMap<>();
		this.avg = -1;
	}
	
	/**
	 * Put data into hashmap.
	 * @param uid user who rated this movie
	 * @param score rating
	 */
	public void insert(int uid, double score) {
		ratings.put(uid, score);
	}
	
	/**
	 * Calcuate average rating of this movie.
	 * @return average rating of this movie.
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
	 * Get a user's rating on this movie.
	 * @param uid user
	 * @return rating on this movie.
	 */
	public double getScore(int uid) {
		return ratings.get(uid);
	}
	
	/**
	 * Get all users who rated this movie.
	 * @return all users who rated this movie.
	 */
	public Set<Integer> getUsers() {
		return ratings.keySet();
	}
}
