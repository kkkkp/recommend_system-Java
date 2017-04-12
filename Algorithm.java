import java.util.*;

public interface Algorithm {
	/**
	 * 
	 * @param dc
	 */
	public void loadDataCenter(DataCenter dc);
	
	/**
	 * 
	 * @param user
	 * @param movie
	 * @return
	 */
	public double getRatingByUserAndMovie(int uid, int mid);
	
	/**
	 * 
	 * @param user
	 * @param n
	 * @return
	 */
	public Set<Integer> getTopNRatingMovies(int uid, int n);
}
