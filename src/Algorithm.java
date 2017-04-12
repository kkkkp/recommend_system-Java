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
	public double getRatingByUserAndMovie(User user, Movie movie);
	
	/**
	 * 
	 * @param user
	 * @param n
	 * @return
	 */
	public List<Movie> getTopNRatingMovies(User user, int n);
}
