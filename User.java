import java.util.*;

public class User {

	private HashMap<Integer, Double> ratings;
	private double avg;
	
	/**
	 * 
	 */
	public User() {
		this.ratings = new HashMap<>();
		this.avg = -1;
	}
	
	/**
	 * 
	 * @param mid
	 * @param score
	 */
	public void insert(int mid, double score) {
		ratings.put(mid, score);
	}
	
	/**
	 * 
	 * @return
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
	 * 
	 * @param mid
	 * @return
	 */
	public double getScore(int mid) {
		return ratings.get(mid);
	}
	
	/**
	 * 
	 * @return
	 */
	public Set<Integer> getMovies() {
		return ratings.keySet();
	}
}
