import java.util.*;

public interface Algorithm {
	public void loadDataCenter(DataCenter dc);
	
	public int getRatingByUserAndMovie(User user, Movie movie);
	
	public List<Movie> getTopNRatingMovies(User user, int n);
}
