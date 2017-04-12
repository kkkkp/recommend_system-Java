import java.util.*;

public class Movie {
	private HashMap<Integer, Double> ratings;
	private double avg;
	
	public Movie() {
		this.ratings = new HashMap<>();
		this.avg = -1;
	}
	
	public void insert(int uid, double score) {
		ratings.put(uid, score);
	}
	
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
	
	public double getScore(int uid) {
		return ratings.get(uid);
	}
	
	public Set<Integer> getUsers() {
		return ratings.keySet();
	}
}
