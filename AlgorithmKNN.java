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
	public double getRatingByUserAndMovie(User user, Movie movie) {
		List<User> neighbors = getNeighbors(user);
		double avg = dc.getAvgRatingScoreByUser(user);
		double numerator = 0, denominator = 0;
		
		for (User u: neighbors) {
			double s = getSimilarity(user, u);
			numerator += s * (dc.getRating(u, movie) - dc.getAvgRatingScoreByUser(u));
			denominator += Math.abs(s);
		}
		return numerator / denominator;
	}

	@Override
	public List<Movie> getTopNRatingMovies(User user, int n) {
		List<Movie> movies = new ArrayList<>();
		PriorityQueue<MovieContainer> pq = new PriorityQueue<>();
		
		for (Movie movie: dc.getMovies()) {
			MovieContainer mc = new MovieContainer(movie, getRatingByUserAndMovie(user, movie));
			if (pq.size() < n) {
				pq.offer(mc);
			}
			else if (pq.peek().getPredict() < mc.getPredict()) {
				pq.poll();
				pq.offer(mc);
			}
		}
		
		while (!pq.isEmpty()) {
			movies.add(pq.poll().getMovie());
		}
		
		return movies;
	}
	
	/**
	 * 
	 * @param u1
	 * @param u2
	 * @return
	 */
	private double getSimilarity(User u1, User u2) {
		List<Movie> commons = getCommonMovies(u1, u2);
		double numerator = 0, denominator1 = 0, denominator2 = 0;
		double avg1 = dc.getAvgRatingScoreByUser(u1);
		double avg2 = dc.getAvgRatingScoreByUser(u2);
		double score1 = 0;
		double score2 = 0;
		
		for (Movie m: commons) {
			score1 = dc.getRating(u1, m) - avg1;
			score2 = dc.getRating(u2, m) - avg2;
			numerator += score1 * score2;
			denominator1 += Math.pow(score1, 2);
			denominator1 += Math.pow(score2, 2);
		}
		
		return numerator / (Math.pow(denominator1 * denominator2, 0.5));
	}
	
	/**
	 * 
	 * @param user
	 * @return
	 */
	private List<User> getNeighbors(User user) {
		List<User> neighbors = new ArrayList<>();
		PriorityQueue<UserContainer> pq = new PriorityQueue<>();
		
		for (User neighbor: dc.getUsers()) {
			if (!neighbor.equals(user)) {
				UserContainer uc = new UserContainer(neighbor, getSimilarity(user, neighbor));
				if (pq.size() < SIZE) {
					pq.offer(uc);
				}
				else if (pq.peek().getSimilarity() < uc.getSimilarity()) {
					pq.poll();
					pq.offer(uc);
				}
			}
		}
		
		while (!pq.isEmpty()) {
			neighbors.add(pq.poll().getUser());
		}
		
		return neighbors;
	}
	
	/**
	 * 
	 * @param u1
	 * @param u2
	 * @return
	 */
	private List<Movie> getCommonMovies(User u1, User u2) {
		List<Movie> movies = new ArrayList<>();
		List<Movie> m1 = dc.getMoviesByUser(u1);
		HashSet<Movie> set = new HashSet<>(dc.getMoviesByUser(u2));
		
		for (Movie m: m1) {
			if (set.contains(m)) {
				movies.add(m);
			}
		}
		return movies;
	}
}
