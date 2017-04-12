/**
 * For priority queue in AlgorithmKNN
 * @author Patrick_Pu
 *
 */
public class MovieContainer implements Comparable<MovieContainer> {
	private Movie movie;
	private double predict;

	public MovieContainer(Movie movie, double predict) {
		this.movie = movie;
		this.predict = predict;
	}

	public Movie getMovie() {
		return movie;
	}

	public double getPredict() {
		return predict;
	}

	@Override
	public int compareTo(MovieContainer o) {
		return Double.compare(this.predict, o.getPredict());
	}
}
