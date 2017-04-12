/**
 * For priorityqueue in AlgorithmKNN
 * @author Patrick_Pu
 *
 */
public class UserContainer implements Comparable<UserContainer>{
	private int uid;
	private double similarity;

	public UserContainer(int uid, double similarity) {
		this.uid = uid;
		this.similarity = similarity;
	}

	public double getSimilarity() {
		return similarity;
	}

	public int getId() {
		return uid;
	}

	@Override
	public int compareTo(UserContainer o) {
		return Double.compare(this.similarity, o.getSimilarity());
	}
}
