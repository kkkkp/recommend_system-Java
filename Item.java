import java.util.*;

/**
 * Wrapper class for a item.
 * @author Han Zhu
 *
 */
public class Item {
	private HashMap<Integer, Double> ratings;
	private double avg;

	/**
	 * Initialize instance variables.
	 */
	public Item() {
		this.ratings = new HashMap<>();
		this.avg = -1;
	}
	
	/**
	 * Put data into hashmap.
	 * @param uid user who rated this item
	 * @param score rating
	 */
	public void insert(int uid, double score) {
		ratings.put(uid, score);
	}
	
	/**
	 * Calcuate average rating of this item.
	 * @return average rating of this item.
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
	 * Get a user's rating on this item.
	 * @param uid user
	 * @return rating on this item.
	 */
	public double getScore(int uid) {
		return ratings.get(uid);
	}
	
	/**
	 * Get all users who rated this item.
	 * @return all users who rated this item.
	 */
	public Set<Integer> getUsers() {
		return ratings.keySet();
	}
}
