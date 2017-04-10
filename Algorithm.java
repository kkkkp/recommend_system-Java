import java.util.*;

public interface Algorithm {
	public void loadDataCenter(DataCenter dc);
	
	public double getRatingByUserAndMovie(User user, Movie movie);
	
	public List<Movie> getTopNRatingMovies(User user, int n);
}
