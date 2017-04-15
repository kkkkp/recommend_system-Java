/**
 * For priority queue in AlgorithmKNN
 * @author Patrick_Pu
 *
 */
public class ItemContainer implements Comparable<ItemContainer> {
	private int mid;
	private double predict;

	public ItemContainer(int mid, double predict) {
		this.mid = mid;
		this.predict = predict;
	}

	/**
	 * Getter for id.
	 * @return item id.
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
	public int compareTo(ItemContainer o) {
		return Double.compare(this.predict, o.getPredict());
	}
}
