import java.util.*;
/**
 * 
 * @author Patrick_Pu
 *
 */
public class AlgorithmKNN implements Algorithm {
	/**
	 * 
	 */
	private DataCenter dc;
	private int SIZE = 20;
	private HashMap<int[], Double> similarities;
	
	
	@Override
	public void loadDataCenter(DataCenter dc) {
		this.dc = dc;
		this.similarities = new HashMap<>();
	}

	
	@Override
	public double getRatingByUserAndMovie(int uid, int mid) {
		Set<Integer> neighbors = getNeighbors(uid, mid);
		
		double avg = dc.getAvgRatingScoreByUser(uid);
		double numerator = 0, denominator = 0;
		
		for (Integer u: neighbors) {
			double s = getSimilarity(uid, u);
			numerator += s * (dc.getRating(u, mid) - dc.getAvgRatingScoreByUser(u));
			denominator += Math.abs(s);
		}
		return avg + (numerator / denominator);
	}

	
	@Override
	public Set<Integer> getTopNRatingMovies(int uid, int n) {
		Set<Integer> movies = new HashSet<>();
		PriorityQueue<MovieContainer> pq = new PriorityQueue<>();
		int count = 0;
		long start = System.currentTimeMillis();
		long end = 0;
		for (Integer mid: dc.getMovies().keySet()) {
			count++;
			if (dc.getMoviesByUser(uid).contains(mid)) {
				continue;
			}
			MovieContainer mc = new MovieContainer(mid, getRatingByUserAndMovie(uid, mid));
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
	 * 
	 * @param user
	 * @return
	 */
	private Set<Integer> getNeighbors(int uid, int mid) {
		Set<Integer> neighbors = new HashSet<>();
		PriorityQueue<UserContainer> pq = new PriorityQueue<>();
		
		for (Integer n: dc.getUsers().keySet()) {
			if (uid == n || !dc.getMoviesByUser(n).contains(mid)) {
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
	 * 
	 * @param u1
	 * @param u2
	 * @return
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
		
		Set<Integer> commons = getCommonMovies(uid1, uid2);
		double numerator = 0, denominator1 = 0, denominator2 = 0, score1 = 0, score2 = 0, rst = 0;
		double avg1 = dc.getAvgRatingScoreByUser(uid1);
		double avg2 = dc.getAvgRatingScoreByUser(uid2);
		
		for (Integer m: commons) {
			score1 = dc.getRating(uid1, m) - avg1;
			score2 = dc.getRating(uid2, m) - avg2;
			numerator += score1 * score2;
			denominator1 += Math.pow(score1, 2);
			denominator2 += Math.pow(score2, 2);
		}
		
		if (denominator1 <= 0.00001 && denominator2 <= 0.00001) {
			rst = 0;
		}
		else {
			rst = numerator / (Math.sqrt(denominator1) * Math.sqrt(denominator2));
		}
		similarities.put(tuple, rst);
		
		return rst;
	}
	
	/**
	 * 
	 * @param u1
	 * @param u2
	 * @return
	 */
	private Set<Integer> getCommonMovies(int uid1, int uid2) {
		Set<Integer> rst = new HashSet<>(dc.getMoviesByUser(uid1));
		Set<Integer> compare = dc.getMoviesByUser(uid2);
		rst.retainAll(compare);
		return rst;
	}
}
