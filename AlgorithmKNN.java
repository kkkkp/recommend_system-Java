import java.util.List;

public class AlgorithmKNN implements Algorithm {
	private DataCenter dc;
	
	@Override
	public void loadDataCenter(DataCenter dc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getRatingByUserAndMovie(User user, Movie movie) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Movie> getTopNRatingMovies(User user, int n) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private int getSimilarity(User u1, User u2) {
		return 0;
	}
	
	private List<User> getNeighbors(User user) {
		return null;
	}
	
	private List<Movie> getCommonMovies(User u1, User u2) {
		return null;
	}
}
