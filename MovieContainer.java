/**
 * For priority queue in AlgorithmKNN
 * @author Patrick_Pu
 *
 */
public class MovieContainer implements Comparable<MovieContainer> {
	private int mid;
	private double predict;

	/**
	 * Assign mid and predicted rating to instance variables.
	 * @param mid movie id
	 * @param predict predicted rating
	 */
	public MovieContainer(int mid, double predict) {
		this.mid = mid;
		this.predict = predict;
	}

	/**
	 * Getter for id.
	 * @return movie id.
	 */
	public int getId() {
		return mid;
	}

	/**
	 * Getter for predicted rating.
	 * @return predict
	 */
	public double getPredict() {
		return predict;
	}

	@Override
	public int compareTo(MovieContainer o) {
		return Double.compare(this.predict, o.getPredict());
	}
}
