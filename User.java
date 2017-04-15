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
	 * Initialize instance variables.
	 */
	public User() {
		this.ratings = new HashMap<>();
		this.avg = -1;
	}
	
	/**
	 * Put data into hashmap.
	 * @param mid item
	 * @param score rating on the item.
	 */
	public void insert(int mid, double score) {
		ratings.put(mid, score);
	}
	
	/**
	 * Get a user's average rating for all items.
	 * @return user's average rating for all items.
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
	 * Get this user's rating on a item.
	 * @param mid item
	 * @return this user's rating on the item.
	 */
	public double getScore(int mid) {
		return ratings.get(mid);
	}
	
	/**
	 * Get all items rated by this user.
	 * @return all items rated by this user.
	 */
	public Set<Integer> getItems() {
		return ratings.keySet();
	}
}
