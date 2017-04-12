/**
 * For priorityqueue in AlgorithmKNN
 * @author Patrick_Pu
 *
 */
public class MovieContainer implements Comparable<MovieContainer> {
	private int mid;
	private double predict;

	public MovieContainer(int mid, double predict) {
		this.mid = mid;
		this.predict = predict;
	}

	public int getId() {
		return mid;
	}

	public double getPredict() {
		return predict;
	}

	@Override
	public int compareTo(MovieContainer o) {
		return Double.compare(this.predict, o.getPredict());
	}
}
