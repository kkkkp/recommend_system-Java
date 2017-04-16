/**
 * For priority queue in AlgorithmKNN
 * @author Patrick_Pu
 *
 */
public class UserContainer implements Comparable<UserContainer>{
	private int uid;
	private double similarity;


	/**
	 * Assign user id and similarity to instance variables.
	 * @param uid user id
	 * @param similarity similarity calculated in AlgorithmKNN
	 */
	public UserContainer(int uid, double similarity) {
		this.uid = uid;
		this.similarity = similarity;
	}

	/**
	 * Getter for similarity.
	 * @return similarity
	 */
	public double getSimilarity() {
		return similarity;
	}


	/**
	 * Getter for user id.
	 * @return user id
	 */
	public int getId() {
		return uid;
	}

	@Override
	public int compareTo(UserContainer o) {
		return Double.compare(this.similarity, o.getSimilarity());
	}
}
