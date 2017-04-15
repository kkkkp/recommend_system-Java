/**
 * For priority queue in AlgorithmKNN
 * @author Patrick_Pu
 *
 */
public class UserContainer implements Comparable<UserContainer>{
	private int uid;
	private double similarity;

<<<<<<< HEAD
=======
	/**
	 * Assign user id and similarity to instance variables.
	 * @param uid user id
	 * @param similarity similarity calculated in AlgorithmKNN
	 */
>>>>>>> xpu2
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

<<<<<<< HEAD
=======
	/**
	 * Getter for user id.
	 * @return user id
	 */
>>>>>>> xpu2
	public int getId() {
		return uid;
	}

	@Override
	public int compareTo(UserContainer o) {
		return Double.compare(this.similarity, o.getSimilarity());
	}
}
