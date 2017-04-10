/**
 * For priorityqueue in AlgorithmKNN
 * @author Patrick_Pu
 *
 */
public class UserContainer implements Comparable<UserContainer>{
	private User user;
	private double similarity;
	
	public UserContainer(User user, double similarity) {
		this.user = user;
		this.similarity = similarity;
	}
	
	public double getSimilarity() {
		return similarity;
	}
	
	public User getUser() {
		return user;
	}

	@Override
	public int compareTo(UserContainer o) {
		return Double.compare(o.getSimilarity(), this.similarity);
	}
}
