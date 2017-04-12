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
	
	
	@Override
	public void loadDataCenter(DataCenter dc) {
		this.dc = dc;
	}

	
	@Override
	public double getRatingByUserAndMovie(int uid, int mid) {
		Set<Integer> neighbors = getNeighbors(uid);
		double avg = dc.getAvgRatingScoreByUser(uid);
		double numerator = 0, denominator = 0;
		
		for (Integer u: neighbors) {
			double s = getSimilarity(uid, u);
			numerator += s * (dc.getRating(u, mid) - dc.getAvgRatingScoreByUser(u));
			denominator += Math.abs(s);
		}
		return avg + numerator / denominator;
	}

	
	@Override
	public Set<Integer> getTopNRatingMovies(int uid, int n) {
		Set<Integer> movies = new HashSet<>();
		PriorityQueue<MovieContainer> pq = new PriorityQueue<>();
		
		for (Integer mid: dc.getMovies().keySet()) {
			MovieContainer mc = new MovieContainer(mid, getRatingByUserAndMovie(uid, mid));
			if (pq.size() < n) {
				pq.offer(mc);
			}
			else if (pq.peek().getPredict() < mc.getPredict()) {
				pq.poll();
				pq.offer(mc);
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
	private Set<Integer> getNeighbors(int uid) {
		Set<Integer> neighbors = new HashSet<>();
		PriorityQueue<UserContainer> pq = new PriorityQueue<>();
		
		for (Integer n: dc.getUsers().keySet()) {
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
	 * 
	 * @param u1
	 * @param u2
	 * @return
	 */
	private double getSimilarity(int uid1, int uid2) {
		Set<Integer> commons = getCommonMovies(uid1, uid2);
		double numerator = 0, denominator1 = 0, denominator2 = 0;
		double avg1 = dc.getAvgRatingScoreByUser(uid1);
		double avg2 = dc.getAvgRatingScoreByUser(uid2);
		double score1 = 0;
		double score2 = 0;
		
		for (Integer m: commons) {
			score1 = dc.getRating(uid1, m) - avg1;
			score2 = dc.getRating(uid2, m) - avg2;
			numerator += score1 * score2;
			denominator1 += Math.pow(score1, 2);
			denominator1 += Math.pow(score2, 2);
		}
		
		return numerator / (Math.pow(denominator1 * denominator2, 0.5));
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
