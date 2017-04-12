import java.util.*;
/**
 * 
 * @author Patrick_Pu
 *
 */
public class DataCenter {
	/**
	 * 
	 */
	
	private HashMap<Integer, User> users;
	private HashMap<Integer, Movie> movies;
	private FileReader fr;
	
	/**
	 * 
	 */
	public DataCenter() {
		this.users = new HashMap<>();
		this.movies = new HashMap<>();
	}
	
	/**
	 * TODO
	 * @param filename
	 */
	public void loadData(String filename) {
		this.fr = new FileReader(filename);

		int count = 0, uid = 0, mid = 0;
		double score = 0;
		
		for (String line: fr.getLines()) {
			String[] seg = line.split("::");
			uid = Integer.parseInt(seg[0]);
			mid = Integer.parseInt(seg[1]);
			score = Double.parseDouble(seg[2]);
			
			if (!users.containsKey(uid)) {
				users.put(uid, new User());
			}
			User user = users.get(uid);
			user.insert(mid, score);
			
//			TODO: uncomment			
//			if (!movies.containsKey(mid)) {
//				movies.put(mid, new Movie());
//			}
//			Movie movie = movies.get(mid);
//			movie.insert(uid, score);
			count++;
			if (count % 50000 == 0) {
				System.out.println("Process... " + count);
			}
		}
		System.out.println("users: " + users.size());
		System.out.println("movies: " + movies.size());
	} 
	
	/**
	 * 
	 * @return
	 */
	public HashMap<Integer, User> getUsers() {
		return users;
	}
	
	/**
	 * 
	 * @return
	 */
	public HashMap<Integer, Movie> getMovies() {
		return movies;
	}

	
	/**
	 * 
	 * @param movie
	 * @return
	 */
	public Set<Integer> getUsersByMovie(int mid) {
		return movies.get(mid).getUsers();
	}
	
	/**
	 * 
	 * @param user
	 * @return
	 */
	public Set<Integer> getMoviesByUser(int uid) {
		return users.get(uid).getMovies();
	}
	
	/**
	 * 
	 * @param user
	 * @param movie
	 * @return
	 */
	public double getRating(int uid, int mid) {
		return users.get(uid).getScore(mid);
	}
	
	/**
	 * 
	 * @param user
	 * @return
	 */
	public double getAvgRatingScoreByUser(int uid) {
		return users.get(uid).getAvg();
	}
	
	/**
	 * 
	 * @param movie
	 * @return
	 */
	public double getAvgRatingScoreByMovie(int mid) {
		return movies.get(mid).getAvg();
	}
}
