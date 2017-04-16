import java.util.*;

/**
 * Uses k nearest neighbor algorithm to make recommendation.
 * @author Patrick_Pu
 *
 */
public class Algorithm2A implements Algorithm {
	private DataCenter dc;
	private int SIZE = 20;
	private HashMap<int[], Double> similarities;
	private HashMap<int[], Double> ratingVectorSum;
	
	@Override
	public void loadDataCenter(DataCenter dc) {
		this.dc = dc;
		this.similarities = new HashMap<>();
	}

	@Override
	public double getRatingByUserAndItem(int uid, int mid) {
		Set<Integer> neighbors = getNeighbors(uid, mid);
		
		double avg = dc.getAvgRatingScoreByUser(uid);
		double numerator = 0, denominator = 0;
		
		for (Integer u: neighbors) {
			double s = getSimilarity(uid, u);
			numerator += s * (dc.getRating(u, mid) - dc.getAvgRatingScoreByUser(u));
			denominator += Math.abs(s);
		}
				
		if (denominator == 0) {
			return avg;
		}
		return avg + (numerator / denominator);
	}

	@Override
	public Set<Integer> getTopNRatingItems(int uid, int n) {
		Set<Integer> movies = new HashSet<>();
		PriorityQueue<ItemContainer> pq = new PriorityQueue<>();

		int count = 0;
		long start = System.currentTimeMillis();
		long end = 0;
		

		for (Integer mid: dc.getItems().keySet()) {
			count++;
			if (dc.getItemsByUser(uid).contains(mid)) {
				continue;
			}
			ItemContainer mc = new ItemContainer(mid, getRatingByUserAndItem(uid, mid));

			if (pq.size() < n) {
				pq.offer(mc);
			}
			else if (pq.peek().getPredict() < mc.getPredict()) {
				pq.poll();
				pq.offer(mc);
			}
			end = System.currentTimeMillis();
			
			if (end - start > 10000) {
				System.out.println(" - Heartbeating " + (end -  start) + " @" + count);
				start = end;
			}
		}
		
		while (!pq.isEmpty()) {
			movies.add(pq.poll().getId());
		}
		
		return movies;
	}
	
	/**
	 * Get a user's neighbors - users who have rated the movie.
	 * @param uid user to predict
	 * @param mid movie to predict
	 * @return a set of user ids.
	 */
	private Set<Integer> getNeighbors(int uid, int mid) {
		Set<Integer> neighbors = new HashSet<>();
		PriorityQueue<UserContainer> pq = new PriorityQueue<>();
		
		for (Integer n: dc.getUsersByItem(mid)) {
			if (uid == n) {
				continue;
			}
			
			UserContainer uc = new UserContainer(n, getSimilarity(uid, n));
			if (pq.size() < SIZE) {
				pq.offer(uc);
			}
			else if (pq.peek().getSimilarity() < uc.getSimilarity()) {
				pq.poll();
				pq.offer(uc);
			}
		}
		
		while (!pq.isEmpty()) {
			neighbors.add(pq.poll().getId());	
		}
		
		return neighbors;
	}
	
	/**
	 * Calculate the similarity between two users.
	 * @param uid1 id of first user
	 * @param uid2 id of second user
	 * @return similarity between users.
	 */
	private double getSimilarity(int uid1, int uid2) {
		if (uid1 > uid2) {
			int temp = uid2;
			uid2 = uid1;
			uid1 = temp;
		}
		
		int[] tuple = new int[] {uid1, uid2};
		
		if (similarities.containsKey(tuple)) {
			return similarities.get(tuple);
		}
		
		double numerator = getVectorProduct(uid1, uid2);
		double denominator1 = getVectorProduct(uid1, uid1);
		double denominator2 = getVectorProduct(uid2, uid2);
		double rst = 0;
		
		if (denominator1 <= 0.00001 || denominator2 <= 0.00001) {
			rst = 0;
		}
		else {
			rst = numerator / (Math.sqrt(denominator1) * Math.sqrt(denominator2));
		}
		
		similarities.put(tuple, rst);
		
		return rst;
	}

	
	private double getVectorProduct(int uid1, int uid2) {
		if (uid1 > uid2) {
			int temp = uid2;
			uid2 = uid1;
			uid1 = temp;
		}
		
		int[] tuple = new int[] {uid1, uid2};
		if (similarities.containsKey(tuple)) {
			return ratingVectorSum.get(tuple);
		}
		
		double rst = 0;

		
		similarities.put(tuple, rst);
		

		return rst;
	}
}
